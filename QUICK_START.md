# 🚀 Guia Rápido de Início

## Pré-requisitos

✅ Java 17+  
✅ Maven 3.6+  
✅ Oracle Database rodando em `localhost:1521`  
✅ Schema criado (use o script do projeto backend original)

## Passos para Executar

### 1. Navegue até o diretório do projeto

```bash
cd C:\Users\bluin\projects\swift-console-app
```

### 2. Compile o projeto

```bash
mvn clean compile
```

### 3. Execute a aplicação

```bash
mvn exec:java
```

## Estrutura do Menu

```
MENU PRINCIPAL
├── 1. Gerenciar Categorias
│   ├── Inserir
│   ├── Buscar por ID
│   └── Listar todas
├── 2. Gerenciar Produtos
│   ├── Inserir
│   ├── Buscar por ID
│   ├── Listar todos
│   ├── Buscar por nome
│   └── Buscar por categoria
├── 3. Gerenciar Usuários
│   ├── Inserir
│   ├── Buscar por ID
│   └── Listar todos
├── 4. Gerenciar Endereços
│   ├── Inserir
│   ├── Buscar por ID
│   └── Listar todos
├── 5. Gerenciar Lojas
│   ├── Inserir
│   ├── Buscar por ID
│   └── Listar todas
└── 0. Sair
```

## Exemplo de Fluxo Completo

### 1. Criar uma Categoria

```
Menu Principal → 1 (Categorias)
  → 1 (Inserir)
  → Digite: "Eletrônicos"
  ✅ Categoria criada com ID: 1
```

### 2. Criar um Produto

```
Menu Principal → 2 (Produtos)
  → 1 (Inserir)
  → Nome: "Smartphone"
  → ID da Categoria: 1
  → Preço: 1500.00
  → Promoção: 1
  → Estoque: 50
  → Imagem: "http://exemplo.com/img.jpg"
  → Descrição: "Smartphone top de linha"
  → Desconto: 10
  ✅ Produto criado com ID: 1
```

### 3. Buscar Produtos por Categoria

```
Menu Principal → 2 (Produtos)
  → 5 (Buscar por categoria)
  → ID da categoria: 1
  ✅ Lista todos produtos da categoria Eletrônicos
```

## Modificar Configurações do Banco

Edite: `src/main/java/com/swift/console/factory/FactoryManager.java`

```java
private static final String URL = "jdbc:oracle:thin:@SEU_HOST:PORTA:SID";
private static final String USERNAME = "seu_usuario";
private static final String PASSWORD = "sua_senha";
```

## Arquitetura

```
┌─────────────┐
│    Main     │ ← Interface console com Scanner
└──────┬──────┘
       │
       ↓
┌─────────────┐
│   DAOs      │ ← Acesso aos dados (CRUD)
└──────┬──────┘
       │
       ↓
┌──────────────┐
│FactoryManager│ ← Gerenciador de conexões (Singleton)
└──────┬───────┘
       │
       ↓
┌──────────────┐
│Oracle Database│
└──────────────┘
```

## Dicas

💡 **Campos opcionais**: Quando aparecer "(Enter para nulo)", pressione Enter sem digitar nada  
💡 **Busca por nome**: É case-sensitive e aceita texto parcial  
💡 **IDs**: São gerados automaticamente pelo banco de dados  
💡 **Conexão**: O FactoryManager gerencia automaticamente a abertura e fechamento de conexões

## Troubleshooting

### Erro de conexão com banco

Verifique se:
- Oracle está rodando
- Porta 1521 está acessível
- Credenciais estão corretas
- Schema existe

### Erro de compilação

```bash
# Limpe o cache do Maven
mvn clean

# Compile novamente
mvn compile
```

### Dependências não baixadas

```bash
# Force o download
mvn dependency:resolve
```

## Funcionalidades Implementadas

✅ Inserção de registros  
✅ Busca por ID  
✅ Listagem completa  
✅ Busca personalizada (Produtos)  
✅ Gerenciamento de conexões via FactoryManager  
✅ Tratamento de erros  
✅ Logging com SLF4J  

❌ Deleção (não implementado conforme requisito)  
❌ Atualização (não implementado conforme requisito)

---

📚 Para mais detalhes, consulte o [README.md](README.md)

