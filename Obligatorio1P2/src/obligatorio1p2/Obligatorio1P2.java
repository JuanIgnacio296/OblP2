/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio1p2;

import java.util.*;

public class Obligatorio1P2 {

    public static void main(String[] args) {
        Scanner lectorInt = new Scanner(System.in);
        boolean seguir = true;
        while (seguir){
            mostrarMenu();
            int opcion = lectorInt.nextInt();
            switch(opcion){
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    seguir=false;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
        tablero();

    }

    public static void tablero() {
        String verdeFondo = "\u001B[42m";
        String reset = "\u001B[0m";
        System.out.println(verdeFondo + "*******************" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*******************" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*******************" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset + " | | " + verdeFondo + "*" + reset);
        System.out.println(verdeFondo + "*******************" + reset);

    }
    public static void mostrarMenu(){
        System.out.println("Menu:");
        System.out.println("1-Registrar un Jugador");
        System.out.println("2-Jugar al Gran Tateti entre 2 personas");
        System.out.println("3-Jugar al Gran Tateti vs la Computadora");
        System.out.println("4-Ranking");
        System.out.println("5-Salir");
        System.out.println("Que desea hacer:");
    }
    public static void registrarJugador(){
        Scanner lectorInt = new Scanner(System.in);
        Scanner lectorLine = new Scanner(System.in);
        System.out.println("Ingrese nombre");
        String nombre = lectorLine.nextLine();
        System.out.println("Ingrese edad");
        int edad = lectorInt.nextInt();
        System.out.println("Ingrese alias");
        String alias = lectorLine.nextLine();
    }

}
