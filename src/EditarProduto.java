import java.io.*;
import java.util.*;

public class EditarProduto {

    public static void editar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o código do produto que deseja editar: ");
        String codigoParaEditar = scanner.nextLine().trim();

        File arquivo = new File("produtos.txt");
        List<String> linhasAtualizadas = new ArrayList<>();
        boolean editou = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length < 5) {
                    // linha inválida, só adiciona
                    linhasAtualizadas.add(linha);
                    continue;
                }

                if (dados[0].equals(codigoParaEditar)) {
                    System.out.println("Produto encontrado: " + linha);
                    // Dados atuais
                    String cod = dados[0];
                    String descricao = dados[1];
                    String custo = dados[2];
                    String precoVenda = dados[3];
                    String codFornecedor = dados[4];

                    boolean sairEdicao = false;
                    while (!sairEdicao) {
                        System.out.println("\nO que deseja editar?");
                        System.out.println("1 - Descrição");
                        System.out.println("2 - Custo");
                        System.out.println("3 - Preço de venda");
                        System.out.println("4 - Código do fornecedor");
                        System.out.println("0 - Finalizar edição");
                        System.out.print("Escolha uma opção: ");

                        String opcao = scanner.nextLine();

                        switch (opcao) {
                            case "1" -> {
                                System.out.print("Novo descrição: ");
                                descricao = scanner.nextLine();
                            }
                            case "2" -> {
                                System.out.print("Novo custo (R$): ");
                                custo = scanner.nextLine();
                            }
                            case "3" -> {
                                System.out.print("Novo preço de venda (R$): ");
                                precoVenda = scanner.nextLine();
                            }
                            case "4" -> {
                                System.out.print("Novo código do fornecedor: ");
                                codFornecedor = scanner.nextLine();
                            }
                            case "0" -> sairEdicao = true;
                            default -> System.out.println("Opção inválida.");
                        }
                    }

                    String novaLinha = cod + ";" + descricao + ";" + custo + ";" + precoVenda + ";" + codFornecedor;
                    linhasAtualizadas.add(novaLinha);
                    editou = true;
                } else {
                    linhasAtualizadas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        if (editou) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo))) {
                for (String l : linhasAtualizadas) {
                    pw.println(l);
                }
                System.out.println("Produto com código " + codigoParaEditar + " atualizado com sucesso.");
                Log.salvar("Edição realizada para produto código: " + codigoParaEditar);
            } catch (IOException e) {
                System.out.println("Erro ao salvar alterações: " + e.getMessage());
                Log.salvar("Erro ao salvar após edição de produto: " + e.getMessage());
            }
        } else {
            System.out.println("Produto com código " + codigoParaEditar + " não encontrado.");
            Log.salvar("Tentativa de edição falhou - código não encontrado: " + codigoParaEditar);
        }
    }
}
