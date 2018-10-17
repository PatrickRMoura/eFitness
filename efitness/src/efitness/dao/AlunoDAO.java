package efitness.dao;

import java.util.List;
import efitness.model.Aluno;

/**
 *
 * @author Patrick Moura
 */
public interface AlunoDAO extends DAO<Aluno>{
    public Aluno procurarPorRG(String rg);
    public List<Aluno> listarPorNome(String nome); 
}
