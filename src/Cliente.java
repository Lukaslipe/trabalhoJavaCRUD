public class Cliente extends Pessoa {
    private String cpf;

    public Cliente(int cod, String nome, String cpf) {
        super(cod, nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return super.toString() + ";Cliente;" + cpf;
    }
}
