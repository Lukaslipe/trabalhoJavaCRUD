import java.io.*;
import java.util.*;

public class ExcluirEndereco {

    public static void excluir() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Excluir Endereço ===");

        System.out.print("Informe o nome completo da pessoa: ");
        String nomeUsuario = scanner.nextLine().trim();

        Integer codigoPessoa = Util.buscarCodigoPessoaPorNome(nomeUsuario);

        if (codigoPessoa == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Informe o CEP do endereço que deseja excluir: ");
        String cepExcluir = scanner.nextLine();

        File arquivo = new File("enderecos.txt");
        List<String> linhas = new ArrayList<>();
        boolean enderecoEncontrado = false;

        // Lê todas as linhas para a lista, pulando o endereço a ser excluído
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("CódigoPessoa:" + codigoPessoa) && linha.contains("CEP:" + cepExcluir)) {
                    enderecoEncontrado = true;
                    // Não adiciona essa linha para excluir
                } else {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de endereços: " + e.getMessage());
            scanner.close();
            return;
        }

        if (!enderecoEncontrado) {
            System.out.println("Endereço não encontrado para o código e CEP informados.");
            scanner.close();
            return;
        }

        // Reescreve o arquivo sem o endereço excluído
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
            System.out.println("Endereço excluído com sucesso!");
            Log.salvar("Endereço excluído para pessoa de código: " + codigoPessoa + ", CEP: " + cepExcluir);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo de endereços: " + e.getMessage());
        }

    }
}
