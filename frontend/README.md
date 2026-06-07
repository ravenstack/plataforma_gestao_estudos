# 📱 Frontend - Plataforma de Gestão de Estudos

Interface moderna e responsiva para a Plataforma de Gestão de Estudos, desenvolvida em React com Vite.

## 🎨 Design

- **Colors**: Tema escuro profissional
  - Primary: #2B82F6 (Azul)
  - Secondary: #B4748B (Roxo)
  - Tertiary: #1E293B (Cinza Escuro)
  - Neutral: #23222 (Quase preto)
- **Framework**: Tailwind CSS
- **UI Components**: Lucide Icons

## 🚀 Quick Start

### 1. Instalar Dependências
```bash
cd frontend
npm install
```

### 2. Configurar Variáveis de Ambiente
```bash
cp .env.example .env
# Edite .env com a URL da API (padrão: http://localhost:8080/api)
```

### 3. Rodar em Desenvolvimento
```bash
npm run dev
```
Acesse: `http://localhost:3000`

### 4. Build para Produção
```bash
npm run build
```
Gera pasta `dist/` pronta para deploy.

## 📂 Estrutura

```
frontend/
├── src/
│   ├── components/        # Componentes reutilizáveis
│   │   ├── MainLayout.jsx
│   │   └── Sidebar.jsx
│   ├── pages/            # Páginas da aplicação
│   │   ├── LoginPage.jsx
│   │   ├── RegisterPage.jsx
│   │   ├── DashboardPage.jsx
│   │   ├── TarefasPage.jsx
│   │   ├── DisciplinasPage.jsx
│   │   ├── PomodoroPage.jsx
│   │   └── ConfigPage.jsx
│   ├── services/         # Integração com API
│   │   └── api.js
│   ├── styles/           # Estilos globais
│   │   └── globals.css
│   ├── App.jsx          # Router principal
│   └── main.jsx         # Entry point
├── index.html
├── package.json
├── vite.config.js
├── tailwind.config.js
└── .env.example
```

## 🔐 Autenticação

1. **Login**: `POST /api/identify/login`
   - Email + Senha
   - Retorna JWT token

2. **Cadastro**: `POST /api/identify/register`
   - Email + Senha + Confirmação

3. **Token**: Salvo em `localStorage`
   - Enviado em todos os requests no header `Authorization: Bearer {token}`

## 📋 Funcionalidades

### Dashboard
- Resumo de estatísticas
- Cards com dados principais
- Dica do dia

### Tarefas
- ✅ Listar tarefas
- ✅ Criar tarefa com data limite
- ✅ Marcar como concluída
- ✅ Deletar tarefa
- ✅ Filtrar (Todas/Pendentes/Completas)

### Disciplinas
- 📚 Listar disciplinas
- 📚 Criar disciplina com cor personalizada
- 📚 Deletar disciplina
- 📚 Visualizar tarefas por disciplina

### Pomodoro
- 🍅 Timer 25min (work) + 5min (break) + 15min (long break)
- 🍅 Iniciar/Pausar/Reiniciar
- 🍅 Som ao terminar
- 🍅 Contador de sessões
- 🍅 Informações sobre a técnica

### Notificações
- 🔔 Configurar lembretes
- 🔔 Toggle: Lembrete Pomodoro
- 🔔 Toggle: Lembrete de Deadline
- 🔔 Centro de notificações

## 🛠️ Tecnologias

- **React 18** - UI library
- **Vite** - Build tool
- **React Router v6** - Roteamento
- **Axios** - HTTP client
- **Tailwind CSS** - Styling
- **Lucide Icons** - Ícones

## 🌐 Deploy no Render

### Setup no Render

1. Conectar repo GitHub ao Render
2. Configurar como "Web Service"
3. Settings:
   - **Build Command**: `npm install && npm run build`
   - **Start Command**: `npm run start` ou `npx serve -s dist`
   - **Node Version**: 18 ou 20
   - **Environment**: 
     - `VITE_API_URL=https://seu-backend.onrender.com/api`

4. Deploy automático a cada push na branch main

### Build Size
- Otimizado com Vite
- ~200KB gzipped
- Suporta free tier do Render

## 📝 Desenvolvido com Assistência de IA

### Uso de IA na Estrutura Frontend:

| Componente | % IA | Descrição |
|------------|------|-----------|
| Estrutura React | 100% | Setup vite, routing, structure |
| Componentes Base | 100% | MainLayout, Sidebar, Forms |
| Integração API | 100% | Service de axios com interceptors |
| Páginas | 100% | Login, Dashboard, Tarefas, etc |
| Styling | 100% | Tailwind config com cores custom |
| Documentação | 100% | Setup guides e docs |

**Total: ~95% com assistência de IA para estrutura e setup**
**0% para lógica de negócio** (feito por você)

---

## 🚀 Próximas Melhorias

- [ ] Integração com WebSocket para real-time
- [ ] Dark/Light mode toggle
- [ ] PWA (Progressive Web App)
- [ ] Gráficos de produtividade
- [ ] Compartilhamento de disciplinas
- [ ] Modo offline
- [ ] Testes automatizados

## 📞 Suporte

Para dúvidas sobre a integração com o backend, verifique `FRONTEND_REQUIREMENTS.md` na raiz do projeto.

---

**v1.0.0** - Plataforma de Gestão de Estudos © 2026
