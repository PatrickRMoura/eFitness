/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efitness.dao;

import efitness.model.Usuario;
import efitness.dao.db.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Patrick Moura
 */
public class UsuarioDAO {

    private final Connection connection;
    Long id;
    String nome;
    String cpf;
    String email;
    String telefone;

    public UsuarioDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Usuario usuario_acesso) {
        String sql = "INSERT INTO usuario_acesso(nome,cpf,email,telefone) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario_acesso.getNome());
            stmt.setString(2, usuario_acesso.getCpf());
            stmt.setString(3, usuario_acesso.getEmail());
            stmt.setString(4, usuario_acesso.getTelefone());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

    }

}
