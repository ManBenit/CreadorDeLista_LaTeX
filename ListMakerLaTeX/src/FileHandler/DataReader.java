package FileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DataReader {
    private LinkedList<String> names;
    
    public DataReader(){
        names= new LinkedList();
    }
    
    public void read(){
        readFile(select("txt"));
    }
    
    public LinkedList<String> getList(){
        return names;
    }
    
    //Read guest list file (.txt)
    private String readFile(File archivo){
        StringBuilder ret= new StringBuilder("");
        try{
            BufferedReader br= new BufferedReader(new FileReader(archivo));
            
            String str;
            str= br.readLine();
            ret.append(str);
            while((str= br.readLine()) != null)
                names.add(str);
            
            br.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo:\n"+ex.getMessage());
        }
        
        return ret.toString();
    }
    
    private File select(String filter){
        JFileChooser jfchooser= new JFileChooser();
        FileNameExtensionFilter filtro= null;
        if(filter!=null)
            filtro= new FileNameExtensionFilter("Specific file (."+filter+")", filter);
        
        jfchooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //¿Qué se puede seleccionar?
        if(filter!=null)
            jfchooser.setFileFilter(filtro);
        jfchooser.showOpenDialog(jfchooser);

        return jfchooser.getSelectedFile();
    }
}
