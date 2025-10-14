package com.swift.console;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.swift.console.dao.AutenticacaoDAO;
import com.swift.console.dao.CategoriaDAO;
import com.swift.console.dao.GastosDAO;
import com.swift.console.dao.UsuarioDAO;
import com.swift.console.model.Autenticacao;
import com.swift.console.model.Categoria;
import com.swift.console.model.Gastos;
import com.swift.console.model.Usuario;

public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    private static final AutenticacaoDAO autenticacaoDAO = new AutenticacaoDAO();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static final GastosDAO gastosDAO = new GastosDAO();

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  SISTEMA FINANCEIRO - SWIFT APP");
        System.out.println("===========================================\n");

        boolean running = true;
        
        while (running) {
            try {
                exibirMenuPrincipal();
                int opcao = lerInt("Escolha uma opção: ");
                
                switch (opcao) {
                    case 1:
                        gerenciarUsuarios();
                        break;
                    case 2:
                        gerenciarAutenticacao();
                        break;
                    case 3:
                        gerenciarCategorias();
                        break;
                    case 4:
                        gerenciarGastos();
                        break;
                    case 0:
                        System.out.println("\nEncerrando sistema...");
                        running = false;
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.err.println("\n❌ Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        scanner.close();
        System.out.println("Sistema encerrado com sucesso!");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1. Gerenciar Usuários");
        System.out.println("2. Gerenciar Autenticação");
        System.out.println("3. Gerenciar Categorias");
        System.out.println("4. Gerenciar Gastos");
        System.out.println("0. Sair");
        System.out.println("====================================");
    }

    private static void exibirMenuCRUD(Boolean esconderEscolha) {
        System.out.println("\n1. Inserir novo registro");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todos");
        if (!esconderEscolha) {
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
        }
    }

    // ==================== USUÁRIOS ====================
    
    private static void gerenciarUsuarios() throws SQLException {
        System.out.println("\n========== GERENCIAR USUÁRIOS ==========");
        exibirMenuCRUD(false);
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                inserirUsuario();
                break;
            case 2:
                buscarUsuarioPorId();
                break;
            case 3:
                listarUsuarios();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }

    private static void inserirUsuario() throws SQLException {
        System.out.println("\n--- INSERIR USUÁRIO ---");
        
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        
        Date dataNascimento = lerData("Data de Nascimento (dd/MM/yyyy): ");
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Ativo (S/N): ");
        String ativo = scanner.nextLine().toUpperCase();
        
        BigDecimal saldo = lerBigDecimal("Saldo inicial: ");

        Usuario usuario = new Usuario();
        usuario.setNmUsuario(nome);
        usuario.setDtNascimento(dataNascimento);
        usuario.setNrTelefone(telefone);
        usuario.setAtivo(ativo);
        usuario.setVlSaldo(saldo);

        Usuario salvo = usuarioDAO.save(usuario);
        System.out.println("✅ Usuário inserido com sucesso! ID: " + salvo.getCdUsuario());
        System.out.println(salvo);
    }

    private static void buscarUsuarioPorId() throws SQLException {
        System.out.println("\n--- BUSCAR USUÁRIO ---");
        int id = lerInt("ID do usuário: ");

        Optional<Usuario> usuario = usuarioDAO.findById(id);
        if (usuario.isPresent()) {
            System.out.println("✅ Usuário encontrado:");
            System.out.println(usuario.get());
        } else {
            System.out.println("❌ Usuário não encontrado!");
        }
    }

    private static void listarUsuarios() throws SQLException {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        List<Usuario> usuarios = usuarioDAO.findAll();
        
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    // ==================== CATEGORIAS ====================
    
    private static void gerenciarCategorias() throws SQLException {
        System.out.println("\n========== GERENCIAR CATEGORIAS ==========");
        exibirMenuCRUD(false);
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                inserirCategoria();
                break;
            case 2:
                buscarCategoriaPorId();
                break;
            case 3:
                listarCategorias();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }

    private static void inserirCategoria() throws SQLException {
        System.out.println("\n--- INSERIR CATEGORIA ---");
        
        System.out.print("Nome da categoria: ");
        String nome = scanner.nextLine();
        
        System.out.print("Tipo (R=Receita, D=Despesa): ");
        String tipo = scanner.nextLine().toUpperCase();

        Categoria categoria = new Categoria();
        categoria.setNmCategoria(nome);
        categoria.setTpCategoria(tipo);

        Categoria salva = categoriaDAO.save(categoria);
        System.out.println("✅ Categoria inserida com sucesso! ID: " + salva.getCdCategoria());
        System.out.println(salva);
    }

    private static void buscarCategoriaPorId() throws SQLException {
        System.out.println("\n--- BUSCAR CATEGORIA ---");
        int id = lerInt("ID da categoria: ");

        Optional<Categoria> categoria = categoriaDAO.findById(id);
        if (categoria.isPresent()) {
            System.out.println("✅ Categoria encontrada:");
            System.out.println(categoria.get());
        } else {
            System.out.println("❌ Categoria não encontrada!");
        }
    }

    private static void listarCategorias() throws SQLException {
        System.out.println("\n--- LISTA DE CATEGORIAS ---");
        List<Categoria> categorias = categoriaDAO.findAll();
        
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            categorias.forEach(System.out::println);
        }
    }

    // ==================== GASTOS ====================
    
    private static void gerenciarGastos() throws SQLException {
        System.out.println("\n========== GERENCIAR GASTOS ==========");
        exibirMenuCRUD(true);
        System.out.println("4. Buscar por usuário");
        System.out.println("5. Buscar por categoria");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                inserirGasto();
                break;
            case 2:
                buscarGastoPorId();
                break;
            case 3:
                listarGastos();
                break;
            case 4:
                buscarGastosPorUsuario();
                break;
            case 5:
                buscarGastosPorCategoria();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }

    private static void inserirGasto() throws SQLException {
        System.out.println("\n--- INSERIR GASTO ---");
        
        int cdUsuario = lerInt("ID do Usuário: ");
        int cdCategoria = lerInt("ID da Categoria: ");
        
        System.out.print("Nome do gasto: ");
        String nome = scanner.nextLine();
        
        Date dataGasto = lerData("Data do gasto (dd/MM/yyyy): ");
        BigDecimal valor = lerBigDecimal("Valor: ");
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Gastos gasto = new Gastos();
        gasto.setCdUsuario(cdUsuario);
        gasto.setCdCategoria(cdCategoria);
        gasto.setNmGasto(nome);
        gasto.setDtGasto(dataGasto);
        gasto.setVlGasto(valor);
        gasto.setDsGasto(descricao);

        Gastos salvo = gastosDAO.save(gasto);
        System.out.println("✅ Gasto inserido com sucesso! ID: " + salvo.getCdGasto());
        System.out.println(salvo);
    }

    private static void buscarGastoPorId() throws SQLException {
        System.out.println("\n--- BUSCAR GASTO ---");
        int id = lerInt("ID do gasto: ");

        Optional<Gastos> gasto = gastosDAO.findById(id);
        if (gasto.isPresent()) {
            System.out.println("✅ Gasto encontrado:");
            System.out.println(gasto.get());
        } else {
            System.out.println("❌ Gasto não encontrado!");
        }
    }

    private static void listarGastos() throws SQLException {
        System.out.println("\n--- LISTA DE GASTOS ---");
        List<Gastos> gastos = gastosDAO.findAll();
        
        if (gastos.isEmpty()) {
            System.out.println("Nenhum gasto cadastrado.");
        } else {
            gastos.forEach(System.out::println);
        }
    }

    private static void buscarGastosPorUsuario() throws SQLException {
        System.out.println("\n--- BUSCAR GASTOS POR USUÁRIO ---");
        int cdUsuario = lerInt("ID do usuário: ");

        List<Gastos> gastos = gastosDAO.findByUsuario(cdUsuario);
        if (gastos.isEmpty()) {
            System.out.println("❌ Nenhum gasto encontrado!");
        } else {
            System.out.println("✅ Gastos encontrados:");
            gastos.forEach(System.out::println);
        }
    }

    private static void buscarGastosPorCategoria() throws SQLException {
        System.out.println("\n--- BUSCAR GASTOS POR CATEGORIA ---");
        int cdCategoria = lerInt("ID da categoria: ");

        List<Gastos> gastos = gastosDAO.findByCategoria(cdCategoria);
        if (gastos.isEmpty()) {
            System.out.println("❌ Nenhum gasto encontrado!");
        } else {
            System.out.println("✅ Gastos encontrados:");
            gastos.forEach(System.out::println);
        }
    }

    // ==================== AUTENTICAÇÃO ====================
    
    private static void gerenciarAutenticacao() throws SQLException {
        System.out.println("\n========== GERENCIAR AUTENTICAÇÃO ==========");
        exibirMenuCRUD(true);
        System.out.println("4. Buscar por email");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                inserirAutenticacao();
                break;
            case 2:
                buscarAutenticacaoPorId();
                break;
            case 3:
                listarAutenticacoes();
                break;
            case 4:
                buscarAutenticacaoPorEmail();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }

    private static void inserirAutenticacao() throws SQLException {
        System.out.println("\n--- INSERIR AUTENTICAÇÃO ---");
        
        int cdUsuario = lerInt("ID do Usuário: ");
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.print("Status da conta: ");
        String status = scanner.nextLine();

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setCdUsuario(cdUsuario);
        autenticacao.setEmail(email);
        autenticacao.setSenha(senha);
        autenticacao.setStatusConta(status);

        Autenticacao salva = autenticacaoDAO.save(autenticacao);
        System.out.println("✅ Autenticação inserida com sucesso! ID: " + salva.getCdAutenticacao());
        System.out.println(salva);
    }

    private static void buscarAutenticacaoPorId() throws SQLException {
        System.out.println("\n--- BUSCAR AUTENTICAÇÃO ---");
        int id = lerInt("ID da autenticação: ");

        Optional<Autenticacao> autenticacao = autenticacaoDAO.findById(id);
        if (autenticacao.isPresent()) {
            System.out.println("✅ Autenticação encontrada:");
            System.out.println(autenticacao.get());
        } else {
            System.out.println("❌ Autenticação não encontrada!");
        }
    }

    private static void listarAutenticacoes() throws SQLException {
        System.out.println("\n--- LISTA DE AUTENTICAÇÕES ---");
        List<Autenticacao> autenticacoes = autenticacaoDAO.findAll();
        
        if (autenticacoes.isEmpty()) {
            System.out.println("Nenhuma autenticação cadastrada.");
        } else {
            autenticacoes.forEach(System.out::println);
        }
    }

    private static void buscarAutenticacaoPorEmail() throws SQLException {
        System.out.println("\n--- BUSCAR AUTENTICAÇÃO POR EMAIL ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Optional<Autenticacao> autenticacao = autenticacaoDAO.findByEmail(email);
        if (autenticacao.isPresent()) {
            System.out.println("✅ Autenticação encontrada:");
            System.out.println(autenticacao.get());
        } else {
            System.out.println("❌ Autenticação não encontrada!");
        }
    }

    // ==================== UTILIDADES ====================
    
    private static int lerInt(String mensagem) {
        System.out.print(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static BigDecimal lerBigDecimal(String mensagem) {
        System.out.print(mensagem);
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();
        return valor;
    }

    private static Date lerData(String mensagem) {
        System.out.print(mensagem);
        String dataStr = scanner.nextLine();
        return lerDataFromString(dataStr);
    }

    private static Date lerDataFromString(String dataStr) {
        try {
            return dateFormat.parse(dataStr);
        } catch (ParseException e) {
            System.err.println("Formato de data inválido! Usando data atual.");
            return new Date();
        }
    }
}
