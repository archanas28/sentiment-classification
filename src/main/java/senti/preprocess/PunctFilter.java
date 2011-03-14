package senti.preprocess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import senti.util.FileOperation;
import senti.util.Props;

public class PunctFilter {

    public static String[] filterPunct(String[] words){

        String content = FileOperation.ReadFile(new Props().getProperty("punct_mark_dict_path"));
        List<String> punctList = Arrays.asList(content.split("\n"));

        ArrayList<String> filterWords = new ArrayList<String>();

        for (String word : words) {
            if(!punctList.contains(word))
                filterWords.add(word);
        }

        String[] filterWordArray = new String[filterWords.size()];
        
        return filterWords.toArray(filterWordArray);

    }

}
