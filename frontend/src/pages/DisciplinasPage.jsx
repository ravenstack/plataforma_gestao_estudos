import { useEffect, useState } from 'react'
import { Plus, Trash2, BookOpen } from 'lucide-react'
import { academicAPI } from '../services/api'

export default function DisciplinasPage() {
  const [disciplinas, setDisciplinas] = useState([])
  const [loading, setLoading] = useState(true)
  const [nomeDisciplina, setNomeDisciplina] = useState('')
  const [corDisciplina, setCorDisciplina] = useState('#2B82F6')

  const cores = ['#2B82F6', '#B4748B', '#1E293B', '#ef4444', '#22c55e', '#f97316', '#06b6d4']

  useEffect(() => {
    loadDisciplinas()
  }, [])

  const loadDisciplinas = async () => {
    try {
      const response = await academicAPI.getSubjects()
      setDisciplinas(response.data || [])
    } catch (err) {
      console.error('Erro ao carregar disciplinas:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleAddDisciplina = async (e) => {
    e.preventDefault()
    if (!nomeDisciplina.trim()) return

    try {
      await academicAPI.createSubject({
        name: nomeDisciplina,
        color: corDisciplina,
      })
      setNomeDisciplina('')
      setCorDisciplina('#2B82F6')
      loadDisciplinas()
    } catch (err) {
      console.error('Erro ao criar disciplina:', err)
    }
  }

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza?')) {
      try {
        await academicAPI.deleteSubject(id)
        loadDisciplinas()
      } catch (err) {
        console.error('Erro ao deletar disciplina:', err)
      }
    }
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <h1 className="text-3xl font-bold text-white">Disciplinas</h1>
        <p className="text-gray-400 mt-2">Organize suas matérias e cursos</p>
      </div>

      {/* Nova Disciplina Form */}
      <div className="card">
        <form onSubmit={handleAddDisciplina} className="space-y-4">
          <h2 className="text-xl font-bold text-white">+ Nova Disciplina</h2>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <input
              type="text"
              value={nomeDisciplina}
              onChange={(e) => setNomeDisciplina(e.target.value)}
              placeholder="Nome da disciplina..."
              className="md:col-span-2"
            />

            <div className="flex gap-2">
              {cores.map((cor) => (
                <button
                  key={cor}
                  type="button"
                  onClick={() => setCorDisciplina(cor)}
                  className={`w-10 h-10 rounded-lg transition-transform ${
                    corDisciplina === cor ? 'ring-2 ring-white scale-110' : ''
                  }`}
                  style={{ backgroundColor: cor }}
                />
              ))}
            </div>
          </div>

          <button type="submit" className="btn-primary flex items-center gap-2">
            <Plus size={20} />
            Adicionar Disciplina
          </button>
        </form>
      </div>

      {/* Disciplinas Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {loading ? (
          <div className="text-center py-8 text-gray-400 col-span-full">
            Carregando...
          </div>
        ) : disciplinas.length === 0 ? (
          <div className="text-center py-8 text-gray-400 col-span-full">
            Nenhuma disciplina criada ainda
          </div>
        ) : (
          disciplinas.map((disciplina) => (
            <div
              key={disciplina.id}
              className="card border-l-4"
              style={{ borderColor: disciplina.color || '#2B82F6' }}
            >
              <div className="flex items-center justify-between mb-4">
                <div className="flex items-center gap-3">
                  <div
                    className="w-10 h-10 rounded-lg flex items-center justify-center"
                    style={{ backgroundColor: disciplina.color || '#2B82F6' }}
                  >
                    <BookOpen size={24} className="text-white" />
                  </div>
                  <h3 className="text-xl font-bold text-white">
                    {disciplina.name}
                  </h3>
                </div>

                <button
                  onClick={() => handleDelete(disciplina.id)}
                  className="text-red-400 hover:text-red-300 transition"
                >
                  <Trash2 size={20} />
                </button>
              </div>

              <div className="pt-4 border-t border-gray-700">
                <p className="text-sm text-gray-400">
                  0 tarefas
                </p>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  )
}
