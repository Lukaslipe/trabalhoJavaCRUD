import java.io.*;
import java.util.*;

public class LocalizarPedido {

    public static void localizar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome completo do cliente: ");
        String nomeCliente = scanner.nextLine().trim();

        List<Integer> codigosClientes = Util.buscarCodigosClientePorNome(nomeCliente);

        if (codigosClientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado com esse nome.");
            return;
        }

        boolean encontrou = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                for (int codigo : codigosClientes) {
                    if (linha.contains("Código do Cliente: " + codigo + ";")) {
                        System.out.println("\n--- Pedido Encontrado ---");
                        System.out.println(linha);
                        encontrou = true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pedidos: " + e.getMessage());
        }

        if (!encontrou) {
            System.out.println("Nenhum pedido encontrado para esse cliente.");
        }
    }


}
