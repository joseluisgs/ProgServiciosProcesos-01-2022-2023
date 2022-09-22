package es.joseluisgs.dam;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) {
        // Enviado datos de un proceso a otro y recibiendo sus datos
        try {
            // Pasandoles datos a un proceso
            Process ls=Runtime.getRuntime().exec ("ls /Users/link/");
            InputStream in=ls.getInputStream();
            Process grep=Runtime.getRuntime().exec ("grep Documents");
            OutputStream out = grep.getOutputStream();
            int b;
            // La salida de ls se la pasamos al grep para que busque el patrón indicado
            while((b = in.read()) != -1)
                out.write(b); // Escribimos en el buuffer de intercambio de grep lo que salga de ls

            ls.waitFor();  // Esperamos
            in.close();
            out.close();

            in = grep.getInputStream(); // Leemos la salida de grep
            while((b = in.read()) != -1)
                System.out.write(b);
            grep.waitFor();
            in.close();

        } catch (IOException ex) {
            System.err.println("Error al ejecutar el proceso");
        } catch (InterruptedException ex) {
            System.err.println("Error en wait for");
        }
    }
}
