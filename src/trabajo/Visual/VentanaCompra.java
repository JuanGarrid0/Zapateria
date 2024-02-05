package trabajo.Visual;
import Clases.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.NewsAddress;
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
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import trabajo.Database.*;
import Funciones.*;


public class VentanaCompra extends JFrame{
	
	private JPanel contentPane;
	private JTable table;
	public String DatoCorreo;
	private JLabel label;
	public static ImageIcon imagen;
	List<Calzado> cesta=new ArrayList<>();
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
		Files files = new Files();
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
		
		label =new JLabel();		
		table = new JTable();
		
		DefaultTableModel modelo = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
		        switch (column) {
		            case 4: return ImageIcon.class;
		            default: return String.class;
		        }
		    }
		};
		table.setModel(modelo);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if(value instanceof JLabel && value!=null) {
					JLabel lbl =(JLabel)value;

					return lbl;
				}
				if (value!=null) {
			        label.setHorizontalAlignment(JLabel.CENTER);
			        label.setIcon(new ImageIcon((byte[])value));

			        }
				if (row%2==0) {
					comp.setBackground( Color.LIGHT_GRAY );
				} else {
					comp.setBackground( Color.WHITE );
				}
				return comp;
			}
		});
	
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Precio");
		modelo.addColumn("Color");
		modelo.addColumn("Imagen");

		scrollPane.setViewportView(table);
		
		modelo.setRowCount(0);
		List<Calzado> zp = bd.getCalzadoList();
		if (zp != null) {
			for (Calzado p : zp) {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
//resizeImage(ImageIO.read(new File("src\\imagenes\\"+name +".png")))
				Object [] fila = new Object[8];
				fila[0] = p.getCodigo();          
				fila[1] = p.getNombre(); 			
				fila[2] = p.getPrecio();
				fila[3] = p.getColor();
				try {
					fila[4] = new ImageIcon( (Utilidades.resizeImage(ImageIO.read(new File("src\\imagenes\\"+p.getNombre() +".png")))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				modelo.addRow(fila);
			}							//

		}
	
		
		
		
		
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
		table.setRowHeight(50);
		
		
		
		JButton btnNewButton = new JButton("Ir a la cesta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calzado calzado =null;
				int SelectedRow = table.getSelectedRow();
				String codigo = modelo.getValueAt(SelectedRow, 0).toString();
				int code = Integer.valueOf(codigo);
				List<Calzado> lista = new ArrayList<>(bd.getCalzadoList());
				for (Calzado c : lista) {		if(code == c.getCodigo()) {	calzado=c;	}		}

			
				//Seleccion del calzado a comprar
				if (cesta.size()!=0 && cliente!=null) {
				VentanaCesta ventana = new VentanaCesta(cliente, cesta);
				ventana.setVisible(true);
					dispose();
				}
				
				
			}
		});
		btnNewButton.setBounds(581, 302, 180, 36);
		contentPane.add(btnNewButton);
		
		JButton botonAdd = new JButton("Añadir a la cesta");
		botonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelectedRow = table.getSelectedRow();
				String codigo = modelo.getValueAt(SelectedRow, 0).toString();
				int code = Integer.valueOf(codigo);
				List<Calzado> lista = new ArrayList<>(bd.getCalzadoList());
				for (Calzado c : lista) {		if(code == c.getCodigo()) {	cesta.add(c);	}		}				
						
			}
		});
		botonAdd.setBounds(400, 302, 180, 36);
		contentPane.add(botonAdd);
		
		
	
		
	}

}
