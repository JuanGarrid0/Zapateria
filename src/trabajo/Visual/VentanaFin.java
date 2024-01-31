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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Funciones.*;
import trabajo.Database.*;

public class VentanaFin extends JFrame{
	private JPanel contentPane;
	private JPasswordField txtContraseña;
	private JTextField txtCorreo;
	public static String DatoCorreo;
	public Cliente cliente=null;
	public Calzado c= null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFin frame = new VentanaFin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaFin() {//Pasar por parametro el producto
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
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(268, 215, 193, 20);
		contentPane.add(txtContraseña);
		
		JButton btnEntrar = new JButton("Confirmar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					DatoCorreo = txtCorreo.getText();
					//Envio correo
					String DatoCorreo="Le agradecemos la compra del nuestras zapatillas "+c.getNombre()+" el precio total de la factura sera de "+c.getPrecio();
					try {
						Mail.sendMail(cliente.getCorreo(), "Compra", DatoCorreo);
						ficha.uploadFile(c.getCodigo());
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
					VentanaCompra ventana =new VentanaCompra();
					ventana.setVisible(true);
					dispose();
				
			} 
		});
		vuelta.setBounds(90, 300, 90, 23);
		contentPane.add(vuelta);
		
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setBounds(101, 144, 67, 14);
		contentPane.add(lblNewLabel_1);
		if(c!=null){	txtCorreo = new JTextField(c.toString());	}
		else { txtCorreo = new JTextField("Error en la seleccion. Vuelva atrás.");}
		txtCorreo.setBounds(268, 141, 250, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
	}
}
