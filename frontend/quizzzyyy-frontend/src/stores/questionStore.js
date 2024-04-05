import { defineStore } from 'pinia';
import axios from 'axios';

export const useQuestionStore = defineStore('question', {
  state: () => ({
    questions: [],
    currentQuestion: null,
  }),
  getters: {
    // Her kan du legge til getters som er relevante for spørsmålene
  },
  actions: {
    async fetchQuestions(quizId) {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get(`http://localhost:8080/questions/${quizId}`, {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        this.questions = response.data;
      } catch (error) {
        console.error('Error fetching questions:', error);
      }
    },
    async createQuestion(quizId, questionData) {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.post(`http://localhost:8080/questions/${quizId}`, questionData, {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        this.questions.push(response.data);
      } catch (error) {
        console.error('Error creating question:', error);
      }
    },
    async updateQuestion(questionId, questionData) {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.put(`http://localhost:8080/questions/${questionId}`, questionData, {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        const index = this.questions.findIndex(question => question.id === questionId);
        if (index !== -1) {
          this.questions[index] = response.data;
        }
      } catch (error) {
        console.error('Error updating question:', error);
      }
    },
    async deleteQuestion(questionId) {
      try {
        const token = localStorage.getItem('token');
        await axios.delete(`http://localhost:8080/questions/${questionId}`, {
          headers: { 'Authorization': `Bearer ${token}` }
        });
        this.questions = this.questions.filter(question => question.id !== questionId);
      } catch (error) {
        console.error('Error deleting question:', error);
      }
    },
    setCurrentQuestion(question) {
      this.currentQuestion = question;
    },
  }
});
