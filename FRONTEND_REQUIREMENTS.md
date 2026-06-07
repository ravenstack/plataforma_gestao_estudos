# 📱 Frontend Requirements - PAES Platform

## 🎯 Visão Geral

Frontend deve integrar com 5 APIs backend:
1. **Identify API** - Autenticação
2. **Task API** - Gerenciamento de Tarefas
3. **Pomodoro API** - Técnica Pomodoro
4. **Academic API** - Gerenciamento de Disciplinas/Matérias
5. **Notification API** - Notificações

---

## 🔐 1. IDENTIFY API (Autenticação)

### Modelos
```
User
├── id: number
├── email: string
└── password: string
```

### Páginas Necessárias

#### Login
- Campo: Email
- Campo: Senha
- Botão: Entrar
- Link: "Criar conta"
- Validação: Email + Senha obrigatórios

#### Cadastro
- Campo: Email
- Campo: Senha
- Campo: Confirmar Senha
- Botão: Cadastrar
- Validação: Senhas devem ser iguais

### Endpoints Esperados
- `POST /api/identify/login` → Autenticar usuário
- `POST /api/identify/register` → Criar novo usuário
- `GET /api/identify/me` → Dados do usuário logado

---

## 📋 2. TASK API (Tarefas)

### Modelos
```
Tarefa
├── id: number
├── usuarioId: number
├── titulo: string
├── dataLimite: string (YYYY-MM-DD)
└── concluida: boolean
```

### Páginas Necessárias

#### Lista de Tarefas
- Listar todas as tarefas do usuário
- Mostrar: Título | Data Limite | Status (✓/✗)
- Filtros: Pendentes | Concluídas | Todas
- Botão: + Nova Tarefa

#### Nova/Editar Tarefa
- Campo: Título
- Campo: Data Limite (date picker)
- Botão: Salvar
- Botão: Cancelar

#### Detalhe da Tarefa
- Mostrar: Título, Data, Status
- Botão: Marcar como Concluída
- Botão: Editar
- Botão: Deletar

### Endpoints Esperados
- `GET /api/task` → Listar tarefas
- `POST /api/task` → Criar tarefa
- `PUT /api/task/{id}` → Atualizar tarefa
- `DELETE /api/task/{id}` → Deletar tarefa
- `PATCH /api/task/{id}/complete` → Marcar como concluída

---

## 🍅 3. POMODORO API (Técnica Pomodoro)

### Modelos
```
PomodoroSession
├── id: number
├── userId: number
├── taskId: number
├── startTime: datetime
├── endTime: datetime
├── durationInMinutes: number (25, 5, 15)
└── status: "RUNNING" | "PAUSED" | "COMPLETED"
```

### Páginas Necessárias

#### Pomodoro Timer
- Mostrar: Tempo restante (mm:ss)
- Mostrar: Fase (Work/Break/LongBreak)
- Botão: Iniciar
- Botão: Pausar
- Botão: Reiniciar
- Botão: Selecionar Tarefa (dropdown)
- Som/Notificação ao terminar

#### Histórico Pomodoro
- Listar: Sessões completadas
- Mostrar: Tarefa | Duração | Data | Status
- Gráfico: Pomodoros por dia/semana

### Endpoints Esperados
- `POST /api/pomodoro/start` → Iniciar sessão
- `PATCH /api/pomodoro/{id}/pause` → Pausar
- `PATCH /api/pomodoro/{id}/resume` → Retomar
- `PATCH /api/pomodoro/{id}/complete` → Concluir
- `GET /api/pomodoro/history` → Histórico

---

## 📚 4. ACADEMIC API (Disciplinas/Matérias)

### Modelos
```
Subject (Disciplina/Matéria)
├── id: number
├── name: string
└── color: string (hex color #FF5733)

Task (Atividade da Disciplina)
├── id: number
├── title: string
├── description: string
├── completed: boolean
└── subject: Subject
```

### Páginas Necessárias

#### Lista de Disciplinas
- Cards com cada disciplina
- Mostrar: Nome | Cor | Quantidade de tarefas
- Botão: + Nova Disciplina
- Clique para ver detalhe

#### Detalhe da Disciplina
- Mostrar: Nome | Cor | Descrição
- Listar: Tarefas da disciplina
- Botão: Nova Tarefa
- Botão: Editar Disciplina
- Botão: Deletar Disciplina

#### Nova/Editar Tarefa Acadêmica
- Campo: Título
- Campo: Descrição
- Dropdown: Selecionar Disciplina
- Checkbox: Concluída
- Botão: Salvar

### Endpoints Esperados
- `GET /api/academic/subjects` → Listar disciplinas
- `POST /api/academic/subjects` → Criar disciplina
- `GET /api/academic/subjects/{id}` → Detalhe disciplina
- `PUT /api/academic/subjects/{id}` → Atualizar disciplina
- `DELETE /api/academic/subjects/{id}` → Deletar disciplina
- `GET /api/academic/tasks` → Listar tarefas
- `POST /api/academic/tasks` → Criar tarefa
- `PUT /api/academic/tasks/{id}` → Atualizar tarefa
- `DELETE /api/academic/tasks/{id}` → Deletar tarefa

---

## 🔔 5. NOTIFICATION API (Notificações)

### Modelos
```
NotificationSettings
├── id: number
├── userId: number
├── pomodoroReminder: boolean
├── taskDeadlineReminder: boolean
└── ...
```

### Páginas Necessárias

#### Configurações de Notificações
- Toggle: Lembrete Pomodoro
- Toggle: Lembrete de Deadline
- Toggle: Notificações gerais
- Botão: Salvar
- Toast/Alert: "Configurações salvas"

#### Centro de Notificações (opcional)
- Listar notificações recentes
- Marcar como lida
- Limpar todas

### Endpoints Esperados
- `GET /api/notification/settings` → Obter configurações
- `PUT /api/notification/settings` → Atualizar configurações
- `GET /api/notification` → Listar notificações
- `PATCH /api/notification/{id}/read` → Marcar como lida

---

## 🎨 Layout Sugerido

### Estrutura de Navegação
```
PAES Platform
├── Logo/Menu
├── Nav Principal
│   ├── 📋 Tarefas
│   ├── 📚 Disciplinas
│   ├── 🍅 Pomodoro
│   ├── 🔔 Notificações
│   └── ⚙️ Configurações
└── User Menu
    ├── Perfil
    ├── Sair
```

### Dashboard (Home)
- Cards de resumo:
  - Tarefas pendentes
  - Pomodoros esta semana
  - Disciplinas inscritas
- Últimas tarefas criadas
- Próxima sessão Pomodoro

### Responsividade
- Desktop: Layout completo
- Tablet: Menu lateral collapsível
- Mobile: Menu hamburger

---

## 🔌 Integração Backend

### Endpoints Base
```
http://localhost:8080/api
```

### Headers Obrigatórios
```
Content-Type: application/json
Authorization: Bearer {token}  (após login)
```

### Fluxo de Autenticação
1. User faz login em `/api/identify/login`
2. Backend retorna JWT token
3. Frontend salva token em localStorage
4. Todo request precisa do token no header

---

## ✅ Checklist de Funcionalidades

### Autenticação
- [ ] Login
- [ ] Cadastro
- [ ] Logout
- [ ] Persistir session

### Tarefas
- [ ] Listar tarefas
- [ ] Criar tarefa
- [ ] Editar tarefa
- [ ] Deletar tarefa
- [ ] Marcar como concluída
- [ ] Filtrar tarefas

### Disciplinas
- [ ] Listar disciplinas
- [ ] Criar disciplina
- [ ] Editar disciplina
- [ ] Deletar disciplina
- [ ] Ver tarefas por disciplina

### Pomodoro
- [ ] Timer funcionando
- [ ] Iniciar/Pausar/Retomar
- [ ] Som ao terminar
- [ ] Histórico de sessões
- [ ] Gráfico de produtividade

### Notificações
- [ ] Configurar notificações
- [ ] Receber alertas
- [ ] Centro de notificações

---

## 🛠️ Stack Recomendado

**Opção 1: React**
- React 18+
- React Router
- Axios / Fetch API
- TailwindCSS / Material-UI
- Zustand / Redux (state)

**Opção 2: Vue**
- Vue 3
- Vue Router
- Axios
- TailwindCSS / Bootstrap
- Pinia (state)

**Opção 3: Angular**
- Angular 17+
- Angular Material
- HttpClient
- RxJS

---

## 📝 Próximos Passos

1. Escolher tecnologia frontend
2. Setup do projeto
3. Implementar componentes
4. Integrar APIs
5. Testes e ajustes
6. Deploy em produção

