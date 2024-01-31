package trabajo.Database;
import trabajo.Database.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Clases.*;



public class Files {
	
	GestorBD bd= new GestorBD();
	
	public void sincFile() {//Vuelca los maps sin ventas
		List<Calzado> cz= new ArrayList<>(bd.getCalzadoList());
		Map<Integer, Integer> mapa = new HashMap<>();//codigo, cantidad
			FileWriter fichero = null;
		    PrintWriter pw = null;
		    try{fichero = new FileWriter("src\\Datos\\Ventas.txt");
		        pw = new PrintWriter(fichero);
		       
		        for (Calzado p : cz) { mapa.put(p.getCodigo(), 0);        }		
		        
		        for(Map.Entry<Integer, Integer> entry : mapa.entrySet()) {    pw.print(entry+"\n");	        }

		    }catch (Exception e) {
		        e.printStackTrace();
		    }finally {
		       try{if (null != fichero)
		          fichero.close();
		       }catch (Exception e2) {
		          e2.printStackTrace();}}
	}
	
	public Map<Integer,Integer> readFile() {//lee los maps
		Map<Integer,Integer> l= new HashMap<>();
		try (Scanner scanner = new Scanner(new File("src\\Datos\\Ventas.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] trozo= linea.split(":");
                l.put(Integer.valueOf(trozo[0]) ,Integer.valueOf(trozo[1]));      
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return l;
	}
	
	public void uploadFile(int codigo) {//actualiza los maps
		//read file and get X vendidos
		String fileName="src\\Datos\\Ventas.txt";
		FileWriter fichero=null;
		PrintWriter pw=null;
		
		//lectura con el readFile y editar con el get
		Map<Integer, Integer> mapa= new HashMap<>(readFile());	//lista de mapas sin actualizar
		mapa.put(codigo, mapa.get(codigo)+1);

		 try{fichero = new FileWriter(fileName);
	        pw = new PrintWriter(fichero);	        
	        for(Map.Entry<Integer, Integer> entry : mapa.entrySet()) {    pw.print(entry+"\n");	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	       try{if (null != fichero)
	          fichero.close();
	       }catch (Exception e2) {
	          e2.printStackTrace();}}
		
		
		
		
	}

}
