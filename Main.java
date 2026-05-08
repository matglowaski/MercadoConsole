import java.util.Scanner;

public class Mercadinho {

    static String[] itens = {
            "Maca", "Banana", "Pao", "Leite", "Ovos",
            "Queijo", "Arroz", "Frango", "Sabao", "Pasta de Dente",
            "Cafe", "Suco"
    };

    static float[] precos = {
            1.5f, 0.8f, 2.0f, 3.5f, 5.0f,
            7.0f, 4.5f, 10.0f, 2.5f, 3.0f,
            8.0f, 4.0f
    };

    static int[] estoque = {
            50, 50, 20, 30, 40,
            15, 25, 10, 35, 40,
            18, 25
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Bem-vindo ao Mercadinho do Glow ===");

        while (true) {
            System.out.print("\nDigite 'Iniciar' para começar a comprar ou 'Sair' para encerrar: ");
            String comando = sc.nextLine().trim();

            if (comando.equalsIgnoreCase("Sair")) {
                System.out.println("Obrigado pela visita! Volte sempre");
                break;
            } else if (comando.equalsIgnoreCase("Iniciar")) {
                float contaTotal = 0;

                while (true) {
                    System.out.print("\nDigite o nome do item (ou 'Concluir' para finalizar): ");
                    String nomeItem = sc.nextLine().trim();

                    if (nomeItem.equalsIgnoreCase("Concluir")) {
                        contaTotal = aplicarDesconto(contaTotal);
                        System.out.println("\nTotal da Conta: R$" + contaTotal);
                        break;
                    }

                    int indice = buscarItem(nomeItem);

                    if (indice == -1) {
                        System.out.println("Item não encontrado!");
                    } else {
                        System.out.println("Item encontrado: " + itens[indice] + " | Preço: R$" + precos[indice]);
                        System.out.print("Digite a quantidade: ");

                        int quantidade = sc.nextInt();
                        sc.nextLine(); // Limpar o buffer

                        if (quantidade > estoque[indice]) {
                            System.out.println("Estoque insuficiente! Disponível: " + estoque[indice]);
                            continue;
                        }

                        float totalDoItem = precos[indice] * quantidade;
                        contaTotal += totalDoItem;

                        estoque[indice] -= quantidade;

                        System.out.println("Adicionado: " + quantidade + " x " + itens[indice] + " = R$" + totalDoItem);
                    }
                }

                System.out.println("\nMédia de preço de todos os itens: R$" + calcularPrecoMedio());

                System.out.print("Digite um preço para ver itens mais baratos: ");
                float limite = sc.nextFloat();
                sc.nextLine();
                filtrarItensAbaixoDoPreco(limite);
                
            } else {
                System.out.println("Opção inválida. Tente novamente!");
            }
        }

        sc.close();
    }

    public static int buscarItem(String nome) {
        for (int i = 0; i < itens.length; i++) {
            if (itens[i].equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    public static float calcularPrecoMedio() {
        float total = 0;
        for (float preco : precos) {
            total += preco;
        }
        return total / precos.length;
    }

    public static void filtrarItensAbaixoDoPreco(float precoMaximo) {
        System.out.println("Itens mais baratos que R$" + precoMaximo + ":");
        boolean encontrado = false;
        for (int i = 0; i < itens.length; i++) {
            if (precos[i] < precoMaximo) {
                System.out.println("- " + itens[i] + " (R$" + precos[i] + ")");
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("Nenhum item encontrado abaixo deste preço.");
    }

    public static float aplicarDesconto(float total) {
        float desconto = 0;
        if (total > 100) {
            desconto = 0.15f;
        } else if (total > 50) {
            desconto = 0.10f;
        } else if (total > 20) {
            desconto = 0.05f;
        }

        if (desconto > 0) {
            System.out.println("Desconto aplicado: " + (desconto * 100) + "% de desconto!");
            total -= total * desconto;
        } else {
            System.out.println("Nenhum desconto aplicado");
        }

        return total;
    }
}
