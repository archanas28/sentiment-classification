package senti.preprocess;

import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import senti.util.Props;


/*
 * ==========================================
 *   Abstraction of Token detection of OpenNLP
 * ==========================================
 */


public class TokenOpenNLP {
    
    public static String[] getTokens(String sentence){
        
        
        String open_nlp_token_path = new Props().getProperty("opennlp_token_path");
        InputStream modelIn = null;
        TokenizerModel model = null;
        
        
        //modelIn = new FileInputStream(new File("").getAbsoluteFile() + "\\" + open_nlp_token_path);
        modelIn = new TokenOpenNLP().getClass().getResourceAsStream(open_nlp_token_path);
        
        try {
          model = new TokenizerModel(modelIn);
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
        
        
        Tokenizer tokenizer = new TokenizerME(model);
        String tokens[] = tokenizer.tokenize(sentence);
        
        return tokens;
    }

}
