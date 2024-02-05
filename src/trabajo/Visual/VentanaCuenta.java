package trabajo.Visual;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import Clases.*;
import trabajo.Database.*;


public class VentanaCuenta extends JFrame{
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JPasswordField txtContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCuenta frame = new VentanaCuenta();
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
	public VentanaCuenta() {
		
		GestorBD bd = new GestorBD();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(63, 97, 78, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CORREO:");
		lblNewLabel_1.setBounds(63, 168, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA");
		lblNewLabel_2.setBounds(63, 231, 178, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("ALTA USUARIO");
		lblNewLabel_4.setBounds(323, 42, 119, 14);
		contentPane.add(lblNewLabel_4);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(151, 103, 96, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(151, 165, 96, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(151, 228, 96, 20);
		contentPane.add(txtContrasena);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Cliente> lista = new ArrayList<>(bd.getClientesList());
				Cliente cliente= new Cliente(txtCorreo.getText(),txtContrasena.getText(),txtNombre.getText());
				lista.add(cliente);
				bd.insertarCliente(lista);
				if (cliente!=null) {
					VentanaCompra ventana = new VentanaCompra(cliente);
					ventana.setVisible(true);
					dispose();
				}
				

				try {Mail.signUpMail(txtCorreo.getText());
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnGuardar.setBounds(251, 352, 89, 23);
		contentPane.add(btnGuardar);
		
		
	}
}
