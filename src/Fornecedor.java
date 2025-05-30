public class Fornecedor extends Pessoa {
    private String cnpj;

    public Fornecedor(int cod, String nome, String cnpj) {
        super(cod, nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return super.toString() + ";Fornecedor;" + cnpj;
    }
}
