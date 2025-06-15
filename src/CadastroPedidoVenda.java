import java.io.*;
import java.util.*;

    public class CadastroPedidoVenda {

        public static void cadastrarPedido() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Cadastro de Pedido de Venda ===");

            int numeroPedido = Util.gerarNumeroPedido();
            System.out.println("Número do pedido gerado: " + numeroPedido);

            System.out.print("Digite o código do cliente: ");
            int codigoCliente = Integer.parseInt(scanner.nextLine());

            if (!Util.clienteExiste(codigoCliente)) {
                System.out.println("Cliente não encontrado!");
                return;
            }

            System.out.print("Digite o CEP do endereço de entrega: ");
            String cep = scanner.nextLine();

            if (!Util.enderecoExistePorCEP(codigoCliente, cep)) {
                System.out.println("Endereço inválido!");
                return;
            }

            List<Produto> produtos = new ArrayList<>();

            while (true) {
                System.out.print("Digite o código do produto (ou 0 para finalizar): ");
                int codProd = Integer.parseInt(scanner.nextLine());

                if (codProd == 0) break;

                Produto produto = Util.buscarProduto(codProd);
                if (produto != null) {
                    produtos.add(produto);
                    System.out.println("Produto adicionado: " + produto.getDescricao());
                } else {
                    System.out.println("Produto não encontrado!");
                }
            }

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto adicionado. Pedido cancelado.");
                return;
            }

            PedidoVenda pedido = new PedidoVenda(numeroPedido, codigoCliente, cep, produtos);

            try (PrintWriter out = new PrintWriter(new FileWriter("pedidos.txt", true))) {
                out.println(pedido.toString());
                Log.salvar("Pedido de venda cadastrado. Nº: " + numeroPedido + " | Cliente: " + codigoCliente);
                System.out.println("Pedido cadastrado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar pedido: " + e.getMessage());
            }
        }
    }


