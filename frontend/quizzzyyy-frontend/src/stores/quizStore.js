import { defineStore } from 'pinia'
import axios from 'axios'

export const useQuizStore = defineStore('quiz', {
  state: () => ({
    quizzes: [],
    currentQuiz: null,
    currentQuestionIndex: 0,
    score: 0
  }),
  getters: {
    currentQuestion: (state) => {
      if (
        state.currentQuiz &&
        state.currentQuiz.questions &&
        state.currentQuiz.questions.length > state.currentQuestionIndex
      ) {
        return state.currentQuiz.questions[state.currentQuestionIndex]
      }
      return null
    },
    isLastQuestion: (state) => {
      return state.currentQuestionIndex === state.currentQuiz.questions.length - 1
    }
  },
  actions: {
    async fetchQuizzes() {
      try {
        const response = await axios.get('http://localhost:8080/quiz')
        console.log(response.data) // Log to see the actual structure
        this.quizzes = response.data
      } catch (error) {
        console.error('There was an error fetching the quizzes', error)
      }
    },

    setCurrentQuiz(quiz) {
      console.log('Setting current quiz:', quiz) // Add this line to log the quiz object
      this.currentQuiz = quiz
      this.currentQuestionIndex = 0
      this.score = 0 // Reset the score each time a new quiz is started.

      // If you want to log the questions specifically, you can add another log statement
      if (quiz.questions && quiz.questions.length > 0) {
        console.log('Questions in the current quiz:', quiz.questions)
      } else {
        console.warn('No questions found in the current quiz')
      }
    },

    submitAnswer(answer) {
      // Get the current question
      const currentQuestion = this.currentQuestion
      if (currentQuestion) {
        // Check if the user's answer is correct
        if (answer.toLowerCase().trim() === currentQuestion.answer.toLowerCase().trim()) {
          this.score++ // Increment score if the answer is correct
        }
      }

      // Move to the next question or end the quiz
      if (!this.isLastQuestion) {
        this.currentQuestionIndex++
      } else {
        this.endQuiz()
      }
    },

    endQuiz() {
      // You can perform any additional actions needed at the end of the quiz here
      console.log(`Quiz finished with score: ${this.score}`)
      // For example, you might navigate to a score page or show a score summary
      // This can be handled in your Vue component using the router
    },

    resetQuiz() {
      this.currentQuiz = null
      this.currentQuestionIndex = 0
      this.score = 0
    }
  }
})
