/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1p2;

public class Tablero {

    private boolean[][] tableroX;
    private boolean[][] tableroO;
    private char[][][] listaTableros = new char[9][3][3];
    private String jugadorX;
    private String jugadorO;

    public Tablero(String jugX, String jugO) {

        tableroX = new boolean[3][3];
        tableroO = new boolean[3][3];
        jugadorX = jugX;
        jugadorO = jugO;
    }

    public char[][][] getListaTableros() {
        return listaTableros;
    }

    public boolean[][] getTableroX() {
        return tableroX;
    }

    public boolean[][] getTableroO() {
        return tableroO;
    }

    public char[][] tableroJugada(String jugada) {
        int pos = 0;
        switch (jugada.charAt(0)) {
            case 'A':
                pos = -1;
                break;
            case 'B':
                pos = 2;
                break;
            case 'C':
                pos = 5;
                break;
            default:
                break;
        }
        return listaTableros[pos + Character.getNumericValue(jugada.charAt(1))];

    }

    public boolean colocarFicha(String jugada, char[][] tab, String jug) {
        boolean seJugo = true;
        int fila = -1;
        int col = Character.getNumericValue(jugada.charAt(1)) - 1;
        switch (jugada.charAt(0)) {
            case 'A':
                fila = 0;
                break;
            case 'B':
                fila = 1;
                break;
            case 'C':
                fila = 2;
                break;
            default:
                break;
        }

        if (tab[fila][col] != 'X' && tab[fila][col] != 'O') {
            if (jug.equals(jugadorX)) {
                tab[fila][col] = 'X';
            } else {
                tab[fila][col] = 'O';
            }
        } else {
            seJugo = false;
        }
        return seJugo;
    }

    public boolean tresEnRaya(char[][] tablero) {
        boolean ganada = false;
        for (int i = 0; i < 3; i++) {
            if ((tablero[i][0] == 'X' || tablero[i][0] == 'O') && tablero[i][0] == tablero[i][1]
                    && tablero[i][1] == tablero[i][2]) {
                ganada = true;
            }
            if ((tablero[0][i] == 'X' || tablero[0][i] == 'O') && tablero[0][i] == tablero[1][i]
                    && tablero[1][i] == tablero[2][i]) {
                ganada = true;
            }
        }

        if ((tablero[0][0] == 'X' || tablero[0][0] == 'O') && tablero[0][0] == tablero[1][1]
                && tablero[1][1] == tablero[2][2]) {
            ganada = true;
        }

        if ((tablero[0][2] == 'X' || tablero[0][2] == 'O') && tablero[0][2] == tablero[1][1]
                && tablero[1][1] == tablero[2][0]) {
            ganada = true;
        }
        return ganada;
    }

    public boolean comprobarGanados(boolean[][] tableroGrande, int[] coord) {
        boolean gano = false;
        for (char[][] cs : listaTableros) {
            if (tresEnRaya(cs)) {
                tableroGanado(tableroGrande, coord[0], coord[1]);
                if (partidaGanada(tableroGrande)) {
                    gano = true;
                }
            }
        }
        return gano;
    }

    public void tableroGanado(boolean[][] tableroGrande, int fila, int col) {
        if (tableroX[fila][col] == false && tableroO[fila][col] == false) {
            tableroGrande[fila][col] = true;
        }
    }

    public boolean partidaGanada(boolean[][] tableroGrande) {
        boolean ganada = false;
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (tableroGrande[i][0] && tableroGrande[i][0] == tableroGrande[i][1]
                    && tableroGrande[i][1] == tableroGrande[i][2]) {
                ganada = true;
            }
        }

        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (tableroGrande[0][i] && tableroGrande[0][i] == tableroGrande[1][i]
                    && tableroGrande[1][i] == tableroGrande[2][i]) {
                ganada = true;
            }
        }

        // Verificar diagonal principal
        if (tableroGrande[0][0] && tableroGrande[0][0] == tableroGrande[1][1]
                && tableroGrande[1][1] == tableroGrande[2][2]) {
            ganada = true;
        }

        // Verificar diagonal secundaria
        if (tableroGrande[0][2] && tableroGrande[0][2] == tableroGrande[1][1]
                && tableroGrande[1][1] == tableroGrande[2][0]) {
            ganada = true;
        }

        // No hay tres en raya
        return ganada;
    }

    public int[] posicion(String pos) {
        int[] coord = new int[2];
        if (pos.charAt(0) == 'A') {
            coord[0] = 0;
        }
        if (pos.charAt(0) == 'B') {
            coord[0] = 1;
        }
        if (pos.charAt(0) == 'C') {
            coord[0] = 2;
        }
        coord[1] = Character.getNumericValue(pos.charAt(1)) - 1;
        return coord;
    }

    public String jugada(int pos) {
        String jug = "";
        switch (pos) {
            case 0, 1, 2:
                jug += "A";
                break;
            case 3, 4, 5:
                jug += "B";
                break;
            case 6, 7, 8:
                jug += "C";
                break;
            default:
                break;
        }
        switch (pos) {
            case 0, 3, 6:
                jug += "1";
                break;
            case 1, 4, 7:
                jug += "2";
                break;
            case 2, 5, 8:
                jug += "3";
                break;
            default:
                break;
        }
        return jug;
    }
}
