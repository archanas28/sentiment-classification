package senti.util;


import java.io.*;

public class FileOperation {

    public static String ReadFile(String path) {
        StringBuilder sb = new StringBuilder();
        try{
            //BufferedReader in = new BufferedReader(new FileReader(path));
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileOperation().getClass().getResourceAsStream(path)));
            try
            {
                String textLine;
                while((textLine = in.readLine()) != null){
                    sb.append(textLine);
                    sb.append("\n");
                }
            }finally{
                in.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e); 
        }

        return sb.toString();
    }
    
    public static String ReadText(String path) {
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader in = new BufferedReader(new FileReader(path));
            //BufferedReader in = new BufferedReader(new InputStreamReader(new FileOperation().getClass().getResourceAsStream(path)));
            try
            {
                String textLine;
                while((textLine = in.readLine()) != null){
                    sb.append(textLine);
                    sb.append("\n");
                }
            }finally{
                in.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e); 
        }

        return sb.toString();
    }

    public static  void WriteFile(String fileName, String text){
        
        File file = new File(fileName);
        if(file.exists())
            file.delete();
        
        BufferedWriter out = null;  
;
        try {  
            out = new BufferedWriter(new OutputStreamWriter(  
                    new FileOutputStream(fileName, true)));  
            out.write(text);  
            out.flush();
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
    }
    
}
