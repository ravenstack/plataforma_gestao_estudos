import { useState, useEffect } from 'react'
import { Save, Bell } from 'lucide-react'
import { notificationAPI } from '../services/api'

export default function ConfigPage() {
  const [settings, setSettings] = useState({
    pomodoroReminder: true,
    taskDeadlineReminder: true,
    notificationsEnabled: true,
  })
  const [saved, setSaved] = useState(false)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    loadSettings()
  }, [])

  const loadSettings = async () => {
    try {
      const response = await notificationAPI.getSettings()
      setSettings(response.data || settings)
    } catch (err) {
      console.error('Erro ao carregar configurações:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleSave = async () => {
    try {
      await notificationAPI.updateSettings(settings)
      setSaved(true)
      setTimeout(() => setSaved(false), 3000)
    } catch (err) {
      console.error('Erro ao salvar configurações:', err)
    }
  }

  const handleToggle = (key) => {
    setSettings((prev) => ({
      ...prev,
      [key]: !prev[key],
    }))
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <h1 className="text-3xl font-bold text-white">⚙️ Configurações</h1>
        <p className="text-gray-400 mt-2">Personalize sua experiência</p>
      </div>

      {/* Success Message */}
      {saved && (
        <div className="p-4 bg-green-500 bg-opacity-20 border border-green-500 rounded-lg text-green-400">
          ✓ Configurações salvas com sucesso!
        </div>
      )}

      {/* Notifications Section */}
      <div className="card">
        <div className="flex items-center gap-3 mb-6">
          <Bell size={24} className="text-[#2B82F6]" />
          <h2 className="text-2xl font-bold text-white">Notificações</h2>
        </div>

        {loading ? (
          <div className="text-center py-8 text-gray-400">Carregando...</div>
        ) : (
          <div className="space-y-6">
            {/* Enable All */}
            <div className="flex items-center justify-between p-4 bg-gray-800 rounded-lg">
              <div>
                <h3 className="font-medium text-white">Ativar Notificações</h3>
                <p className="text-sm text-gray-400 mt-1">
                  Receba alertas e lembretes
                </p>
              </div>
              <label className="relative inline-flex items-center cursor-pointer">
                <input
                  type="checkbox"
                  checked={settings.notificationsEnabled}
                  onChange={() => handleToggle('notificationsEnabled')}
                  className="sr-only peer"
                />
                <div className="w-11 h-6 bg-gray-700 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-800 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-[#2B82F6]"></div>
              </label>
            </div>

            {/* Pomodoro Reminder */}
            <div className="flex items-center justify-between p-4 bg-gray-800 rounded-lg">
              <div>
                <h3 className="font-medium text-white">Lembrete Pomodoro</h3>
                <p className="text-sm text-gray-400 mt-1">
                  Aviso ao terminar cada sessão Pomodoro
                </p>
              </div>
              <label className="relative inline-flex items-center cursor-pointer">
                <input
                  type="checkbox"
                  checked={settings.pomodoroReminder}
                  onChange={() => handleToggle('pomodoroReminder')}
                  className="sr-only peer"
                />
                <div className="w-11 h-6 bg-gray-700 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-800 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-[#2B82F6]"></div>
              </label>
            </div>

            {/* Task Deadline Reminder */}
            <div className="flex items-center justify-between p-4 bg-gray-800 rounded-lg">
              <div>
                <h3 className="font-medium text-white">Lembrete de Deadline</h3>
                <p className="text-sm text-gray-400 mt-1">
                  Notificação quando se aproximar do prazo de tarefa
                </p>
              </div>
              <label className="relative inline-flex items-center cursor-pointer">
                <input
                  type="checkbox"
                  checked={settings.taskDeadlineReminder}
                  onChange={() => handleToggle('taskDeadlineReminder')}
                  className="sr-only peer"
                />
                <div className="w-11 h-6 bg-gray-700 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-800 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-[#2B82F6]"></div>
              </label>
            </div>

            {/* Save Button */}
            <button
              onClick={handleSave}
              className="btn-primary w-full flex items-center justify-center gap-2 mt-6"
            >
              <Save size={20} />
              Salvar Configurações
            </button>
          </div>
        )}
      </div>

      {/* About Section */}
      <div className="card">
        <h2 className="text-xl font-bold text-white mb-4">📱 Sobre</h2>
        <div className="space-y-2 text-gray-300 text-sm">
          <p><strong>Nome:</strong> Plataforma de Gestão de Estudos</p>
          <p><strong>Versão:</strong> 1.0.0</p>
          <p><strong>Desenvolvido com:</strong> React + Spring Boot</p>
          <p><strong>Licença:</strong> © 2026 PAES Platform</p>
        </div>
      </div>
    </div>
  )
}
