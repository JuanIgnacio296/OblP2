/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1p2;

import java.util.*;

public class Jugadores {
    private ArrayList<Jugador> listaJugadores;
    
    public Jugadores() {
        listaJugadores = new ArrayList<Jugador>();
    }
    public void agregarJugador(Jugador j){
        listaJugadores.add(j);
    }
    public boolean validarAlias(String unAlias){
        boolean valido = true;
        for (int i = 0; i < listaJugadores.size() && valido; i++) {
            if(listaJugadores.get(i).getAlias().equals(unAlias)){
                valido = false;
            }
        }
        return valido;
    }
}
