package htmldoc;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class ProgramacionTV {

    private final List<Canal> lista_canales;
    private Document web_document;
    private Elements canales_to_parse;
    private Elements info_canales;
    private final String url = "http://www.elmundo.es/television/programacion-tv/";
    private final Time time = new Time(Calendar.HOUR_OF_DAY, Calendar.MINUTE, 0);

    public ProgramacionTV() {
        lista_canales = new ArrayList<>();
    }

    public void launch() {

        ConexionTV conexion = new ConexionTV(url);

        try {
            if (conexion.getStatusConnectionCode() == 200) {

                //Obtener HTML
                web_document = conexion.getHtmlDocument();

                // Búsqueda de los canales y su información
                canales_to_parse = web_document.select("div.lineaCanal");
                info_canales = web_document.select("div.itemCanal");

                int num_canal = 1;

                Iterator<Element> canal_it = canales_to_parse.iterator();
                Iterator<Element> info_canal_it = info_canales.iterator();

                while (canal_it.hasNext() && info_canal_it.hasNext()) {

                    Canal canal = new Canal(
                            info_canal_it.next().select("a > img").attr("title"),
                            canal_it.next().getElementsByClass("itemPrograma")
                    );

                    lista_canales.add(canal);
                    num_canal++;
                }

                showData();
            } else {
                System.out.println("No se puede establecer conexión con el servidor.");
            }
        } catch (Exception e) {
            System.out.println("Error en la descarga de datos ... " + e.getLocalizedMessage());
        }
    }

    public void showData() {

        Iterator<Canal> it = lista_canales.iterator();

        while (it.hasNext()) {
            it.next().showCanal();
        }
    }
}
