package efitness.negocio;

import efitness.dao.db.AlunoDAOBD;
import java.util.List;
import efitness.model.Aluno;
import efitness.dao.AlunoDAO;

/**
 *
 * @author Patrick Moura
 */
public class AlunoNegocio {
    private final AlunoDAO alunoDAO;
    
    public AlunoNegocio(){
        this.alunoDAO = new AlunoDAOBD();
    }
    
    public void salvar(Aluno aluno) throws NegocioException {
        this.validarCamposObrigatorios(aluno);
        this.validarRGExistente(aluno);
        alunoDAO.salvar(aluno);
    }
    
    public List<Aluno> listar(){
        return (alunoDAO.listar());
    }
    
    public void deletar(Aluno aluno) throws NegocioException {
        if (aluno == null || aluno.getRg() == null) {
            throw new NegocioException("Aluno nao existe");
        }
        alunoDAO.deletar(aluno);
    }
    
    public void atualizar(Aluno aluno) throws NegocioException {
        if (aluno == null || aluno.getRg() == null) {
            throw new NegocioException("Aluno nao existe");
        }
        alunoDAO.atualizar(aluno);
    }
    public Aluno procurarPorID(int id) throws NegocioException {
        Aluno aluno = alunoDAO.procurarPorId(id);
        
        if (aluno == null) {
            throw new NegocioException("Aluno nao encontrado");
        }
        return (aluno);
    }
    public Aluno procurarPorRG(String rg) throws NegocioException {
        if (rg == null || rg.isEmpty()) {
            throw new NegocioException("RG nao informado!");
        }
        
        Aluno aluno = alunoDAO.procurarPorRG(rg);
        
        if (aluno == null) {
            throw new NegocioException("Aluno nao encontrado!");
        }
        return (aluno);
    }
    
    public List<Aluno> listarPorNome(String nome) throws NegocioException{
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Nome nao informado!");
        }
        return(alunoDAO.listarPorNome(nome));
    }
    
    public boolean alunoExiste(String rg) {
        Aluno aluno = alunoDAO.procurarPorRG(rg);
        return (aluno != null);
    }
    
    private void validarCamposObrigatorios(Aluno aluno) throws NegocioException {
        if (aluno.getRg() == null || aluno.getRg().isEmpty()) {
            throw new NegocioException("RG nao informado!");
        }
        
        if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new NegocioException("Nome nao informado!");
        }
    }
    
    private void validarRGExistente(Aluno aluno) throws NegocioException {
        if (alunoExiste(aluno.getRg())) {
            throw new NegocioException("RG ja existe");
        }
    }

    public String procurarPorID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
