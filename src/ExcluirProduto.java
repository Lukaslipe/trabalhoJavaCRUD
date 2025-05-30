import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcluirProduto {

    public static void excluir() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o código do produto que deseja excluir: ");
        String codigoParaExcluir = scanner.nextLine();

        List<String> linhasAtualizadas = new ArrayList<>();
        boolean encontrado = false;
        String descricaoExcluida = "";

        try (BufferedReader br = new BufferedReader(new FileReader("produtos.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5 && dados[0].equals(codigoParaExcluir)) {
                    encontrado = true;
                    descricaoExcluida = dados[1];
                    continue; // pula essa linha para excluir
                }
                linhasAtualizadas.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        if (encontrado) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("produtos.txt"))) {
                for (String linha : linhasAtualizadas) {
                    writer.println(linha);
                }
                Log.salvar("Produto excluído: " + descricaoExcluida);
                System.out.println("Produto excluído com sucesso.");
            } catch (IOException e) {
                System.out.println("Erro ao salvar arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Produto com código " + codigoParaExcluir + " não encontrado.");
        }
    }
}
