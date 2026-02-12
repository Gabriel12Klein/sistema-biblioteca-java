package minha_biblioteca;

import Utility.Datas;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Emprestimo {

    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucao; // null enquanto não devolvido

    // GETTERS e SETTERS
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // Registra o empréstimo: salva usuário/livro/data e marca o livro como indisponível
    public void registrarEmprestimo(Usuario u, Livro l, Date d) {
        this.usuario = u;
        this.livro = l;
        this.dataEmprestimo = d;
        this.dataDevolucao = null;
        l.alterarStatus(false); // fica indisponível
    }

    // Registra a devolução: salva a data e marca o livro como disponível
    public void registrarDevolucao(Date d) {
        this.dataDevolucao = d;
        this.livro.alterarStatus(true); // volta a ficar disponível
    }

    // Retorna true se o empréstimo está atrasado considerando a data atual e o prazo em dias
    public boolean estaEmAtraso(Date hoje, int prazoDias) {
        Date a = Datas.truncDia(this.dataEmprestimo);
        Date b = Datas.truncDia(hoje);
        long diffMs = b.getTime() - a.getTime();
        long dias = diffMs / (24L * 60L * 60L * 1000L);
        return this.dataDevolucao == null && dias > prazoDias;
    }
}
