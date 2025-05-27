import java.io.*;
import java.util.*;

public class Util {
    public static List<String> lerOpcoesDoArquivo(String nomeArquivo) {
        List<String> opcoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    opcoes.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return opcoes;
    }

    public class PerguntaUtil {

        public static int perguntar(String nomeArquivo, String pergunta) {
            List<String[]> opcoes = carregarOpcoes(nomeArquivo);

            if (opcoes.isEmpty()) {
                System.out.println("Nenhuma opção encontrada no arquivo: " + nomeArquivo);
                return -1;
            }

            Scanner scanner = new Scanner(System.in);
            int escolha;

            while (true) {
                System.out.println("\n" + pergunta);
                for (String[] opcao : opcoes) {
                    System.out.printf("[%s] %s%n", opcao[0], opcao[1]);
                }

                System.out.print("Digite o código da opção desejada: ");
                String entrada = scanner.nextLine().trim();

                for (String[] opcao : opcoes) {
                    if (opcao[0].equals(entrada)) {
                        return Integer.parseInt(opcao[0]);
                    }
                }

                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        public static String retornarDescricao(String nomeArquivo, int codigo) {
            List<String[]> opcoes = carregarOpcoes(nomeArquivo);

            for (String[] opcao : opcoes) {
                if (opcao[0].equals(String.valueOf(codigo))) {
                    return opcao[1];
                }
            }

            return null;
        }

        private static List<String[]> carregarOpcoes(String nomeArquivo) {
            List<String[]> opcoes = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(";", 2);
                    if (partes.length == 2) {
                        opcoes.add(new String[]{partes[0].trim(), partes[1].trim()});
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }

            return opcoes;
        }
    }
}
