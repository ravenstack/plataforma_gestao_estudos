import { Outlet, useNavigate } from 'react-router-dom'
import { Menu, X, LogOut, Home, CheckSquare, BookOpen, Clock, Settings } from 'lucide-react'
import { useState } from 'react'
import Sidebar from './Sidebar'

export default function MainLayout({ setIsAuthenticated }) {
  const [sidebarOpen, setSidebarOpen] = useState(true)
  const navigate = useNavigate()

  const handleLogout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    setIsAuthenticated(false)
    navigate('/login')
  }

  const navItems = [
    { icon: Home, label: 'Dashboard', path: '/' },
    { icon: CheckSquare, label: 'Tarefas', path: '/tarefas' },
    { icon: BookOpen, label: 'Disciplinas', path: '/disciplinas' },
    { icon: Clock, label: 'Pomodoro', path: '/pomodoro' },
    { icon: Settings, label: 'Configurações', path: '/config' },
  ]

  return (
    <div className="flex h-screen bg-gray-950">
      {/* Sidebar */}
      <Sidebar
        open={sidebarOpen}
        setOpen={setSidebarOpen}
        navItems={navItems}
      />

      {/* Main Content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Header */}
        <header className="bg-gray-900 border-b border-gray-800 px-6 py-4 flex items-center justify-between">
          <div className="flex items-center gap-4">
            <button
              onClick={() => setSidebarOpen(!sidebarOpen)}
              className="p-2 hover:bg-gray-800 rounded-lg transition"
            >
              {sidebarOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
            <h1 className="text-2xl font-bold text-white hidden md:block">
              📚 Plataforma de Gestão de Estudos
            </h1>
          </div>

          <button
            onClick={handleLogout}
            className="flex items-center gap-2 px-4 py-2 text-red-400 hover:bg-red-500 hover:bg-opacity-10 rounded-lg transition"
          >
            <LogOut size={20} />
            <span className="hidden md:inline">Sair</span>
          </button>
        </header>

        {/* Content */}
        <main className="flex-1 overflow-auto">
          <div className="p-4 md:p-8">
            <Outlet />
          </div>
        </main>
      </div>
    </div>
  )
}
