import java.io.*;
import java.util.*;

public class EditarPedido {

    public static void editar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Só é possível editar o endereço de entrega do pedido.");
        System.out.print("\nDigite o número do pedido que deseja editar: ");
        int numeroPedido = Integer.parseInt(scanner.nextLine());

        List<String> linhas = new ArrayList<>();
        boolean pedidoEncontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Número do Pedido: " + numeroPedido)) {
                    pedidoEncontrado = true;

                    // Extrair código do cliente atual
                    String[] partes = linha.split(";");
                    int codigoCliente = -1;
                    for (String parte : partes) {
                        if (parte.trim().startsWith("Código do Cliente:")) {
                            codigoCliente = Integer.parseInt(parte.split(":")[1].trim());
                            break;
                        }
                    }

                    if (codigoCliente == -1) {
                        System.out.println("Não foi possível identificar o cliente do pedido.");
                        return;
                    }

                    System.out.print("Digite o novo CEP de entrega: ");
                    String novoCep = scanner.nextLine().trim();

                    if (!Util.enderecoExistePorCEP(codigoCliente, novoCep)) {
                        System.out.println("Novo CEP inválido para esse cliente.");
                        return;
                    }

                    // Atualiza o CEP na linha
                    String linhaAtualizada = "";
                    for (String parte : partes) {
                        if (parte.trim().startsWith("CEP de Entrega:")) {
                            linhaAtualizada += "CEP de Entrega: " + novoCep + ";";
                        } else {
                            linhaAtualizada += parte + ";";
                        }
                    }

                    // Remove último ponto e vírgula, se necessário
                    if (linhaAtualizada.endsWith(";")) {
                        linhaAtualizada = linhaAtualizada.substring(0, linhaAtualizada.length() - 1);
                    }

                    linhas.add(linhaAtualizada);
                    System.out.println("CEP atualizado com sucesso!");

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

        // Reescreve o arquivo com as alterações
        try (PrintWriter writer = new PrintWriter(new FileWriter("pedidos.txt"))) {
            for (String l : linhas) {
                writer.println(l);
            }
            Log.salvar("Endereço de entrega editado para o pedido nº " + numeroPedido);
        } catch (IOException e) {
            System.out.println("Erro ao salvar alterações: " + e.getMessage());
        }
    }
}
