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
                    // Pede novos dados
                    System.out.println("Pessoa encontrada: " + linha);
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Novo tipo de pessoa (Física/Jurídica): ");
                    String novoTipo = scanner.nextLine();

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
            scanner.close();

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
        scanner.close();

    }
}
