package model;

public class Cliente {

    private String id;
    private String endereco;
    private String nome;
    private String telefone;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco( String endereco ) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone( String telefone ) {
        this.telefone = telefone;
    }

    public String reportData() {
        String reportData = "Cliente\n";
        reportData += "Identificador\t" + id;
        reportData += "\nNome\t" + nome;
        reportData += "\nEndereço\t" + endereco;
        reportData += "\nTelefone\t" + telefone + "\n\n";

        return reportData;
    }

}
