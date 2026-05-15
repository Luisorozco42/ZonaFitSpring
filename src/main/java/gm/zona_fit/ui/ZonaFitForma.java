package gm.zona_fit.ui;

import gm.zona_fit.model.Cliente;
import gm.zona_fit.service.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gm.zona_fit.service.ClienteServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JButton guardarBoton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaModeloClientes;
    Integer idCliente;

    @Autowired//por cierto este tiene esto para añadirlo al paquete de spring
    public ZonaFitForma(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
        guardarBoton.addActionListener(e -> guardarCliente());
        clientesTabla.addComponentListener(new ComponentAdapter() {
        });
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
    }

    private void iniciarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);//Se centra la ventana
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloClientes = new DefaultTableModel(0, 4);
        String[] cabeceros = {"ID", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloClientes.setColumnIdentifiers(cabeceros);
        this.clientesTabla = new JTable(this.tablaModeloClientes);
        //cargar listado de clientes
        listarClientes();
    }

    private void listarClientes() {
        this.tablaModeloClientes.setRowCount(0);
        var clientes = this.clienteServicio.listarClientes();
        clientes.forEach(cliente -> {
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.tablaModeloClientes.addRow(renglonCliente);
        });
    }

    private void guardarCliente() {
        if (nombreTexto.getText().trim().isEmpty()) {
            mostrarMensaje("Proporcione un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if (membresiaTexto.getText().trim().isEmpty()) {
            mostrarMensaje("Proporcione un membresia");
            membresiaTexto.requestFocusInWindow();
            return;
        }
        //recuperamos los valores del formulario
        var nombre =  nombreTexto.getText();
        var apellido = apellidoTexto.getText();
        var membresia = Integer.parseInt(membresiaTexto.getText());
        Cliente cliente = new Cliente();

        cliente.setId(this.idCliente);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setMembresia(membresia);

        this.clienteServicio.guardarCliente(cliente);//inserta
        if(this.idCliente == null) mostrarMensaje("Se agrego el nuevo cliente");
        else mostrarMensaje("Se actualizo el cliente");

        limpiarFormulario();
        listarClientes();
    }

    private void cargarClienteSeleccionado() {
        var renglon = clientesTabla.getSelectedRow();
        if (renglon != -1) { //-1 significa que ningun registro ha sido seleccionado
            var id = Integer.parseInt(clientesTabla.getModel().getValueAt(renglon, 0).toString());
            var nombre = clientesTabla.getModel().getValueAt(renglon, 1).toString();
            var apellido = clientesTabla.getModel().getValueAt(renglon, 2).toString();
            var membresia = clientesTabla.getModel().getValueAt(renglon, 3).toString();

            this.idCliente = id;
            nombreTexto.setText(nombre);
            apellidoTexto.setText(apellido);
            membresiaTexto.setText(membresia);
        }
    }

    private void limpiarFormulario() {
        this.idCliente = null;
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaTexto.setText("");
        //deseleleccionamos algun elemento de la tabla
        this.clientesTabla.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
