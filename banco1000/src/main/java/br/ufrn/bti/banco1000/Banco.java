/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufrn.bti.banco1000;

import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Conta;

import java.util.ArrayList;

/**
 *
 * @author vinicius
 */
public class Banco {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Conta> contas = new ArrayList<>();
    private static int contadorContas = 0;

    public static int gerarNumeroConta() {
        contadorContas++;
        return contadorContas;
    }


    public void cadastrarCliente(String nome, String cpf, String email, String telefone, int senha) {
        Cliente cliente = new Cliente(nome, cpf, email, telefone, senha);
        clientes.add(cliente);
    }

    public void cadastrarConta(Cliente cliente, int agencia, String tipo, double saldoInicial, int senha) {
        if (cliente == null) {
            System.out.println("Cliente não autenticado.");
            return;
        }

        Conta novaConta = new Conta(cliente.getNome(), agencia, gerarNumeroConta(), tipo, senha, saldoInicial);
        cliente.setConta(novaConta);
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso!");
    }


    public Cliente autenticarUsuario(String cpf, int senha) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                if (cliente.getConta().getSenha() == senha) {
                    return cliente;
                }
            }
        }
        return null;
    }


    public Conta buscarConta(int agencia, int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getAgencia() == agencia && conta.getNumConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public Cliente login(String cpfLogin, int senhaLogin) {
        for (Cliente cliente : clientes) {
            System.out.println("Verificando CPF: " + cpfLogin + " com " + cliente.getCpf());
            if (cliente.getCpf().equals(cpfLogin)) {
                System.out.println("CPF encontrado. Verificando senha...");
                if (cliente.getSenha() == senhaLogin) {
                    System.out.println("Senha correta!");
                    return cliente;
                } else {
                    System.out.println("Senha incorreta!");
                    return null;
                }
            }
        }
        System.out.println("Cliente não encontrado!");
        return null;
    }
}
