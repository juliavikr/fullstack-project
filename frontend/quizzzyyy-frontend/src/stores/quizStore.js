import { defineStore } from 'pinia'
import axios from 'axios'

export const useQuizStore = defineStore('quiz', {
  state: () => ({
    quizzes: [],
    currentQuiz: null,
    currentQuestionIndex: 0,
    score: 0,
    userAnswers: [], // To keep track of all answers given
    activities: [] // To keep track of all activities
  }),
  getters: {
    currentQuestion: (state) => {
      if (!state.currentQuiz || !state.currentQuiz.questions) {
        return null
      }
      return state.currentQuiz.questions[state.currentQuestionIndex]
    },
    isLastQuestion: (state) => {
      if (!state.currentQuiz || !state.currentQuiz.questions) {
        return true
      }
      return state.currentQuestionIndex === state.currentQuiz.questions.length - 1
    }
  },
  actions: {
    async fetchQuizzes() {
      try {
        const token = localStorage.getItem('token')
        if (!token) {
          throw new Error('No authentication token found')
        }

        const response = await axios.get('http://localhost:8080/quiz/my', {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })
        this.quizzes = response.data
      } catch (error) {
        console.error('Error fetching quizzes:', error)

      }
    },

    setCurrentQuiz(quiz) {
      console.log('Selected quiz:', quiz)
      this.currentQuiz = {
      ...quiz,
      totalQuestions: quiz.questions.length, // Legg til dette feltet
      } // Set the current quiz
        this.currentQuestionIndex = 0
        this.score = 0
        this.userAnswers = []
        this.saveState() // Save state after setting the current quiz
      },
    submitAnswer(answer) {
    const currentQuestion = this.currentQuestion
    if (!currentQuestion) {
      console.error('No current question available for submitting an answer.')
      return
    }

    console.log('Current question:', currentQuestion)
    console.log('Submitted answer:', answer)
    const isCorrect = answer.toLowerCase().trim() === currentQuestion.answer.toLowerCase().trim()
    this.userAnswers.push({
      questionId: currentQuestion.id,
      answer,
      isCorrect
    })

  // Update the score immediately if the answer is correct
    if (isCorrect) {
      this.score++
    }

    if (this.isLastQuestion) {
      this.recordActivity(this.currentQuiz.title, this.score)
      this.endQuiz()
    } else {
      this.currentQuestionIndex++
    }

    this.saveState() // Save state after submitting an answer
    return isCorrect // Return whether the answer is correct
  },

    endQuiz() {
      console.log(`Quiz finished with score: ${this.score}`)
      this.saveState()
    },

    recordActivity(quizTitle, score) {
      const newActivity = {
        quizTitle,
        score,
        totalQuestions: this.currentQuiz.totalQuestions,
        timestamp: Date.now()
      }
      this.activities.push(newActivity)
      this.saveState()
    },

    resetQuiz() {
      this.currentQuiz = null
      this.currentQuestionIndex = 0
      this.score = 0
      this.userAnswers = []
      localStorage.removeItem('quizState') // Clear the saved state upon resetting
    },

    saveState() {
      const stateToSave = {
        quizzes: this.quizzes,
        currentQuiz: this.currentQuiz,
        currentQuestionIndex: this.currentQuestionIndex,
        score: this.score,
        userAnswers: this.userAnswers,
        activities: this.activities
      }
      localStorage.setItem('quizState', JSON.stringify(stateToSave))
    },

    loadState() {
      const savedState = localStorage.getItem('quizState')
      if (savedState) {
        const parsedState = JSON.parse(savedState)
        // Update the store with the saved state
        this.quizzes = parsedState.quizzes || []
        this.currentQuiz = parsedState.currentQuiz
        this.currentQuestionIndex = parsedState.currentQuestionIndex || 0
        this.score = parsedState.score || 0
        this.userAnswers = parsedState.userAnswers || []
        this.activities = parsedState.activities || []
      }
    },
    removeQuiz(quizId) {
      this.quizzes = this.quizzes.filter((q) => q.id !== quizId)
    }
  }
})
