import axios from 'axios';

export const axiosIntance = axios.create({
  baseURL: 'http://localhost:9000/spring-reactive/productos',
  timeout: 30000,
});
