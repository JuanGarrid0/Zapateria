package main;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import trabajo.Visual.*;

public class Main {
	public static void main(String[] args){
	 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	    
	}

}
