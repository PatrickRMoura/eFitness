package eFitness.negocio;

import eFitness.dao.ExercicioDAO;
import eFitness.dao.db.ExercicioDAOBD;
import java.util.List;
import eFitness.model.Exercicio;

/**
 *
 * @author gustavo.fonseca
 */
public class ExercicioNegocio {
    private ExercicioDAO exercicioDAO;
    
    public ExercicioNegocio(){
        this.exercicioDAO = new ExercicioDAOBD();
    }
    
    public void salvar(Exercicio exercicio) throws NegocioException {
        this.validarCamposObrigatorios(exercicio);
        this.exercicioDAO.salvar(exercicio);
    }
    
    public void deletar(Exercicio exercicio) throws NegocioException{
        if (exercicio == null || exercicio.getNome() == null) {
            throw new NegocioException("Exercício não existe!");
        }
        this.exercicioDAO.deletar(exercicio);
    }
    
    public void atualizar(Exercicio exercicio) throws NegocioException{
        if (exercicio == null || exercicio.getNome() == null) {
            throw new NegocioException("Exercício não existe!");
        }
        this.exercicioDAO.atualizar(exercicio);
    }
    
    public List<Exercicio> listar() {
        return (exercicioDAO.listar());
    }
    
    private void validarCamposObrigatorios(Exercicio exercicio) throws NegocioException{
        if (exercicio.getNome() == null || exercicio.getNome().isEmpty()) {
            throw new NegocioException("Nome não informado");
        }
    }
    
    public Exercicio procurarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Nome não informado");
        }
        
        Exercicio exercicio = exercicioDAO.procurarPorNome(nome);
        
        if (exercicio == null) {
            throw new NegocioException("Exercício não encontrado");
        }
        return exercicio;
    }
}
