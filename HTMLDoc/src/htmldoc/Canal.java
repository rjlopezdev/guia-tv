package htmldoc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Canal {

    private final String nombre;
    private final List<Programa> lista_programas;

    public Canal(String nombre, Elements programas) {
        this.nombre = nombre;
        lista_programas = new ArrayList<>();

        Iterator<Element> programas_it = programas.iterator();

        while (programas_it.hasNext()) {

            Element programa = programas_it.next();

            this.lista_programas.add(new Programa(
                    programa.getElementsByTag("h4").text(),      //NOMBRE
                    programa.getElementsByClass("fecha").text(), //HORARIO
                    programa.getElementsByTag("p").text()));     //CAPITULO
        }
    }

    public void showCanal() {
        System.out.println(
                "\n\n**************************\n\n "
                + this.nombre
                + " \n\n**************************\n\n");

        Iterator<Programa> it = lista_programas.iterator();

        while (it.hasNext()) {
            it.next().showPrograma();
        }
    }
}
