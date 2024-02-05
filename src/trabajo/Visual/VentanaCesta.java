package trabajo.Visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Clases.Calzado;
import Clases.Cliente;
import trabajo.Database.Files;
import trabajo.Database.GestorBD;

public class VentanaCesta extends JFrame{


	private JPanel contentPane;
	private JTable table;
	public String DatoCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Cliente cliente= new Cliente();
		List<Calzado> calzado= new ArrayList<>();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCesta frame = new VentanaCesta(cliente, calzado);
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
	public VentanaCesta(Cliente cliente, List<Calzado> calzado) {
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
		
		modelo.setRowCount(0);
		if (calzado != null) {
			for (Calzado p : calzado) {
				Object [] fila = new Object[8];
				fila[0] = p.getCodigo();
				fila[1] = p.getNombre();
				fila[2] = p.getPrecio();
				fila[3] = p.getColor();
				
				modelo.addRow(fila);
			}
		}
		
		
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (row%2==0) {
					l.setBackground( Color.LIGHT_GRAY );
				} else {
					l.setBackground( Color.WHITE );
				}
				return l;
			}
		});
		
		
		table.setRowHeight(30);
		
		
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Seleccion del calzado a comprar
				if (calzado!=null && cliente!=null) {
					VentanaFin ventana = new VentanaFin(cliente, calzado);
					ventana.setVisible(true);
					dispose();
				}
				
				
			}
		});
		btnNewButton.setBounds(551, 302, 150, 36);
		contentPane.add(btnNewButton);
		
	
		JButton goBack = new JButton("Cancelar");
		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( cliente!=null) {
					VentanaCompra ventana = new VentanaCompra(cliente);
					ventana.setVisible(true);
					dispose();
				}
				
				
			}
		});
		goBack.setBounds(450, 302, 100, 36);
		contentPane.add(goBack);
	
		
	}

}
