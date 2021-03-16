package FileHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Usando ProcessBuilder puedes juntar stdout y stderr de tu proceso. 
El método waitFor() bloquea la ejecución y espera hasta que el proceso termina, 
luego devolviendo el resultado (normalmente 0 en caso de terminación normal o >=1 en caso de error).
*/

public class ConsoleHandler {
    private ProcessBuilder pb;
    private String out=null;
    private Process proc=null;

    public ConsoleHandler(String... cmd){
        pb = new ProcessBuilder(cmd);
        // redirigir stdErr>stdOut
        pb.redirectErrorStream(true);
    }

    // espera a que el proceso termina y devuelve el estado (0 si es correcto, otro si no lo es)
    public int runProcess() throws IOException, InterruptedException{
        proc = pb.start();
        new Stream().start();
        return proc.waitFor();
    }

    // el proceso tiene resultado?
    public boolean hasResult(){
        return !proc.isAlive() && out!=null;
    }

    //devuelve resultado o null
    public String getResult(){
        return out;
    }

    final class Stream extends Thread{

        @Override
        public void run() {
            StringBuilder sb=new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            try {
                while((line = br.readLine())!=null){
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out=sb.toString();
            }
        }
    }

}