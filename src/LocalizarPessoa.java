import java.util.Scanner;

public class LocalizarPessoa {

    public static void localizar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine().trim();

        Integer codigo = Util.buscarCodigoPessoaPorNome(nome);

        if (codigo == null) {
            System.out.println("Pessoa não encontrada.");
        } else {
            Util.buscarUsuarioPorID(codigo);
        }
    }
}
