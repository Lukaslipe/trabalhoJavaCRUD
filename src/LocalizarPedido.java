import java.io.*;
import java.util.Scanner;

public class LocalizarPedido {

    public static void localizar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome completo do cliente: ");
        String nomeCliente = scanner.nextLine().trim();

        Integer codigoCliente = Util.buscarCodigoClientePorNome(nomeCliente);

        if (codigoCliente == null) {
            System.out.println("Nenhum cliente encontrado com esse nome.");
            return;
        }

        boolean encontrou = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linha;
            String codigoBusca = "Código do Cliente: " + codigoCliente + ";";

            while ((linha = reader.readLine()) != null) {
                if (linha.contains(codigoBusca)) {
                    System.out.println("\n--- Pedido Encontrado ---");
                    System.out.println(linha);
                    encontrou = true;
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
