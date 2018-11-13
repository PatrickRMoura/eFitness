package efitness.dao;

import efitness.model.Aluno;
import java.util.List;
import efitness.model.Treino;

/**
 *
 * @author Pablo Schlusen
 */
public interface TreinoDAO extends DAO<Treino>{
    public Treino procurarPorAluno(Aluno aluno);
    public List<Treino> listarPorAluno(Aluno aluno); 
}
