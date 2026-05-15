package gm.zona_fit;

import gm.zona_fit.model.Cliente;
import gm.zona_fit.service.IClienteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//Se desactivo para poder crear la version de swing
//Segun yo no es necesario ya que podemos reemplazar el codigo, pero supongo que se hace para no perder esta otra app
//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	static void main(String[] args) {

		logger.info("Iniciando la aplicacion");
		//levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicación Finalizada!!");
	}
	//Spring es una fabrica de objetos, por lo tanto hay que esperar primero que spring se inicie y luego ya se pueden usar los objetos
	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp() {
		Boolean salir = false;
		Scanner scanner = new Scanner(System.in);

		while (!salir){
			var opcion = mostrarMenu(scanner);
			salir = ejecutarOpciones(scanner, opcion);
			logger.info("");
		}
	}

	private int mostrarMenu(Scanner scanner) {
		logger.info("""
				*** Aplicación zona fit (GYM) ***
				1. Listar clientes
				2. Buscar cliente
				3. Agregar cliente
				4. Modificar cliente
				5. Eliminar cliente
				6. Salir
				Elige una opcion""");
		var opcion = Integer.parseInt(scanner.nextLine());
		return opcion;
	}

	private Boolean ejecutarOpciones(Scanner scanner, int opcion){
		var salir = false;
		switch (opcion) {
			case 1 -> {
				logger.info("--- Listado de clientes ---");
				List<Cliente> clientes = clienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString()));
			}

			case 2 -> {
				logger.info("--- Buscar Cliente por el Id ---");
				var idCliente = Integer.parseInt(scanner.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if (cliente != null) logger.info("Cliente encontrado: " + cliente);
				else logger.info("Cliente no encontrado: " + cliente);
			}

			case 3 -> {
				logger.info("--- Agregar Cliente ---");
				logger.info("Nombre: ");
				var nombre = scanner.nextLine();
				logger.info("Apellido: ");
				var apellido = scanner.nextLine();
				logger.info("Membresia: ");
				var membresia = Integer.parseInt(scanner.nextLine());
				Cliente cliente = new Cliente();

				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);

				clienteServicio.guardarCliente(cliente);

				logger.info("Cliente agregado correctamente a la base de datos" + cliente);
			}

			case 4 -> {
				logger.info("--- Modificar cliente ---");
				logger.info("Id Cliente: ");
				var idCliente = Integer.parseInt(scanner.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

				if (cliente != null) {
					logger.info("Nombre: ");
					var nombre = scanner.nextLine();
					logger.info("Apellido: ");
					var apellido = scanner.nextLine();
					logger.info("Membresia: ");
					var membresia = Integer.parseInt(scanner.nextLine());

					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);

					clienteServicio.guardarCliente(cliente);
					logger.info("Cliente modificado: " + cliente);
				} else logger.info("Cliente no encontrado: " + cliente);
			}

			case 5 -> {
				logger.info("--- Eliminar cliente ---");
				logger.info("Id Cliente: ");
				var idCliente = Integer.parseInt(scanner.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);

				if (cliente != null) {
					clienteServicio.eliminarCliente(cliente);
					logger.info("Cliente Eliminado " + cliente);
				} else logger.info("Cliente no encontrado " + cliente);
			}

			case 6 -> {
				logger.info("Hasta pronto!!\n");
				salir = true;
			}

			default -> logger.info("Opcion no reconocida: " + opcion);
		}
		return salir;
	}

}
