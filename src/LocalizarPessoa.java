import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LocalizarPessoa {

    public static void localizar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o código da pessoa que deseja localizar: ");
        String codigoBuscado = scanner.nextLine();

        boolean encontrada = false;

        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3 && dados[0].equals(codigoBuscado)) {
                    System.out.println("Pessoa encontrada:");
                    System.out.println("Código: " + dados[0]);
                    System.out.println("Nome: " + dados[1]);
                    System.out.println("Tipo: " + dados[2]);
                    Log.salvar("Pessoa localizada: " + dados[1]);
                    encontrada = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de pessoas: " + e.getMessage());
        }

        if (!encontrada) {
            System.out.println("Pessoa com código " + codigoBuscado + " não encontrada.");
        }
    }
}
