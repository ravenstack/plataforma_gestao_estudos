import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { useEffect, useState } from 'react'

// Pages
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import DashboardPage from './pages/DashboardPage'
import TarefasPage from './pages/TarefasPage'
import DisciplinasPage from './pages/DisciplinasPage'
import PomodoroPage from './pages/PomodoroPage'
import ConfigPage from './pages/ConfigPage'

// Layout
import MainLayout from './components/MainLayout'

export default function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    // Verificar se usuário está logado
    const token = localStorage.getItem('token')
    setIsAuthenticated(!!token)
    setLoading(false)
  }, [])

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen bg-gray-950">
        <div className="text-center">
          <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-[#2B82F6] mx-auto mb-4"></div>
          <p className="text-gray-400">Carregando...</p>
        </div>
      </div>
    )
  }

  return (
    <BrowserRouter>
      <Routes>
        {!isAuthenticated ? (
          <>
            <Route path="/login" element={<LoginPage setIsAuthenticated={setIsAuthenticated} />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="*" element={<Navigate to="/login" />} />
          </>
        ) : (
          <Route element={<MainLayout setIsAuthenticated={setIsAuthenticated} />}>
            <Route path="/" element={<DashboardPage />} />
            <Route path="/tarefas" element={<TarefasPage />} />
            <Route path="/disciplinas" element={<DisciplinasPage />} />
            <Route path="/pomodoro" element={<PomodoroPage />} />
            <Route path="/config" element={<ConfigPage />} />
          </Route>
        )}
      </Routes>
    </BrowserRouter>
  )
}
