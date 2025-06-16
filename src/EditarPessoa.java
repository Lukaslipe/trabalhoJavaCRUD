import java.io.*;
import java.util.*;

public class EditarPessoa {

    public static void executarEdicao() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome completo da pessoa: ");
        String nomeUsuario = scanner.nextLine().trim();

        Integer codigoParaEditar = Util.buscarCodigoPessoaPorNome(nomeUsuario);
        if (codigoParaEditar == null) {
            System.out.println("Pessoa não encontrada.");
            return;
        }

        File arquivo = new File("pessoas.txt");
        List<String> linhasAtualizadas = new ArrayList<>();
        boolean editou = false;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith(codigoParaEditar + ";")) {
                    String[] partes = linha.split(";", 3);
                    String nomeAtual = partes.length > 1 ? partes[1] : "";
                    String tipoAtual = partes.length > 2 ? partes[2] : "";

                    System.out.println("Pessoa encontrada: " + linha);
                    System.out.print("Digite o novo nome: ");
                    String novoNome = scanner.nextLine().trim();

                    String novaLinha = codigoParaEditar + ";" + novoNome + ";" + tipoAtual;
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
                System.out.println("Nome da pessoa atualizado com sucesso.");
                Log.salvar("Nome editado com sucesso para código: " + codigoParaEditar);
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
