import java.util.List;

    public class PedidoVenda {
        private int numeroPedido;
        private int codigoCliente;
        private String codigoEnderecoEntrega;
        private List<Produto> produtos;
        private double montanteTotal;

        public PedidoVenda(int numeroPedido, int codigoCliente, String codigoEnderecoEntrega, List<Produto> produtos) {
            this.numeroPedido = numeroPedido;
            this.codigoCliente = codigoCliente;
            this.codigoEnderecoEntrega = codigoEnderecoEntrega;
            this.produtos = produtos;
            this.montanteTotal = calcularMontanteTotal();
        }

        private double calcularMontanteTotal() {
            double total = 0.0;
            for (Produto produto : produtos) {
                total += produto.getPrecoVenda(); // Aqui assume-se 1 unidade por produto
            }
            return total;
        }

        public int getNumeroPedido() {
            return numeroPedido;
        }

        public int getCodigoCliente() {
            return codigoCliente;
        }

        public List<Produto> getProdutos() {
            return produtos;
        }

        public double getMontanteTotal() {
            return montanteTotal;
        }

        @Override
        public String toString() {
            String produtosStr = "";
            for (Produto produto : produtos) {
                produtosStr += produto.getDescricao() + " (R$" + produto.getPrecoVenda() + "), ";
            }
            if (!produtosStr.isEmpty()) {
                produtosStr = produtosStr.substring(0, produtosStr.length() - 2); // remove a última vírgula
            }

            return "Número do Pedido: " + numeroPedido +";"+
                    "Código do Cliente: " + codigoCliente +";"+
                    "CEP de Entrega: " + codigoEnderecoEntrega +";"+
                    "Produtos: " + produtosStr +";"+
                    "Montante Total: R$" + montanteTotal;
        }

    }

