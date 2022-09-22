package es.joseluisgs.dam;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        // Path Actual, directorio de la BD
        String path = System.getProperty("user.dir");
        // usamos path separator para mejorar la detección del path del sistema
        String pathDir = path+ File.separator+"src"+File.separator;
        System.out.println(pathDir);
    }
}
