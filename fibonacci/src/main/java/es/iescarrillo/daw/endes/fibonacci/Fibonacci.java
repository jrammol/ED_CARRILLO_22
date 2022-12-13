package es.iescarrillo.daw.endes.fibonacci;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * Serie de Fibonacci en Java
		 * Programa que imprima los N primeros n�meros de la serie de Fibonacci.
		 * El primer n�mero de la serie es 1, el segundo n�mero es 1 y cada uno de los                                    
		 * siguientes es la suma de los dos anteriores.
		 * 1, 1, 2, 3, 5, 8, 13,  ....... , N
		 */
		
		Scanner consola=new Scanner(System.in);
		int numero, fibo1, fibo2, i;
		
		do{
            System.out.print("Introduce numero mayor que 1: ");
            numero = consola.nextInt();
        }while(numero<=1);
  
        System.out.println("Los " + numero + " primeros t�rminos de la serie de Fibonacci son:");                 

        fibo1=1;
        fibo2=1;

        System.out.print(fibo1 + " ");
        for(i=2;i<=numero;i++){
            System.out.print(fibo2 + " ");
            fibo2 = fibo1 + fibo2;
            fibo1 = fibo2 - fibo1;
        }
        System.out.println();

        consola.close();

	}

}
