package FileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DataReader {
    private void loadNames(){
        
    }
    
    private String adaptarRuta(String ruta){
        if(System.getProperty("os.name").equals("Windows"))
            return ruta.replace("/", "\\");
        else
            return ruta;
    }
    
    public String readFile(File archivo){
        StringBuilder ret= new StringBuilder("");
        try{
            BufferedReader br= new BufferedReader(new FileReader(archivo));
            
            String str;
            str= br.readLine();
            ret.append(str);
            while((str= br.readLine()) != null)
                ret.append(str).append("\n");
            
            br.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        }
        
        return ret.toString();
    }
    
    public File select(String filter){
        FileNameExtensionFilter filtro= null;
        if(filter!=null)
            filtro= new FileNameExtensionFilter("Specific file (."+filter+")", filter);
        JFileChooser jfchooser= new JFileChooser();
        jfchooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //¿Qué se puede seleccionar?
        if(filter!=null)
            jfchooser.setFileFilter(filtro);
        jfchooser.showOpenDialog(jfchooser);

        return jfchooser.getSelectedFile();
    }
}
