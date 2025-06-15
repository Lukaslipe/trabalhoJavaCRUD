import java.io.*;
import java.util.Scanner;

public class CadastroProduto {

    public static void cadastrar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro de Produto ===");

        int cod = gerarNovoCodigo();
        System.out.println("Código gerado automaticamente: " + cod);

        System.out.print("Digite a descrição do produto: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite o custo do produto (R$): ");
        double custo = Double.parseDouble(scanner.nextLine());

        System.out.print("Digite o preço de venda do produto (R$): ");
        double precoVenda = Double.parseDouble(scanner.nextLine());

        int codigoFornecedor;
        while (true) {
            System.out.print("Digite o código do fornecedor: ");
            try {
                codigoFornecedor = Integer.parseInt(scanner.nextLine());
                if (fornecedorExiste(codigoFornecedor)) {
                    break;
                } else {
                    System.out.println("Fornecedor não encontrado. Cadastre o fornecedor antes de associá-lo ao produto.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Código inválido. Tente novamente.");
            }
        }

        Produto produto = new Produto(cod, descricao, custo, precoVenda, codigoFornecedor);

        try (PrintWriter out = new PrintWriter(new FileWriter("produtos.txt", true))) {
            out.println(produto.toString());
            System.out.println("Produto cadastrado com sucesso!");
            Log.salvar("Cadastro de produto: " + descricao);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    private static int gerarNovoCodigo() {
        int maiorCodigo = 999;
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

    private static boolean fornecedorExiste(int codigo) {
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

}
