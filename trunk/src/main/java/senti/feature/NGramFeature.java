package senti.feature;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import senti.classifier.KCrossUnit;
import senti.object.Doc;
import senti.object.NGram;
import senti.object.Word;
import senti.util.Tool;

public class NGramFeature {
    
    public static List<NGram> GetNGramFeature(Doc doc, int n){
        
        ArrayList<NGram> nGramList = new ArrayList<NGram>();
        
        for(int i = 0; i < doc.allWordList.size()-(n-1); i++){
            NGram nGram = new NGram(n, i+1);
            
            for(int j = 0; j < n; j++)
                nGram.words[j] = doc.allWordList.get(i+j);
            
            nGramList.add(nGram);
        }
        
        return nGramList;
    } 
    
    
    public static List<String> toStringList(List<NGram> nGramList){
        
        List<String> nGramFeatureList = new ArrayList<String>();
        
        for (NGram nGram : nGramList) {
            String tmpString = "";
            for (Word word : nGram.words) {
                tmpString += word.wordString + "_";
            }
            nGramFeatureList.add(tmpString);
        }
        
        return nGramFeatureList;
    }
    
    
    
    
    public static KCrossUnit GetNGramBiRep(KCrossUnit kCrossUnit, int n){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin "+ n + "gram Feature Bi Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = NGramFeature.toStringList(NGramFeature.GetNGramFeature(doc, n));
            allTrainFeatureList.addAll(oneDocFeatureList);
        }
        
        allTrainFeatureTF = FeatureStatistics.GetAllFeatureTF(allTrainFeatureList);
        thresholdTrainFeatureTF = FeatureStatistics.GetThresholdFeatureTF(allTrainFeatureTF);
        
        
        List<String> keyList = Tool.toKeyList(thresholdTrainFeatureTF);
        
        for(Doc doc:trainDocs){
            List<String> oneDocFeatureList = NGramFeature.toStringList(NGramFeature.GetNGramFeature(doc, n));
            
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
            List<String> oneDocFeatureList = NGramFeature.toStringList(NGramFeature.GetNGramFeature(doc, n));
            
            kCrossUnit.testDocsRep.append( doc.polarity.getValue() + " ");
            for (String wordString : keyList) {
                if(oneDocFeatureList.contains(wordString))
                    kCrossUnit.testDocsRep.append(1 + " ");
                else
                    kCrossUnit.testDocsRep.append(0 + " ");
            }
            
            kCrossUnit.testDocsRep.append("\n");
        }
        
        Tool.Print("================End " + n + "gram Feature Bi Rep=======================");
        
        
        return kCrossUnit;
    }
    
    
    public static KCrossUnit GetNGramTFRep(KCrossUnit kCrossUnit, int n){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        //ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin "+ n + "gram Feature TF Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = NGramFeature.toStringList(NGramFeature.GetNGramFeature(doc, n));
            
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
            List<String> oneDocFeatureList = NGramFeature.toStringList(NGramFeature.GetNGramFeature(doc, n));
            
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
            List<String> oneDocFeatureList = NGramFeature.toStringList(NGramFeature.GetNGramFeature(doc, n));
            
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
        
        
        
        Tool.Print("================End " + n + "gram Feature TF Rep=======================");
        
        
        return kCrossUnit;
    }
    
    
    
    
    
    

}
