import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcluirPessoa {

    public static void excluir() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome completo da pessoa: ");
        String nomeUsuario = scanner.nextLine().trim();

        Integer codigoParaExcluir = Util.buscarCodigoPessoaPorNome(nomeUsuario);
        if (codigoParaExcluir == null) {
            System.out.println("Pessoa não encontrada.");
            return;
        }

        // Exibe os dados da pessoa antes de confirmar exclusão
        Util.buscarUsuarioPorID(codigoParaExcluir);

        System.out.print("\nDeseja realmente excluir esta pessoa? (1 = Sim / 0 = Não): ");
        String resposta = scanner.nextLine().trim();

        if (!resposta.equals("1")) {
            System.out.println("Exclusão cancelada.");
            return;
        }

        List<String> linhasAtualizadas = new ArrayList<>();
        boolean encontrada = false;
        String nomeExcluido = "";

        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3 && dados[0].trim().equals(String.valueOf(codigoParaExcluir))) {
                    encontrada = true;
                    nomeExcluido = dados[1];
                    continue; // pula a linha para excluir
                }
                linhasAtualizadas.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        if (encontrada) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("pessoas.txt"))) {
                for (String linha : linhasAtualizadas) {
                    writer.println(linha);
                }
                Log.salvar("Pessoa excluída: " + nomeExcluido);
                System.out.println("Pessoa excluída com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Pessoa com código " + codigoParaExcluir + " não encontrada.");
        }
    }
}
