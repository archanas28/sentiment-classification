package senti.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class Props {

    public String getProperty(String propName){
    //public static void getProperty(String propName){

        Properties defaultProps = new Properties();
        InputStream in = null;
        String propValue = null;
        
        
        //in = new FileInputStream(new File("").getAbsoluteFile() + "/src/main/resources/META-INF/application.properties");
        //in = new FileInputStream(this.getClass().getClassLoader().getResource("").toString().substring(6));
        in = getClass().getResourceAsStream("/META-INF/application.properties");
        
        
        try {
            defaultProps.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Tool.Print("Load error in application.properties!");
            e.printStackTrace();
        }
        
        propValue = defaultProps.getProperty(propName);
        
        
        try {
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Tool.Print("Close error in applicaiton.properties!");
            e.printStackTrace();
        }
        
        return propValue;
        
    }
    
    
        public static String getDatasetProperty(String propName){

            Properties defaultProps = new Properties();
            InputStream in = null;
            String propValue = null;
            
            //File file = new File("./dataset.properties");
            Tool.Print(new File("").getAbsolutePath());
            try {
                in = new FileInputStream("./dataset.properties");
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                Tool.Print("Cannot open dataset.properties");
            }
            
            try {
                defaultProps.load(in);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Tool.Print("Load error in application.properties!");
                e.printStackTrace();
            }
            
            propValue = defaultProps.getProperty(propName);
            
            
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Tool.Print("Close error in applicaiton.properties!");
                e.printStackTrace();
            }
            
            return propValue;
            
        }
}
