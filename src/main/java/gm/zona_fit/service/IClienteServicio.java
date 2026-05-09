package gm.zona_fit.service;

import gm.zona_fit.model.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer id);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}
