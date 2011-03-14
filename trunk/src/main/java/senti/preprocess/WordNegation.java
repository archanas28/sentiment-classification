package senti.preprocess;

import java.util.ArrayList;
import java.util.Hashtable;

import senti.util.FileOperation;
import senti.util.Props;

public class WordNegation {
    
    Hashtable<String, String> abbrNegDict = new Hashtable<String, String>();
    
    public WordNegation(){
        String dictContent = FileOperation.ReadFile(new Props().getProperty("abbr_neg_dict_path"));
        
        String[] lineStrings = dictContent.split("\n");
        for (String line : lineStrings) {
            String[] words = line.split("\t");
            abbrNegDict.put(words[0], words[1]);
        }
        
    }
    
    public String[] abbrNegExtension(String[] original){
                
        ArrayList<String>arrayList = new ArrayList<String>();
        
        for (String word : original) {
            if (abbrNegDict.containsKey(word)) {
                String[] tmpStrings = abbrNegDict.get(word).split(" ");
                for (String tmpString : tmpStrings) {
                    arrayList.add(tmpString);
                }
            }
            else {
                arrayList.add(word);
            }
        }
        
        String[] negExtension = new String[arrayList.size()];
        return arrayList.toArray(negExtension);
    }
    
    //public String[] transNegTransform(String[])
    

}
