package eFitness.negocio;

import eFitness.dao.AlunoDAO;
import eFitness.dao.db.AlunoDAOBD;
import java.util.List;
import eFitness.model.Aluno;

/**
 *
 * @author Patrick Moura
 */
public class AlunoNegocio {
    private AlunoDAO alunoDAO;
    
    public AlunoNegocio(){
        this.alunoDAO = new AlunoDAOBD();
    }
    
    public void salvar(Aluno aluno) throws NegocioException {
        this.validarCamposObrigatorios(aluno);
        this.alunoDAO.salvar(aluno);
    }
    
    public void deletar(Aluno aluno) throws NegocioException{
        if (aluno == null || aluno.getNome() == null) {
            throw new NegocioException("Aluno não existe!");
        }
        this.alunoDAO.deletar(aluno);
    }
    
    public void atualizar(Aluno aluno) throws NegocioException{
        if (aluno == null || aluno.getNome() == null) {
            throw new NegocioException("Aluno não existe!");
        }
        this.alunoDAO.atualizar(aluno);
    }
    
    public List<Aluno> listar() {
        return (alunoDAO.listar());
    }
    
    private void validarCamposObrigatorios(Aluno aluno) throws NegocioException{
        if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new NegocioException("Nome não informado");
        }
    }
    
    public Aluno procurarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Nome não informado");
        }
        
        Aluno aluno = alunoDAO.procurarPorNome(nome);
        
        if (aluno == null) {
            throw new NegocioException("Aluno não encontrado");
        }
        return (aluno);
    }
}
