package minha_biblioteca;

/**
 *
 * @author Gabriel
 */
public class Livro {

    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String id;
    private boolean status; // true = disponível, false = emprestado

    public Livro(String titulo, String autor, int anoPublicacao, String id) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.id = id;
        this.status = true;
    }

    // GETTERS e SETTERS
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public boolean isDisponivel() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Ele exibe as informaçoes do livro no console como o, ID, nome, autor, anoPublicação, 
    //status de disponibilidade(sim/não)
    public void exibirInfo() {
        System.out.println("[" + id + "] "
                + titulo + " (" + autor + ", " + anoPublicacao + ")"
                + " | Disponível: " + (status ? "Sim" : "Não"));
    }
    
    // Altera o status conforme ação de emprestar/devolver onde (true=disponivel., false=emprestado.)
    public void alterarStatus(boolean d) {
        this.status = d;
    }

}
