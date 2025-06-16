import java.io.*;
import java.util.*;

public class ExcluirPedido {

    public static void excluir() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número do pedido que deseja excluir (5 dígitos): ");
        String entrada = scanner.nextLine().trim();

        if (!entrada.matches("\\d{5}")) {
            System.out.println("Número de pedido inválido. Deve conter exatamente 5 dígitos.");
            return;
        }

        int numeroPedido = Integer.parseInt(entrada);
        List<String> linhas = new ArrayList<>();
        boolean pedidoEncontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Número do Pedido: " + numeroPedido)) {
                    pedidoEncontrado = true;
                    System.out.println("Pedido encontrado e removido.");
                    Log.salvar("Pedido de venda excluído. Nº: " + numeroPedido);
                    // Não adiciona essa linha — será excluída
                } else {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pedidos.txt: " + e.getMessage());
            return;
        }

        if (!pedidoEncontrado) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        // Reescreve o arquivo sem o pedido excluído
        try (PrintWriter writer = new PrintWriter(new FileWriter("pedidos.txt"))) {
            for (String l : linhas) {
                writer.println(l);
            }
            System.out.println("Pedido excluído com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar alterações no arquivo: " + e.getMessage());
        }
    }
}
