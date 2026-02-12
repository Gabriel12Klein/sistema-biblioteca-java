package minha_biblioteca;

import Utility.Datas;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Usuario {

    private String nome;
    private String matricula;
    private String curso;
    
    
    
   //Construtores 
    public Usuario(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }
    
    
    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
        // Quando solicitado no menu exibe os dados do usuario como (nome, matricula, curso)
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Curso: " + curso);
    }
    
    // Lista no console apenas os empréstimos deste usuário, mostrando livro e datas formatadas
    public void listarEmprestimos(ArrayList<Emprestimo> lista) {
        System.out.println("---- Empréstimos do usuário: (" + nome + ") ----");
        boolean achou = false;
        for (int i = 0; i < lista.size(); i++) {
            Emprestimo e = lista.get(i);
            if (e.getUsuario() == this) { // confere se o empréstimo pertence a este usuário
                achou = true;
                String dtEmp = Datas.formatar(e.getDataEmprestimo());
                String dtDev = (e.getDataDevolucao() == null) ? "—" : Datas.formatar(e.getDataDevolucao());
                System.out.println("Livro: " + e.getLivro().getTitulo()
                        + " | Código: " + e.getLivro().getid()
                        + " | Empréstimo: " + dtEmp
                        + " | Devolução: " + dtDev);
            }
        }
        if (!achou) {
            System.out.println("(nenhum empréstimo encontrado)");
        }

    }
}
