import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LocalizarProduto {

    public static void localizar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome ou parte do nome do produto: ");
        String termoBusca = scanner.nextLine().trim();

        List<String> resultados = Util.buscarProdutosPorNome(termoBusca);

        if (resultados.isEmpty()) {
            System.out.println("Nenhum produto encontrado com o termo: " + termoBusca);
        } else {
            System.out.println("Produtos encontrados:");
            for (String linha : resultados) {
                String[] dados = linha.split(";");
                System.out.println("Código: " + dados[0]);
                System.out.println("Descrição: " + dados[1]);
                System.out.printf("Custo: R$ %.2f%n", Double.parseDouble(dados[2]));
                System.out.printf("Preço de Venda: R$ %.2f%n", Double.parseDouble(dados[3]));
                System.out.println("Código do Fornecedor: " + dados[4]);
                System.out.println("-----");
            }
        }
    }
}
