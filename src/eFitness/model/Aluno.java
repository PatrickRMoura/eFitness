package eFitness.model;

import java.util.Objects;

/**
 *
 * @author Patrick Moura
 */
public class Aluno {
    private int id;
    private String nome, tipo;
    private Personal personal;
    
    public Aluno(int id, String nome, String tipo, Personal personal) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.personal = personal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Aluno(String nome, String tipo, Personal personal) {
        this.nome = nome;
        this.tipo = tipo;
        this.personal = personal;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Personal getPersonal() {
        return personal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno outro = (Aluno) obj;
        if (!Objects.equals(this.nome, outro.nome)) {
            return false;
        }
        return true;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
