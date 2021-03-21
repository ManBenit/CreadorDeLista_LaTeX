package FileHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DataWriter {
    private StringBuilder initMainTex= new StringBuilder("");
    private String fest;
    
    public DataWriter(String fest, String greenAd, String blueAd, String redAd, List<String> caps, List<String> team, List<String> guests){
        this.fest= fest;
        initMainTex.append("\\input{Preambulo.tex}\n");
        initMainTex.append("\\begin{document}\n");
        initMainTex.append("\\begin{spacing}{0.5}\n");
        initMainTex.append("\\festejado{").append(fest).append("}\n");
        initMainTex.append("\\asesores");
        initMainTex.append("{").append(greenAd).append("}");
        initMainTex.append("{").append(blueAd).append("}");
        initMainTex.append("{").append(redAd).append("}\n");
        initMainTex.append("\\ \\\\ \n");
        initMainTex.append("\\justifying\n");
        initMainTex.append("\\begin{paracol}{2}\n");
        initMainTex.append("\\section*{Fase amarilla}\n");
        initMainTex.append("\\begin{enumerate}\n");
        for(String s: caps)
            initMainTex.append("\\item ").append(s).append("\n");
        initMainTex.append("\\end{enumerate}\n");
        initMainTex.append("\\switchcolumn\n");
        initMainTex.append("\\section*{Equipo}\n");
        initMainTex.append("\\begin{enumerate}\n");
        for(String s: team)
            initMainTex.append("\\item ").append(s).append("\n");
        initMainTex.append("\\end{enumerate}\n");
        initMainTex.append("\\end{paracol}\n");
        
        if(!guests.isEmpty()){
            int counter=0;
            initMainTex.append("\\section*{Invitados (").append(guests.size()).append(" en lista)}\n");
            initMainTex.append("\\begin{paracol}{2}\n");
            initMainTex.append("\\begin{itemize}\n");
            if(guests.size()>20){ //Si es mayor a 20,
                for(int i=0; i<20; i++){ //enlista 20 en la primera columna
                    initMainTex.append("\\item ").append(guests.get(i)).append("\n");
                }
                initMainTex.append("\\end{itemize}\n");
                initMainTex.append("\\switchcolumn\n");
                initMainTex.append("\\begin{itemize}\n"); //y el resto en la segunda (ahorita debe ser menor o igual a 40)
                for(int i=20; i<guests.size(); i++){
                    initMainTex.append("\\item ").append(guests.get(i)).append("\n");
                }
                initMainTex.append("\\end{itemize}\n");
                initMainTex.append("\\end{paracol}\n");
            }
            else{
                for(String s: guests){
                    initMainTex.append("\\item ").append(s).append("\n");
                }
                initMainTex.append("\\end{itemize}\n");
                initMainTex.append("\\end{paracol}\n");
            }
        }
        initMainTex.append("\\end{spacing}\n");
        initMainTex.append("\\end{document}\n");
    }
    
    public void write(){
        writeFile(new File(".."+System.getProperty("file.separator")+"Document"+System.getProperty("file.separator")+"Main.tex"), initMainTex.toString(), false);
    }
    
    private void writeFile(File archivo, String valor, boolean concatenacion){
        try {
            BufferedWriter bw= new BufferedWriter(new FileWriter(archivo, concatenacion));
            bw.write(valor);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo\n"+archivo.getAbsolutePath());
        }
    }
}
