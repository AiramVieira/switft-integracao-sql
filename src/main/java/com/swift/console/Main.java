package com.swift.console;

import com.swift.console.dao.*;
import com.swift.console.model.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static ProductDAO productDAO = new ProductDAO();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static EnderecoDAO enderecoDAO = new EnderecoDAO();

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  SISTEMA DE GERENCIAMENTO - SWIFT APP");
        System.out.println("===========================================\n");

        boolean running = true;
        
        while (running) {
            try {
                exibirMenuPrincipal();
                int opcao = lerInt("Escolha uma opção: ");
                
                switch (opcao) {
                    case 1:
                        gerenciarCategorias();
                        break;
                    case 2:
                        gerenciarProdutos();
                        break;
                    case 3:
                        gerenciarUsuarios();
                        break;
                    case 4:
                        gerenciarEnderecos();
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
        System.out.println("1. Gerenciar Categorias");
        System.out.println("2. Gerenciar Produtos");
        System.out.println("3. Gerenciar Usuários");
        System.out.println("4. Gerenciar Endereços");
        System.out.println("0. Sair");
        System.out.println("====================================");
    }

    private static void exibirMenuCRUD() {
        System.out.println("\n1. Inserir novo registro");
        System.out.println("2. Buscar por ID");
        System.out.println("3. Listar todos");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
    }

    // ==================== CATEGORIAS ====================
    
    private static void gerenciarCategorias() throws SQLException {
        System.out.println("\n========== GERENCIAR CATEGORIAS ==========");
        exibirMenuCRUD();
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

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
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Categoria categoria = new Categoria();
        categoria.setDescricao(descricao);

        Categoria salva = categoriaDAO.save(categoria);
        System.out.println("✅ Categoria inserida com sucesso! ID: " + salva.getId());
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

    // ==================== PRODUTOS ====================
    
    private static void gerenciarProdutos() throws SQLException {
        System.out.println("\n========== GERENCIAR PRODUTOS ==========");
        exibirMenuCRUD();
        System.out.println("4. Buscar por nome");
        System.out.println("5. Buscar por categoria");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                inserirProduto();
                break;
            case 2:
                buscarProdutoPorId();
                break;
            case 3:
                listarProdutos();
                break;
            case 4:
                buscarProdutoPorNome();
                break;
            case 5:
                buscarProdutoPorCategoria();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }

    private static void inserirProduto() throws SQLException {
        System.out.println("\n--- INSERIR PRODUTO ---");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        int categoriaId = lerInt("ID da Categoria: ");
        float preco = lerFloat("Preço: ");
        
        System.out.print("Promoção (0 ou 1, Enter para nulo): ");
        String promocaoStr = scanner.nextLine();
        Integer promocao = promocaoStr.isEmpty() ? null : Integer.parseInt(promocaoStr);
        
        int estoque = lerInt("Estoque: ");
        
        System.out.print("Imagem (URL): ");
        String imagem = scanner.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        System.out.print("Desconto (%, Enter para nulo): ");
        String descontoStr = scanner.nextLine();
        Integer desconto = descontoStr.isEmpty() ? null : Integer.parseInt(descontoStr);

        Product product = new Product();
        product.setNome(nome);
        product.setCategoriaId(categoriaId);
        product.setPreco(preco);
        product.setPromocao(promocao);
        product.setEstoque(estoque);
        product.setImagem(imagem);
        product.setDescricao(descricao);
        product.setDesconto(desconto);

        Product salvo = productDAO.save(product);
        System.out.println("✅ Produto inserido com sucesso! ID: " + salvo.getId());
        System.out.println(salvo);
    }

    private static void buscarProdutoPorId() throws SQLException {
        System.out.println("\n--- BUSCAR PRODUTO ---");
        int id = lerInt("ID do produto: ");

        Optional<Product> product = productDAO.findById(id);
        if (product.isPresent()) {
            System.out.println("✅ Produto encontrado:");
            System.out.println(product.get());
        } else {
            System.out.println("❌ Produto não encontrado!");
        }
    }

    private static void listarProdutos() throws SQLException {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        List<Product> products = productDAO.findAll();
        
        if (products.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void buscarProdutoPorNome() throws SQLException {
        System.out.println("\n--- BUSCAR PRODUTO POR NOME ---");
        System.out.print("Nome (parcial): ");
        String nome = scanner.nextLine();

        List<Product> products = productDAO.searchByNome(nome);
        if (products.isEmpty()) {
            System.out.println("❌ Nenhum produto encontrado!");
        } else {
            System.out.println("✅ Produtos encontrados:");
            products.forEach(System.out::println);
        }
    }

    private static void buscarProdutoPorCategoria() throws SQLException {
        System.out.println("\n--- BUSCAR PRODUTOS POR CATEGORIA ---");
        int categoriaId = lerInt("ID da categoria: ");

        List<Product> products = productDAO.findByCategoria(categoriaId);
        if (products.isEmpty()) {
            System.out.println("❌ Nenhum produto encontrado!");
        } else {
            System.out.println("✅ Produtos encontrados:");
            products.forEach(System.out::println);
        }
    }

    // ==================== USUÁRIOS ====================
    
    private static void gerenciarUsuarios() throws SQLException {
        System.out.println("\n========== GERENCIAR USUÁRIOS ==========");
        exibirMenuCRUD();
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

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
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        
        System.out.print("ID do Endereço (Enter para nulo): ");
        String enderecoStr = scanner.nextLine();
        Integer enderecoId = enderecoStr.isEmpty() ? null : Integer.parseInt(enderecoStr);
        
        System.out.print("Telefone: ");
        String telephone = scanner.nextLine();
        
        System.out.print("Tipo (PF/PJ): ");
        String tipo = scanner.nextLine();

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setEnderecoId(enderecoId);
        usuario.setTelephone(telephone);
        usuario.setTipo(tipo);

        Usuario salvo = usuarioDAO.save(usuario);
        System.out.println("✅ Usuário inserido com sucesso! ID: " + salvo.getId());
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

    // ==================== ENDEREÇOS ====================
    
    private static void gerenciarEnderecos() throws SQLException {
        System.out.println("\n========== GERENCIAR ENDEREÇOS ==========");
        exibirMenuCRUD();
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        switch (opcao) {
            case 1:
                inserirEndereco();
                break;
            case 2:
                buscarEnderecoPorId();
                break;
            case 3:
                listarEnderecos();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }

    private static void inserirEndereco() throws SQLException {
        System.out.println("\n--- INSERIR ENDEREÇO ---");
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        
        System.out.print("Latitude: ");
        BigDecimal latitude = new BigDecimal(scanner.nextLine());
        
        System.out.print("Longitude: ");
        BigDecimal longitude = new BigDecimal(scanner.nextLine());

        Endereco endereco = new Endereco();
        endereco.setDescricao(descricao);
        endereco.setCep(cep);
        endereco.setLatitude(latitude);
        endereco.setLongitude(longitude);

        Endereco salvo = enderecoDAO.save(endereco);
        System.out.println("✅ Endereço inserido com sucesso! ID: " + salvo.getId());
        System.out.println(salvo);
    }

    private static void buscarEnderecoPorId() throws SQLException {
        System.out.println("\n--- BUSCAR ENDEREÇO ---");
        int id = lerInt("ID do endereço: ");

        Optional<Endereco> endereco = enderecoDAO.findById(id);
        if (endereco.isPresent()) {
            System.out.println("✅ Endereço encontrado:");
            System.out.println(endereco.get());
        } else {
            System.out.println("❌ Endereço não encontrado!");
        }
    }

    private static void listarEnderecos() throws SQLException {
        System.out.println("\n--- LISTA DE ENDEREÇOS ---");
        List<Endereco> enderecos = enderecoDAO.findAll();
        
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado.");
        } else {
            enderecos.forEach(System.out::println);
        }
    }

    // ==================== UTILIDADES ====================
    
    private static int lerInt(String mensagem) {
        System.out.print(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpar buffer
        return valor;
    }

    private static float lerFloat(String mensagem) {
        System.out.print(mensagem);
        float valor = scanner.nextFloat();
        scanner.nextLine(); // limpar buffer
        return valor;
    }
}

