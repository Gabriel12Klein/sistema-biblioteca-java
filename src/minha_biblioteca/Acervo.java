package minha_biblioteca;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Acervo {

    // Lista interna de livros do acervo
    private ArrayList<Livro> livros = new ArrayList<Livro>();

    // GETTER (se precisar acessar fora)
    public ArrayList<Livro> getLivros() {
        return livros;
    }

    // Adiciona um livro ao acervo e confirma no console
    public void adicionarLivro(Livro l) {

        for (Livro existente : livros) {
            if (existente.getid().equalsIgnoreCase(l.getid())) {
                System.out.println();
                System.out.println("Erro: Já existe um livro com esse ID.");
                return; 
            }
        }

        livros.add(l);
        System.out.println("Livro adicionado ao acervo.");
    }

    // Remove um livro pelo código e retorna true se conseguiu remover
    public boolean removerLivro(String cod) {
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getid().equalsIgnoreCase(cod)) {
                livros.remove(i);
                System.out.println("Livro removido do acervo.");
                return true;
            }
        }
        System.out.println("Livro não encontrado.");
        return false;
    }

    // Busca livros cujo título contém o texto informado (ignorando maiúsculas/minúsculas)
    public ArrayList<Livro> buscarPorTitulo(String t) {
        ArrayList<Livro> res = new ArrayList<>();
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getTitulo().toLowerCase().contains(t.toLowerCase())) {
                res.add(l);
            }
        }
        return res;
    }

    // Busca e retorna um livro exato pelo código de identificação (ou null se não achar)
    public Livro buscarPorCodigo(String c) {
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.getid().equalsIgnoreCase(c)) {
                return l;
            }
        }
        return null;
    }

    // Lista apenas os livros disponíveis (status = true) ou informa se não houver
    public void listarLivrosDisponiveis() {
        System.out.println("---- Livros Disponíveis ----");
        boolean achou = false;
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.isDisponivel()) {
                l.exibirInfo();
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("(nenhum disponível)");
        }
    }

    // Lista todos os livros do acervo (útil para a opção de menu “Listar livros”)
    public void listarTodos() {
        System.out.println("---- Todos os Livros ----");
        if (livros.size() == 0) {
            System.out.println("(vazio)");
        } else {
            for (int i = 0; i < livros.size(); i++) {
                livros.get(i).exibirInfo();
            }
        }
    }
}
