import { useEffect, useState } from 'react'
import { Plus, Trash2, CheckCircle } from 'lucide-react'
import { taskAPI } from '../services/api'

export default function TarefasPage() {
  const [tarefas, setTarefas] = useState([])
  const [filtro, setFiltro] = useState('todas')
  const [novaTitle, setNovaTitle] = useState('')
  const [novaData, setNovaData] = useState('')
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    loadTarefas()
  }, [])

  const loadTarefas = async () => {
    try {
      const response = await taskAPI.getAll()
      setTarefas(response.data || [])
    } catch (err) {
      console.error('Erro ao carregar tarefas:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleAddTarefa = async (e) => {
    e.preventDefault()
    if (!novaTitle.trim()) return

    try {
      await taskAPI.create({
        titulo: novaTitle,
        dataLimite: novaData,
        concluida: false,
      })
      setNovaTitle('')
      setNovaData('')
      loadTarefas()
    } catch (err) {
      console.error('Erro ao criar tarefa:', err)
    }
  }

  const handleComplete = async (id) => {
    try {
      await taskAPI.complete(id)
      loadTarefas()
    } catch (err) {
      console.error('Erro ao completar tarefa:', err)
    }
  }

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza?')) {
      try {
        await taskAPI.delete(id)
        loadTarefas()
      } catch (err) {
        console.error('Erro ao deletar tarefa:', err)
      }
    }
  }

  const filtered = tarefas.filter((t) => {
    if (filtro === 'pendentes') return !t.concluida
    if (filtro === 'completas') return t.concluida
    return true
  })

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <h1 className="text-3xl font-bold text-white">Tarefas</h1>
        <p className="text-gray-400 mt-2">Organize suas tarefas diárias</p>
      </div>

      {/* Nova Tarefa Form */}
      <div className="card">
        <form onSubmit={handleAddTarefa} className="space-y-4">
          <h2 className="text-xl font-bold text-white">+ Nova Tarefa</h2>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <input
              type="text"
              value={novaTitle}
              onChange={(e) => setNovaTitle(e.target.value)}
              placeholder="Título da tarefa..."
              className="col-span-2"
            />
            <input
              type="date"
              value={novaData}
              onChange={(e) => setNovaData(e.target.value)}
            />
          </div>

          <button type="submit" className="btn-primary flex items-center gap-2">
            <Plus size={20} />
            Adicionar Tarefa
          </button>
        </form>
      </div>

      {/* Filters */}
      <div className="flex gap-2">
        {['todas', 'pendentes', 'completas'].map((f) => (
          <button
            key={f}
            onClick={() => setFiltro(f)}
            className={`px-4 py-2 rounded-lg font-medium transition-all ${
              filtro === f
                ? 'bg-[#2B82F6] text-white'
                : 'bg-gray-800 text-gray-400 hover:text-white'
            }`}
          >
            {f.charAt(0).toUpperCase() + f.slice(1)}
          </button>
        ))}
      </div>

      {/* Tarefas List */}
      <div className="space-y-3">
        {loading ? (
          <div className="text-center py-8 text-gray-400">Carregando...</div>
        ) : filtered.length === 0 ? (
          <div className="text-center py-8 text-gray-400">
            Nenhuma tarefa neste filtro
          </div>
        ) : (
          filtered.map((tarefa) => (
            <div key={tarefa.id} className="card-sm flex items-center justify-between">
              <div className="flex items-center gap-4 flex-1">
                <button
                  onClick={() => handleComplete(tarefa.id)}
                  className={`flex-shrink-0 ${
                    tarefa.concluida ? 'text-green-400' : 'text-gray-500'
                  }`}
                >
                  <CheckCircle size={24} />
                </button>

                <div className="flex-1">
                  <h3 className={`font-medium ${tarefa.concluida ? 'line-through text-gray-500' : 'text-white'}`}>
                    {tarefa.titulo}
                  </h3>
                  {tarefa.dataLimite && (
                    <p className="text-sm text-gray-400">
                      Até: {new Date(tarefa.dataLimite).toLocaleDateString('pt-BR')}
                    </p>
                  )}
                </div>
              </div>

              <button
                onClick={() => handleDelete(tarefa.id)}
                className="text-red-400 hover:text-red-300 transition"
              >
                <Trash2 size={20} />
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  )
}
