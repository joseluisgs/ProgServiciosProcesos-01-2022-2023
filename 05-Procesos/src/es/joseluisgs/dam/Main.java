package es.joseluisgs.dam;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String path = System.getProperty("user.dir");
            // usamos path separator para mejorar la detección del path del sistema
            String pathDir = path+ File.separator+"05-Procesos.iml";
            System.out.println(pathDir);
            Process p1 = Runtime.getRuntime().exec ("code " + pathDir);
            // Ahora dejamos el programa bloqueado (este de java) hasta que se cierre el otro
            p1.waitFor();
            System.out.println("valor de salida " + p1.exitValue());
        }catch (IOException ex) {
            System.err.println("Error al lanzar proceso");
        } catch (InterruptedException ex) {
            System.err.println("Error n waitFor");
        }
    }
}
