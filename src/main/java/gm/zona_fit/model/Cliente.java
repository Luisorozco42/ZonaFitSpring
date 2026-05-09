package gm.zona_fit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data // Esto genera los getter y setter de cada uno de los valores (Me recuerda a kotlin)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Se utiliza la clase porque para jpa es más facil enternder el valor por default de null
    private String nombre;
    private String apellido;
    private Integer membresia;
}
