package trabajo.Visual;
import Clases.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import trabajo.Database.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class VentanaInicio extends JFrame{
	private JPanel contentPane;
	private JPasswordField txtContraseña;
	private JTextField txtCorreo;
	public static String DatoCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
	     
	    
		
		Files files= new Files();
		files.sincFile();
		GestorBD bd = new GestorBD();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inicia Sesión");
		lblNewLabel.setBounds(329, 47, 93, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA:");
		lblNewLabel_2.setBounds(101, 210, 180, 31);
		contentPane.add(lblNewLabel_2);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(268, 215, 193, 20);
		contentPane.add(txtContraseña);
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean usuario = bd.buscarUsuarioRegistrado(txtCorreo.getText(),txtContraseña.getText());
				
				if(usuario) {
					Cliente cliente=null;
					JOptionPane.showMessageDialog(null, "Bienvenido ");
					DatoCorreo = txtCorreo.getText();
					List<Cliente> lista = new ArrayList<>(bd.getClientesList());
					

					
					for (Cliente c : lista) {
						if(c.getCorreo().equals(txtCorreo.getText())) {		 cliente =c; 		}
					}
					//c es parametro
					
					if(cliente!=null) {
						

						VentanaCompra ventana = new VentanaCompra(cliente);
						ventana.setVisible(true);
						dispose();
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "El usuario es incorrecto");
					VentanaCuenta v = new VentanaCuenta();
					v.setVisible(true);
					dispose();
				}
			} 
		});
		btnEntrar.setBounds(268, 300, 89, 23);
		contentPane.add(btnEntrar);
		
	
		
		
		JLabel lblNewLabel_1 = new JLabel("CORREO:");
		lblNewLabel_1.setBounds(101, 144, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(268, 141, 193, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
	}
	
	
}
