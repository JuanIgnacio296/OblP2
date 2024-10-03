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
    public boolean validarAlias(String unAlias){
        Jugador aux = new Jugador();
        aux.setAlias(unAlias);
        return listaJugadores.contains(aux);
    }
}
