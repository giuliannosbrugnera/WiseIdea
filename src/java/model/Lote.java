package model;

public class Lote {

    private Integer id;
    private Integer id_produto;
    private Integer qtdeProd;
    private String validade;
    private String dataEntrada;
    private String dataFabricacao;
    private Produto produto;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto( Integer id_produto ) {
        this.id_produto = id_produto;
    }

    public Integer getQtdeProd() {
        return qtdeProd;
    }

    public void setQtdeProd( Integer qtdeProd ) {
        this.qtdeProd = qtdeProd;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade( String validade ) {
        this.validade = validade;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada( String dataEntrada ) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao( String dataFabricacao ) {
        this.dataFabricacao = dataFabricacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto( Produto produto ) {
        this.produto = produto;
    }

    public String reportData() {
        String reportData = "Lote\n";
        reportData += "Identificador\t" + id;
        reportData += "\nQuantidade do produto\t" + qtdeProd;
        reportData += "\nValidade\t" + validade;
        reportData += "\nData de entrada\t" + dataEntrada;
        reportData += "\nData de fabricação\t" + dataFabricacao + "\n\n";

        return reportData;
    }
}
