# PAES Platform - Plataforma Integrada de Gestão de Estudos

Sistema de microserviços consolidado para gerenciamento acadêmico e técnicas de estudo.

## 📊 Estrutura do Projeto

```
PAES/
├── Backend (Java/Spring Boot)
│   ├── pom.xml                          # Configuração Maven
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/gestaoestudo/
│   │   │   │   ├── taskapi/            # API de Tarefas
│   │   │   │   ├── notificacaoapi/     # API de Notificações
│   │   │   │   ├── pomodoroapi/        # API de Técnica Pomodoro
│   │   │   │   ├── identifyapi/        # API de Identificação/Autenticação
│   │   │   │   ├── academicapi/        # API de Gerenciamento Acadêmico
│   │   │   │   └── common/             # Código compartilhado
│   │   │   └── resources/              # Configurações
│   │   └── test/java/                  # Testes
│   ├── Dockerfile                       # Multi-stage build
│   ├── docker-compose.yml               # Orquestração
│   ├── .github/workflows/ci.yml         # CI/CD Pipeline
│   └── README.md                        # Docs backend
│
└── Frontend (React/Vite)
    ├── frontend/
    │   ├── src/
    │   │   ├── components/
    │   │   │   ├── MainLayout.jsx
    │   │   │   └── Sidebar.jsx
    │   │   ├── pages/
    │   │   │   ├── LoginPage.jsx
    │   │   │   ├── RegisterPage.jsx
    │   │   │   ├── DashboardPage.jsx
    │   │   │   ├── TarefasPage.jsx
    │   │   │   ├── DisciplinasPage.jsx
    │   │   │   ├── PomodoroPage.jsx
    │   │   │   └── ConfigPage.jsx
    │   │   ├── services/
    │   │   │   └── api.js
    │   │   ├── App.jsx
    │   │   └── main.jsx
    │   ├── package.json
    │   ├── vite.config.js
    │   ├── tailwind.config.js
    │   └── README.md                    # Docs frontend
    │
    └── Documentação
        ├── FRONTEND_REQUIREMENTS.md     # Specs do frontend
        ├── DOCKER.md                    # Setup Docker
        └── CONSOLIDACAO.md              # Processo de consolidação
```

## 🚀 Quick Start

### Pré-requisitos
- Java 21+
- Maven 3.8+

### Compilar
```bash
mvn clean install
```

### Rodar localmente
```bash
mvn spring-boot:run
```

### Rodar testes
```bash
mvn test
```

## 📦 APIs Incluídas

| API | Descrição | Porta (planejado) |
|-----|-----------|-------------------|
| **Task API** | Gerenciamento de tarefas | 8001 |
| **Notification API** | Sistema de notificações | 8002 |
| **Pomodoro API** | Técnica Pomodoro | 8003 |
| **Identify API** | Autenticação e identificação | 8004 |
| **Academic API** | Gestão acadêmica | 8005 |

## 🏗️ Arquitetura

### Stack Tecnológico
- **Framework:** Spring Boot 4.0.5
- **Linguagem:** Java 21
- **Build:** Maven
- **Banco:** H2 Database (desenvolvimento)
- **ORM:** JPA/Hibernate

### Dependências Principais
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- lombok
- h2 (database)

## 🔧 Configuração

### Properties principais (src/main/resources/application.properties)
```properties
spring.application.name=paes-platform
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
server.port=8080
```

## 📝 Classes Consolidadas

### Total: 32 classes Java
- Task API: 6 classes
- Notification API: 5 classes
- Pomodoro API: 7 classes
- Identify API: 7 classes
- Academic API: 7 classes

### Testes: 5 testes

## 🌐 Frontend - React + Vite

### Estrutura
- **Framework**: React 18 + Vite
- **Styling**: Tailwind CSS com tema customizado
- **Roteamento**: React Router v6
- **HTTP**: Axios com interceptors
- **Ícones**: Lucide Icons

### Páginas Incluídas
✅ Login/Cadastro com JWT
✅ Dashboard com estatísticas
✅ CRUD de Tarefas
✅ CRUD de Disciplinas (com cores)
✅ Timer Pomodoro (25/5/15 minutos)
✅ Configurações de notificações
✅ Menu responsivo (mobile/desktop)

### Quick Start Frontend
```bash
cd frontend
npm install
npm run dev          # http://localhost:3000
```

### Build para Produção
```bash
npm run build        # Gera: dist/
```

### Documentação
- Ver `frontend/README.md` para detalhes completos
- Ver `FRONTEND_REQUIREMENTS.md` para specs das APIs

## 📦 Build & Deployment

### Build para produção
```bash
mvn clean package
```

Gerará um `.jar` em `target/paes-platform-1.0.0.jar`

### Deploy
Ver DEPLOY.md para instruções detalhadas.

## ✅ Próximos Passos

- [ ] Documentar todos os endpoints REST (Swagger/OpenAPI)
- [ ] Implementar autenticação JWT
- [ ] Configurar integração entre APIs
- [ ] Criar Docker containers
- [ ] Setup CI/CD pipeline
- [ ] Implementar frontend

## 🤖 Uso de Inteligência Artificial

### Transparência sobre o Uso de IA

Este projeto utilizou **Claude AI (Anthropic)** em algumas etapas da consolidação e setup:

| Etapa | Descrição | % IA | % Manual |
|-------|-----------|------|---------|
| **Consolidação de Código** | Unificar 32 classes Java de 5 APIs | 100% | 0% |
| **Estrutura Maven** | Criar `pom.xml` e estrutura de diretórios | 100% | 0% |
| **Testes** | Consolidar 5 testes de todas as APIs | 100% | 0% |
| **Configurações** | `.gitignore`, `application.properties` | 100% | 0% |
| **Documentação** | README.md, CONSOLIDACAO.md | 100% | 0% |
| **CI/CD Setup** | `.github/workflows/ci.yml` | 0% | 100% |
| **Código de Negócio** | Classes Java (TaskAPI, NotificationAPI, etc.) | 0% | 100% |
| **Lógica de Teste** | Testes unitários e integração | 0% | 100% |

### Resumo

- **Consolidação e Setup**: ~90% com IA (automation de tarefas repetitivas)
- **Código de Negócio**: 0% com IA (100% original dos alunos)
- **CI/CD Pipeline**: 0% com IA (de acordo com especificação do professor)

### O que IA Fez

✅ Organizou código existente em estrutura única
✅ Criou configurações de build (Maven)
✅ Consolidou múltiplos repositórios
✅ Gerou documentação automática
✅ Limpou duplicações

### O que IA NÃO Fez

❌ Não criou nenhuma lógica de negócio
❌ Não modificou APIs originais
❌ Não alterou testes originais
❌ Não implementou novas features

---

## 👥 Equipe

Projeto acadêmico - SEXTO SEMESTRE

## 📄 Licença

Este projeto é de propriedade da instituição acadêmica.
