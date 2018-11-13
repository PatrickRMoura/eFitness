package efitness.model;

/**
 *
 * @author Patrick Moura
 */

public class Aluno {
    private int id;
    private String rg, nome, telefone;

    public Aluno(int id, String rg, String nome, String telefone) {
        this.id = id;
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
    }
    
    public Aluno(String rg, String nome, String telefone) {
        this.rg = rg;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
