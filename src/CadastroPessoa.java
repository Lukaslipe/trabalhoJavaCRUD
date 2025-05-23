import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CadastroPessoa {

    public static void cadastrarPessoa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o código da pessoa: ");
        int cod = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o tipo da pessoa (Física ou Jurídica): ");
        String tipo = scanner.nextLine();

        Pessoa pessoa = new Pessoa(cod, nome, tipo);

        try (PrintWriter out = new PrintWriter(new FileWriter("pessoas.txt", true))) {
            out.println(pessoa.getCod() + ";" + pessoa.getNome() + ";" + pessoa.getTipoPessoa());
            Log.salvar("Cadastro de pessoa: " + pessoa.getNome());
            System.out.println("Pessoa cadastrada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar pessoa: " + e.getMessage());
        }
    }
}
