import { defineStore } from 'pinia'
import axios from 'axios'

export const useQuizStore = defineStore('quiz', {
  state: () => ({
    quizzes: [],
    currentQuiz: null,
    currentQuestionIndex: 0,
    score: 0,
    userAnswers: [] // To keep track of all answers given
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
        const response = await axios.get('http://localhost:8080/quiz')
        console.log('Quizzes fetched:', response.data)
        this.quizzes = response.data
        this.saveState() // Save state after fetching quizzes
      } catch (error) {
        console.error('Error fetching quizzes:', error)
      }
    },

    setCurrentQuiz(quiz) {
      console.log('Selected quiz:', quiz)
      this.currentQuiz = quiz
      this.currentQuestionIndex = 0
      this.score = 0
      this.userAnswers = []
      this.saveState() // Save state after setting the current quiz
    },

    submitAnswer(answer) {
      const currentQuestion = this.currentQuestion
      console.log('Current question:', currentQuestion)
      console.log('Submitted answer:', answer)
      if (answer.toLowerCase().trim() === currentQuestion.answer.toLowerCase().trim()) {
        this.score++
        console.log('Correct answer! Score:', this.score)
      } else {
        console.log('Wrong answer. Score remains:', this.score)
      }
      this.userAnswers.push(answer)
      if (this.isLastQuestion) {
        this.endQuiz()
      } else {
        this.currentQuestionIndex++
      }
      this.saveState() // Save state after submitting an answer
    },

    endQuiz() {
      console.log(`Quiz finished with score: ${this.score}`)
      this.saveState() // Save state after ending the quiz
    },

    resetQuiz() {
      this.currentQuiz = null
      this.currentQuestionIndex = 0
      this.score = 0
      this.userAnswers = []
      localStorage.removeItem('quizState') // Clear the saved state upon resetting
    },

    saveState() {
      const stateToSave = JSON.stringify(this.$state)
      localStorage.setItem('quizState', stateToSave)
    },

    loadState() {
      const savedState = localStorage.getItem('quizState')
      if (savedState) {
        this.$state = JSON.parse(savedState)
      }
    }
  }
})
