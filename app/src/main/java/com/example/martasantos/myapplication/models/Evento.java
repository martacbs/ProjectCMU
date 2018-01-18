package com.example.martasantos.myapplication.models;

/**
 * Created by vieir on 16/01/2018.
 */

public class Evento {
    private String nome;
    private String comeca;
    private int id;
    private String local;
    private String duracao;
    private String lembrete;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComeca() {
        return comeca;
    }

    public void setComeca(String comeca) {
        this.comeca = comeca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getLembrete() {
        return lembrete;
    }

    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }

    public Evento(String nome, String comeca, int id, String local, String duracao, String lembrete) {

        this.nome = nome;
        this.comeca = comeca;
        this.id = id;
        this.local = local;
        this.duracao = duracao;
        this.lembrete = lembrete;
    }
    public Evento(){

    }
}
