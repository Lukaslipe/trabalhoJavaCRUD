import java.util.List;
import java.util.Scanner;

public class MenuDinamico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        List<String> menuPrincipal = Util.lerOpcoesDoArquivo("menu.txt");

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            for (String opcao : menuPrincipal) {
                System.out.println(opcao);
            }
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1 -> MenuPessoa.exibir(scanner);
                    case 2 -> MenuEndereco.exibir(scanner);
                    case 0 -> System.out.println("Encerrando o programa...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                escolha = -1;
            }

        } while (escolha != 0);
    }
}
