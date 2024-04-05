import { defineStore } from 'pinia';
import axios from 'axios';

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
        return null;
      }
      return state.currentQuiz.questions[state.currentQuestionIndex];
    },
    isLastQuestion: (state) => {
      if (!state.currentQuiz || !state.currentQuiz.questions) {
        return true;
      }
      return state.currentQuestionIndex === state.currentQuiz.questions.length - 1;
    }
  },
  actions: {
    async fetchQuizzes() {
      try {
        const response = await axios.get('http://localhost:8080/quiz');
        this.quizzes = response.data;
      } catch (error) {
        console.error('Error fetching quizzes:', error);
      }
    },
    async fetchUserQuizzes() {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/quiz/user-quizzes', {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        this.quizzes = response.data;
      } catch (error) {
        console.error('Error fetching user quizzes:', error);
      }
    },
    async createQuiz(quizData) {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.post('http://localhost:8080/quiz', quizData, {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        this.quizzes.push(response.data);
      } catch (error) {
        console.error('Error creating quiz:', error);
      }
    },
     setDifficulty(difficultyLevel) {
    if (this.currentQuiz) {
      this.currentQuiz.difficulty = difficultyLevel;
    }
  },
    async updateQuiz(quizId, quizData) {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.put(`http://localhost:8080/quiz/${quizId}`, quizData, {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        const index = this.quizzes.findIndex(quiz => quiz.id === quizId);
        if (index !== -1) {
          this.quizzes[index] = response.data;
        }
      } catch (error) {
        console.error('Error updating quiz:', error);
      }
    },
    async deleteQuiz(quizId) {
      try {
        const token = localStorage.getItem('token');
        await axios.delete(`http://localhost:8080/quiz/${quizId}`, {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        this.quizzes = this.quizzes.filter(quiz => quiz.id !== quizId);
      } catch (error) {
        console.error('Error deleting quiz:', error);
      }
    },
    setCurrentQuiz(quiz) {
      this.currentQuiz = quiz;
      this.currentQuestionIndex = 0;
      this.score = 0;
      this.userAnswers = [];
    },
    submitAnswer(answer) {
      const currentQuestion = this.currentQuestion;
      if (answer.toLowerCase().trim() === currentQuestion.answer.toLowerCase().trim()) {
        this.score++;
      }
      this.userAnswers.push(answer);
      if (this.isLastQuestion) {
        this.endQuiz();
      } else {
        this.currentQuestionIndex++;
      }
    },
    endQuiz() {
      // Logic to handle the end of a quiz
    },
    resetQuiz() {
      this.currentQuiz = null;
      this.currentQuestionIndex = 0;
      this.score = 0;
      this.userAnswers = [];
      localStorage.removeItem('quizState');
    },
    saveState() {
      localStorage.setItem('quizState', JSON.stringify(this.$state));
    },
    loadState() {
      const savedState = localStorage.getItem('quizState');
      if (savedState) {
        this.$state = JSON.parse(savedState);
      }
    }
  }
});

