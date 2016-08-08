package htmldoc;

import java.sql.Time;

public class Programa {
    
    private final String nombre;
    private final String capitulo;
    private final Time inicio;
    private final Time fin;
    
    public Programa(String nombre, String horario, String capitulo){
        this.nombre = nombre;
        this.capitulo = capitulo;
        
        inicio = new Time(
                Integer.valueOf(horario.substring(0, 2)),
                Integer.valueOf(horario.substring(3, 5)),
                0
        );
              
        fin = new Time(
                Integer.valueOf(horario.substring(6, 8)),
                Integer.valueOf(horario.substring(9, 11)),
                0
        );
    }
    
    public void showPrograma(){
            System.out.print(nombre);
            System.out.print((capitulo.isEmpty()) ? " -> " : (" [ " + capitulo + " ] -> "));
            System.out.println(inicio + " - " + fin);
    }
}
