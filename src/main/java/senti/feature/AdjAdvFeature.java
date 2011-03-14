package senti.feature;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import senti.classifier.KCrossUnit;
import senti.object.Doc;
import senti.object.NGram;
import senti.object.Word;
import senti.util.Tool;

public class AdjAdvFeature {

    public static List<NGram> GetAdjAdvFeature(Doc doc){
        
        ArrayList<NGram> nGramList = new ArrayList<NGram>();
        
        for (NGram nGram : NGramFeature.GetNGramFeature(doc, 2)) {
            boolean flag = false;
            
            for (Word word : nGram.words)
                if(word.POS.startsWith("JJ") || word.POS.startsWith("VB")){
                    flag = true;
                    break;
                }
            
            if(flag)
                nGramList.add(nGram);
        }
        
        return nGramList;
    }
    
    
    public static List<String> toStringList(List<NGram> adjAdvFeature){
        List<String> stringFeatureList = new ArrayList<String>();
        
        for (NGram nGram : adjAdvFeature) {
            String tmpString = "";
            for (Word word : nGram.words) {
                tmpString += word.wordString + "_";
            }
            stringFeatureList.add(tmpString);
        }
        
        return stringFeatureList;
    }
    
    
    
    public static KCrossUnit GetAdjAdvBiRep(KCrossUnit kCrossUnit){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin AdjAdv Feature Bi Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = AdjAdvFeature.toStringList(AdjAdvFeature.GetAdjAdvFeature(doc));
            allTrainFeatureList.addAll(oneDocFeatureList);
        }
        
        allTrainFeatureTF = FeatureStatistics.GetAllFeatureTF(allTrainFeatureList);
        thresholdTrainFeatureTF = FeatureStatistics.GetThresholdFeatureTF(allTrainFeatureTF);
        
        
        List<String> keyList = Tool.toKeyList(thresholdTrainFeatureTF);
        
        for(Doc doc:trainDocs){
            List<String> oneDocFeatureList = AdjAdvFeature.toStringList(AdjAdvFeature.GetAdjAdvFeature(doc));
            
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
            List<String> oneDocFeatureList = AdjAdvFeature.toStringList(AdjAdvFeature.GetAdjAdvFeature(doc));
            
            kCrossUnit.testDocsRep.append( doc.polarity.getValue() + " ");
            for (String wordString : keyList) {
                if(oneDocFeatureList.contains(wordString))
                    kCrossUnit.testDocsRep.append(1 + " ");
                else
                    kCrossUnit.testDocsRep.append(0 + " ");
            }
            
            kCrossUnit.testDocsRep.append("\n");
        }
        
        Tool.Print("================End AdjAdv Feature Bi Rep=======================");
        
        
        return kCrossUnit;
    }
    
    
    public static KCrossUnit GetAdjAdvTFRep(KCrossUnit kCrossUnit){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        //ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin AdjAdv Feature TF Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = AdjAdvFeature.toStringList(AdjAdvFeature.GetAdjAdvFeature(doc));
            
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
            List<String> oneDocFeatureList = AdjAdvFeature.toStringList(AdjAdvFeature.GetAdjAdvFeature(doc));
            
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
            List<String> oneDocFeatureList = AdjAdvFeature.toStringList(AdjAdvFeature.GetAdjAdvFeature(doc));
            
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
        
        
        
        Tool.Print("================End AdjAdv Feature TF Rep=======================");
        
        
        return kCrossUnit;
    }
    
}
