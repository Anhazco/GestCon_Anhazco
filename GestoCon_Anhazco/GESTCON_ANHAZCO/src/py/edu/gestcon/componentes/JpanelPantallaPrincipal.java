package py.edu.gestcon.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JpanelPantallaPrincipal extends JPanel {

	public JpanelPantallaPrincipal() {
		
	}
	URL url = getClass().getResource("/py/edu/gestcon/img/Anhaz_fondo.png");
	Image image = new ImageIcon(url).getImage();
	
	public void paintComponent(Graphics g){
	g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	setOpaque(false);
	super.paintComponent(g);
	}
}
 