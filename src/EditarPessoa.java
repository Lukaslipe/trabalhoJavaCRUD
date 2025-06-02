import java.io.*;
import java.util.*;

public class EditarPessoa {

    public static void executarEdicao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o código da pessoa que deseja editar: ");
        String codigoParaEditar = scanner.nextLine().trim();

        File arquivo = new File("pessoas.txt");
        List<String> linhasAtualizadas = new ArrayList<>();
        boolean editou = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith(codigoParaEditar + ";")) {
                    // Pessoa encontrada
                    System.out.println("Pessoa encontrada: " + linha);

                    String[] partes = linha.split(";", 3);
                    String nomeAtual = partes.length > 1 ? partes[1] : "";
                    String tipoAtual = partes.length > 2 ? partes[2] : "";

                    String novoNome = nomeAtual;
                    String novoTipo = tipoAtual;

                    int escolha = Util.PerguntaUtil.perguntar("opcoes_editar_pessoa.txt", "O que você deseja editar?");

                    if (escolha == 0) {
                        System.out.println("Edição cancelada.");
                        Log.salvar("Edição cancelada pelo usuário para o código: " + codigoParaEditar);
                        return;
                    }

                    if (escolha == 1 || escolha == 3) {
                        System.out.print("Novo nome: ");
                        novoNome = scanner.nextLine();
                    }

                    if (escolha == 2 || escolha == 3) {
                        System.out.print("Novo tipo de pessoa (Física/Jurídica): ");
                        novoTipo = scanner.nextLine();
                    }

                    String novaLinha = codigoParaEditar + ";" + novoNome + ";" + novoTipo;
                    linhasAtualizadas.add(novaLinha);
                    editou = true;

                } else {
                    linhasAtualizadas.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            Log.salvar("Erro na edição: " + e.getMessage());
            return;
        }

        if (editou) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo))) {
                for (String l : linhasAtualizadas) {
                    pw.println(l);
                }
                System.out.println("Pessoa com código " + codigoParaEditar + " atualizada com sucesso.");
                Log.salvar("Edição realizada para código: " + codigoParaEditar);
            } catch (IOException e) {
                System.out.println("Erro ao salvar alterações: " + e.getMessage());
                Log.salvar("Erro ao salvar após edição: " + e.getMessage());
            }
        } else {
            System.out.println("Pessoa com código " + codigoParaEditar + " não encontrada.");
            Log.salvar("Tentativa de edição falhou - código não encontrado: " + codigoParaEditar);
        }
    }
}
