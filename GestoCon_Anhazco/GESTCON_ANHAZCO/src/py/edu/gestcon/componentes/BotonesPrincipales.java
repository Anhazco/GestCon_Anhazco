package py.edu.gestcon.componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BotonesPrincipales extends JButton {

	    public BotonesPrincipales() {
	        setSize(100, 100);
	        setForeground(Color.BLACK);
	        setHorizontalTextPosition(SwingConstants.CENTER);
	        setVerticalTextPosition(SwingConstants.BOTTOM);
	        setBorder(new EmptyBorder(10, 10, 10, 10)); // Bordes internos para dar espacio al texto e icono
	        setBackground(new Color(220, 220, 220, 200)); // Fondo sutilmente gris y ligeramente opaco
	        setOpaque(false);
	        setFocusable(false);
	        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor en forma de mano

	        // Efecto Hover
	        addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                setBackground(new Color(200, 200, 200, 220)); // Fondo un poco más oscuro al hacer hover
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                setBackground(new Color(220, 220, 220, 200)); // Fondo original al salir del hover
	            }
	        });
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        // Dibujar el fondo redondeado
	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bordes redondeados
	        
	        super.paintComponent(g);
	    }
	
	public void setText(String string){
		setIcon(string);
		super.setText(string);
	}

	public void setIcon(String nombreIcono){
		try {
			URL url = BotonesPrincipales.class.getResource("/py/edu/gestcon/img/"+nombreIcono.toLowerCase()+".png");
			setIcon(new ImageIcon(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}