package FileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DataReader {
    private LinkedList<String> names;
    private String selectedFilePath;
    
    public DataReader(String title){
        names= new LinkedList();
        readFile(select("txt", title));
    }
    
    public List<String> getList(){
        //Order by alphabet
        List<String> sortedNames = names.stream().sorted().collect(Collectors.toList());
        
        return sortedNames;
    }
    
    public String getPath(){
        return selectedFilePath;
    }
    
    //Read guest list file (.txt)
    private void readFile(File archivo){
        try{
            BufferedReader br= new BufferedReader(new FileReader(archivo));
            
            String str;
            while((str= br.readLine()) != null){
                str= capitalFormat(str);
                names.add(str);
            }
            
            br.close();
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo:\n"+ex.getMessage());
        }
    }
    
    private String capitalFormat(String name){
        StringBuilder sb= new StringBuilder("");
        String[] namePart= name.split(" ");
        
        StringBuilder partAnalyze;
        for(String np: namePart){
            if(!np.equalsIgnoreCase("de") && !np.equalsIgnoreCase("del") && !np.equalsIgnoreCase("la") && !np.equalsIgnoreCase("las") && !np.equalsIgnoreCase("los")){
                partAnalyze= new StringBuilder(np.toLowerCase());
                partAnalyze.replace(0, 1, String.valueOf(partAnalyze.charAt(0)).toUpperCase());
                partAnalyze.append(" ");
                sb.append(partAnalyze.toString()).append(" ");
            }
            else{
                sb.append(np.toLowerCase()).append(" ");
            }
        }
        return sb.toString();
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
