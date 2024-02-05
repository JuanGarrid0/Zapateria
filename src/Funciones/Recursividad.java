package Funciones;

import static org.junit.Assert.assertFalse;

import org.junit.validator.PublicClassValidator;

public class Recursividad {

	public static void main(String[] args){
	    //Pasar por parametro contrasena
		//Si el numero es capicua, no es robusto
		/*
		
	    int num = 444344;
	    if (simetric(num)) {
	            System.out.println("Es capicua");}
	        else {
	            System.out.println("No es capicua");}*/
	}
	
	
	public static boolean capicua(int contrasena) {
		 if (simetric(contrasena)) {
	            System.out.println("No es robusto");
	            return false;}
	        else {
	            System.out.println("Es robusto");
	            return true;}		
	}
	
	static int digitos(int num){
	    if(num==0) return 0;
	    return 1 + digitos(num/10);
	}
	
	static boolean simetric(int num){
		String numero="";
	    if(digitos(num)<=1) {	return true;  }
	    else {
	    	String nu = Integer.toString(num);
	    	String[]trozo=nu.split("");
	    	if(trozo[0].equals(trozo[trozo.length-1])) {//Revisar comparativa
	    		int counter=0;
	    		for(String s : trozo) {
	    			if(counter!=0 && counter!=trozo.length) {		numero=numero+s; 	} 
	    			counter++;
	    		}    		 
	    		if(numero =="") {return true;	}
	    		  return simetric((Integer.parseInt(numero)));	  
	    	} 
	        else { return false;}
	          
	    }
	}
	

}
