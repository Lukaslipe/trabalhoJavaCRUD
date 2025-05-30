import java.util.List;
import java.util.Scanner;

public class MenuProduto {

    public static void exibir(Scanner scanner) {
        int opcao;

        List<String> menuProdutos = Util.lerOpcoesDoArquivo("menu_produtos.txt");

        do {
            System.out.println("\n--- Menu Produtos ---");
            for (String item : menuProdutos) {
                System.out.println(item);
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> CadastroProduto.cadastrar();
                    case 2 -> LocalizarProduto.localizar();
                    case 3 -> EditarProduto.editar();
                    case 4 -> ExcluirProduto.excluir();
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
