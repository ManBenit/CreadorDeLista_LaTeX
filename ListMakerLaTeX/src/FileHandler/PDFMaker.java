package FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PDFMaker {
    private String path, festName, consoleOutput;
    private final String compilePath= ".."+System.getProperty("file.separator")+"Document"+System.getProperty("file.separator");
    
    public PDFMaker(String path, String festName){
        //writeFile(new File(path+"Lista"+festName.replace(" ", "")), initMainTex.toString(), false);
        this.path=path;
        this.festName= festName;
    }
    
    public String consOut(){
        return consoleOutput;
    }
    
    public void compileAndSave(){
        try {
            //Compiling .tex file
            ConsoleHandler ch= new ConsoleHandler("cmd.exe", "/C", "cd "+compilePath+" && pdflatex Main.tex");
            System.out.println(ch.runProcess()); 
            if (ch.hasResult()){
                // aquí viene tu resultado
                consoleOutput= ch.getResult();
                //System.out.println(resultado);
            }
        
            //Copying Main to Documents
            File aux= new File(compilePath+"Main.tex");
            aux= new File(compilePath+"Main.pdf");
            InputStream is= new FileInputStream(aux);
            FileOutputStream fos= new FileOutputStream(path+"CVPE_Lista_"+festName.replace(" ", "")+".pdf");
            copyIO(is, fos);
            is.close();
            fos.close();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error guardando o compilando\n"+ex.getMessage());
        }
    }
    
    private void copyIO(InputStream is, OutputStream os) throws IOException {
        int i;
        byte[] b = new byte[1024];
        while ((i = is.read(b)) != -1) {
            os.write(b, 0, i);
        }
    }
}
