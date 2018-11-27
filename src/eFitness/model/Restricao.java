package efitness.model;

/**
 *
 * @author Pablo Schlusen
 */

public class Restricao {
    private int id;
    private Aluno aluno;
    private String cid;
    private String causa;
    private String descricao;

    public Restricao(int id, Aluno aluno, String cid, String causa, String descricao) {
        this.id = id;
        this.aluno = aluno;
        this.cid = cid;
        this.causa = causa;
        this.descricao = descricao;
    }
    
      public Restricao(Aluno aluno, String cid, String causa, String descricao) {
        this.aluno = aluno;
        this.cid = cid;
        this.causa = causa;
        this.descricao = descricao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    
}
