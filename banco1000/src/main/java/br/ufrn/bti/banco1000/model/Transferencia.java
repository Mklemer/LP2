/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

/**
 *
 * @author vinicius
 */
public class Transferencia {
    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;

    public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        if (valor <= 0 || contaOrigem.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente ou valor inválido.");
        }
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        executar();
    }

    private void executar() {
        contaOrigem.transferir(contaDestino, valor);
    }

    @Override
    public String toString() {
        return "Transferência [Origem: " + contaOrigem.getNumConta() + ", Destino: " + contaDestino.getNumConta() + ", Valor: R$ " + valor + "]";
    }
}

