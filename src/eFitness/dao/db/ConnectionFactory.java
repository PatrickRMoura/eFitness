package efitness.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Patrick Moura
 */
public class ConnectionFactory {
    private final static String HOST = "localhost";
    private final static String PORT = "5432";
    private final static String BD = "efitness";
    private final static String URL = "jdbc:postgresql://"+HOST+":"+PORT+"/"+BD;
    private final static String USUARIO = "postgres";
    private final static String SENHA = "1234";
    
    public static Connection getConnection(){
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Erro de Sistema - Classe do Driver Nao Encontrada!");
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema na conexão do banco de dados");
            throw new RuntimeException(ex);
        }
        return(conexao);
    }
}
