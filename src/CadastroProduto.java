import java.io.*;
import java.util.Scanner;

public class CadastroProduto {

    public static void cadastrar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro de Produto ===");

        int cod = Util.gerarNumeroProduto();
        System.out.println("Código gerado automaticamente: " + cod);

        System.out.print("Digite a descrição do produto: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite o custo do produto (R$): ");
        double custo = Double.parseDouble(scanner.nextLine());

        System.out.print("Digite o preço de venda do produto (R$): ");
        double precoVenda = Double.parseDouble(scanner.nextLine());

        int codigoFornecedor;
        while (true) {
            System.out.print("Digite o nome completo do fornecedor: ");
            try {
                String nomeFornecedor = scanner.nextLine();
                codigoFornecedor = Util.buscarCodigoFornecedorPorNome(nomeFornecedor);
                if (Util.fornecedorExiste(codigoFornecedor)) {
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

}
