package listmakerlatex;

import FileHandler.DataReader;
import FileHandler.DataWriter;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ListMakerLaTeX {

    public static void main(String[] args) {
        DataReader dr= new DataReader();
        
        //Invitados
        System.out.println("Selecciona invitados");
        dr.read();
        LinkedList<String> inv= dr.getList();
        
        //Capitalizadores
        System.out.println("Selecciona capis");
        dr.read();
        LinkedList<String> cap= dr.getList();
        
        //Equipo
        System.out.println("Selecciona equipo");
        dr.read();
        LinkedList<String> eq= dr.getList();
        
        
        String destPath= System.getProperty("user.home")+
                System.getProperty("file.separator")+
                "Documents"+
                System.getProperty("file.separator");
        System.out.println(destPath);
        
        DataWriter dw= new DataWriter("Leticia Javier Chavarría",
                "Benítez Morales Manuel Emilio",
                "Sánchez Negrete Silvia", 
                "Morales Flemate Gabriela", 
                cap, eq, inv);
        
        dw.write(destPath);
    }
    
    private String adaptarRuta(String ruta){
        if(System.getProperty("os.name").equals("Windows"))
            return ruta.replace("/", "\\");
        else
            return ruta;
    }
}
