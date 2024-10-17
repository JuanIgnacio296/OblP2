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
                                        boolean [][] tabJ; 
                                        String jugada = "A";
                                        String jugadaMini = ingresarMiniCuadro();
                                        char[][] miniCuadro = tab.tableroJugada(jugadaMini);
                                        boolean jugar = true;
                                        while (jugar) {
                                                tablero(tab, jugadaMini);
                                                jugada = ingresarJugada();
                                                if (!jugada.equals("X")) {
                                                        if (!tab.colocarFicha(jugada, miniCuadro, jugador)
                                                                        && !jugada.equals("X")) {
                                                                System.out.println("Lugar ya ocupado");
                                                                jugada = ingresarJugada();
                                                        }
                                                        jugadaMini = jugada;
                                                        miniCuadro = tab.tableroJugada(jugadaMini);
                                                        if(jugador.equals(j1)){
                                                                tabJ = tab.getTableroX();
                                                        }else{
                                                                tabJ = tab.getTableroO();
                                                        }
                                                        if(tab.comprobarGanados(tabJ, tab.posicion(jugadaMini))){
                                                                
                                                                jugar = false;
                                                                tablero(tab, jugadaMini);
                                                                System.out.println("Gano "+ jugador);
                                                        }
                                                        jugador = alternar(jugador, j1, j2);

                                                } else {
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

        public static void tablero(Tablero tab, String miniTablero) {
                char[][][] listaTablero = tab.getListaTableros();
                String amarilloFondo = "\u001B[43m";
                String verdeFondo = "\u001B[42m";
                String reset = "\u001B[0m";
                String borde = "";
                
                String impresion = borde + "\n";
                for (int f = 0; f < 3; f++) {
                        char letra = ' ';
                        switch (f) {
                                case 0:
                                        letra = 'A';
                                        break;
                                case 1:
                                        letra = 'B';
                                        break;
                                case 2:
                                        letra = 'C';
                                        break;
                                default:
                                        break;
                        }
                        if (miniTablero.charAt(1) == '1' && miniTablero.charAt(0)==letra) {
                                borde = amarilloFondo + "*******" + reset + verdeFondo + "************" + reset;
                        } else {
                                if (miniTablero.charAt(1) == '2'&& miniTablero.charAt(0)==letra) {
                                        borde = verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                        + "******" + reset;
                                } else {
                                        if(miniTablero.charAt(1) == '3' && miniTablero.charAt(0)==letra){
                                                borde = verdeFondo + "************" + reset + amarilloFondo + "*******" + reset;
                                        }else{
                                                borde = verdeFondo + "*******************" + reset;
                                        }
                                }
                        }
                        impresion += "\n" + borde + "\n";
                        for (int z = 0; z < 3; z++) {
                                for (int i = 0 + (3 * f); i < 3 + (3 * f); i++) {
                                        String cuadroGanado = "";
                                        impresion += colorFondo(miniTablero, i + 1-(3*f), i-(3*f), letra) + "*" + reset;
                                        for (int j = 0; j < 3; j++) {
                                                cuadroGanado+=colorFicha(listaTablero[i][z][j]);
                                                if (j < 2) {
                                                        cuadroGanado += "|";
                                                }

                                        }
                                        impresion+=miniCuadroGanado(cuadroGanado, tab, i);
                                }
                                impresion += colorFondo(miniTablero, 3, 0, letra) + "*" + reset;
                                String medio = colorFondo(miniTablero, 1, 0, letra) + "*" + reset + "-+-+-"
                                                + colorFondo(miniTablero, 2, 1, letra) + "*" + reset
                                                + "-+-+-" + colorFondo(miniTablero, 3, 2, letra) + "*"
                                                + reset + "-+-+-" + colorFondo(miniTablero, 3, 0, letra) + "*" + reset;
                                if (z < 2) {
                                        impresion += "\n" + medio + "\n";
                                }
                                
                        }
                }
                impresion += "\n" + borde;
                System.out.println(impresion);
        }
        public static String miniCuadroGanado(String texto, Tablero tablero, int pos){
                String rojo = "\u001B[31m";
                String azul = "\u001B[34m";
                String reset = "\u001B[0m";
                String retorno= texto;
                int[]coord = tablero.posicion(tablero.jugada(pos));
                if((tablero.getTableroX())[coord[0]][coord[1]]){
                        retorno=rojo+texto+reset;
                }
                if((tablero.getTableroO())[coord[0]][coord[1]]){
                        retorno=azul+texto+reset;
                }
                return retorno;
        }
        public static String colorFondo(String miniTablero, int pos1, int pos2, char letra) {
                String color = "";
                int num = Character.getNumericValue(miniTablero.charAt(1));
                if ((pos1==num || num==pos2) && miniTablero.charAt(0)==letra) {
                        color = "\u001B[43m"; // Amarillo
                } else {
                        color = "\u001B[42m"; // Verde
                }
                return color;
        }
       
}
