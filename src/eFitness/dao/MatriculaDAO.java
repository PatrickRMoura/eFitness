/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efitness.dao;

import efitness.model.Aluno;
import efitness.model.Matricula;
import java.util.List;

/**
 *
 * @author gustavo.fonseca
 */
public interface MatriculaDAO extends DAO<Matricula>{      
    public List<Matricula> listarPorAluno(Aluno aluno); 
}
