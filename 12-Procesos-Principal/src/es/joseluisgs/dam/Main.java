package es.joseluisgs.dam;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(args[0]);
        String pathDir = System.getProperty("user.dir")+File.separator+"util"+File.separator;
        String jarFile = "12-Procesos-Secundario.jar";
        String orden = "java -jar "+pathDir+jarFile+" pepe 45 casa 88 luis PSP mola";
        System.out.println(orden);
        int tiempo=0;
        int i=0;


        // repetimos tantas veces como le hayamos pasado por parámetros
        //for(int i=0; i<Integer.parseInt(args[0]);i++){
        // Repetimos 20 segundos
        while(tiempo<20000){
            System.out.println("\nInteracción: "+i);
            try {
                Process p=Runtime.getRuntime().exec (orden);
                Process grep=Runtime.getRuntime().exec ("grep PSP");
                InputStream in=p.getInputStream();
                OutputStream out = grep.getOutputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                PrintWriter pw =new PrintWriter(new OutputStreamWriter(out));

                String linea;
                while ((linea = br.readLine()) != null){
                    System.out.println(linea);
                    pw.println(linea);
                }
                br.close();
                pw.close();
                in.close();
                out.close();
                // Esto no es obligatorio pero me gusta
                p.waitFor();
                //System.out.println("valor de salida de interaction " +i+": "+ p.exitValue());

                System.out.println("Veamos si la palabra a encontrar estaba en este proceso");
                in = grep.getInputStream(); // Leemos la salida de grep
                br=new BufferedReader(new InputStreamReader(in));
                while ((linea = br.readLine()) != null){
                    System.out.println("Palabra secreta: "+linea+" encontrada en la interaction: "+i);
                }
                grep.waitFor();
                in.close();
                br.close();
                Thread.sleep(1000);
                tiempo+=1000;
                i++;
            } catch (IOException ex) {
                System.err.println("Error al ejecutar: "+orden+ " "+ex.getMessage());
            } catch (InterruptedException ex) {
                System.err.println("Error en waitFor "+ex.getMessage());
            }
        }

    }
}
