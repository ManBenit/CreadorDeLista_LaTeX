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
    private String selectedFilePath;
    
    public DataReader(String title){
        names= new LinkedList();
        readFile(select("txt", title));
    }
    
    public LinkedList<String> getList(){
        return names;
    }
    
    public String getPath(){
        return selectedFilePath;
    }
    
    //Read guest list file (.txt)
    private void readFile(File archivo){
        try{
            BufferedReader br= new BufferedReader(new FileReader(archivo));
            
            String str;
            while((str= br.readLine()) != null)
                names.add(str);
            
            br.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo:\n"+ex.getMessage());
        }
    }
    
    private File select(String filter, String title){
        JFileChooser jfchooser= new JFileChooser();
        jfchooser.setDialogTitle(title);
        FileNameExtensionFilter filtro= null;
        if(filter!=null)
            filtro= new FileNameExtensionFilter("Specific file (."+filter+")", filter);
        
        jfchooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //¿Qué se puede seleccionar?
        if(filter!=null)
            jfchooser.setFileFilter(filtro);
        jfchooser.showOpenDialog(jfchooser);
        
        try{
            selectedFilePath= jfchooser.getSelectedFile().getCanonicalPath();
        }catch(IOException ex){
            System.out.println("Error al obtener la ruta canónica\n"+ex.getMessage());
        }
            
        return jfchooser.getSelectedFile();
    }
}
