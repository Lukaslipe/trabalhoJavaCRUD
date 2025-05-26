import java.util.List;
import java.util.Scanner;


public class MenuPessoa {

    public static void exibir(Scanner scanner) {  // Recebe o scanner
        int opcao;

        List<String> menuPessoas = Util.lerOpcoesDoArquivo("menu_pessoas.txt");

        do {
            System.out.println("\n--- Menu Pessoas ---");
            for (String item : menuPessoas) {
                System.out.println(item);
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> CadastroPessoa.cadastrarPessoa();
                    case 2 -> LocalizarPessoa.localizar();
                    case 3 -> ExcluirPessoa.excluir();
                    case 4 -> EditarPessoa.executarEdicao();
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
