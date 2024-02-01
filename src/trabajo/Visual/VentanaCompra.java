package trabajo.Visual;
import Clases.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
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
		Cliente cliente= new Cliente();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCompra frame = new VentanaCompra(cliente);
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
		
		modelo.setRowCount(0);
		List<Calzado> zp = bd.getCalzadoList();
		if (zp != null) {
			for (Calzado p : zp) {
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
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component comp =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (isSelected) {
					comp.setBackground(Color.RED);
				}else {
					if (row%2==0) {
						comp.setBackground( Color.LIGHT_GRAY );
					} else {
						comp.setBackground( Color.WHITE );
					}				}
				
				
				
				return comp;		
			}
		});
		table.setRowHeight(30);
		
		
		
		JButton btnNewButton = new JButton("Comprar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calzado calzado =null;
				int SelectedRow = table.getSelectedRow();
				String codigo = modelo.getValueAt(SelectedRow, 0).toString();
				int code = Integer.valueOf(codigo);
				List<Calzado> lista = new ArrayList<>(bd.getCalzadoList());
				for (Calzado c : lista) {		if(code == c.getCodigo()) {	calzado=c;	}		}

			
				//Seleccion del calzado a comprar
				if (calzado!=null && cliente!=null) {
					VentanaFin ventana = new VentanaFin(cliente, calzado);
					ventana.setVisible(true);
					dispose();
				}
				
				
			}
		});
		btnNewButton.setBounds(678, 302, 89, 36);
		contentPane.add(btnNewButton);
		
	
		
	
		
	}

}
