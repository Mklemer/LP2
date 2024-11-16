package br.ufrn.bti.banco1000;

import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Conta;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);

        Cliente clienteAutenticado = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Senha: ");
                    int senha = scanner.nextInt();
                    banco.cadastrarCliente(nome, cpf, email, telefone, senha);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Informe seu CPF: ");
                    String cpfLogin = scanner.nextLine();
                    System.out.print("Informe sua senha: ");
                    int senhaLogin = scanner.nextInt();

                    clienteAutenticado = banco.login(cpfLogin, senhaLogin);

                    if (clienteAutenticado != null) {
                        System.out.println("Login bem-sucedido!");
                    } else {
                        System.out.println("CPF ou senha incorretos.");
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }

            if (clienteAutenticado != null) {
                if (clienteAutenticado.getConta() == null) {
                    System.out.println("Você não tem uma conta. Vamos criar uma agora.");
                    System.out.print("Informe a agência: ");
                    int agencia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Informe o tipo de conta (corrente/poupanca): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Informe o saldo inicial: ");
                    double saldoInicial = scanner.nextDouble();
                    System.out.print("Informe a senha da conta: ");
                    int senhaConta = scanner.nextInt();
                    banco.cadastrarConta(clienteAutenticado, agencia, tipo, saldoInicial, senhaConta);
                }

                while (true) {
                    System.out.println("\nMenu de Conta:");
                    System.out.println("1. Consultar saldo");
                    System.out.println("2. Realizar depósito");
                    System.out.println("3. Realizar saque");
                    System.out.println("4. Realizar transferência");
                    System.out.println("5. Sair");
                    System.out.print("Escolha uma opção: ");
                    int opcaoConta = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoConta) {
                        case 1:

                            if (clienteAutenticado.getConta() != null) {
                                System.out.println("Saldo: R$ " + clienteAutenticado.getConta().getSaldo());
                            }
                            break;

                        case 2:

                            System.out.print("Valor do depósito: ");
                            double valorDeposito = scanner.nextDouble();
                            scanner.nextLine();
                            if (clienteAutenticado.getConta() != null) {
                                clienteAutenticado.getConta().depositar(valorDeposito);
                                System.out.println("Depósito realizado com sucesso!");
                            }
                            break;

                        case 3:

                            System.out.print("Valor do saque: ");
                            double valorSaque = scanner.nextDouble();
                            scanner.nextLine();
                            if (clienteAutenticado.getConta() != null) {
                                clienteAutenticado.getConta().sacar(valorSaque);
                            }
                            break;

                        case 4:

                            System.out.print("Informe o número da conta destino: ");
                            int numContaDestino = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Informe o valor da transferência: ");
                            double valorTransferencia = scanner.nextDouble();
                            scanner.nextLine();
                            Conta contaDestino = banco.buscarConta(clienteAutenticado.getConta().getAgencia(), numContaDestino);
                            if (contaDestino != null) {
                                clienteAutenticado.getConta().transferir(contaDestino, valorTransferencia);
                                System.out.println("Transferência realizada com sucesso!");
                            } else {
                                System.out.println("Conta destino não encontrada.");
                            }
                            break;

                        case 5:

                            System.out.println("Saindo...");
                            return;

                        default:
                            System.out.println("Opção inválida.");
                    }
                }
            }
        }
    }
}






