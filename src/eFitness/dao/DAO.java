package eFitness.dao;

import java.util.List;

/**
 *
 * @author Patrick Moura
 */
public interface DAO<T> {
    public void salvar(T dominio);
    public void deletar(T dominio);
    public void atualizar(T dominio);
    public List<T> listar();
    public T procurarPorId(int id);
    
}
