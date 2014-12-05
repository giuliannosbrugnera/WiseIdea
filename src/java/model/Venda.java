package model;

public class Venda {

    private String id;
    private String idCliente;
    private String dataVenda;
    private float valorVenda;
    private Integer qtdeProd;
    private Cliente cliente;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente( String idCliente ) {
        this.idCliente = idCliente;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda( String dataVenda ) {
        this.dataVenda = dataVenda;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda( float valorVenda ) {
        this.valorVenda = valorVenda;
    }

    public Integer getQtdeProd() {
        return qtdeProd;
    }

    public void setQtdeProd( Integer qtdeProd ) {
        this.qtdeProd = qtdeProd;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente( Cliente cliente ) {
        this.cliente = cliente;
    }

    public String reportData() {
        String reportData = "Venda\n";
        reportData += "Identificador\t" + id;
        reportData += "\nData da venda\t" + dataVenda;
        reportData += "\nValor da venda\t" + valorVenda;
        reportData += "\nQuantidade de produtos\t" + qtdeProd + "\n\n";

        return reportData + cliente.reportData();
    }

}
