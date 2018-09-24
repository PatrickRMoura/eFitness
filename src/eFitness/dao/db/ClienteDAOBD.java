package eFitness.dao.db;

import eFitness.dao.db.BDException;
import eFitness.dao.db.DAOBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import eFitness.dao.ClienteDAO;
import eFitness.model.Personal;

/**
 *
 * @author ibrum
 */
public class ClienteDAOBD extends DAOBD<Personal> implements ClienteDAO{

    @Override
    public void salvar(Personal cliente) {
        int id = 0;
        try {
            String sql = "INSERT INTO clientes (nome, rg, telefone)"
                        +"VALUES (?, ?, ?)";
            conectarObtendoID(sql);
            
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getRg());
            comando.setString(3, cliente.getTelefone());
            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                cliente.setId(id);
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
    public void deletar(Personal cliente) {
        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, cliente.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(Personal cliente) {
        try {
            String sql = "UPDATE clientes SET rg=?, nome=?, telefone=? "
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, cliente.getRg());
            comando.setString(2, cliente.getNome());
            comando.setString(3, cliente.getTelefone());
            comando.setInt(4, cliente.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar cliente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Personal> listar() {
        List<Personal> listaClientes = new ArrayList<>();

        String sql = "SELECT * FROM clientes";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String rg = resultado.getString("rg");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Personal c = new Personal(id, rg, nome, telefone);
                listaClientes.add(c);

            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes);
    }

    @Override
    public Personal procurarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String rg = resultado.getString("rg");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Personal c = new Personal(id, rg, nome, telefone);

                return c;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Personal procurarPorRG(String rg) {
        String sql = "SELECT * FROM clientes WHERE rg = ?";

        try {
            conectar(sql);
            comando.setString(1, rg);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Personal c = new Personal(id, rg, nome, telefone);
                return c;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o cliente pelo rg do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public List<Personal> listarPorNome(String nome) {
        List<Personal> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String rg = resultado.getString("rg");
                String nomeX = resultado.getString("nome");
                String telefone = resultado.getString("telefone");
                
                Personal c = new Personal(id, rg, nomeX, telefone);
                listaClientes.add(c);
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os clientes pelo nome do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaClientes);
    }
    
}
