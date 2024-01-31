package Funciones;

import static org.junit.Assert.assertTrue;

public class Recursividad {

	public static void main(String[] args){
	    //Pasar por parametro contrasena
		//Si el numero es capicua, no es robusto
	    int num = 2444;
	    if (simetric(num)) {
	            System.out.println("No es robusto");}
	        else {
	            System.out.println("Es robusto");}
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
