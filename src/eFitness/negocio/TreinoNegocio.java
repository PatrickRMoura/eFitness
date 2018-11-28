package efitness.negocio;

import efitness.dao.db.TreinoDAOBD;
import java.util.List;
import efitness.model.Treino;
import efitness.dao.TreinoDAO;
import efitness.model.Aluno;

/**
 *
 * @author Pablo Schlusen
 */
public class TreinoNegocio {
    private final TreinoDAO treinoDAO;
    
    public TreinoNegocio(){
        this.treinoDAO = new TreinoDAOBD();
    }
    
    public void salvar(Treino treino) throws NegocioException {
        this.validarCamposObrigatorios(treino);
        treinoDAO.salvar(treino);
    }
    
    public List<Treino> listar(){
        return (treinoDAO.listar());
    }
    
    public void deletar(Treino treino) throws NegocioException {
        if (treino == null || treino.getId() == 0) {
            throw new NegocioException("Treino nao existe");
        }
        treinoDAO.deletar(treino);
    }
    
    public void atualizar(Treino treino) throws NegocioException {
        if (treino == null || treino.getId() == 0) {
            throw new NegocioException("Treino nao existe");
        }
        treinoDAO.atualizar(treino);
    }
    public Treino procurarPorID(int id) throws NegocioException {
        Treino treino = treinoDAO.procurarPorId(id);
        
        if (treino == null) {
            throw new NegocioException("Treino nao encontrado");
        }
        return (treino);
    }
   
    public List<Treino> listarPorAluno(Aluno aluno) {
        return (treinoDAO.listarPorAluno(aluno));
    }
    
    
    
    private void validarCamposObrigatorios(Treino treino) throws NegocioException {
        if (treino.getObjetivo() == null || treino.getObjetivo().isEmpty()) {
            throw new NegocioException("Objetivo nao informado");
        }
        
        if (treino.getAluno() == null) {
            throw new NegocioException("Aluno nao informado!");
        }
    }
    
   

    public String procurarPorID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
