package efitness.dao;

import efitness.model.Aluno;
import java.util.List;
import efitness.model.Restricao;

/**
 *
 * @author Pablo Schlusen
 */


public interface RestricaoDAO extends DAO<Restricao>{
    public Restricao procurarPorAluno(Aluno aluno);
    public List<Restricao> listarPorAluno(Aluno aluno); 
}
