import java.util.List;
import java.util.Scanner;

public class MenuEndereco {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        List<String> menuEnderecos = Util.lerOpcoesDoArquivo("menu_enderecos.txt");

        do {
            System.out.println("\n--- Menu Endereços ---");
            for (String item : menuEnderecos) {
                System.out.println(item);
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 4 -> ExcluirEndereco.excluir();
                    case 3 -> EditarEndereco.editar();
                    case 2 -> LocalizarEndereco.localizar();
                    case 1 -> CadastroEndereco.cadastrarEndereco();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                opcao = -1;
            }

        } while (opcao != 0);
        scanner.close();
    }
}
