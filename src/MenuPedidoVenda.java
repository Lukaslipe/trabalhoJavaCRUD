import java.util.List;
import java.util.Scanner;

public class MenuPedidoVenda {

    public static void exibir(Scanner scanner) {
        int opcao;

        List<String> menuPedidos = Util.lerOpcoesDoArquivo("menu_pedido_venda.txt");

        do {
            System.out.println("\n--- Menu Pedido de Venda ---");
            for (String item : menuPedidos) {
                System.out.println(item);
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> CadastroPedidoVenda.cadastrarPedido();
                    case 2 -> LocalizarPedido.localizar();
                    case 3 -> EditarPedido.editar();
                    case 4 -> ExcluirPedido.excluir();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                opcao = -1;
            }

        } while (opcao != 0);
    }
}
