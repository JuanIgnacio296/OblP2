/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import java.util.*;
import obligatorio1p2.Jugador;
import obligatorio1p2.Jugadores;

public class Obligatorio1P2 {
    //hola
    public static Jugadores MisJugadores = new Jugadores();

    public static void main(String[] args) {
        Scanner lectorInt = new Scanner(System.in);

        boolean seguir = true;
        while (seguir) {
            mostrarMenu();
            int opcion = 0;
            boolean correcto = false;
            while (!correcto) {
                try {
                    opcion = lectorInt.nextInt();
                    correcto = true;
                } catch (InputMismatchException e) {
                    System.out.println("El dato que ingreso no es numerico, reingrese:");
                    lectorInt.nextLine();
                }
            }
            switch (opcion) {
                case 1:
                    registrarJugador();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    seguir = false;
                    break;
                default:
                    System.out.println("No hay mas opciones");
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

    public static void mostrarMenu() {
        System.out.println("Menu:");
        System.out.println("1-Registrar un Jugador");
        System.out.println("2-Jugar al Gran Tateti entre 2 personas");
        System.out.println("3-Jugar al Gran Tateti vs la Computadora");
        System.out.println("4-Ranking");
        System.out.println("5-Salir");
        System.out.println("Que desea hacer:");
    }

    public static void registrarJugador() {
        Scanner lectorInt = new Scanner(System.in);
        Scanner lectorLine = new Scanner(System.in);
        System.out.println("Ingrese nombre");
        String nombre = lectorLine.nextLine();
        System.out.println("Ingrese edad");
        int edad = 0;
        boolean correcto = false;
        while (!correcto) {
            try {
                edad = lectorInt.nextInt();
                correcto = true;
            } catch (InputMismatchException e) {
                System.out.println("El dato que ingreso no es numerico, reingrese:");
                lectorInt.nextLine();
            }
        }

        System.out.println("Ingrese alias");
        String alias = lectorLine.nextLine();
        while (!MisJugadores.validarAlias(alias)) {
            System.out.println("Ya existe ese alias, por favor reingrese");
            alias = lectorLine.nextLine();
        }
        Jugador j = new Jugador(nombre, edad, alias);
        MisJugadores.agregarJugador(j);
    }

}
