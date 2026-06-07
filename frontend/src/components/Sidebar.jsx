import { Link, useLocation } from 'react-router-dom'

export default function Sidebar({ open, setOpen, navItems }) {
  const location = useLocation()

  return (
    <>
      {/* Overlay (mobile) */}
      {open && (
        <div
          className="md:hidden fixed inset-0 bg-black bg-opacity-50 z-40"
          onClick={() => setOpen(false)}
        />
      )}

      {/* Sidebar */}
      <aside
        className={`
          fixed md:relative z-50 h-screen w-64 bg-gray-900 border-r border-gray-800
          transition-transform duration-300 ease-in-out
          ${open ? 'translate-x-0' : '-translate-x-full md:translate-x-0'}
        `}
      >
        {/* Logo */}
        <div className="p-6 border-b border-gray-800">
          <div className="text-2xl font-bold text-[#2B82F6]">PAES</div>
          <p className="text-xs text-gray-500 mt-1">Gestão de Estudos</p>
        </div>

        {/* Navigation */}
        <nav className="p-4 space-y-2">
          {navItems.map((item) => {
            const Icon = item.icon
            const isActive = location.pathname === item.path

            return (
              <Link
                key={item.path}
                to={item.path}
                onClick={() => setOpen(false)}
                className={`
                  flex items-center gap-3 px-4 py-3 rounded-lg transition-all
                  ${isActive
                    ? 'bg-[#2B82F6] text-white shadow-lg'
                    : 'text-gray-400 hover:text-white hover:bg-gray-800'
                  }
                `}
              >
                <Icon size={20} />
                <span className="font-medium">{item.label}</span>
              </Link>
            )
          })}
        </nav>

        {/* Footer */}
        <div className="absolute bottom-0 left-0 right-0 p-4 border-t border-gray-800 bg-gray-900">
          <p className="text-xs text-gray-500 text-center">
            v1.0.0 - PAES Platform
          </p>
        </div>
      </aside>
    </>
  )
}
