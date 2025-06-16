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

    public static boolean fornecedorExiste(int codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3) {
                    int cod = Integer.parseInt(dados[0]);
                    String tipo = dados[2];
                    if (cod == codigo && tipo.equalsIgnoreCase("Fornecedor")) {
                        return true;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao verificar fornecedor: " + e.getMessage());
        }
        return false;
    }

    public static boolean enderecoExistePorCEP(int codigoCliente, String cep) {
        try (BufferedReader reader = new BufferedReader(new FileReader("enderecos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("CódigoPessoa:" + codigoCliente) && linha.contains("CEP:" + cep)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao verificar endereço.");
        }
        return false;
    }

    public static int gerarNumeroProduto() {
        int maiorCodigo = 999;
        try (BufferedReader br = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length > 0) {
                    int cod = Integer.parseInt(dados[0]);
                    if (cod > maiorCodigo) {
                        maiorCodigo = cod;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
        }
        return maiorCodigo + 1;
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
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";");
                if (partes.length > 0 && partes[0].startsWith("Número do Pedido:")) {
                    try {
                        String[] campo = partes[0].split(":");
                        int num = Integer.parseInt(campo[1].trim());
                        if (num > max) max = num;
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Número de pedido inválido na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            // Arquivo ainda não existe ou erro de leitura
        }
        return max + 1;
    }


    public static Integer buscarCodigoClientePorNome(String nomeCliente) {
        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3 && dados[2].equalsIgnoreCase("Cliente")) {
                    String nomeLido = dados[1].trim();
                    if (nomeLido.equalsIgnoreCase(nomeCliente)) {
                        try {
                            return Integer.parseInt(dados[0]);
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter código de cliente: " + dados[0]);
                            return null;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pessoas.txt: " + e.getMessage());
        }

        return null; // Nenhum encontrado
    }

    public static Integer buscarCodigoFornecedorPorNome(String nomeFornecedor) {
        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3 && dados[2].equalsIgnoreCase("Fornecedor")) {
                    String nomeLido = dados[1].trim();
                    if (nomeLido.equalsIgnoreCase(nomeFornecedor)) {
                        try {
                            return Integer.parseInt(dados[0]); // Retorna o primeiro que encontrar
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter código de fornecedor: " + dados[0]);
                            return null;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pessoas.txt: " + e.getMessage());
        }

        return null; // Nenhum encontrado
    }

    public static Integer buscarCodigoPessoaPorNome(String nomePessoa) {
        try (BufferedReader reader = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3) {
                    String nomeLido = dados[1].trim();
                    if (nomeLido.equalsIgnoreCase(nomePessoa)) {
                        try {
                            return Integer.parseInt(dados[0]); // Retorna o código
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter código da pessoa: " + dados[0]);
                            return null;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pessoas.txt: " + e.getMessage());
        }

        return null; // Nenhum encontrado
    }

    public static void buscarUsuarioPorID(int codigo) {
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 4 && Integer.parseInt(dados[0]) == codigo) {
                    String tipoPessoa = dados[2];

                    System.out.println("\n--- " + tipoPessoa + " encontrado ---");
                    System.out.println("Código: " + dados[0]);
                    System.out.println("Nome: " + dados[1]);
                    System.out.println("Tipo: " + tipoPessoa);

                    if (tipoPessoa.equalsIgnoreCase("Cliente")) {
                        System.out.println("CPF: " + dados[3]);
                    } else if (tipoPessoa.equalsIgnoreCase("Fornecedor")) {
                        System.out.println("CNPJ: " + dados[3]);
                    } else {
                        System.out.println("Documento: " + dados[3]);
                    }

                    Log.salvar(tipoPessoa + " localizado: " + dados[1]);
                    return;
                }
            }

            System.out.println("Pessoa com código " + codigo + " não encontrada.");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo pessoas.txt: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao interpretar o código: " + e.getMessage());
        }
    }

    public static int gerarCodigoPessoa() {
        int maiorCodigo = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length > 0) {
                    int cod = Integer.parseInt(dados[0]);
                    if (cod > maiorCodigo) {
                        maiorCodigo = cod;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Se der erro ao ler, assume código inicial 0
        }
        return maiorCodigo + 1;
    }

    public static List<String> buscarProdutosPorNome(String termoBusca) {
        List<String> resultados = new ArrayList<>();
        termoBusca = termoBusca.toLowerCase();

        try (BufferedReader br = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 2) {
                    String descricao = dados[1].toLowerCase();
                    if (descricao.contains(termoBusca)) {
                        resultados.add(linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de produtos: " + e.getMessage());
        }

        return resultados;
    }

}
