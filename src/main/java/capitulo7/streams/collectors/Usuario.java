/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitulo7.streams.collectors;

class Usuario {

    private String nome;
    private int pontos;
    private boolean moderador;

    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
        this.moderador = false;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void tornaModerador() {

        this.moderador = true;
    }

    public void deixarDeSerModerador() {

        this.moderador = false;
    }

    public boolean isModerador() {
        return moderador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setModerador(boolean moderador) {
        this.moderador = moderador;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", pontos=" + pontos + ", moderador=" + moderador + '}';
    }
}
