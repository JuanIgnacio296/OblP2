/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import java.util.*;
import obligatorio1p2.*;

public class Obligatorio1P2 {

        public static Partida MisJugadores = new Partida();
        public static Scanner lectorInt = new Scanner(System.in);
        public static Scanner lectorLine = new Scanner(System.in);

        public static void main(String[] args) {

                Jugador J1 = new Jugador("A", 1, "A");
                Jugador J2 = new Jugador("B", 2, "B");
                MisJugadores.agregarJugador(J1);
                MisJugadores.agregarJugador(J2);
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
                                        System.out.println("El dato que ingreso no es nùmerico, reingrese:");
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
                                        while (j2.equals(j1)) {
                                                System.out.println("Elija otro jugador");
                                                j2 = jugadorQueJuega();
                                        }
                                        Tablero tab = new Tablero(j1, j2);
                                        String jugador = j1;
                                        String jugada = "A";
                                        String jugadaMini = ingresarMiniCuadro();
                                        char[][] miniCuadro = tab.tableroJugada(jugadaMini);
                                        boolean jugar = true;
                                        while (jugar) {
                                                mostrarTablero(tab.getListaTableros(), jugadaMini);
                                                jugada = ingresarJugada();
                                                if (!jugada.equals("X")) {
                                                        if (!tab.colocarFicha(jugada, miniCuadro,jugador) && !jugada.equals("X")) {
                                                                System.out.println("Lugar ya ocupado");
                                                                jugada = ingresarJugada();
                                                        }
                                                        jugadaMini = jugada;
                                                        miniCuadro = tab.tableroJugada(jugadaMini);
                                                        jugador = alternar(jugador, j1, j2);
                                                }else{
                                                        jugar = false;
                                                }
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

        }

        public static String colorFicha(char ficha) {
                String retorno = " ";
                String rojo = "\u001B[31m";
                String azul = "\u001B[34m";
                String reset = "\u001B[0m";
                if (ficha == 'X') {
                        retorno = rojo + "X" + reset;
                } else {
                        if (ficha == 'O') {
                                retorno = azul + "O" + reset;
                        }
                }

                return retorno;
        }

        /*
         * public static void tableroActual(String letra, String pos, String
         * miniTablero) {
         * 
         * String [][] tablero = new String [9][9];
         * mostrarTablero(tablero);
         * String [][] aux = new String[3][3];
         * int fila = -1;
         * int col = Character.getNumericValue(pos.charAt(1)) - 1;
         * switch (pos.charAt(0)) {
         * case 'A':
         * fila = 0;
         * break;
         * case 'B':
         * fila = 1;
         * break;
         * case 'C':
         * fila = 2;
         * break;
         * default:
         * break;
         * }
         * aux[fila][col]=letra;
         * 
         * }
         */

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

        public static String alternar(String j, String j1, String j2) {
                String jug = j;
                if (jug.equals(j1)) {
                        jug = j2;
                } else {
                        jug = j1;
                }
                return jug;

        }

        public static void mostrarRanking() {
                ArrayList<Jugador> lista = MisJugadores.getListaJugadores();
                Collections.sort(lista);
                for (int i = 0; i < lista.size(); i++) {
                        System.out.println(lista.get(i) + " | " + "#".repeat(lista.get(i).getGanadas()));
                }
        }

        public static String jugadorQueJuega() {
                System.out.println("Eliga jugador que va a jugar por su alias:");
                String j = lectorLine.nextLine();
                while (MisJugadores.validarAlias(j)) {
                        System.out.println("El jugador no existe, ingrese otro");
                        j = lectorLine.nextLine();
                }
                return j;
        }

        public static String ingresarMiniCuadro() {
                System.out.println("Ingresar cuadro en el que quiere jugar");
                String jugada = lectorLine.nextLine().toUpperCase();
                if (jugada != "X") {
                        while (!jugadaValida(jugada)) {
                                System.out.println("Jugada incorrecta, reingrese");
                                jugada = lectorLine.nextLine();
                        }
                }
                return jugada;
        }

        public static String ingresarJugada() {
                System.out.println("Ingresar jugada");
                String jugada = lectorLine.nextLine().toUpperCase();
                while (!jugadaValida(jugada)) {
                        System.out.println("Jugada incorrecta, reingrese");
                        jugada = lectorLine.nextLine();
                }
                return jugada;
        }

        public static boolean jugadaValida(String jugada) {
                return (jugada.equals("X") || jugada.equals("M")) || ((jugada.length() == 2)
                                && (jugada.charAt(0) == 'A' || jugada.charAt(0) == 'B' || jugada.charAt(0) == 'C')
                                && (jugada.charAt(1) == '1' || jugada.charAt(1) == '2' || jugada.charAt(1) == '3'));
        }

        public static void ponerJugada(char tipo, String tablero, String pos) {

        }

        public static void mostrarTablero(char[][][] listaTablero, String miniTablero) {
                String amarilloFondo = "\u001B[43m";
                String verdeFondo = "\u001B[42m";
                String reset = "\u001B[0m";
                switch (miniTablero) {
                        case "A1":
                                System.out.println(amarilloFondo + "*******" + reset + verdeFondo + "************"
                                                + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                amarilloFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                amarilloFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*******" + reset + verdeFondo + "************"
                                                + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);

                                break;
                        case "A2":
                                System.out.println(
                                                verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                                + "******" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + amarilloFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + amarilloFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                                + "******" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                break;
                        case "A3":
                                System.out.println(verdeFondo + "************" + reset + amarilloFondo + "*******"
                                                + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + amarilloFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + amarilloFondo + "*"
                                                                + reset + "-+-+-" + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + amarilloFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + amarilloFondo + "*"
                                                                + reset + "-+-+-" + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "************" + reset + amarilloFondo + "*******"
                                                + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                break;
                        case "B1":
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*******" + reset + verdeFondo + "************"
                                                + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                amarilloFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                amarilloFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*******" + reset + verdeFondo + "************"
                                                + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                break;
                        case "B2":
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                                + "******" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + amarilloFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + amarilloFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                                + "******" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                break;
                        case "B3":
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "************" + reset + amarilloFondo + "*******"
                                                + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + amarilloFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + amarilloFondo + "*"
                                                                + reset + "-+-+-" + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + amarilloFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + amarilloFondo + "*"
                                                                + reset + "-+-+-" + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "************" + reset + amarilloFondo + "*******"
                                                + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                break;
                        case "C1":
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*******" + reset + verdeFondo + "************"
                                                + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                amarilloFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                amarilloFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(amarilloFondo + "*******" + reset + verdeFondo + "************"
                                                + reset);
                                break;
                        case "C2":
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                                + "******" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + amarilloFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + amarilloFondo + "*" + reset
                                                                + "-+-+-" + amarilloFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + amarilloFondo
                                                + "*" + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                                + "******" + reset);
                                break;
                        case "C3":
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][0][0]) + "|"
                                                + colorFicha(listaTablero[0][0][1]) + "|"
                                                + colorFicha(listaTablero[0][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][0][0]) + "|"
                                                + colorFicha(listaTablero[1][0][1]) + "|"
                                                + colorFicha(listaTablero[1][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][0][0]) + "|"
                                                + colorFicha(listaTablero[2][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][1][0]) + "|"
                                                + colorFicha(listaTablero[0][1][1]) + "|"
                                                + colorFicha(listaTablero[0][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][1][0]) + "|"
                                                + colorFicha(listaTablero[1][1][1]) + "|"
                                                + colorFicha(listaTablero[1][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][1][0]) + "|"
                                                + colorFicha(listaTablero[2][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[0][2][0]) + "|"
                                                + colorFicha(listaTablero[0][2][1]) + "|"
                                                + colorFicha(listaTablero[0][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[1][2][0]) + "|"
                                                + colorFicha(listaTablero[1][2][1]) + "|"
                                                + colorFicha(listaTablero[1][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[2][2][0]) + "|"
                                                + colorFicha(listaTablero[2][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*******************" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][0][0]) + "|"
                                                + colorFicha(listaTablero[3][0][1]) + "|"
                                                + colorFicha(listaTablero[3][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][0][0]) + "|"
                                                + colorFicha(listaTablero[4][0][1]) + "|"
                                                + colorFicha(listaTablero[4][0][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][0][0]) + "|"
                                                + colorFicha(listaTablero[5][0][1]) + "|"
                                                + colorFicha(listaTablero[5][0][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][1][0]) + "|"
                                                + colorFicha(listaTablero[3][1][1]) + "|"
                                                + colorFicha(listaTablero[3][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][1][0]) + "|"
                                                + colorFicha(listaTablero[4][1][1]) + "|"
                                                + colorFicha(listaTablero[4][1][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][1][0]) + "|"
                                                + colorFicha(listaTablero[5][1][1]) + "|"
                                                + colorFicha(listaTablero[5][1][2]) + verdeFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + verdeFondo + "*"
                                                                + reset + "-+-+-" + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[3][2][0]) + "|"
                                                + colorFicha(listaTablero[3][2][1]) + "|"
                                                + colorFicha(listaTablero[3][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[4][2][0]) + "|"
                                                + colorFicha(listaTablero[4][2][1]) + "|"
                                                + colorFicha(listaTablero[4][2][2]) + verdeFondo + "*"
                                                + reset + colorFicha(listaTablero[5][2][0]) + "|"
                                                + colorFicha(listaTablero[5][2][1]) + "|"
                                                + colorFicha(listaTablero[5][2][2]) + verdeFondo + "*" + reset);
                                System.out.println(verdeFondo + "************" + reset + amarilloFondo + "*******"
                                                + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][0][0]) + "|"
                                                + colorFicha(listaTablero[6][0][1]) + "|"
                                                + colorFicha(listaTablero[6][0][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][0][0]) + "|"
                                                + colorFicha(listaTablero[7][0][1]) + "|"
                                                + colorFicha(listaTablero[7][0][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[8][0][0]) + "|"
                                                + colorFicha(listaTablero[8][0][1]) + "|"
                                                + colorFicha(listaTablero[2][0][2]) + amarilloFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + amarilloFondo + "*"
                                                                + reset + "-+-+-" + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][1][0]) + "|"
                                                + colorFicha(listaTablero[6][1][1]) + "|"
                                                + colorFicha(listaTablero[6][1][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][1][0]) + "|"
                                                + colorFicha(listaTablero[7][1][1]) + "|"
                                                + colorFicha(listaTablero[7][1][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[8][1][0]) + "|"
                                                + colorFicha(listaTablero[8][1][1]) + "|"
                                                + colorFicha(listaTablero[2][1][2]) + amarilloFondo + "*" + reset);
                                System.out.println(
                                                verdeFondo + "*" + reset + "-+-+-" + verdeFondo + "*" + reset + "-+-+-"
                                                                + amarilloFondo + "*"
                                                                + reset + "-+-+-" + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "*" + reset + colorFicha(listaTablero[6][2][0]) + "|"
                                                + colorFicha(listaTablero[6][2][1]) + "|"
                                                + colorFicha(listaTablero[6][2][2]) + verdeFondo + "*"
                                                + reset
                                                + colorFicha(listaTablero[7][2][0]) + "|"
                                                + colorFicha(listaTablero[7][2][1]) + "|"
                                                + colorFicha(listaTablero[7][2][2]) + amarilloFondo + "*"
                                                + reset + colorFicha(listaTablero[8][2][0]) + "|"
                                                + colorFicha(listaTablero[8][2][1]) + "|"
                                                + colorFicha(listaTablero[2][2][2]) + amarilloFondo + "*" + reset);
                                System.out.println(verdeFondo + "************" + reset + amarilloFondo + "*******"
                                                + reset);
                                break;
                        default:
                                break;
                }

        }

}
