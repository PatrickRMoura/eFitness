package efitness.model;

/**
 *
 * @author Pablo Schlusen
 */

import java.util.Date;

public class Treino {
    private int id;
    private Aluno aluno;
    private Date data;
    private String objetivo;

    public Treino(int id, Aluno aluno, Date data, String objetivo) {
        this.id = id;
        this.aluno = aluno;
        this.data = data;
        this.objetivo = objetivo;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

  
    
}
