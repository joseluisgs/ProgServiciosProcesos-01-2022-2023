package es.joseluisgs.dam;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Pasandoles datos a un proceso
            Process p=Runtime.getRuntime().exec ("grep java");
            // La entrada de su información es OutputStream
            // Returns the output stream connected to the normal input of the process.
            // O sea, la salida de mi programa se la conecto a la entrada del proceso.
            // De esa manera le mando información
            OutputStream out = p.getOutputStream();
            PrintWriter pw =new PrintWriter(new OutputStreamWriter(out));
            pw.println("Me gusta PSP y java");
            pw.println("Soy un crack de java");
            pw.println("No se me escapa ni una");
            pw.println("Pedazo de clase de java");
            pw.println("java y los procesos me quieren");
            pw.close();

            // Ya le hemos pasado la información, ahora leemos su salida
            // Pero no vemos la salida. Vamos a leer la salida
            // La salida de su programa es la entrada del mío
            InputStream in=p.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String linea;
            while ((linea = br.readLine()) != null)
                System.out.println(linea);
            br.close();
            // Esto no es obligatorio pero me gusta
            p.waitFor();
            System.out.println("valor de salida " + p.exitValue());
        } catch (IOException ex) {
            System.err.println("Error al ejecutar el proceso");
        } catch (InterruptedException ex) {
            System.err.println("Error en wait for");
        }
    }
}
