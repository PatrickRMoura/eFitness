/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efitness.dao.db;

import java.sql.SQLException;
import efitness.dao.MatriculaDAO;
import efitness.model.Aluno;
import efitness.model.Matricula;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo.fonseca
 */
public class MatriculaDAOBD extends DAOBD<Matricula> implements MatriculaDAO {
  
    @Override
    public void salvar(Matricula matricula) {
        int id = 0;
        try {
            String sql = "INSERT INTO matricula (id_aluno, data, valor, periodicidade, vencimento)"
                        +"VALUES (?, ?, ?, ?, ?)";            
            conectarObtendoID(sql);
            
            comando.setInt(1, matricula.getAluno().getId());
            comando.setObject(2, matricula.getData());
            comando.setObject(3, matricula.getValor());
            comando.setInt(4, matricula.getPeriodicidade());
            comando.setObject(5, matricula.getVencimento());            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                matricula.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar matricula no Banco de Dados!");
            throw new BDException(ex);
        } finally {
          fecharConexao();
        }
    }

    @Override
    public void deletar(Matricula matricula) {
        try {
            String sql = "DELETE FROM matricula WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, matricula.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar matricula no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Matricula matricula) {
        try {
            String sql = "UPDATE matricula SET data=?, valor=?, periodicidade=?, vencimento=?"
                    + "WHERE id=?";

            conectar(sql);            
            comando.setObject(1, matricula.getData());
            comando.setBigDecimal(2, matricula.getValor());
            comando.setInt(3, matricula.getPeriodicidade());
            comando.setObject(4, matricula.getVencimento());
            comando.setInt(5, matricula.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar matricula no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Matricula> listar() {
        List<Matricula> listaAvaliacoes = new ArrayList<>();        
        
        String sql = "SELECT * FROM matricula";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String data = resultado.getString("data");
                BigDecimal valor = resultado.getBigDecimal("valor");
                int periodicidade = resultado.getInt("periodicidade");
                String vencimento = resultado.getString("vencimento");
                
                AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
                Matricula m = new Matricula(id, 
                  alunoDAOBD.procurarPorId(id_aluno), 
                  LocalDate.parse(data), 
                  valor, 
                  periodicidade,
                  LocalDate.parse(vencimento));
                
                listaAvaliacoes.add(m);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os alunos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaAvaliacoes);
    }

    @Override
    public Matricula procurarPorId(int id) {
        String sql = "SELECT * FROM matricula WHERE id = ?";        
        
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int _id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String data = resultado.getString("data");
                BigDecimal valor = resultado.getBigDecimal("valor");
                int periodicidade = resultado.getInt("periodicidade");
                String vencimento = resultado.getString("vencimento");
                
                AlunoDAOBD alunoDAOBD = new AlunoDAOBD();         
                Matricula m = new Matricula(id, 
                  alunoDAOBD.procurarPorId(id_aluno), 
                  LocalDate.parse(data), 
                  valor, 
                  periodicidade,
                  LocalDate.parse(vencimento));

                return m;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o matricula pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Matricula> listarPorAluno(Aluno aluno) {
        List<Matricula> listaAvaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM matricula WHERE id_aluno = ?";
         
        try {
            conectar(sql);
            comando.setInt(1, aluno.getId());
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String data = resultado.getString("data");
                BigDecimal valor = resultado.getBigDecimal("valor");
                int periodicidade = resultado.getInt("periodicidade");
                String vencimento = resultado.getString("vencimento");
                    
                AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
                Matricula m = new Matricula(id, 
                  alunoDAOBD.procurarPorId(id_aluno), 
                  LocalDate.parse(data), 
                  valor, 
                  periodicidade,
                  LocalDate.parse(vencimento));
                
                listaAvaliacoes.add(m);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as avaliações do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaAvaliacoes);
    }
}
