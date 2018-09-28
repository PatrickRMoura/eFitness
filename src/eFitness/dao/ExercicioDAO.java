package eFitness.dao;

import java.util.List;
import eFitness.model.Exercicio;

/**
 *
 * @author Patrick Moura
 */
public interface ExercicioDAO extends DAO<Exercicio>{    
    public Exercicio procurarPorNome(String nome); 
}
