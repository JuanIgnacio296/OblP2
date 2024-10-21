// Juan Ignacio Alvarez - nro. estudiante: 319853
// Facundo Lorenzoni - nro. estudiante: 342249

package obligatorio1p2;

import java.util.*;

public class Partida {
    //Variables de clase
    private ArrayList<Jugador> listaJugadores;
    private Tablero tablero;

    //constructor
    public Partida() {
        listaJugadores = new ArrayList<Jugador>();
    }

    //get y agregar
    public void agregarJugador(Jugador j){
        listaJugadores.add(j);
    }
    public ArrayList<Jugador> getListaJugadores(){
        return listaJugadores;
    }

    //valida que no haya un jugador con ese alias
    public boolean validarAlias(String unAlias){
        boolean valido = true;
        for (int i = 0; i < listaJugadores.size() && valido; i++) {
            if(listaJugadores.get(i).getAlias().equals(unAlias)){
                valido = false;
            }
        }
        return valido;
    }

    //aumenta las partidas ganadas del jugador que gano
    public void aumentarGanadas(String jugador){
        Jugador aux = new Jugador();
        aux.setAlias(jugador);
        for (Jugador j : listaJugadores) {
            if(j.equals(aux)){
                j.ganar();
            }
        }
        
    }
    
}
