package htmldoc;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConexionTV {
    
    public Connection.Response response = null;
    public Document doc = null;
    public String url;
    
    public ConexionTV(String url){
        this.url = url;
    }

    public int getStatusConnectionCode() {

        try {
            response = Jsoup.connect(url).timeout(10000).ignoreHttpErrors(true).execute();
            System.out.println("Comprobando entradas de: " + url);
            return response.statusCode();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return 0;
    }

    public Document getHtmlDocument() {

        try {
            doc = Jsoup.connect(url).timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML" + ex.getMessage());
        }
        return doc;
    }
}
