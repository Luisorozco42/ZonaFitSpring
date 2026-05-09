package gm.zona_fit.repository;

import gm.zona_fit.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository es un tipo generico y se le especifica el tipo de clase con el que se trabaja y la llave primaria
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

}
