# Swift Console App

Aplicação console Java para gerenciamento de dados do sistema Swift usando JDBC e Oracle Database.

## 📋 Descrição

Este projeto é uma aplicação console que permite inserir e buscar registros das seguintes entidades:
- **Categorias**
- **Produtos**
- **Usuários**
- **Endereços**
- **Lojas**

A aplicação utiliza o padrão DAO (Data Access Object) e um FactoryManager para gerenciar as conexões com o banco de dados Oracle.

## 🛠️ Tecnologias

- Java 17
- Maven
- Oracle JDBC Driver (ojdbc11)
- SLF4J + Logback (para logging)

## 📦 Estrutura do Projeto

```
swift-console-app/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── swift/
│                   └── console/
│                       ├── Main.java              # Classe principal com menu interativo
│                       ├── factory/
│                       │   └── FactoryManager.java # Gerenciador de conexões (Singleton)
│                       ├── dao/                   # Data Access Objects
│                       │   ├── CategoriaDAO.java
│                       │   ├── ProductDAO.java
│                       │   ├── UsuarioDAO.java
│                       │   ├── EnderecoDAO.java
│                       │   └── LojaDAO.java
│                       └── model/                 # Classes de modelo
│                           ├── Categoria.java
│                           ├── Product.java
│                           ├── Usuario.java
│                           ├── Endereco.java
│                           └── Loja.java
└── pom.xml
```

## 🔧 Configuração

### Pré-requisitos

1. Java JDK 17 ou superior
2. Maven 3.6 ou superior
3. Oracle Database (XE ou superior) rodando em `localhost:1521`
4. Schema do banco de dados criado (veja o projeto backend original)

### Configuração do Banco de Dados

Por padrão, a aplicação se conecta ao banco com as seguintes credenciais:

```
URL: jdbc:oracle:thin:@localhost:1521:XE
Username: system
Password: oracle
```

Para alterar essas configurações, edite o arquivo `FactoryManager.java`:

```java
private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
private static final String USERNAME = "system";
private static final String PASSWORD = "oracle";
```

## 🚀 Como Executar

### Compilar o projeto

```bash
cd swift-console-app
mvn clean compile
```

### Executar a aplicação

```bash
mvn exec:java
```

Ou compile e execute em um único comando:

```bash
mvn clean compile exec:java
```

## 📖 Como Usar

Ao executar a aplicação, você verá um menu principal:

```
========== MENU PRINCIPAL ==========
1. Gerenciar Categorias
2. Gerenciar Produtos
3. Gerenciar Usuários
4. Gerenciar Endereços
5. Gerenciar Lojas
0. Sair
====================================
```

Para cada entidade, você pode:
- **Inserir** novo registro
- **Buscar** por ID
- **Listar** todos os registros

### Exemplos de Uso

#### Inserir Categoria

1. Selecione opção `1` (Gerenciar Categorias)
2. Selecione opção `1` (Inserir novo registro)
3. Digite a descrição da categoria
4. O sistema exibirá o ID gerado

#### Buscar Produto por Nome

1. Selecione opção `2` (Gerenciar Produtos)
2. Selecione opção `4` (Buscar por nome)
3. Digite o nome (parcial) do produto
4. O sistema exibirá todos os produtos que contenham o texto

## 🏗️ Padrões de Projeto Utilizados

### Singleton (FactoryManager)

O `FactoryManager` utiliza o padrão Singleton para garantir uma única instância responsável por gerenciar as conexões com o banco de dados.

```java
FactoryManager factory = FactoryManager.getInstance();
Connection conn = factory.getConnection();
```

### DAO (Data Access Object)

Cada entidade possui seu próprio DAO que encapsula toda a lógica de acesso ao banco de dados, separando a lógica de negócio da lógica de persistência.

## 📝 Funcionalidades Implementadas

### CategoriaDAO
- ✅ Inserir categoria
- ✅ Buscar por ID
- ✅ Listar todas

### ProductDAO
- ✅ Inserir produto
- ✅ Buscar por ID
- ✅ Listar todos
- ✅ Buscar por nome (LIKE)
- ✅ Buscar por categoria

### UsuarioDAO
- ✅ Inserir usuário
- ✅ Buscar por ID
- ✅ Listar todos

### EnderecoDAO
- ✅ Inserir endereço
- ✅ Buscar por ID
- ✅ Listar todos

### LojaDAO
- ✅ Inserir loja
- ✅ Buscar por ID
- ✅ Listar todas

## 🔍 Observações

- A aplicação **não implementa funcionalidade de DELETE** conforme especificado nos requisitos
- Todas as operações de banco utilizam o `FactoryManager` para obter conexões
- As conexões são fechadas adequadamente após cada operação
- Logging é feito via SLF4J com Logback

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos (FIAP).

## 👨‍💻 Autor

Desenvolvido com base no projeto Swift Backend FIAP.

