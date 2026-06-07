# 🐳 Docker & Deploy - PAES Platform

## Arquivos Criados

### 1. **Dockerfile**
- Build em 2 estágios (Multi-stage build)
- Etapa 1: Compilação com Maven
- Etapa 2: Runtime com JRE 21
- Resultado: Imagem otimizada (~500MB)

### 2. **.dockerignore**
- Exclui arquivos desnecessários da imagem
- Reduz tamanho do build context
- Acelera o build

### 3. **docker-compose.yml**
- Orquestra o container
- Configura variáveis de ambiente
- Health check automático
- Porta 8080 exposta

## 🚀 Como Usar

### Build da Imagem
```bash
docker build -t paes-platform:1.0.0 .
```

### Rodar com Docker Compose
```bash
docker-compose up
```

### Rodar Container Direto
```bash
docker run -p 8080:8080 paes-platform:1.0.0
```

### Ver logs
```bash
docker-compose logs -f
```

### Parar
```bash
docker-compose down
```

## 📊 Estrutura do Dockerfile

```
Dockerfile (Multi-stage)
├── Stage 1: Builder
│   ├── FROM maven:3.9.6-eclipse-temurin-21
│   ├── Download dependências
│   └── Build JAR
└── Stage 2: Runtime
    ├── FROM eclipse-temurin:21-jre
    ├── Copy JAR do builder
    └── Expose porta 8080
```

## ✅ Verificação

Para verificar se está funcionando:
```bash
curl http://localhost:8080
```

## 🔧 Configurações

Todas as configs estão no `docker-compose.yml`:
- Port: 8080
- DDL: update (cria tables automaticamente)
- Database: H2 in-memory

## 📝 Próximos Passos

- [ ] Deploy em container registry (Docker Hub, AWS ECR, etc)
- [ ] Kubernetes para produção
- [ ] CI/CD pipeline docker push automático
