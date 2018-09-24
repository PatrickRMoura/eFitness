package eFitness.negocio;

import eFitness.dao.ClienteDAO;
import eFitness.dao.db.ClienteDAOBD;
import java.util.List;
import eFitness.model.Personal;

/**
 *
 * @author Patrick Moura
 */
public class ClienteNegocio {
    private ClienteDAO clienteDAO;
    
    public ClienteNegocio(){
        this.clienteDAO = new ClienteDAOBD();
    }
    
    public void salvar(Personal cliente) throws NegocioException {
        this.validarCamposObrigatorios(cliente);
        this.validarRGExistente(cliente);
        clienteDAO.salvar(cliente);
    }
    
    public List<Personal> listar(){
        return (clienteDAO.listar());
    }
    
    public void deletar(Personal cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == null) {
            throw new NegocioException("Cliente nao existe");
        }
        clienteDAO.deletar(cliente);
    }
    
    public void atualizar(Personal cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == null) {
            throw new NegocioException("Cliente nao existe");
        }
        clienteDAO.atualizar(cliente);
    }
    public Personal procurarPorID(int id) throws NegocioException {
        Personal cliente = clienteDAO.procurarPorId(id);
        
        if (cliente == null) {
            throw new NegocioException("Cliente nao encontrado");
        }
        return (cliente);
    }
    public Personal procurarPorRG(String rg) throws NegocioException {
        if (rg == null || rg.isEmpty()) {
            throw new NegocioException("RG nao informado!");
        }
        
        Personal cliente = clienteDAO.procurarPorRG(rg);
        
        if (cliente == null) {
            throw new NegocioException("Cliente nao encontrado!");
        }
        return (cliente);
    }
    
    public List<Personal> listarPorNome(String nome) throws NegocioException{
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Nome nao informado!");
        }
        return(clienteDAO.listarPorNome(nome));
    }
    
    public boolean clienteExiste(String rg) {
        Personal cliente = clienteDAO.procurarPorRG(rg);
        return (cliente != null);
    }
    
    private void validarCamposObrigatorios(Personal cliente) throws NegocioException {
        if (cliente.getRg() == null || cliente.getRg().isEmpty()) {
            throw new NegocioException("RG nao informado!");
        }
        
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new NegocioException("Nome nao informado!");
        }
    }
    
    private void validarRGExistente(Personal cliente) throws NegocioException {
        if (clienteExiste(cliente.getRg())) {
            throw new NegocioException("RG ja existe");
        }
    }

    public String procurarPorID(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
