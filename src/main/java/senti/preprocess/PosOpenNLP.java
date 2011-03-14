package senti.preprocess;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import senti.util.Props;

/*
 * ==========================================
 *   Abstraction of POS detection of OpenNLP
 * ==========================================
 */

public class PosOpenNLP {
    
    public static String[] getPOSs(String[] tokens){
        
        InputStream modelIn = null;
        POSModel model = null;
        String opennlp_pos_maxent_path = new Props().getProperty("opennlp_pos_maxent_path");
        
        try {
          //modelIn = new FileInputStream(new File("").getAbsoluteFile() + "\\" + opennlp_pos_maxent_path);
            modelIn = new PosOpenNLP().getClass().getResourceAsStream(opennlp_pos_maxent_path);
          model = new POSModel(modelIn);
        }
        catch (IOException e) {
          // Model loading failed, handle the error
          e.printStackTrace();
        }
        finally {
          if (modelIn != null) {
            try {
              modelIn.close();
            }
            catch (IOException e) {
            }
          }
        }
        
        POSTaggerME tagger = new POSTaggerME(model);
        String tags[] = tagger.tag(tokens);
        
        return tags;
    }
}
