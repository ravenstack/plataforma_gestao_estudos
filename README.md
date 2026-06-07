# PAES Platform - Plataforma Integrada de Gestão de Estudos

Sistema de microserviços consolidado para gerenciamento acadêmico e técnicas de estudo.

## 📊 Estrutura do Projeto

```
PAES/
├── pom.xml                          # Configuração Maven
├── src/
│   ├── main/
│   │   ├── java/com/gestaoestudo/
│   │   │   ├── taskapi/            # API de Tarefas
│   │   │   ├── notificacaoapi/     # API de Notificações
│   │   │   ├── pomodoroapi/        # API de Técnica Pomodoro
│   │   │   ├── identifyapi/        # API de Identificação/Autenticação
│   │   │   ├── academicapi/        # API de Gerenciamento Acadêmico
│   │   │   └── common/             # Código compartilhado
│   │   └── resources/              # Configurações
│   └── test/java/                  # Testes
├── .gitignore
├── README.md
└── DEPLOY.md                        # Instruções de deployment
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

## 🌐 Frontend Integration

Para integrar com o frontend:
1. Certificar que todas as APIs estão rodando
2. Configurar CORS em cada controller
3. Documentar endpoints REST
4. Usar ferramentas como Swagger/OpenAPI para documentação

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

## 👥 Equipe

Projeto acadêmico - SEXTO SEMESTRE

## 📄 Licença

Este projeto é de propriedade da instituição acadêmica.
