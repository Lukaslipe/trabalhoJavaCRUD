import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CadastroEndereco {

    public static void cadastrarEndereco() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro de Endereço ===");

        System.out.print("Informe o código da pessoa: ");
        String codigoPessoa = scanner.nextLine();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();

        System.out.print("Número: ");
        String numero = scanner.nextLine();

        System.out.print("Complemento: ");
        String complemento = scanner.nextLine();

        System.out.print("Tipo de endereço (residencial, comercial, etc.): ");
        String tipo = scanner.nextLine();

        // Criação do texto a ser salvo no arquivo de endereços
        String enderecoFormatado = String.format(
                "PessoaCódigo:%s; CEP:%s; Logradouro:%s; Número:%s; Complemento:%s; Tipo:%s",
                codigoPessoa, cep, logradouro, numero, complemento, tipo
        );

        // Salvando no arquivo de endereços
        try (PrintWriter writer = new PrintWriter(new FileWriter("enderecos.txt", true))) {
            writer.println(enderecoFormatado);
            System.out.println("Endereço cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar endereço: " + e.getMessage());
        }

        // Salvando no log
        Log.salvar("Endereço cadastrado para pessoa de código: " + codigoPessoa);
    }
}
