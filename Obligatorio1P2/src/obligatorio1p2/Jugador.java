/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1p2;

public class Jugador implements Comparable<Jugador>{
    private String nombre;
    private int edad;
    private String alias;
    private static int Ganadas;

    public Jugador(){
        this.nombre = "Sin nombre";
        this.edad = 0;
        this.alias = "Sin alias";
    }
    public Jugador(String unNombre, int unaEdad, String unAlias) {
        this.nombre = unNombre;
        this.edad = unaEdad;
        this.alias = unAlias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static int getGanadas() {
        return Ganadas;
    }

//GH
    @Override
    public String toString() {
        return alias;
    }
    public boolean equals(Jugador j){
        return this.getAlias().equalsIgnoreCase(j.getAlias());
    }
    public int compareTo(Jugador j){
        return Jugador.Ganadas - j.Ganadas;       
    }
    
    
}
