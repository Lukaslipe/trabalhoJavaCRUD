import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LocalizarProduto {

    public static void localizar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o código do produto que deseja localizar: ");
        String codigoBuscado = scanner.nextLine();

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5 && dados[0].equals(codigoBuscado)) {
                    System.out.println("Produto encontrado:");
                    System.out.println("Código: " + dados[0]);
                    System.out.println("Descrição: " + dados[1]);
                    System.out.printf("Custo: R$ %.2f%n", Double.parseDouble(dados[2]));
                    System.out.printf("Preço de Venda: R$ %.2f%n", Double.parseDouble(dados[3]));
                    System.out.println("Código do Fornecedor: " + dados[4]);
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de produtos: " + e.getMessage());
        }

        if (!encontrado) {
            System.out.println("Produto com código " + codigoBuscado + " não encontrado.");
        }
    }
}
