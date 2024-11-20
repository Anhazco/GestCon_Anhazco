package py.edu.gestcon.componentes;

import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotonesJtollsbarPersonalizados extends JButton {

    public BotonesJtollsbarPersonalizados() {
        setSize(70, 70);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setFocusPainted(false);
        setFont(new Font("Tahoma", Font.BOLD, 12));
    }

    @Override
    public void setText(String text) {
        // Solo configura el texto sin intentar configurar el icono directamente
        super.setText(text);
    }

    public void setIcon(String nombreIcono) {
        try {
            URL url = BotonesJtollsbarPersonalizados.class.getResource("/py/edu/gestcon/img/" + nombreIcono.toLowerCase() + ".png");
            if (url != null) {
                setIcon(new ImageIcon(url));
            } else {
                System.err.println("Imagen no encontrada en la ruta: /py/edu/gestcon/img/" + nombreIcono.toLowerCase() + ".png");
                // Podrías establecer un ícono por defecto aquí si lo deseas
                setIcon(new ImageIcon("ruta/de/tu/icono/por/defecto.png")); // Opcional: coloca aquí un ícono por defecto
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
