package trabajo.Visual;
import Clases.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Funciones.*;
import trabajo.Database.*;

public class VentanaFin extends JFrame{
	private JPanel contentPane;
	private JTextArea txtContraseña;
	private JTextArea txtCorreo;
	public static String DatoCorreo;
	
	public static void main(String[] args) {
		 Cliente cliente=new Cliente();
		 Calzado calzado= new Calzado();
		
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

	
	public VentanaFin(Cliente cliente, Calzado calzado) {//Pasar por parametro el producto
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
		
		txtContraseña = new JTextArea();
		if(cliente!=null){	txtContraseña = new JTextArea(cliente.getCorreo());	}
		else { txtContraseña = new JTextArea("Error.");}
		txtContraseña.setBounds(268, 215, 193, 20);
		contentPane.add(txtContraseña);
		
		JButton btnEntrar = new JButton("Confirmar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					DatoCorreo = txtCorreo.getText();
					//Envio correo
					String DatoCorreo="Le agradecemos la compra del nuestras zapatillas "+calzado.getNombre()+" el precio total de la factura sera de "+calzado.getPrecio();
					ProgressHilo hilo = new ProgressHilo();
					try {
						Mail.sendMail(cliente.getCorreo(), "Compra", DatoCorreo);
						ficha.uploadFile(calzado.getCodigo());
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				
			} 
		});
		btnEntrar.setBounds(268, 300, 199, 23);
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
		if(calzado!=null){	txtCorreo = new JTextArea(calzado.toString());	}
		else { txtCorreo = new JTextArea("Error en la seleccion. Vuelva atrás.");}
		
		txtCorreo.setBounds(268, 141, 250, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

	}
}
