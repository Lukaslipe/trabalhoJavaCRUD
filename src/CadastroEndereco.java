import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CadastroEndereco {

    public static void cadastrarEndereco() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro de Endereço ===");

        System.out.print("Digite o nome completo do usuário: ");
        String nomeUsuario = scanner.nextLine().trim();

        Integer codigoPessoa = Util.buscarCodigoPessoaPorNome(nomeUsuario);

        if (codigoPessoa == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();

        System.out.print("Número: ");
        String numero = scanner.nextLine();

        System.out.print("Complemento: ");
        String complemento = scanner.nextLine();

        int codigoTipo = Util.PerguntaUtil.perguntar("tipos_endereco.txt", "Escolha o tipo de endereço:");
        String tipoEndereco = Util.PerguntaUtil.retornarDescricao("tipos_endereco.txt", codigoTipo);

        String enderecoFormatado = String.format(
                "CódigoPessoa:%s; CEP:%s; Logradouro:%s; Número:%s; Complemento:%s; Tipo:%s",
                codigoPessoa, cep, logradouro, numero, complemento, tipoEndereco
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter("enderecos.txt", true))) {
            writer.println(enderecoFormatado);
            System.out.println("Endereço cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar endereço: " + e.getMessage());
        }

        Log.salvar("Endereço cadastrado para pessoa de código: " + codigoPessoa);
    }
}
