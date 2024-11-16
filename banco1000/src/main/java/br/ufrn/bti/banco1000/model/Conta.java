/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;

public class Conta {
    private String nome;
    private String cliente;
    private int agencia;
    private int numeroConta;
    private String tipo;
    private int senha;
    private double saldo;
    private ArrayList<Movimentacao> movimentacao = new ArrayList<>();


    public Conta(String cliente, int agencia, int numeroConta, String tipo, int senha, double saldo) {
        this.cliente = cliente;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.tipo = tipo;
        this.senha = senha;
        this.saldo = saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumConta() {
        return numeroConta;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        this.movimentacao.add(new Movimentacao("FORMA", this.cliente, "DEPOSITO", valor));
    }

    public void sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            this.movimentacao.add(new Movimentacao("FORMA", this.cliente, "SAQUE", valor));
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void transferir(Conta conta, double valor) {
        if (this.saldo >= valor) {
            this.sacar(valor);
            conta.depositar(valor);
            this.movimentacao.add(new Movimentacao("FORMA", this.cliente, "SAIDA POR TRANSFERENCIA", valor));
            conta.movimentacao.add(new Movimentacao("FORMA", this.cliente, "ENTRADA POR TRANSFERENCIA", valor));
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }
}





