package es.joseluisgs.dam;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            // -- Linux --

            // Run a shell command
            // Process process = Runtime.getRuntime().exec("ls /home/link/");

            // Run a shell script
            // Process process = Runtime.getRuntime().exec("path/to/hello.sh");

            // -- Windows --

            // Run a command
            //Process process = Runtime.getRuntime().exec("cmd /c dir C:\\Users\\link");

            //Run a bat file
            Process process = Runtime.getRuntime().exec("ls /Users/link");

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                System.out.println("Ha habido un error");
                System.out.printf("El proceso de obtener un directorio termino %d", exitVal);
                System.exit(1);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(34);
        }
    }
}
