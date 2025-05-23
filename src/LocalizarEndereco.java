import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LocalizarEndereco {

    public static void localizar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Localizar Endereços ===");
        System.out.print("Informe o código da pessoa: ");
        String codigoPessoa = scanner.nextLine();

        boolean encontrou = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("enderecos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("PessoaCódigo:" + codigoPessoa)) {
                    System.out.println(linha);
                    encontrou = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de endereços: " + e.getMessage());
        }

        if (!encontrou) {
            System.out.println("Nenhum endereço encontrado para o código informado.");
        } else {
            Log.salvar("Localizou endereços da pessoa de código: " + codigoPessoa);
        }
        scanner.close();
    }
}
