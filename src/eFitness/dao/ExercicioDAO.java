package efitness.dao;

import efitness.model.Exercicio;

/**
 *
 * @author Patrick Moura
 */
public interface ExercicioDAO extends DAO<Exercicio> {

    public Exercicio procurarPorNome(String nome);
    
}
