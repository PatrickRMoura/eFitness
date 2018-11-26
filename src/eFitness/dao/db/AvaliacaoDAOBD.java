package efitness.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import efitness.model.Aluno;
import efitness.dao.AvaliacaoDAO;
import efitness.model.Avaliacao;
import java.time.LocalDate;

/**
 *
 * @author gustavo.fonseca
 */
public class AvaliacaoDAOBD extends DAOBD<Avaliacao> implements AvaliacaoDAO{

    @Override
    public void salvar(Avaliacao avaliacao) {
        int id = 0;
        try {
            String sql = "INSERT INTO avaliacao (id_aluno, data, massa_corporal, frequencia_cardiaca, pressao_arterial)"
                        +"VALUES (?, ?, ?, ?, ?)";
            conectarObtendoID(sql);
            
            comando.setInt(1, avaliacao.getAluno().getId());
            comando.setObject(2, avaliacao.getData());
            comando.setDouble(3, avaliacao.getMassaCorporal());
            comando.setDouble(4, avaliacao.getFrequenciaCardiaca());
            comando.setDouble(5, avaliacao.getPressaoArterial());            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                avaliacao.setId(id);
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
    public void deletar(Avaliacao avaliacao) {
        try {
            String sql = "DELETE FROM avaliacao WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, avaliacao.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar aluno no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Avaliacao avaliacao) {
        try {
            String sql = "UPDATE avaliacao SET massa_corporal=?, frequencia_cardiaca=?, pressao_arterial=?"
                    + "WHERE id=?";

            conectar(sql);            
            comando.setDouble(1, avaliacao.getMassaCorporal());
            comando.setDouble(2, avaliacao.getFrequenciaCardiaca());
            comando.setDouble(3, avaliacao.getPressaoArterial());
            comando.setInt(4, avaliacao.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar avaliação no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Avaliacao> listar() {
        List<Avaliacao> listaAvaliacoes = new ArrayList<>();
        AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
        
        String sql = "SELECT * FROM avaliacao";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String data = resultado.getString("data");
                double massaCorporal = resultado.getDouble("massa_corporal");
                double frequenciaCardiaca = resultado.getDouble("frequencia_cardiaca");
                double pressaoArterial = resultado.getDouble("pressao_arterial");
                                
                Avaliacao a = new Avaliacao(id, 
                  alunoDAOBD.procurarPorId(id_aluno), 
                  LocalDate.parse(data), 
                  massaCorporal, 
                  frequenciaCardiaca,
                  pressaoArterial);
                
                listaAvaliacoes.add(a);

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
    public Avaliacao procurarPorId(int id) {
        String sql = "SELECT * FROM avaliacao WHERE id = ?";
        AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
        
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int _id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String data = resultado.getString("data");
                double massaCorporal = resultado.getDouble("massa_corporal");
                double frequenciaCardiaca = resultado.getDouble("frequencia_cardiaca");
                double pressaoArterial = resultado.getDouble("pressao_arterial");
                
                Avaliacao a = new Avaliacao(id, 
                  alunoDAOBD.procurarPorId(id_aluno), 
                  LocalDate.parse(data),
                  massaCorporal, 
                  frequenciaCardiaca,
                  pressaoArterial);

                return a;
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
    public List<Avaliacao> listarPorAluno(Aluno aluno) {
        List<Avaliacao> listaAvaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM avaliacao WHERE id_aluno = ?";
         
        try {
            conectar(sql);
            comando.setInt(1, aluno.getId());
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                int id_aluno = resultado.getInt("id_aluno");
                String date = resultado.getString("data");
                double massaCorporal = resultado.getDouble("massa_corporal");
                double frequenciaCardiaca = resultado.getDouble("frequencia_cardiaca");
                double pressaoArterial = resultado.getDouble("pressao_arterial");
                
                AlunoDAOBD alunoDAOBD = new AlunoDAOBD();
                Avaliacao a = new Avaliacao(id, 
                  alunoDAOBD.procurarPorId(id_aluno), 
                  LocalDate.parse(date), 
                  massaCorporal, 
                  frequenciaCardiaca,
                  pressaoArterial);
                
                listaAvaliacoes.add(a);
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
