    package gm.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import gm.zona_fit.ui.ZonaFitForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

//@SpringBootApplication // ahora es la nueva clase principal, actualizacion, ya no lo es xd
public class ZonaFitSwing {
    static void main(String[] args) {
        //configuramos el modo oscuro
        FlatDarculaLaf.setup();

        //Instancia la fabrica de spring
        ConfigurableApplicationContext contextoSpring =
                new SpringApplicationBuilder(ZonaFitSwing.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);

        //crear un objeto swing
        SwingUtilities.invokeLater(() -> {
            ZonaFitForma zonaFitForma = contextoSpring.getBean(ZonaFitForma.class); //Se realiza para poder obtener la
            //inyeccion de dependencias que necesitamos
            zonaFitForma.setVisible(true);
        });
    }
}
