/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efitness.negocio;
import efitness.dao.db.AvaliacaoDAOBD;
import java.util.List;
import efitness.model.Avaliacao;
import efitness.dao.AvaliacaoDAO;
import efitness.model.Aluno;

/**
 *
 * @author gustavo.fonseca
 */
public class AvaliacaoNegocio {
   private final AvaliacaoDAO avaliacaoDAO;
    
    public AvaliacaoNegocio(){
        this.avaliacaoDAO = new AvaliacaoDAOBD();
    }
    
    public void salvar(Avaliacao avaliacao) throws NegocioException {
        this.validarCamposObrigatorios(avaliacao);
        avaliacaoDAO.salvar(avaliacao);
    }
    
    public List<Avaliacao> listar(){
        return (avaliacaoDAO.listar());
    }
    
    public void deletar(Avaliacao avaliacao) throws NegocioException {
        if (avaliacao == null || avaliacao.getId() == 0) {
            throw new NegocioException("Avaliação não existe");
        }
        avaliacaoDAO.deletar(avaliacao);
    }
    
    public void atualizar(Avaliacao avaliacao) throws NegocioException {
        if (avaliacao == null || avaliacao.getId() == 0) {
            throw new NegocioException("Avaliação não existe");
        }
        avaliacaoDAO.atualizar(avaliacao);
    }
    
    public Avaliacao procurarPorID(int id) throws NegocioException {
        Avaliacao avaliacao = avaliacaoDAO.procurarPorId(id);
        
        if (avaliacao == null) {
            throw new NegocioException("Avaliação não encontrado");
        }
        return (avaliacao);
    }
    
    public List<Avaliacao> listarPorAluno(Aluno aluno) {
        List<Avaliacao> listaAvaliacoes = avaliacaoDAO.listarPorAluno(aluno);
        
        return (listaAvaliacoes);
    }
    
    private void validarCamposObrigatorios(Avaliacao avaliacao) throws NegocioException {
        
    }


    public String procurarPorID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
