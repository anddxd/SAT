package com.salveaterra.salveaterra.model;

/**
 * Created by Andrey Medeiros on 05/05/2016.
 */
abstract class Animal {
    protected String nome;
    protected int fome;
    protected int sede;
    protected int idade;
    protected int genero;
    protected int energia;
    protected int felicidade;
    protected int tamanho;
    protected int quantidadeDeComidaConsumida;
    protected int quantidadeDeAguaConsumida;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFome() {
        return fome;
    }

    public void setFome(int fome) {
        this.fome = fome;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getFelicidade() {
        return felicidade;
    }

    public void setFelicidade(int felicidade) {
        this.felicidade = felicidade;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidadeDeComidaConsumida() {
        return quantidadeDeComidaConsumida;
    }

    public void setQuantidadeDeComidaConsumida(int quantidadeDeComidaConsumida) {
        this.quantidadeDeComidaConsumida = quantidadeDeComidaConsumida;
    }

    public int getQuantidadeDeAguaConsumida() {
        return quantidadeDeAguaConsumida;
    }

    public void setQuantidadeDeAguaConsumida(int quantidadeDeAguaConsumida) {
        this.quantidadeDeAguaConsumida = quantidadeDeAguaConsumida;
    }

    public void digerirAlimento() {
        if (getFome() != 0)
            this.setFome(getFome() - 1);
    }

    public void absorverAgua() {
        if (getSede() != 0)
            this.setSede(getSede() - 1);
    }

    abstract void andar(Boolean b);

    abstract void dormir(Boolean b);

    abstract void comer(Boolean b);

    abstract void emitirSom();

    abstract void reproduzir(Animal a);
}
