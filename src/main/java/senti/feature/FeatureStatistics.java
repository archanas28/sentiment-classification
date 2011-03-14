package senti.feature;

import java.util.Hashtable;
import java.util.List;

import senti.util.Props;

public class FeatureStatistics {
    
    public static Hashtable<String, Integer> GetAllFeatureTF(List<String> allFeatureList){
        
        Hashtable<String, Integer> allFeatureTF = new Hashtable<String, Integer>();
        
        for (String feature : allFeatureList) {
            if(allFeatureTF.containsKey(feature))
                allFeatureTF.put(feature, allFeatureTF.get(feature)+1);
            else
                allFeatureTF.put(feature, 1);
        }
        
        return allFeatureTF;
    }

    public static Hashtable<String, Integer> GetThresholdFeatureTF(Hashtable<String, Integer> allFeatureTF) {
        
        Hashtable<String, Integer> thresholdFeatureTF = new Hashtable<String, Integer>();
        int threshold = Integer.parseInt(new Props().getProperty("TF_Threshold"));
        
        for (String key : allFeatureTF.keySet()) {
            if(allFeatureTF.get(key) >= threshold)
                thresholdFeatureTF.put(key, allFeatureTF.get(key));
        }
        
        return thresholdFeatureTF;
    }

}
