package efitness.dao.db;

import efitness.dao.RestricaoDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import efitness.model.Aluno;
import efitness.dao.RestricaoDAO;
import efitness.model.Restricao;
import java.sql.Date;

/**
 *
 * @author Pablo Schlusen
 */
public class RestricaoDAOBD extends DAOBD<Restricao> implements RestricaoDAO{

    @Override
    public void salvar(Restricao restricao) {
        int id = 0;
        try {
            String sql = "INSERT INTO restricao (id_aluno, cid, causa, descricao)"
                        +"VALUES (?, ?, ?, ?)";
            conectarObtendoID(sql);
            
            comando.setInt(1, restricao.getAluno().getId());
            comando.setString(2, restricao.getCid());
            comando.setString(3, restricao.getCausa());
            comando.setString(4, restricao.getDescricao());
            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                restricao.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar Restricao no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Restricao restricao) {
        try {
            String sql = "DELETE FROM restricao WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, restricao.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar aluno no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Restricao restricao) {
        try {
            String sql = "UPDATE restricao SET cid=?, causa=?"
                    + "WHERE descricao=?";

            conectar(sql);
            comando.setString(1, restricao.getCid());
            comando.setString(2, restricao.getCausa());
            comando.setString(3, restricao.getDescricao());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar Restricao no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Restricao> listar() {
        List<Restricao> listaRestricoes = new ArrayList<>();
        AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
        
        String sql = "SELECT * FROM restricao";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String cid = resultado.getString("cid");
                String causa = resultado.getString("causa");
                String descricao = resultado.getString("descricao");
                
                
                Restricao r = new Restricao(id, alunoDAOBD.procurarPorId(id_aluno), cid, causa, descricao);
                listaRestricoes.add(r);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os Restricoes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaRestricoes);
    }

    @Override
    public Restricao procurarPorId(int id) {
        String sql = "SELECT * FROM restricao WHERE id = ?";
        AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
        
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int _id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String cid = resultado.getString("cid");
                String causa = resultado.getString("causa");
                String descricao = resultado.getString("descricao");
                
                Restricao r = new Restricao(_id, alunoDAOBD.procurarPorId(id_aluno), cid, causa, descricao);

                return r;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o Restricao pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    

    @Override
    public List<Restricao> listarPorAluno(Aluno aluno) {
        List<Restricao> listaRestricoes = new ArrayList<>();
        String sql = "SELECT * FROM restricao INNER JOIN aluno WHERE restricao.? = aluno.id ";

        return (listaRestricoes);
    }

    @Override
    public Restricao procurarPorAluno(Aluno aluno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
