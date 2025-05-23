public class Endereco {
    private int codPessoa;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String tipoEndereco;

    public Endereco(int codPessoa, String cep, String logradouro, String numero, String complemento, String tipoEndereco) {
        this.codPessoa = codPessoa;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.tipoEndereco = tipoEndereco;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    @Override
    public String toString() {
        return codPessoa + ";" + cep + ";" + logradouro + ";" + numero + ";" + complemento + ";" + tipoEndereco;
    }

    public static Endereco fromString(String linha) {
        String[] partes = linha.split(";");
        if (partes.length != 6) return null;
        return new Endereco(
                Integer.parseInt(partes[0]),
                partes[1],
                partes[2],
                partes[3],
                partes[4],
                partes[5]
        );
    }
}
