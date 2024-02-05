package trabajo.Visual;
import Clases.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import Funciones.*;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import Funciones.*;
import trabajo.Database.*;

public class VentanaFin extends JFrame{
	private JPanel contentPane;
	private JTextArea txtContrasena;
	private JTextArea txtCorreo;
	private JComboBox<Calzado> productosBox;
	
	public static void main(String[] args) {
		 Cliente cliente=new Cliente();
		 List<Calzado> calzado= new ArrayList<>();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFin frame = new VentanaFin(cliente,calzado);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaFin(Cliente cliente, List<Calzado> calzado) {//Pasar por parametro el producto
		productosBox= new JComboBox<Calzado>();
		Files ficha=new Files();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Confirmar compra");
		lblNewLabel.setBounds(329, 47, 193, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Correo:");
		lblNewLabel_2.setBounds(101, 210, 80, 31);
		contentPane.add(lblNewLabel_2);
		
		txtContrasena = new JTextArea();
		if(cliente!=null){	txtContrasena = new JTextArea(cliente.getCorreo());	}
		else { txtContrasena = new JTextArea("Error.");}
		txtContrasena.setBounds(168, 215, 193, 20);
		contentPane.add(txtContrasena);
		
		
		
		JButton btnEntrar = new JButton("Confirmar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//Envio correo
					String DatoCorreo="Le agradecemos la compra del nuestras zapatillas  el precio total de la factura sera de "+Utilidades.sumaPrecio(calzado);
					ProgressHilo hilo = new ProgressHilo();
					hilo.setVisible(true);
					
					try {
						Mail.sendMail(cliente.getCorreo(), "Compra", DatoCorreo);
						try {					
							
							for (Calzado c : calzado) {		ficha.uploadFile(c.getCodigo());	}
							
						} catch (Exception e2) {
						}
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			} 
		});
		btnEntrar.setBounds(250, 300, 200, 23);
		contentPane.add(btnEntrar);
		
	
		JButton vuelta = new JButton("Atras");
		vuelta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					VentanaCompra ventana =new VentanaCompra(cliente);
					ventana.setVisible(true);
					dispose();
					
				
			} 
		});
		vuelta.setBounds(90, 300, 90, 23);
		contentPane.add(vuelta);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setBounds(101, 144, 67, 14);
		contentPane.add(lblNewLabel_1);
		if(calzado!=null){	
			for (Calzado c : calzado) {		
					productosBox.addItem(c);		}
			productosBox.setBounds(168, 141, 500, 20);
			contentPane.add(productosBox);

				    }
		else { txtCorreo = new JTextArea("Error en la seleccion. Vuelva atrás.");
		txtCorreo.setBounds(168, 141, 250, 20);
		contentPane.add(txtCorreo);
		}	
		
			
	}
}
