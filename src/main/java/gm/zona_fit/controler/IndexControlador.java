package gm.zona_fit.controler;

import gm.zona_fit.model.Cliente;
import gm.zona_fit.service.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
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
    private Cliente clienteSeleccionado;

    //El constructor vacio lo manda a llamar por default jsf
    //Este metodo es despues de que es llamado
    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos() {
        this.clientes = clienteServicio.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente() {
        this.clienteSeleccionado = new Cliente();
    }

    public void guardarCliente() {
        logger.info("Cliente a guardar: " + this.clienteSeleccionado);
        //Agregar
        if (this.clienteSeleccionado.getId() == null) {
            this.clienteServicio.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Agregado"));
            //Ocultar la ventana modal
            PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
            //Actualizar la ventana usando ajax
            PrimeFaces.current().ajax().update("forma-clientes:mensajes", "forma-clientes:clientes-tabla");
            //reset del objeto cliente seleccionado
            this.clienteSeleccionado = null;
        }
    }
}
