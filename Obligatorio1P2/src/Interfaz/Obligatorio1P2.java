/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import java.util.*;
import obligatorio1p2.Jugador;
import obligatorio1p2.Partida;

public class Obligatorio1P2 {

    public static Partida MisJugadores = new Partida();

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
                    String j1 = jugadorQueJuega();
                    String j2 = jugadorQueJuega();
                    while (!j2.equals(j1)) {
                        j2 = jugadorQueJuega();
                    }
                    break;
                case 3:

                    break;
                case 4:
                    mostrarRanking();
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

    public static void mostrarRanking() {
        ArrayList<Jugador> lista = MisJugadores.getListaJugadores();
        Collections.sort(lista);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i) + " | " + "#".repeat(lista.get(i).getGanadas()));
        }
    }

    public static String jugadorQueJuega() {
        Scanner lectorLine = new Scanner(System.in);
        ArrayList<Jugador> lista = MisJugadores.getListaJugadores();
        System.out.println("Eliga jugador que va a jugar por su alias:");
        String j = lectorLine.nextLine();
        Jugador aux = new Jugador();
        aux.setAlias(j);
        while (!lista.contains(j)) {
            System.out.println("El jugador no existe, ingrese otro");
            j = lectorLine.nextLine();
        }
        return j;
    }

    public static String iniciarPartida() {
        Scanner lectorLine = new Scanner(System.in);
        System.out.println("Ingresar cuadro en el que quiere jugar");
        String jugada = lectorLine.nextLine().toUpperCase();
        if(jugada != "X"){
            while (jugada.length() != 2 || (jugada.charAt(0) != 'A' || jugada.charAt(0) != 'B' || jugada.charAt(0) != 'C') || (jugada.charAt(1) != '1' || jugada.charAt(1) != '2' || jugada.charAt(1) != '3')) {
                System.out.println("Jugada incorrecta, reingrese");
                jugada = lectorLine.nextLine();
            }
        }
        return jugada;
    }

    public static String ingresarJugada() {
        Scanner lectorLine = new Scanner(System.in);
        System.out.println("Ingresar jugada");
        String jugada = lectorLine.nextLine().toLowerCase();
        if (jugada != "x") {
            while (jugada.length() != 2 || (jugada.charAt(0) != 'a' || jugada.charAt(0) != 'b' || jugada.charAt(0) != 'c') || (jugada.charAt(1) != '1' || jugada.charAt(1) != '2' || jugada.charAt(1) != '3')) {
                System.out.println("Jugada incorrecta, reingrese");
                jugada = lectorLine.nextLine();
            }
        }
        return jugada;
    }

}
