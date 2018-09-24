package eFitness.dao.db;

import eFitness.dao.db.ClienteDAOBD;
import eFitness.dao.AlunoDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import eFitness.model.Personal;
import eFitness.model.Aluno;

/**
 *
 * @author ibrum
 */
public class AlunoDAOBD extends DAOBD<Aluno> implements AlunoDAO{
    
    @Override
    public void salvar(Aluno aluno) {
        int id = 0;
        try {
            String sql = "INSERT INTO alunos (nome, tipo, id_cliente)"
                        +"VALUES (?, ? , ?)";
            
            conectarObtendoID(sql);
            
            comando.setString(1, aluno.getNome());
            comando.setString(2, aluno.getTipo());
            comando.setInt(3, aluno.getPersonal().getId());
            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                aluno.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar o aluno no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Aluno aluno) {
        try {
            String sql = "DELETE FROM alunos WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, aluno.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        try {
            String sql = "UPDATE alunos SET nome=?, tipo=?, id_cliente=? "
                    + "WHERE id=?";
            conectar(sql);
            
            comando.setString(1, aluno.getNome());
            comando.setString(2, aluno.getTipo());
            comando.setInt(3, aluno.getPersonal().getId());
            comando.setInt(4, aluno.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar o aluno no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Aluno> listar() {
        List<Aluno> listaAlunos = new ArrayList<>();
        
        String sql = "SELECT * FROM alunos";
        
        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("tipo");
                int id_cliente =  resultado.getInt("id_cliente");
                
                Personal personal = new ClienteDAOBD().procurarPorId(id_cliente);
                Aluno aluno = new Aluno(id, nome, tipo, personal);
                
                listaAlunos.add(aluno);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os alunos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaAlunos);
    }

    @Override
    public Aluno procurarPorId(int id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        
        try {
            conectar(sql);
            comando.setInt(1, id);
            
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("tipo");
                int id_cliente = resultado.getInt("id_cliente");
                Personal personal = new ClienteDAOBD().procurarPorId(id_cliente);
                
                Aluno aluno = new Aluno(id, nome, tipo, personal);
                
                return aluno;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }

    @Override
    public Aluno procurarPorNome(String nome) {
        String slq = "SELECT * FROM alunos WHERE nome = ?";
        
        try {
            conectar(slq);
            comando.setString(1, nome);
            
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                String nome_aluno = resultado.getString("nome");
                String tipo = resultado.getString("tipo");
                int id = resultado.getInt("id");
                
                Personal personal = new ClienteDAOBD().procurarPorId(id);
                
                Aluno aluno = new Aluno(id, nome_aluno, tipo, personal);
                return aluno;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (null);
    }
    
}
