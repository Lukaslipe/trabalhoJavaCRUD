public class Produto {
    private int codigo;
    private String descricao;
    private double custo;
    private double precoVenda;
    private int codigoFornecedor;

    public Produto(int codigo, String descricao, double custo, double precoVenda, int codigoFornecedor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.custo = custo;
        this.precoVenda = precoVenda;
        this.codigoFornecedor = codigoFornecedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(int codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    @Override
    public String toString() {
        // formato para salvar ou exibir
        return codigo + ";" + descricao + ";" + custo + ";" + precoVenda + ";" + codigoFornecedor;
    }
}
