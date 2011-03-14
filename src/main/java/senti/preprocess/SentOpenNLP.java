package senti.preprocess;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import senti.util.Props;


/*
 * ==========================================
 *   Abstraction of sentence detection of OpenNLP
 * ==========================================
 */

public class SentOpenNLP {

    /**
     * @param textString the content of one review
     * @return split the review content into sentences and return as an ArrayList
     */
    public static String[] getSents(String textString){
        
        
        InputStream modelIn = null;
        SentenceModel model = null;
        String open_nlp_sent_path = new Props().getProperty("opennlp_sent_path");
        
        //modelIn = new FileInputStream(new File("").getAbsoluteFile() + "\\" + open_nlp_sent_path);
        modelIn = new SentOpenNLP().getClass().getResourceAsStream(open_nlp_sent_path);

        try {
          model = new SentenceModel(modelIn);
        }
        catch (IOException e) {
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

        
        SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
        String sentences[] = sentenceDetector.sentDetect(textString);

        return sentences;
    }
    
}
