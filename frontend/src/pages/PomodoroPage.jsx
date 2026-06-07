import { useEffect, useState } from 'react'
import { Play, Pause, RotateCcw } from 'lucide-react'

export default function PomodoroPage() {
  const [time, setTime] = useState(25 * 60) // 25 minutos em segundos
  const [isRunning, setIsRunning] = useState(false)
  const [phase, setPhase] = useState('work') // work, break, longBreak
  const [sessionCount, setSessionCount] = useState(0)

  useEffect(() => {
    let interval
    if (isRunning && time > 0) {
      interval = setInterval(() => {
        setTime((t) => t - 1)
      }, 1000)
    } else if (time === 0) {
      handlePhaseComplete()
    }
    return () => clearInterval(interval)
  }, [isRunning, time])

  const handlePhaseComplete = () => {
    setIsRunning(false)
    // Som de notificação
    const audio = new Audio('data:audio/wav;base64,UklGRnoGAABXQVZFZm10IBAAAAABAAEAQB8AAAB9AAACABAAZGF0YQoGAACBhYqFbF1fdJivrJBhNjVgodDbq2EcBj==')
    audio.play().catch(() => {})

    if (phase === 'work') {
      setSessionCount(s => s + 1)
      if (sessionCount % 4 === 3) {
        setPhase('longBreak')
        setTime(15 * 60)
      } else {
        setPhase('break')
        setTime(5 * 60)
      }
    } else {
      setPhase('work')
      setTime(25 * 60)
    }
  }

  const toggleTimer = () => setIsRunning(!isRunning)

  const resetTimer = () => {
    setIsRunning(false)
    if (phase === 'work') setTime(25 * 60)
    else if (phase === 'break') setTime(5 * 60)
    else setTime(15 * 60)
  }

  const minutes = Math.floor(time / 60)
  const seconds = time % 60
  const phaseNames = {
    work: 'Tempo de Trabalho',
    break: 'Intervalo',
    longBreak: 'Intervalo Longo',
  }

  const phaseColors = {
    work: 'from-blue-600 to-blue-700',
    break: 'from-green-600 to-green-700',
    longBreak: 'from-purple-600 to-purple-700',
  }

  return (
    <div className="space-y-8">
      {/* Header */}
      <div className="text-center">
        <h1 className="text-4xl font-bold text-white">🍅 Pomodoro Timer</h1>
        <p className="text-gray-400 mt-2">Técnica de produtividade com intervalos</p>
      </div>

      {/* Timer Display */}
      <div
        className={`card bg-gradient-to-br ${phaseColors[phase]} flex flex-col items-center justify-center py-16`}
      >
        <p className="text-xl text-white mb-4 opacity-90">{phaseNames[phase]}</p>

        <div className="text-7xl font-bold text-white font-mono tracking-wider mb-8">
          {String(minutes).padStart(2, '0')}:{String(seconds).padStart(2, '0')}
        </div>

        {/* Controls */}
        <div className="flex gap-6">
          <button
            onClick={toggleTimer}
            className="bg-white text-blue-600 hover:bg-gray-100 p-4 rounded-full transition-all"
          >
            {isRunning ? <Pause size={32} /> : <Play size={32} />}
          </button>

          <button
            onClick={resetTimer}
            className="bg-white bg-opacity-20 text-white hover:bg-opacity-30 p-4 rounded-full transition-all"
          >
            <RotateCcw size={32} />
          </button>
        </div>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div className="card text-center">
          <p className="text-gray-400 mb-2">Sessões Completas</p>
          <p className="text-4xl font-bold text-[#2B82F6]">{sessionCount}</p>
        </div>

        <div className="card text-center">
          <p className="text-gray-400 mb-2">Fase Atual</p>
          <p className="text-2xl font-bold text-white capitalize">{phaseNames[phase]}</p>
        </div>

        <div className="card text-center">
          <p className="text-gray-400 mb-2">Próximo Intervalo</p>
          <p className="text-xl font-bold text-[#B4748B]">
            {phase === 'work' ? '5 min' : '25 min'}
          </p>
        </div>
      </div>

      {/* Info */}
      <div className="card">
        <h2 className="text-xl font-bold text-white mb-4">💡 Como Funciona</h2>
        <div className="space-y-2 text-gray-300">
          <p>• <strong>Trabalho:</strong> 25 minutos de foco total</p>
          <p>• <strong>Intervalo Curto:</strong> 5 minutos de descanso</p>
          <p>• <strong>Intervalo Longo:</strong> 15 minutos (a cada 4 ciclos)</p>
          <p className="mt-4 text-sm text-gray-400">
            A técnica Pomodoro ajuda a manter o foco e a produtividade. Use-a para estudar, trabalhar ou qualquer tarefa!
          </p>
        </div>
      </div>
    </div>
  )
}
