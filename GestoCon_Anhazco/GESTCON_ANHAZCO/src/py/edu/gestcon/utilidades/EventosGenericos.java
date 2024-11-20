package py.edu.gestcon.utilidades;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;

import py.edu.gestcon.componentes.JtextFieldPersonalizado;

public class EventosGenericos {

	public static void estadosJTextField(Component component, boolean estado ){
		if (component instanceof JtextFieldPersonalizado) {
			JtextFieldPersonalizado jtextFieldPersonalizado =((JtextFieldPersonalizado) component);
			jtextFieldPersonalizado.setEnabled(estado);
		}else{
			if(component instanceof Container){
				for(Component c : ((Container) component).getComponents()){
					estadosJTextField(c, estado);
				}
			}
		}
	}
	public static void limpiarJTextField(Component component, boolean estado ){
		if (component instanceof JtextFieldPersonalizado) {
			JtextFieldPersonalizado jtextFieldPersonalizado =((JtextFieldPersonalizado) component);
			jtextFieldPersonalizado.setText("");
		}else{
			if(component instanceof Container){
				for(Component c : ((Container)component).getComponents()){
					estadosJTextField(c, estado);
				}
			}
		}
	}
	public static void moverConEnter(KeyEvent e, Component component){
		if(e.getKeyChar()==KeyEvent.VK_ENTER) component.requestFocus();
	}
}
