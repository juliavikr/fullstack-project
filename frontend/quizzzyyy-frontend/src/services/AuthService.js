// src/services/AuthService.js
import apiClient from '@/api';

export default {
  signUp(credentials) {
    return apiClient.post('/register', credentials);
  },
  logIn(credentials) {
    return apiClient.post('/user/login', credentials);
  }
};