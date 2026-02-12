package minha_biblioteca;

import Utility.Datas;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class SistemaBiblioteca {

    // Estruturas principais do sistema (acervo, lista de usuários e de empréstimos)
    private final Acervo acervo = new Acervo();
    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    private final ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    private final Scanner scanMenu = new Scanner(System.in);

    // Mostra o menu e executa a opção escolhida, qualquer outro que não seja entre 1 a 9 é opção invalida
    public void executarMenu() {
        while (true) {
            System.out.println("\n===== Sistema Minha Biblioteca =====");
            System.out.println("1) Cadastrar usuário");
            System.out.println("2) Listar usuários");
            System.out.println("3) Cadastrar livro");
            System.out.println("4) Listar livros");
            System.out.println("5) Realizar empréstimo");
            System.out.println("6) Registrar devolução");
            System.out.println("7) Consultar histórico de empréstimos (por matrícula)");
            System.out.println("8) Consultar livros disponíveis");
            System.out.println("9) Sair");
            System.out.print("Opção: ");
            String opcaoMenu = scanMenu.nextLine().trim();

            System.out.println();

            switch (opcaoMenu) {
                case "1" ->
                    cadastrarUsuario();
                case "2" ->
                    listarUsuarios();
                case "3" ->
                    cadastrarLivro();
                case "4" ->
                    acervo.listarTodos();
                case "5" ->
                    realizarEmprestimo();
                case "6" ->
                    registrarDevolucao();
                case "7" ->
                    consultarHistoricoEmprestimos();
                case "8" ->
                    acervo.listarLivrosDisponiveis();
                case "9" -> {
                    System.out.println("Encerrando Programa...");
                    return; // sai do menu
                }
                default ->
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // ===== Opções do menu =====
    // Adiciona um novo usuário na lista 
    public void cadastrarUsuario(String nome, String matricula, String curso) {

        for (Usuario u : usuarios) {
            if (u.getMatricula().equalsIgnoreCase(matricula)) {

                System.out.println();

                System.out.println("Erro: Já existe um usuário cadastrado com essa matrícula.");
                return;
            }
        }

        Usuario u = new Usuario(nome, matricula, curso);
        usuarios.add(u);

        System.out.println();

        System.out.println("O usuário foi cadastrado com sucesso.");
    }

    // Lê os dados no teclado e chama o cadastro de usuário
    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanMenu.nextLine();
        System.out.print("Matrícula: ");
        String mat = scanMenu.nextLine();
        System.out.print("Curso: ");
        String curso = scanMenu.nextLine();
        cadastrarUsuario(nome, mat, curso);
    }

    // Lista todos os usuários cadastrados
    public void listarUsuarios() {
        System.out.println("---- Usuários ----");
        if (usuarios.isEmpty()) {
            System.out.println("(nenhum cadastrado)");
        }
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).exibirDados();
            System.out.println("------------------");
        }
    }

    // Adiciona um novo livro no acervo 
    public void cadastrarLivro(String titulo, String autor, int anoPublicacao, String codigoIdentificacao) {
        Livro l = new Livro(titulo, autor, anoPublicacao, codigoIdentificacao);
        acervo.adicionarLivro(l);

    }

    // Lê os dados no teclado e chama o cadastro de livro
    private void cadastrarLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanMenu.nextLine();
        System.out.print("Autor: ");
        String autor = scanMenu.nextLine();
        System.out.print("Ano de publicação: ");
        int ano = Integer.parseInt(scanMenu.nextLine());
        System.out.print("ID: ");
        String cod = scanMenu.nextLine();
        cadastrarLivro(titulo, autor, ano, cod);

    }

    // Chama a listagem de todos os livros do acervo
    public void listarLivros() {
        acervo.listarTodos();
    }

    // Registra um empréstimo validando usuário, livro e disponibilidade
    public void realizarEmprestimo(String matricula, String codigoLivro, Date data) {
        Usuario u = buscarUsuarioPorMatricula(matricula);
        Livro l = acervo.buscarPorCodigo(codigoLivro);

        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(u) 
                    && e.getLivro().equals(l)
                    && e.getDataDevolucao() == null) {
                System.out.println();
                System.out.println("Erro: este usuário já possui este livro emprestado e ainda não devolveu.");
                return;
            }
                    
        }

        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (l == null) {
            System.out.println("Livro não encontrado no acervo.");
            return;
        }
        if (!l.isDisponivel()) {
            System.out.println("Livro indisponível (já emprestado).");
            return;
        }

        Emprestimo e = new Emprestimo();
        e.registrarEmprestimo(u, l, data);
        emprestimos.add(e);
        System.out.println("Empréstimo registrado.");
    }

    // Lê dados no teclado e chama o registro de empréstimo
    private void realizarEmprestimo() {
        System.out.print("Matrícula do usuário: ");
        String mat = scanMenu.nextLine();
        System.out.print("Código do livro: ");
        String cod = scanMenu.nextLine();

        System.out.print("Data do empréstimo - dia: ");
        int dia = Integer.parseInt(scanMenu.nextLine());
        System.out.print("mês: ");
        int mes = Integer.parseInt(scanMenu.nextLine());
        System.out.print("ano: ");
        int ano = Integer.parseInt(scanMenu.nextLine());
        Date d = Datas.montarData(dia, mes, ano);

        realizarEmprestimo(mat, cod, d);
    }

    // Registra a devolução do livro se houver um empréstimo ativo correspondente
    public void registrarDevolucao(String matricula, String codigoLivro, Date data) {
        Emprestimo alvo = null;
        for (int i = 0; i < emprestimos.size(); i++) {
            Emprestimo e = emprestimos.get(i);
            if (e.getUsuario().getMatricula().equalsIgnoreCase(matricula)
                    && e.getLivro().getid().equalsIgnoreCase(codigoLivro)
                    && e.getDataDevolucao() == null) {
                alvo = e;
            }
        }
        if (alvo == null) {
            System.out.println("Empréstimo ativo não encontrado.");
        } else {
            alvo.registrarDevolucao(data);
            System.out.println("Devolução registrada.");
        }
    }

    // Lê matrícula, código e data pelo console e chama o registro de devolução
    private void registrarDevolucao() {
        System.out.print("Matrícula do usuário: ");
        String mat = scanMenu.nextLine();
        System.out.print("Código do livro: ");
        String cod = scanMenu.nextLine();

        System.out.print("Data da devolução - dia: ");
        int dia = Integer.parseInt(scanMenu.nextLine());
        System.out.print("mês: ");
        int mes = Integer.parseInt(scanMenu.nextLine());
        System.out.print("ano: ");
        int ano = Integer.parseInt(scanMenu.nextLine());
        Date d = Datas.montarData(dia, mes, ano);

        registrarDevolucao(mat, cod, d);
    }

    // Mostra no console o histórico de empréstimos de uma matrícula
    public void consultarHistoricoEmprestimos(String matricula) {
        Usuario u = buscarUsuarioPorMatricula(matricula);
        if (u == null) {
            System.out.println("Usuário não encontrado.");
        } else {
            u.listarEmprestimos(emprestimos);
        }
    }

    // Lê a matrícula pelo console e chama a consulta do histórico
    private void consultarHistoricoEmprestimos() {
        System.out.print("Matrícula do usuário: ");
        String mat = scanMenu.nextLine();
        consultarHistoricoEmprestimos(mat);
    }

    // Lista apenas os livros disponíveis do acervo
    public void consultarLivrosDisponiveis() {
        acervo.listarLivrosDisponiveis();
    }

    // Busca e retorna um usuário na lista pela matrícula (ou null se não encontrar)
    private Usuario buscarUsuarioPorMatricula(String m) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getMatricula().equalsIgnoreCase(m)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    // ===== main ===== // Ponto de entrada do programa: cria o objeto e abre o menu
    public static void main(String[] args) {
        SistemaBiblioteca s = new SistemaBiblioteca();
        s.executarMenu();
    }
}
