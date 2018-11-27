/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efitness.negocio;

import efitness.dao.MatriculaDAO;
import efitness.dao.db.MatriculaDAOBD;
import efitness.model.Aluno;
import efitness.model.Matricula;
import java.util.List;

/**
 *
 * @author gustavo.fonseca
 */
public class MatriculaNegocio { 
  private final MatriculaDAO matriculaDAO;
    
    public MatriculaNegocio(){
        this.matriculaDAO = new MatriculaDAOBD();
    }
    
    public void salvar(Matricula matricula) throws NegocioException {
        this.validarCamposObrigatorios(matricula);
        this.matriculaDAO.salvar(matricula);
    }
    
    public void deletar(Matricula matricula) throws NegocioException{
        if (matricula == null) {
            throw new NegocioException("Matrícula não existe!");
        }
        this.matriculaDAO.deletar(matricula);
    }
    
    public void atualizar(Matricula matricula) throws NegocioException{
        if (matricula == null || matricula.getAluno() == null) {
            throw new NegocioException("Matrícula não existe!");
        }
        this.matriculaDAO.atualizar(matricula);
    }
    
    public List<Matricula> listar() {
        return (matriculaDAO.listar());
    }
    
    public List<Matricula> listarPorAluno(Aluno aluno) {
        return (matriculaDAO.listarPorAluno(aluno));
    }
    
    private void validarCamposObrigatorios(Matricula matricula) throws NegocioException{
        if (matricula.getAluno() == null) {
            throw new NegocioException("Aluno não informado");
        }
    }   
}
