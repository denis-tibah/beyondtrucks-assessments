import axios, { AxiosInstance } from 'axios';

const api: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080/students',
  headers: {
    'Content-Type': 'application/json',
  },
});

api.interceptors.response.use(
  response => response,
  error => {
    alert('An error occurred: ' + (error.response?.data?.message || error.message));
    return Promise.reject(error);
  }
);

export default api;
