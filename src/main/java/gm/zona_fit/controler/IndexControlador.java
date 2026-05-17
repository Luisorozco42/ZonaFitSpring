package gm.zona_fit.controler;

import gm.zona_fit.model.Cliente;
import gm.zona_fit.service.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@Data
@ViewScoped
public class IndexControlador {

    @Autowired
    IClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    //El constructor vacio lo manda a llamar por default jsf
    //Este metodo es despues de que es llamado
    @PostConstruct
    public void init(){
        cargarDatos();
    }

    private void cargarDatos() {
        this.clientes = clienteServicio.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }
}
