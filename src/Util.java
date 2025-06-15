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

    public static boolean clienteExiste(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == codigo && dados[2].equalsIgnoreCase("Cliente")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar cliente.");
        }
        return false;
    }

    public static boolean enderecoExistePorCEP(int codigoCliente, String cep) {
        try (BufferedReader reader = new BufferedReader(new FileReader("enderecos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Exemplo de linha:
                // CódigoPessoa:123; CEP:83010390; Logradouro:Rua maria; Número:668; Complemento:Casa; Tipo:Residencial
                if (linha.contains("CódigoPessoa:" + codigoCliente) && linha.contains("CEP:" + cep)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar endereço.");
        }
        return false;
    }


    public static Produto buscarProduto(int codigoProduto) {
        try (BufferedReader reader = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == codigoProduto) {
                    int codigo = Integer.parseInt(dados[0]);
                    String descricao = dados[1];
                    double custo = Double.parseDouble(dados[2]);
                    double precoVenda = Double.parseDouble(dados[3]);
                    int codFornecedor = Integer.parseInt(dados[4]);
                    return new Produto(codigo, descricao, custo, precoVenda, codFornecedor);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar produto.");
        }
        return null;
    }

    public static int gerarNumeroPedido() {
        int max = 9999;
        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int num = Integer.parseInt(partes[0]);
                if (num > max) max = num;
            }
        } catch (IOException e) {
            // Arquivo ainda não existe ou erro de leitura
        }
        return max + 1;
    }

    public static List<Integer> buscarCodigosClientePorNome(String nomeBuscado) {
        List<Integer> codigos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3 && dados[2].equalsIgnoreCase("Cliente")) {
                    String nomeLido = dados[1].trim();
                    if (nomeLido.equalsIgnoreCase(nomeBuscado)) {
                        try {
                            codigos.add(Integer.parseInt(dados[0]));
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter código de cliente: " + dados[0]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pessoas.txt: " + e.getMessage());
        }

        return codigos;
    }

    public static List<Integer> buscarCodigosFornecedorPorNome(String nomeBuscado) {
        List<Integer> codigos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3 && dados[2].equalsIgnoreCase("Fornecedor")) {
                    String nomeLido = dados[1].trim();
                    if (nomeLido.equalsIgnoreCase(nomeBuscado)) {
                        try {
                            codigos.add(Integer.parseInt(dados[0]));
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter código de fornecedor: " + dados[0]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pessoas.txt: " + e.getMessage());
        }

        return codigos;
    }

}
