import { useEffect, useState } from 'react'
import { BookOpen, CheckSquare, Clock, TrendingUp } from 'lucide-react'
import { taskAPI, academicAPI, pomodoroAPI } from '../services/api'

export default function DashboardPage() {
  const [stats, setStats] = useState({
    tarefas: 0,
    disciplinas: 0,
    pomodoros: 0,
    tarefasCompletas: 0,
  })
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const loadStats = async () => {
      try {
        const [tarefasRes, disciplinasRes, pomodoroRes] = await Promise.all([
          taskAPI.getAll(),
          academicAPI.getSubjects(),
          pomodoroAPI.getHistory(),
        ])

        const tarefas = tarefasRes.data || []
        const tarefasCompletas = tarefas.filter(t => t.concluida).length

        setStats({
          tarefas: tarefas.length,
          disciplinas: (disciplinasRes.data || []).length,
          pomodoros: (pomodoroRes.data || []).length,
          tarefasCompletas,
        })
      } catch (err) {
        console.error('Erro ao carregar stats:', err)
      } finally {
        setLoading(false)
      }
    }

    loadStats()
  }, [])

  const cards = [
    {
      icon: CheckSquare,
      label: 'Tarefas Pendentes',
      value: stats.tarefas - stats.tarefasCompletas,
      color: 'from-blue-600 to-blue-700',
      bg: '#2B82F6',
    },
    {
      icon: BookOpen,
      label: 'Disciplinas',
      value: stats.disciplinas,
      color: 'from-purple-600 to-purple-700',
      bg: '#B4748B',
    },
    {
      icon: Clock,
      label: 'Pomodoros',
      value: stats.pomodoros,
      color: 'from-orange-600 to-orange-700',
      bg: '#f97316',
    },
    {
      icon: TrendingUp,
      label: 'Taxa de Conclusão',
      value: stats.tarefas > 0 ? Math.round((stats.tarefasCompletas / stats.tarefas) * 100) + '%' : '0%',
      color: 'from-green-600 to-green-700',
      bg: '#22c55e',
    },
  ]

  return (
    <div className="space-y-8">
      {/* Header */}
      <div>
        <h1 className="text-3xl font-bold text-white">Bem-vindo!</h1>
        <p className="text-gray-400 mt-2">Aqui está um resumo do seu progresso</p>
      </div>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
        {cards.map((card, idx) => {
          const Icon = card.icon
          return (
            <div
              key={idx}
              className={`card bg-gradient-to-br ${card.color} relative overflow-hidden`}
            >
              <div className="absolute -right-8 -top-8 w-24 h-24 opacity-10">
                <Icon size={96} />
              </div>

              <div className="relative">
                <div className="flex items-center justify-between mb-4">
                  <Icon size={24} className="text-white" />
                </div>

                <div className="text-3xl font-bold text-white mb-1">
                  {loading ? '...' : card.value}
                </div>

                <p className="text-sm text-gray-200 opacity-90">{card.label}</p>
              </div>
            </div>
          )
        })}
      </div>

      {/* Quick Links */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {/* Próximas Tarefas */}
        <div className="card">
          <h2 className="text-xl font-bold text-white mb-4 flex items-center gap-2">
            <CheckSquare size={24} className="text-[#2B82F6]" />
            Próximas Tarefas
          </h2>
          <div className="space-y-2">
            <p className="text-gray-400 text-center py-8">
              Sem tarefas no momento
            </p>
          </div>
        </div>

        {/* Tips */}
        <div className="card">
          <h2 className="text-xl font-bold text-white mb-4">💡 Dica do Dia</h2>
          <div className="bg-gray-800 rounded-lg p-4">
            <p className="text-gray-300">
              Use a Técnica Pomodoro para aumentar sua produtividade. Trabalhe 25 minutos focado, depois tire um intervalo de 5 minutos!
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}
