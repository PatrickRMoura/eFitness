package efitness.negocio;

import efitness.dao.db.RestricaoDAOBD;
import java.util.List;
import efitness.model.Restricao;
import efitness.dao.RestricaoDAO;
import efitness.model.Aluno;

/**
 *
 * @author Pablo Schlusen
 */
public class RestricaoNegocio {
    private final RestricaoDAO restricaoDAO;
    
    public RestricaoNegocio(){
        this.restricaoDAO = new RestricaoDAOBD();
    }
    
    public void salvar(Restricao restricao) throws NegocioException {
        this.validarCamposObrigatorios(restricao);
        restricaoDAO.salvar(restricao);
    }
    
    public List<Restricao> listar(){
        return (restricaoDAO.listar());
    }
    
    public void deletar(Restricao restricao) throws NegocioException {
        if (restricao == null || restricao.getId() == 0) {
            throw new NegocioException("Restricao nao existe");
        }
        restricaoDAO.deletar(restricao);
    }
    
    public void atualizar(Restricao restricao) throws NegocioException {
        if (restricao == null || restricao.getId() == 0) {
            throw new NegocioException("Restricao nao existe");
        }
        restricaoDAO.atualizar(restricao);
    }
    public Restricao procurarPorID(int id) throws NegocioException {
        Restricao restricao = restricaoDAO.procurarPorId(id);
        
        if (restricao == null) {
            throw new NegocioException("Restricao nao encontrado");
        }
        return (restricao);
    }
   
     public List<Restricao> listarPorAluno(Aluno aluno) {
        return (restricaoDAO.listarPorAluno(aluno));
    }
    
    
    private void validarCamposObrigatorios(Restricao restricao) throws NegocioException {
        if (restricao.getCid() == null || restricao.getCid().isEmpty()) {
            throw new NegocioException("CID nao informado");
        }
        
        if (restricao.getCausa() == null || restricao.getCausa().isEmpty()) {
            throw new NegocioException("Causa nao informado");
        }
        
          
        if (restricao.getDescricao() == null || restricao.getDescricao().isEmpty()) {
            throw new NegocioException("Descricao nao informado");
        }
        
        
        if (restricao.getAluno() == null) {
            throw new NegocioException("Aluno nao informado!");
        }
    }
    
   

    public String procurarPorID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
