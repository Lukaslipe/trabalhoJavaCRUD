import java.io.*;
import java.util.*;

public class EditarEndereco {

    public static void editar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Editar Endereço ===");

        System.out.print("Informe o nome completo da pessoa: ");
        String nomeUsuario = scanner.nextLine().trim();

        Integer codigoPessoa = Util.buscarCodigoPessoaPorNome(nomeUsuario);

        if (codigoPessoa == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.print("Informe o CEP do endereço que deseja editar: ");
        String cepBusca = scanner.nextLine();

        File arquivo = new File("enderecos.txt");
        List<String> linhas = new ArrayList<>();
        boolean enderecoEncontrado = false;

        // Lê todas as linhas do arquivo para memória
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Verifica se essa linha corresponde ao endereço a ser editado
                if (linha.startsWith("CódigoPessoa:" + codigoPessoa) && linha.contains("CEP:" + cepBusca)) {
                    enderecoEncontrado = true;

                    System.out.println("Endereço atual: " + linha);

                    // Solicita os novos dados
                    System.out.print("Novo CEP: ");
                    String novoCep = scanner.nextLine();

                    System.out.print("Novo Logradouro: ");
                    String novoLogradouro = scanner.nextLine();

                    System.out.print("Novo Número: ");
                    String novoNumero = scanner.nextLine();

                    System.out.print("Novo Complemento: ");
                    String novoComplemento = scanner.nextLine();

                    int codigoTipo = Util.PerguntaUtil.perguntar("tipos_endereco.txt", "Escolha o novo tipo de endereço:");
                    String novoTipo = Util.PerguntaUtil.retornarDescricao("tipos_endereco.txt", codigoTipo);

                    // Monta a nova linha do endereço
                    String novoEndereco = String.format(
                            "CódigoPessoa:%s; CEP:%s; Logradouro:%s; Número:%s; Complemento:%s; Tipo:%s",
                            codigoPessoa, novoCep, novoLogradouro, novoNumero, novoComplemento, novoTipo
                    );

                    linhas.add(novoEndereco);
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

        // Reescreve o arquivo com as linhas atualizadas
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
            System.out.println("Endereço atualizado com sucesso!");
            Log.salvar("Endereço editado para pessoa de código: " + codigoPessoa);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo de endereços: " + e.getMessage());
        }
    }
}
