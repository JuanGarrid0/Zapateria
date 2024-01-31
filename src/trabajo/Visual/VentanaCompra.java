package trabajo.Visual;
import Clases.*;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import trabajo.Database.*;
import Funciones.*;


public class VentanaCompra extends JFrame{
	
	private JPanel contentPane;
	private JTable table;
	public String DatoCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCompra frame = new VentanaCompra(null);
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
	public VentanaCompra(Cliente cliente) {
		
		GestorBD  bd= new GestorBD();		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 757, 259);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		DefaultTableModel modelo = new DefaultTableModel();
		table.setModel(modelo);
		
	
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Color");

		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Comprar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calzado calzado =null;
				int SelectedRow = table.getSelectedRow();
				int SelectedColumn = table.getSelectedColumn();
				String codigo = modelo.getValueAt(SelectedRow, SelectedColumn).toString();
				int code = Integer.valueOf(codigo);
				List<Calzado> lista = new ArrayList<>(bd.getCalzadoList());
				for (Calzado c : lista) {		if(code == c.getCodigo()) {	calzado=c;	}		}

			
				//Seleccion del calzado a comprar
				if (calzado!=null && cliente!=null) {
					VentanaFin ventana = new VentanaFin(calzado,cliente);
					ventana.setVisible(true);
					dispose();
				}
				
				
			}
		});
		btnNewButton.setBounds(678, 302, 89, 36);
		contentPane.add(btnNewButton);
		
	
		
	
		
	}

}
