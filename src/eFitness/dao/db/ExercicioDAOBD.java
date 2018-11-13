package efitness.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import efitness.model.Exercicio;
import efitness.dao.ExercicioDAO;
/**
 *
 * @author Patrick Moura
 */
public class ExercicioDAOBD extends DAOBD<Exercicio> implements ExercicioDAO{
    
    @Override
    public void salvar(Exercicio exercicio) {
        int id = 0;
        try {
          String sql = "INSERT INTO exercicio (nome)"
                      + "VALUES (?)";

          conectarObtendoID(sql);

          comando.setString(1, exercicio.getNome());                                    
          comando.executeUpdate();

          ResultSet resultado = comando.getGeneratedKeys();
          if (resultado.next()) {
              id = resultado.getInt(1);
              exercicio.setId(id);
          } else {
              System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
              throw new BDException("Nao gerou o id conforme esperado!");
          }
      } catch (SQLException ex) {
          System.err.println("Erro de Sistema - Problema ao salvar o exercício no Banco de Dados!");
          throw new BDException(ex);
      } finally {
          fecharConexao();
      }
    }

    @Override
    public void deletar(Exercicio exercicio) {
        try {
            String sql = "DELETE FROM exercicio WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, exercicio.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Exercicio exercicio) {
        try {
            String sql = "UPDATE exercicio SET nome=? "
                    + "WHERE id=?";
            conectar(sql);
            
            comando.setString(1, exercicio.getNome());
            comando.setInt(2, exercicio.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar o exercício no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Exercicio> listar() {
        List<Exercicio> exercicios = new ArrayList<>();
        
        String sql = "SELECT * FROM exercicio";
        
        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");                                
                Exercicio exercicio = new Exercicio(id, nome);                
                exercicios.add(exercicio);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os exercícios do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (exercicios);
    }

    @Override
    public Exercicio procurarPorId(int id) {
        String sql = "SELECT * FROM exercicio WHERE id = ?";
        
        try {
            conectar(sql);
            comando.setInt(1, id);
            
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                String nome = resultado.getString("nome");                
                Exercicio exercicio = new Exercicio(id, nome);
                return exercicio;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return null;
    }

    @Override
    public Exercicio procurarPorNome(String nome) {
        String slq = "SELECT * FROM exercicio WHERE nome = ?";        
        try {
            conectar(slq);
            comando.setString(1, nome);            
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                String nomeExercicio = resultado.getString("nome");                
                int id = resultado.getInt("id");                
                Exercicio exercicio = new Exercicio(id, nomeExercicio);
                return exercicio;
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
