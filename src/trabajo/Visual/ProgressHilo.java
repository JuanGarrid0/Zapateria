package trabajo.Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


	public class ProgressHilo extends JFrame {
		
		  Thread hilo;
		  Object objeto = new Object();
		  boolean pideParar = false;
		  JTextField texto;
		  JProgressBar barra;
		  JPanel panel;
		  
		  public static void main( String args[] ) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ProgressHilo frame = new ProgressHilo();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			  }
		  
		  public  ProgressHilo() {
			panel = new JPanel();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 731, 517);
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		    panel.setLayout( new GridLayout(0,1) );
			setContentPane(panel);
		    texto = new JTextField();
		    add( texto,BorderLayout.NORTH );
		    
		    barra = new JProgressBar();
		    panel.add( barra );
		    panel.add( new JLabel( "ENVIANDO CORREO..." ) );

		    JPanel panelBotones = new JPanel();
		    JButton botonArranque = new JButton( "Arrancar" );
		    botonArranque.setBackground( SystemColor.control );
		    panelBotones.add( botonArranque );
		    botonArranque.addActionListener( new ActionListener() {
		      public void actionPerformed( ActionEvent evt ) {
			iniciaCuenta();
		      }
		    } );
		    
		    JButton botonParar = new JButton( "Parar" );
		    botonParar.setBackground( SystemColor.control );
		    panelBotones.add( botonParar );
		    botonParar.addActionListener( new ActionListener() {
		      public void actionPerformed( ActionEvent evt ) {
			detieneCuenta();
		      }
		    } );
		    
		    panel.add( panelBotones );
		  } 
		    
		  public void iniciaCuenta() {
		    if( hilo == null ) {
		      hilo = new ThreadCarga();
		      pideParar = false;
		      hilo.start();
		    }
		  }
		    
		  public void detieneCuenta() {
		    synchronized( objeto ) {
		      pideParar = true;
		      objeto.notify();
		      
		    }
		  } 

		    
		  class ThreadCarga extends Thread {
		    public void run() {
		      int min = 0;
		      int max = 100;

		      barra.setValue( min );
		      barra.setMinimum( min );
		      barra.setMaximum( max );

		      for (int i=min; i <= max; i++ ) {
			barra.setValue( i );
			if(100==barra.getValue()) {
				try {
					texto.setText("CORREO ENVIADO");
					sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				dispose();
			}
			texto.setText( ""+i );
			synchronized( objeto ) {
			  if( pideParar )
			    break;
			  try {
			    objeto.wait( 100 );
			  } catch( InterruptedException e ) {
			  }
			}
		      }
		      hilo = null;
		    }
		  }
		  
		
		}
	

