/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio1p2;

public class Jugador{
    private String nombre;
    private int edad;
    private String alias;
    private static int Ganadas;

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

    public static void setGanadas(int Ganadas) {
        Jugador.Ganadas = Ganadas;
    }

    
    @Override
    public String toString() {
        return alias;
    }
    public boolean equals(Jugador j){
        return this.getAlias()==j.getAlias();
    }
    
    
    
}
