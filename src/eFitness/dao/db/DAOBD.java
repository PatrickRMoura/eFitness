package eFitness.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import eFitness.dao.DAO;

/**
 *
 * @author Patrick Moura
 */
public abstract class DAOBD<T> implements DAO<T> {
    protected Connection conexao;
    protected PreparedStatement comando;
    
    public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }
    
    public void conectarObtendoID(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    
    public void fecharConexao(){
        try {
            if (comando != null || conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);
        }
    }
}
