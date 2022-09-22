package es.joseluisgs.dam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newSingleThreadExecutor();

        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", "ping -c 4 google.com");

        try {

            Process process = processBuilder.start();

            System.out.println("process ping...");
            ProcessReadTask task = new ProcessReadTask(process.getInputStream());
            // Espertamos porque recibimos una promesa, es asincrono
            // Le decimos que se ejecute una tarea como hilo
            Future<List<String>> future = pool.submit(task);

            // no bloqueo, se puede usar otras tareas aquí
            System.out.println("Otra tarea 1...");
            System.out.println("Otra tarea 2..");
            
            // Esperamos que se cumpla la tarea 5 segundos
            List<String> result = future.get(4, TimeUnit.SECONDS);
            result.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    private static class ProcessReadTask implements Callable<List<String>> {

        private InputStream inputStream;

        public ProcessReadTask(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public List<String> call() {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.toList());
        }
    }
}
