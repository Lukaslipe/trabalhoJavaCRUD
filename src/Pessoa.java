public class Pessoa {
    private int cod;
    private String nome;
    private String tipoPessoa;

    public Pessoa(int cod, String nome, String tipoPessoa) {
        this.cod = cod;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public String toString() {
        return cod + ";" + nome + ";" + tipoPessoa;
    }
}
