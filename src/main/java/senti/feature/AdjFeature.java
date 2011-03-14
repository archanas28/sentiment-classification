package senti.feature;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import senti.classifier.KCrossUnit;
import senti.object.Doc;
import senti.object.Word;
import senti.util.Tool;

public class AdjFeature {
    
    public static List<Word> GetAdjFeature(Doc doc){
        
        List<Word> adjWordList = new ArrayList<Word>();
        
        for (Word word : doc.allWordList) {
            if(word.POS.startsWith("JJ"))
                adjWordList.add(word);
        }
        
        return adjWordList;
    }
    
    
    
    public static List<String> toStringList(List<Word> adjFeature){
        
        List<String> adjFeatureList = new ArrayList<String>();
        
        for (Word word : adjFeature) {
            adjFeatureList.add(word.wordString);
        }
        
        return adjFeatureList;
    }
    
public static KCrossUnit GetAdjBiRep(KCrossUnit kCrossUnit){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin Adj Feature Bi Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = AdjFeature.toStringList(AdjFeature.GetAdjFeature(doc));
            allTrainFeatureList.addAll(oneDocFeatureList);
        }
        
        allTrainFeatureTF = FeatureStatistics.GetAllFeatureTF(allTrainFeatureList);
        thresholdTrainFeatureTF = FeatureStatistics.GetThresholdFeatureTF(allTrainFeatureTF);
        
        
        List<String> keyList = Tool.toKeyList(thresholdTrainFeatureTF);
        
        for(Doc doc:trainDocs){
            List<String> oneDocFeatureList = AdjFeature.toStringList(AdjFeature.GetAdjFeature(doc));
            
            kCrossUnit.trainDocsRep.append( doc.polarity.getValue() + " ");
            for (String wordString : keyList) {
                if(oneDocFeatureList.contains(wordString))
                    kCrossUnit.trainDocsRep.append(1 + " ");
                else
                    kCrossUnit.trainDocsRep.append(0 + " ");
            }
            
            kCrossUnit.trainDocsRep.append("\n");
        }
        
        
        for(Doc doc:testDocs){
            List<String> oneDocFeatureList = AdjFeature.toStringList(AdjFeature.GetAdjFeature(doc));
            
            kCrossUnit.testDocsRep.append( doc.polarity.getValue() + " ");
            for (String wordString : keyList) {
                if(oneDocFeatureList.contains(wordString))
                    kCrossUnit.testDocsRep.append(1 + " ");
                else
                    kCrossUnit.testDocsRep.append(0 + " ");
            }
            
            kCrossUnit.testDocsRep.append("\n");
        }
        
        Tool.Print("================End Adj Feature Bi Rep=======================");
        
        
        return kCrossUnit;
    }
    
    
    public static KCrossUnit GetAdjTFRep(KCrossUnit kCrossUnit){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        //ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin Adj Feature TF Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = AdjFeature.toStringList(AdjFeature.GetAdjFeature(doc));
            
            for (String featureString : oneDocFeatureList) {
                if(allTrainFeatureTF.containsKey(featureString))
                    allTrainFeatureTF.put(featureString, allTrainFeatureTF.get(featureString)+1);
                else 
                    allTrainFeatureTF.put(featureString, 1);
            }
        }
        
        thresholdTrainFeatureTF = FeatureStatistics.GetThresholdFeatureTF(allTrainFeatureTF);
        
        
        List<String> keyList = Tool.toKeyList(thresholdTrainFeatureTF);
        
        for(Doc doc:trainDocs){
            List<String> oneDocFeatureList = AdjFeature.toStringList(AdjFeature.GetAdjFeature(doc));
            
            Hashtable<String, Integer> oneDocFeatureTable = new Hashtable<String, Integer>();
            
            for (String feature : oneDocFeatureList) {
                if(oneDocFeatureTable.containsKey(feature))
                    oneDocFeatureTable.put(feature, oneDocFeatureTable.get(feature)+1);
                else 
                    oneDocFeatureTable.put(feature, 1);
            }
            
            kCrossUnit.trainDocsRep.append(doc.polarity.getValue() + " ");
            
            for (String wordString : keyList) {
                if(oneDocFeatureTable.containsKey(wordString))
                    kCrossUnit.trainDocsRep.append(oneDocFeatureTable.get(wordString) + " ");
                else
                    kCrossUnit.trainDocsRep.append(0 + " ");
            }
            
            kCrossUnit.trainDocsRep.append("\n");
        }
        
        
        for(Doc doc:testDocs){
            List<String> oneDocFeatureList = AdjFeature.toStringList(AdjFeature.GetAdjFeature(doc));
            
            Hashtable<String, Integer> oneDocFeatureTable = new Hashtable<String, Integer>();
            
            for (String feature : oneDocFeatureList) {
                if(oneDocFeatureTable.containsKey(feature))
                    oneDocFeatureTable.put(feature, oneDocFeatureTable.get(feature)+1);
                else 
                    oneDocFeatureTable.put(feature, 1);
            }
            
            kCrossUnit.testDocsRep.append(doc.polarity.getValue() + " ");
            
            for (String wordString : keyList) {
                if(oneDocFeatureTable.containsKey(wordString))
                    kCrossUnit.testDocsRep.append(oneDocFeatureTable.get(wordString) + " ");
                else
                    kCrossUnit.testDocsRep.append(0 + " ");
            }
            
            kCrossUnit.testDocsRep.append("\n");
        }
        
        
        
        Tool.Print("================End Adj Feature TF Rep=======================");
        
        
        return kCrossUnit;
    }

}
