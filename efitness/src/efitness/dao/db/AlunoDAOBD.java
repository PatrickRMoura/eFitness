package efitness.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import efitness.model.Aluno;
import efitness.dao.AlunoDAO;

/**
 *
 * @author Patrick Moura
 */
public class AlunoDAOBD extends DAOBD<Aluno> implements AlunoDAO{

    @Override
    public void salvar(Aluno aluno) {
        int id = 0;
        try {
            String sql = "INSERT INTO aluno (nome, rg, telefone)"
                        +"VALUES (?, ?, ?)";
            conectarObtendoID(sql);
            
            comando.setString(1, aluno.getNome());
            comando.setString(2, aluno.getRg());
            comando.setString(3, aluno.getTelefone());
            
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
            System.err.println("Erro de Sistema - Problema ao salvar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Aluno aluno) {
        try {
            String sql = "DELETE FROM aluno WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, aluno.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar aluno no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        try {
            String sql = "UPDATE aluno SET rg=?, nome=?, telefone=? "
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, aluno.getRg());
            comando.setString(2, aluno.getNome());
            comando.setString(3, aluno.getTelefone());
            comando.setInt(4, aluno.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar aluno no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Aluno> listar() {
        List<Aluno> listaAlunos = new ArrayList<>();

        String sql = "SELECT * FROM aluno";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String rg = resultado.getString("rg");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Aluno c = new Aluno(id, rg, nome, telefone);
                listaAlunos.add(c);

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
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String rg = resultado.getString("rg");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Aluno c = new Aluno(id, rg, nome, telefone);

                return c;
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
    public Aluno procurarPorRG(String rg) {
        String sql = "SELECT * FROM aluno WHERE rg = ?";

        try {
            conectar(sql);
            comando.setString(1, rg);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Aluno c = new Aluno(id, rg, nome, telefone);
                return c;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o aluno pelo rg do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Aluno> listarPorNome(String nome) {
        List<Aluno> listaAlunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String rg = resultado.getString("rg");
                String nomeX = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Aluno c = new Aluno(id, rg, nomeX, telefone);
                listaAlunos.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os alunos pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaAlunos);
    }
    
}
