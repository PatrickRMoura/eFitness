/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efitness.dao;

import efitness.model.Avaliacao;
import java.util.List;
import efitness.model.Aluno;
/**
 *
 * @author gustavo.fonseca
 */
public interface AvaliacaoDAO extends DAO<Avaliacao>{   
    public List<Avaliacao> listarPorAluno(Aluno aluno); 
}