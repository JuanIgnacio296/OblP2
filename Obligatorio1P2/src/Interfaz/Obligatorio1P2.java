// Juan Ignacio Alvarez - nro. estudiante: 319853
// Facundo Lorenzoni - nro. estudiante: 342249

package Interfaz;

import java.util.*;
import obligatorio1p2.*;

public class Obligatorio1P2 {

        public static Partida partida = new Partida();
        public static Scanner lectorInt = new Scanner(System.in);
        public static Scanner lectorLine = new Scanner(System.in);

        public static void main(String[] args) {
                bienvenidos();
                //Datos de prueba:
                Jugador J1 = new Jugador("A", 1, "A");
                Jugador J2 = new Jugador("B", 2, "B");
                partida.agregarJugador(J1);
                partida.agregarJugador(J2);
                //
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
                                //Registrar jugador
                                        registrarJugador();
                                        break;
                                case 2:
                                //Jugador vs Jugador
                                        //Se inicializan todas las variables
                                        System.out.println("Jugadores disponibles: " + partida.getListaJugadores());
                                        String j1 = jugadorQueJuega();
                                        String j2 = jugadorQueJuega();
                                        boolean magj1 = true;
                                        boolean magj2 = true;
                                        while (j2.equals(j1)) {
                                                System.out.println("Elija otro jugador");
                                                j2 = jugadorQueJuega();
                                        }
                                        Tablero tab = new Tablero(j1, j2);
                                        String jugador = j1;
                                        boolean[][] tabJ;
                                        String jugada = "A";
                                        boolean jugar = true;
                                        String jugadaMini = ingresarMiniCuadro();
                                        char[][] miniCuadro = new char[3][3];
                                        if (jugadaMini.equals("X")) {
                                                jugar = false;
                                        } else {
                                                miniCuadro = tab.tableroJugada(jugadaMini);
                                        }
                                        while (jugar) {
                                                //Se muestra el tablero y se pide ingreso de una jugada
                                                System.out.println("Turno de: " + jugador);
                                                tablero(tab, jugadaMini);
                                                jugada = ingresarJugada();
                                                if (!jugada.equals("X")) {
                                                        //Jugada magica
                                                        if (jugada.equals("M")) {
                                                                //Si es que tiene habilitada la jugada magica se utiliza y se desahlita
                                                                if (jugador.equals(j1)&&magj1) {
                                                                        tab.jugadaMagica(jugadaMini);
                                                                        jugada = ingresarJugada();
                                                                        miniCuadro = tab.tableroJugada(jugadaMini);
                                                                        magj1=false;
                                                                }else{
                                                                        if (jugador.equals(j2)&&magj2) {
                                                                                tab.jugadaMagica(jugadaMini);
                                                                                jugada = ingresarJugada();
                                                                                miniCuadro = tab.tableroJugada(jugadaMini);
                                                                                magj2=false;
                                                                        }else{
                                                                                //Si ya se ingreso la jugada magica se pide una jugada normal
                                                                                while(jugada.equalsIgnoreCase("M")){
                                                                                        System.out.println("Ya se ha usado la jugada magica");
                                                                                        jugada = ingresarJugada();
                                                                                }
                                                                        }
                                                                }

                                                        }
                                                        //Aqui se coloca la ficha, segun quien sea el jugador, en el tablero
                                                        if (!tab.colocarFicha(jugada, miniCuadro, jugador)
                                                                        && !jugada.equals("X")) {
                                                                System.out.println("Lugar ya ocupado");
                                                                jugada = ingresarJugada();
                                                        }
                                                        //Se cambia al mini tablero correspondiente
                                                        jugadaMini = jugada;
                                                        miniCuadro = tab.tableroJugada(jugadaMini);
                                                        //Estos son los tableros que verifican los minitableros ganados de cada jugador
                                                        if (jugador.equals(j1)) {
                                                                tabJ = tab.getTableroX();
                                                        } else {
                                                                tabJ = tab.getTableroO();
                                                        }
                                                        //Se comprueba si alguno de los jugadores gano el juego
                                                        if (tab.comprobarGanados(tabJ)) {

                                                                jugar = false;
                                                                tablero(tab, jugadaMini);
                                                                System.out.println("Gano " + jugador);
                                                                partida.aumentarGanadas(jugador);
                                                        }
                                                        jugador = alternar(jugador, j1, j2);

                                                } else {
                                                        //Si se ingresa X el otro jugador gana
                                                        System.out.println("Gano: " + alternar(jugador, j1, j2));
                                                        partida.aumentarGanadas(jugador);
                                                        jugar = false;
                                                }

                                                if (tab.empate(miniCuadro)) {
                                                        jugar = false;
                                                        System.out.println("EMPATE");
                                                }
                                        }
                                        break;
                                case 3:
                                //Jugador vs Computadora
                                        //Se inicializan las varaibles correspondientes
                                        System.out.println("Jugadores disponibles: " + partida.getListaJugadores());
                                        String j = jugadorQueJuega();
                                        String c = "Computadora";
                                        tab = new Tablero(j, c);
                                        jugador = j;
                                        jugada = "A";
                                        jugadaMini = ingresarMiniCuadro();
                                        if (jugadaMini.equals("X")) {
                                                jugar = false;
                                        }
                                        miniCuadro = tab.tableroJugada(jugadaMini);
                                        jugar = true;
                                        while (jugar) {
                                                //Se muestra el tablero y segun de quien sea el turno se hace una u otra cosa
                                                tablero(tab, jugadaMini);
                                                if (jugador == j) {
                                                        System.out.println("Turno de " + j);
                                                        jugada = ingresarJugada();
                                                } else {
                                                        jugada = tab.jugadaComputadora();
                                                        System.out.println("La jugada de la computadora fue: "+ jugada);
                                                }
                                                if (!jugada.equals("X")) {
                                                        //Se coloca la ficha correspondiente
                                                        if (!tab.colocarFicha(jugada, miniCuadro, jugador)
                                                                        && !jugada.equals("X")) {
                                                                if (jugador == j) {
                                                                        //Si el lugar esta ocupado se le informa
                                                                        System.out.println("Lugar ya ocupado");
                                                                        jugada = ingresarJugada();
                                                                } else {
                                                                        //Si es la computadora no, y se vuelve a pedir otra nueva posicion
                                                                        jugada = tab.jugadaComputadora();
                                                                }
                                                        }
                                                        jugadaMini = jugada;
                                                        miniCuadro = tab.tableroJugada(jugadaMini);
                                                        if (jugador.equals(j)) {
                                                                tabJ = tab.getTableroX();
                                                        } else {
                                                                tabJ = tab.getTableroO();
                                                        }
                                                        if (tab.comprobarGanados(tabJ) && !tab.empate(miniCuadro)) {
                                                                jugar = false;
                                                                tablero(tab, jugadaMini);
                                                                System.out.println("Gano " + jugador);

                                                        }
                                                        jugador = alternar(jugador, j, c);

                                                } else {
                                                        System.out.println("Gano: " + alternar(jugador, j, c));

                                                        jugar = false;
                                                }
                                                if (tab.empate(miniCuadro)) {
                                                        jugar = false;
                                                        System.out.println("EMPATE");
                                                }
                                        }
                                        break;
                                case 4:
                                //Ranking
                                        mostrarRanking();
                                        break;
                                case 5:
                                //Salir
                                        seguir = false;
                                        break;
                                default:
                                        System.out.println("No hay mas opciones");
                                        break;
                        }
                }

        }
        //Animacion de bienvenida
        public static void bienvenidos() {
                String mensaje = "Bienvenidos";
                for (int i = 0; i < mensaje.length(); i++) {
                        System.out.print(mensaje.charAt(i));

                        esperar(300);
                }
                esperar(2000);
                limpiarPantalla();
        }
        //Segun la ficha que sea y si esta ganado el mini cuadro o no, se pinta de un color determinado
        public static String colorFicha(char ficha, int num, Tablero tab) {
                String retorno = " ";
                String rojo = "\u001B[31m";
                String azul = "\u001B[34m";
                String reset = "\u001B[0m";
                boolean estaGanada = false;
                String colorGanador = "";
                int[] pos = tab.posicion(tab.jugada(num));
                boolean[][] tabX = tab.getTableroX();
                boolean[][] tabO = tab.getTableroO();
                if (tabX[pos[0]][pos[1]]) {
                        colorGanador = rojo;
                        estaGanada = true;
                } else {
                        if (tabO[pos[0]][pos[1]]) {
                                colorGanador = azul;
                                estaGanada = true;
                        }
                }

                if (ficha == 'X') {
                        if (estaGanada) {
                                retorno = colorGanador + "X" + reset;
                        } else {
                                retorno = rojo + "X" + reset;
                        }
                } else {
                        if (ficha == 'O') {
                                if (estaGanada) {
                                        retorno = colorGanador + "O" + reset;
                                } else {

                                        retorno = azul + "O" + reset;
                                }
                        }
                }

                return retorno;
        }

        private static void esperar(int milisegundos) {
                try {
                        Thread.sleep(milisegundos);
                } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                }
        }

        private static void limpiarPantalla() {
                try {
                        if (System.getProperty("os.name").contains("Windows")) {
                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        } else {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                        }
                } catch (Exception e) {
                        System.out.println("No se pudo limpiar la pantalla.");
                }
        }

        public static void mostrarMenu() {
                System.out.println(
                                """
                                                ░██████╗░ ██████╗░ ░█████╗░ ███╗░░██╗   ████████╗ ░█████╗░ ████████╗ ███████╗ ████████╗ ██╗
                                                ██╔════╝░ ██╔══██╗ ██╔══██╗ ████╗░██║   ╚══██╔══╝ ██╔══██╗ ╚══██╔══╝ ██╔════╝ ╚══██╔══╝ ██║
                                                ██║░░██╗░ ██████╔╝ ███████║ ██╔██╗██║   ░░░██║░░░ ███████║ ░░░██║░░░ █████╗░░ ░░░██║░░░ ██║
                                                ██║░░╚██╗ ██╔══██╗ ██╔══██║ ██║╚████║   ░░░██║░░░ ██╔══██║ ░░░██║░░░ ██╔══╝░░ ░░░██║░░░ ██║
                                                ╚██████╔╝ ██║░░██║ ██║░░██║ ██║░╚███║   ░░░██║░░░ ██║░░██║ ░░░██║░░░ ███████╗ ░░░██║░░░ ██║
                                                ░╚═════╝░ ╚═╝░░╚═╝ ╚═╝░░╚═╝ ╚═╝░░╚══╝   ░░░╚═╝░░░ ╚═╝░░╚═╝ ░░░╚═╝░░░ ╚══════╝ ░░░╚═╝░░░ ╚═╝
                                                """);
                System.out.println("Menu:");
                System.out.println("1-Registrar un Jugador");
                System.out.println("2-Jugar al Gran Tateti entre 2 personas");
                System.out.println("3-Jugar al Gran Tateti vs la Computadora");
                System.out.println("4-Ranking");
                System.out.println("5-Salir");
                System.out.println("Que desea hacer:");
        }
        //Se piden los datos del jugador uno por uno
        //Si en edad se ingresa un dato no numerico se vuelve a pedir
        //Tambien se valida si el alias es unico fijandose en la lista de jugadores
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
                while (!partida.validarAlias(alias)) {
                        System.out.println("Ya existe ese alias, por favor reingrese");
                        alias = lectorLine.nextLine();
                }
                Jugador j = new Jugador(nombre, edad, alias);
                partida.agregarJugador(j);
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
                ArrayList<Jugador> lista = partida.getListaJugadores();
                Collections.sort(lista);
                for (int i = 0; i < lista.size(); i++) {
                        System.out.println(lista.get(i) + " | " + "#".repeat(lista.get(i).getGanadas()));
                }
        }
        //Se ingresa el jugador, por el alias, que va a jugar
        public static String jugadorQueJuega() {
                System.out.println("Eliga jugador que va a jugar por su alias:");
                String j = lectorLine.nextLine();
                while (partida.validarAlias(j)) {
                        System.out.println("El jugador no existe, ingrese otro");
                        j = lectorLine.nextLine();
                }
                return j;
        }
        //Se pide el mini cuadro del principio de la partida al que se juega
        public static String ingresarMiniCuadro() {
                System.out.println("Ingresar cuadro en el que quiere jugar");
                String jugada = lectorLine.nextLine().toUpperCase();
                while (!jugadaValida(jugada)) {
                        System.out.println("Jugada incorrecta, reingrese");
                        jugada = lectorLine.nextLine();
                }
                return jugada;
        }
        //Se pide la jugada que va ingresar
        public static String ingresarJugada() {
                System.out.println("Ingresar jugada");
                String jugada = lectorLine.nextLine().toUpperCase();
                while (!jugadaValida(jugada)) {
                        System.out.println("Jugada incorrecta, reingrese");
                        jugada = lectorLine.nextLine().toUpperCase();
                }
                return jugada;
        }
        //Este medtodo para validar todas las opciones posibles que se podrian ingresar
        public static boolean jugadaValida(String jugada) {
                return (jugada.equals("X") || jugada.equals("M")) || ((jugada.length() == 2)
                                && (jugada.charAt(0) == 'A' || jugada.charAt(0) == 'B' || jugada.charAt(0) == 'C')
                                && (jugada.charAt(1) == '1' || jugada.charAt(1) == '2' || jugada.charAt(1) == '3'));
        }
        //Muestra el tablero
        public static void tablero(Tablero tab, String miniTablero) {
                char[][][] listaTablero = tab.getListaTableros();
                String reset = "\u001B[0m";
                //primero se muestra el primero borde
                String impresion = imprimirBordeHorizontal(miniTablero, 'A', 'A') + "\n";
                //Este for es para recorrer las filas de la matriz grande
                for (int f = 0; f < 3; f++) {
                        char letra = ' ';
                        char letra2 = ' ';
                        //Las letras son para saber a que fila corresponde la posicion que se esta imprimiendo
                        switch (f) {
                                case 0:
                                        letra = 'A';
                                        letra2 = 'B';
                                        break;
                                case 1:
                                        letra = 'B';
                                        letra2 = 'C';
                                        break;
                                case 2:
                                        letra = 'C';
                                        letra2 = 'C';
                                        break;
                                default:
                                        break;
                        }
                        //este for recorre cada fila de 3 mini cuadros
                        for (int z = 0; z < 3; z++) {
                                //este for recorre las filas de cada mini cuadro
                                for (int i = 0 + (3 * f); i < 3 + (3 * f); i++) {
                                        String cuadroGanado = "";
                                        impresion += colorFondo(miniTablero, i + 1 - (3 * f), i - (3 * f), letra) + "*"
                                                        + reset;
                                        //este for es para recorrer cada fila interna
                                        for (int j = 0; j < 3; j++) {
                                                cuadroGanado += miniCuadroGanado(tab, i)
                                                                + colorFicha(listaTablero[i][z][j], i, tab) + reset;
                                                if (j < 2) {
                                                        cuadroGanado += miniCuadroGanado(tab, i) + "|" + reset;
                                                }
                                        }
                                        impresion += cuadroGanado;

                                }
                                impresion += colorFondo(miniTablero, 3, 0, letra) + "*" + reset;
                                //imprime las intersecciones de los minicuadros
                                String medio = colorFondo(miniTablero, 1, 0, letra) + "*" + reset
                                                + miniCuadroGanado(tab, 0 + (3 * f)) + "-+-+-" + reset +
                                                colorFondo(miniTablero, 2, 1, letra) + "*" + reset
                                                + miniCuadroGanado(tab, 1 + (3 * f))
                                                + "-+-+-" + reset + colorFondo(miniTablero, 3, 2, letra) + "*"
                                                + reset + miniCuadroGanado(tab, 2 + (3 * f)) + "-+-+-"
                                                + colorFondo(miniTablero, 3, 0, letra) + "*" + reset;
                                if (z < 2) {
                                        impresion += "\n" + medio + "\n";
                                }

                        }
                        impresion += "\n" + imprimirBordeHorizontal(miniTablero, letra, letra2) + "\n";

                }
                //Se imprime todo
                System.out.println(impresion);

        }
        //este metodo sirve para los bordes horizontales de * de la matriz
        public static String imprimirBordeHorizontal(String miniTablero, char letra, char letra2) {
                String amarilloFondo = "\u001B[43m";
                String verdeFondo = "\u001B[42m";
                String reset = "\u001B[0m";
                int posElegido = Character.getNumericValue(miniTablero.charAt(1));
                String borde = verdeFondo + "*******************" + reset;
                //Si la fila a la que corresponde es a la que se tiene que marcar de amarillo
                if (miniTablero.charAt(0) == letra || miniTablero.charAt(0) == letra2) {
                        //y la columna tambien lo es, entonces se pinta de amarillo la parte de abajo y de arriba
                        switch (posElegido) {
                                case 1:
                                        borde = amarilloFondo + "*******" + reset + verdeFondo + "************" + reset;
                                        break;
                                case 2:
                                        borde = verdeFondo + "******" + amarilloFondo + "*******" + reset + verdeFondo
                                                        + "******" + reset;
                                        break;
                                case 3:
                                        borde = verdeFondo + "************" + reset + amarilloFondo + "*******" + reset;
                                        break;

                                default:
                                        break;
                        }
                }
                return borde;

        }
        //este es para saber si esta ganado y pintarlo del color q le corresponde
        public static String miniCuadroGanado(Tablero tablero, int pos) {
                String rojo = "\u001B[31m";
                String azul = "\u001B[34m";
                String retorno = "";
                boolean[][] tabx = tablero.getTableroX();
                boolean[][] tabo = tablero.getTableroO();
                int[] coord = tablero.posicion(tablero.jugada(pos));
                if (tabx[coord[0]][coord[1]]) {
                        retorno = rojo;
                }
                if (tabo[coord[0]][coord[1]]) {
                        retorno = azul;
                }
                return retorno;
        }
        //Este para pintar los * que hay entre minicuadro
        public static String colorFondo(String miniTablero, int pos1, int pos2, char letra) {
                String color = "";
                int num = Character.getNumericValue(miniTablero.charAt(1));
                if ((pos1 == num || num == pos2) && miniTablero.charAt(0) == letra) {
                        color = "\u001B[43m"; // Amarillo
                } else {
                        color = "\u001B[42m"; // Verde
                }
                return color;
        }

}
