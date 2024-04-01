// src/api.js
import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api', // Endre dette til backend-serverens URL
  withCredentials: false,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
});
export default apiClient;
apiClient.interceptors.request.use(config => {
  const token = localStorage.getItem('user-token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});