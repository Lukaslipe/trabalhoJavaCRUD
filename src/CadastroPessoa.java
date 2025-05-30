import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CadastroPessoa {

    public static void cadastrarPessoa() {
        Scanner scanner = new Scanner(System.in);

        String escolha;
        // Loop até a escolha ser válida
        do {
            System.out.println("Escolha o tipo de pessoa para cadastrar:");
            System.out.println("1 - Cliente");
            System.out.println("2 - Fornecedor");
            System.out.print("Opção: ");
            escolha = scanner.nextLine().trim();

            if (!escolha.equals("1") && !escolha.equals("2")) {
                System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!escolha.equals("1") && !escolha.equals("2"));

        System.out.print("Digite o código: ");
        int cod = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        Pessoa pessoa;

        if (escolha.equals("1")) {
            System.out.print("Digite o CPF do Cliente: ");
            String cpf = scanner.nextLine();
            pessoa = new Cliente(cod, nome, cpf);
        } else {
            System.out.print("Digite o CNPJ do Fornecedor: ");
            String cnpj = scanner.nextLine();
            pessoa = new Fornecedor(cod, nome, cnpj);
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("pessoas.txt", true))) {
            out.println(pessoa.toString());
            Log.salvar("Cadastro de pessoa: " + pessoa.getNome());
            System.out.println("Pessoa cadastrada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar pessoa: " + e.getMessage());
        }
    }
}
