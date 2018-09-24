package eFitness.dao;

import java.util.List;
import eFitness.model.Personal;

/**
 *
 * @author Patrick Moura
 */
public interface ClienteDAO extends DAO<Personal>{
    public Personal procurarPorRG(String rg);
    public List<Personal> listarPorNome(String nome); 
}
