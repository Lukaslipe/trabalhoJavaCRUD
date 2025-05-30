import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CadastroProduto {

    public static void cadastrar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro de Produto ===");

        System.out.print("Digite o código do produto: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite a descrição do produto: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite o custo do produto (R$): ");
        double custo = Double.parseDouble(scanner.nextLine());

        System.out.print("Digite o preço de venda do produto (R$): ");
        double precoVenda = Double.parseDouble(scanner.nextLine());

        System.out.print("Digite o código do fornecedor: ");
        int codigoFornecedor = Integer.parseInt(scanner.nextLine());

        Produto produto = new Produto(codigo, descricao, custo, precoVenda, codigoFornecedor);

        try (PrintWriter out = new PrintWriter(new FileWriter("produtos.txt", true))) {
            out.println(produto.toString());
            System.out.println("Produto cadastrado com sucesso!");
            Log.salvar("Cadastro de produto: " + descricao);
        } catch (IOException e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
    }
}
