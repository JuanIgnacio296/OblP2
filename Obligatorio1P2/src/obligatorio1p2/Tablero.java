// Juan Ignacio Alvarez - nro. estudiante: 319853
// Facundo Lorenzoni - nro. estudiante: 342249

package obligatorio1p2;

public class Tablero {

    //Variables de clase
    private boolean[][] tableroX;
    private boolean[][] tableroO;
    private char[][][] listaTableros = new char[9][3][3];
    private String jugadorX;
    private String jugadorO;

    //constructor
    public Tablero(String jugX, String jugO) {

        tableroX = new boolean[3][3];
        tableroO = new boolean[3][3];
        jugadorX = jugX;
        jugadorO = jugO;
    }

    //Gets y sets
    public char[][][] getListaTableros() {
        return listaTableros;
    }

    public boolean[][] getTableroX() {
        return tableroX;
    }

    public boolean[][] getTableroO() {
        return tableroO;
    }

    //metodo para la jugada magica la cual segun el mini cuadro borra todas las fichas y si estaba ganado se pasa a false
    public void jugadaMagica(String miniCuadro) {
        int[] coord = posicion(miniCuadro);
        listaTableros[posLista(miniCuadro)]=new char[3][3];
        tableroX[coord[0]][coord[1]] = false;
        tableroO[coord[0]][coord[1]] = false; 
    }

    //Devuelve un tablero segun el que se haya seleccionado
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

    //coloca la ficha en el tablero
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

    //verifica que haya un tres en raya
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

    //comprueba si el juego se ha ganado
    public boolean comprobarGanados(boolean[][] tableroGrande) {
        boolean gano = false;
        for (int i = 0; i< listaTableros.length;i++) {
            char[][] tab = listaTableros[i];
            int[]coord = posicion(jugada(i));
            if (tresEnRaya(tab)) {
                tableroGanado(tableroGrande, coord[0], coord[1]);
                if (partidaGanada(tableroGrande)) {
                    gano = true;
                }
            }
        }
        return gano;
    }

    //Se cambia el tablero al true de que esta ganado
    public void tableroGanado(boolean[][] tableroGrande, int fila, int col) {
        if (tableroX[fila][col] == false && tableroO[fila][col] == false) {
            tableroGrande[fila][col] = true;
        }
    }

    //valida que haya un tres en raya en el tablero grande
    public boolean partidaGanada(boolean[][] tableroGrande) {
        boolean ganada = false;
        for (int i = 0; i < 3; i++) {
            if ((tableroGrande[i][0] && tableroGrande[i][1] && tableroGrande[i][2]) || 
            (tableroGrande[0][i] && tableroGrande[1][i] && tableroGrande[2][i])){
                ganada = true;
            }
        }
        if((tableroGrande[0][0] && tableroGrande[1][1] && tableroGrande[2][2]) || 
        (tableroGrande[2][0] && tableroGrande[1][1] && tableroGrande[0][2])){
            ganada = true;
        }
        return ganada;
    }

    //ayuda a convertir de una jugada a una posicion de la matriz
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

    //Valida que haya un empate cuando no hay ninguna jugada disponible
    public boolean empate(char[][]miniCuadro){
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(miniCuadro[i][j]!='X' && miniCuadro[i][j]!='O'){
                    empate = false;
                }
            }
        }
        return empate;
    }

    //convierte una jugada en una posicion de la lista
    public int posLista(String pos) {
        int num = Character.getNumericValue(pos.charAt(1))-1;
        switch (pos.charAt(0)) {
            case 'A':
                num += 0;
                break;
            case 'B':
                num += 3;
                break;
            case 'C':
                num += 6;
                break;
            default:
                break;
        }
        return num;
    }
    //convierte una posicion de la lista a una jugada
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
    //genera una jugada aleatoria
    public String jugadaComputadora(){
        int jugadaPC = (int)(Math.random()*9);
        return jugada(jugadaPC);
        
    }
}
