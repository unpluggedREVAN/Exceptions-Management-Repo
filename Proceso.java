// Tarea manejo de excepciones - Incluyendo interfaz
// Jose Pablo Agüero Mora (2021126372) - Grupo 2
// Clase Proceso

public class Proceso {
    int total;

    public Proceso(){
        total = 0;
    }

    public void PresionarPrimero(){
        total += 5;
    }

    public void PresionarSegundo(){
        total += 50;
    }

    public void PresionarTercero(){
        total += 500;
    }

    public int DevuelveTotal(){
        return total;
    }
}
