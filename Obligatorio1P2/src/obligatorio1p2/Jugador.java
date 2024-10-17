/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1p2;

public class Jugador implements Comparable<Jugador>{
    private String nombre;
    private int edad;
    private String alias;
    private int ganadas = 0;

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

    public int getGanadas() {
        return ganadas;
    }

//GH
    @Override
    public String toString() {
        return alias;
    }
    @Override
    public boolean equals(Object o){
        return this.getAlias().equals(((Jugador)o).getAlias());
    }
    @Override
    public int compareTo(Jugador j){
        return j.ganadas - this.ganadas;       
    }
    
    
}
