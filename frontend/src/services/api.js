import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api'

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

// Interceptor para adicionar token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Interceptor para tratar erros
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token')
      window.location.href = '/login'
    }
    return Promise.reject(error)
  }
)

// Auth
export const authAPI = {
  login: (email, password) => api.post('/identify/login', { email, password }),
  register: (email, password) => api.post('/identify/register', { email, password }),
  getMe: () => api.get('/identify/me'),
}

// Tasks
export const taskAPI = {
  getAll: () => api.get('/task'),
  create: (data) => api.post('/task', data),
  update: (id, data) => api.put(`/task/${id}`, data),
  delete: (id) => api.delete(`/task/${id}`),
  complete: (id) => api.patch(`/task/${id}/complete`),
}

// Academic
export const academicAPI = {
  // Disciplinas
  getSubjects: () => api.get('/academic/subjects'),
  createSubject: (data) => api.post('/academic/subjects', data),
  updateSubject: (id, data) => api.put(`/academic/subjects/${id}`, data),
  deleteSubject: (id) => api.delete(`/academic/subjects/${id}`),
  getSubjectById: (id) => api.get(`/academic/subjects/${id}`),

  // Tarefas Acadêmicas
  getTasks: () => api.get('/academic/tasks'),
  createTask: (data) => api.post('/academic/tasks', data),
  updateTask: (id, data) => api.put(`/academic/tasks/${id}`, data),
  deleteTask: (id) => api.delete(`/academic/tasks/${id}`),
}

// Pomodoro
export const pomodoroAPI = {
  start: (data) => api.post('/pomodoro/start', data),
  pause: (id) => api.patch(`/pomodoro/${id}/pause`),
  resume: (id) => api.patch(`/pomodoro/${id}/resume`),
  complete: (id) => api.patch(`/pomodoro/${id}/complete`),
  getHistory: () => api.get('/pomodoro/history'),
}

// Notifications
export const notificationAPI = {
  getSettings: () => api.get('/notification/settings'),
  updateSettings: (data) => api.put('/notification/settings', data),
  getNotifications: () => api.get('/notification'),
  markAsRead: (id) => api.patch(`/notification/${id}/read`),
}

export default api
