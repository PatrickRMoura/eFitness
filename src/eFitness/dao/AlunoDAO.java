package eFitness.dao;

import eFitness.model.Aluno;

/**
 *
 * @author Patrick Moura
 */
public interface AlunoDAO extends DAO<Aluno> {

    public Aluno procurarPorNome(String nome);
    
}
