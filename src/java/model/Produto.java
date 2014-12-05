package model;

public class Produto {

    private Integer id;
    private float valor;
    private String nome;
    private String categoria;
    private Integer limiteValidade;
    private Integer quantidadeEmEstoque;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor( float valor ) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria( String categoria ) {
        this.categoria = categoria;
    }

    public Integer getLimiteValidade() {
        return limiteValidade;
    }

    public void setLimiteValidade( Integer limiteValidade ) {
        this.limiteValidade = limiteValidade;
    }

    public void setQuantidadeEmEstoque( Integer quantidadeEmEstoque ) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public String reportData() {
        String reportData = "Produto\n";
        reportData += "Identificador\t" + id;
        reportData += "\nNome\t" + nome;
        reportData += "\nCategoria\t" + categoria;
        reportData += "\nValor\t" + valor + "\n\n";

        return reportData;
    }

}
