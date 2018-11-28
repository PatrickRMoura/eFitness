package efitness.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import efitness.model.Aluno;
import efitness.dao.TreinoDAO;
import efitness.model.Matricula;
import efitness.model.Treino;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Pablo Schlusen
 */
public class TreinoDAOBD extends DAOBD<Treino> implements TreinoDAO{

    @Override
    public void salvar(Treino treino) {
        int id = 0;
        try {
            String sql = "INSERT INTO treino (id_aluno, data, objetivo)"
                        +"VALUES (?, ?, ?)";
            conectarObtendoID(sql);
            
            comando.setInt(1, treino.getAluno().getId());
            comando.setDate(2, new java.sql.Date(treino.getData().getTime()));
            comando.setString(3, treino.getObjetivo());
            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                treino.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Treino treino) {
        try {
            String sql = "DELETE FROM treino WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, treino.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar aluno no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Treino treino) {
        try {
            String sql = "UPDATE treino SET data=?, objetivo=?"
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, treino.getData().toString());
            comando.setString(2, treino.getObjetivo());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar aluno no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Treino> listar() {
        List<Treino> listaTreinos = new ArrayList<>();
        AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
        
        String sql = "SELECT * FROM treino";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String date = resultado.getString("date");
                String objetivo = resultado.getString("objetivo");
                
                
                Treino t = new Treino(id, alunoDAOBD.procurarPorId(id_aluno), Date.valueOf(date), objetivo);
                listaTreinos.add(t);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os alunos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaTreinos);
    }

    @Override
    public Treino procurarPorId(int id) {
        String sql = "SELECT * FROM treino WHERE id = ?";
        AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
        
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int _id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String date = resultado.getString("date");
                String objetivo = resultado.getString("objetivo");
                
                Treino t = new Treino(_id, alunoDAOBD.procurarPorId(id_aluno), Date.valueOf(date), objetivo);

                return t;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o aluno pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    

    @Override
    public List<Treino> listarPorAluno(Aluno aluno) {
        List<Treino> listaTreinos = new ArrayList<>();
        String sql = "SELECT * FROM treino WHERE id_aluno = ?";
         
        try {
            conectar(sql);
            comando.setInt(1, aluno.getId());
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                Date data = resultado.getDate("data");
                String objetivo = resultado.getString("objetivo");
                    
                AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
                Treino t = new Treino(id, 
                  alunoDAOBD.procurarPorId(id_aluno), 
                  data, 
                  objetivo);
                
                listaTreinos.add(t);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as Treinos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaTreinos);
    }

    @Override
    public Treino procurarPorAluno(Aluno aluno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
