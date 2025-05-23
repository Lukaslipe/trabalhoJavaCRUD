import java.io.*;
import java.util.*;

public class Util {
    public static List<String> lerOpcoesDoArquivo(String nomeArquivo) {
        List<String> opcoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    opcoes.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return opcoes;
    }
}
