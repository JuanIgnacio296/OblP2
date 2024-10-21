// Juan Ignacio Alvarez - nro. estudiante: 319853
// Facundo Lorenzoni - nro. estudiante: 342249

package obligatorio1p2;

public class Jugador implements Comparable<Jugador>{
    //Variables de clase
    private String nombre;
    private int edad;
    private String alias;
    private int ganadas;

    //constructor vacio
    public Jugador(){
        this.nombre = "Sin nombre";
        this.edad = 0;
        this.alias = "Sin alias";
    }
    //constructor con paramteros
    public Jugador(String unNombre, int unaEdad, String unAlias) {
        this.nombre = unNombre;
        this.edad = unaEdad;
        this.alias = unAlias;
        this.ganadas = 0;
    }
    //gets y sets
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
    public void ganar(){
        ganadas++;
    }

    //los Override
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
