package senti.feature;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import senti.classifier.KCrossUnit;
import senti.object.Doc;
import senti.object.NGram;
import senti.object.PolarizedLexicon;
import senti.object.Word;
import senti.util.Tool;

public class PolarizedBigramFeature {

    public static List<String> GetPolarizedBigramFeature(Doc doc){
        
        List<String> polarizedBigramList = new ArrayList<String>();
        Hashtable<String, Word> sentimentDict = PolarizedLexicon.polarizedLexicon;
        List<NGram> bigramList = NGramFeature.GetNGramFeature(doc, 2);
        
        for (NGram nGram : bigramList) {
            
            Word firstWord = nGram.words[0];
            Word secondWord = nGram.words[1];
            
            if(sentimentDict.containsKey(firstWord.wordString)){
                String tmpString = sentimentDict.get(firstWord.wordString).polarity.toString()+ "/" + firstWord.POS;
                polarizedBigramList.add(tmpString + "_" + secondWord.wordString);
                polarizedBigramList.add(tmpString + "_" + secondWord.POS);
            }
            
            if(sentimentDict.containsKey(secondWord.wordString)){
                String tmpString = sentimentDict.get(secondWord.wordString).polarity.toString()+ "/" + secondWord.POS;
                polarizedBigramList.add(firstWord.wordString + "_" + tmpString);
                polarizedBigramList.add(firstWord.POS + "_" + tmpString);
            }
                    
        }
        
        return polarizedBigramList;
    }
    
    public static List<String> toStringList(List<String> polarizedBigramList){
        return polarizedBigramList;
    }
    
    
public static KCrossUnit GetPolarizedBigramBiRep(KCrossUnit kCrossUnit){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin PolarizedBigram Feature Bi Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = PolarizedBigramFeature.toStringList(PolarizedBigramFeature.GetPolarizedBigramFeature(doc));
            allTrainFeatureList.addAll(oneDocFeatureList);
        }
        
        allTrainFeatureTF = FeatureStatistics.GetAllFeatureTF(allTrainFeatureList);
        thresholdTrainFeatureTF = FeatureStatistics.GetThresholdFeatureTF(allTrainFeatureTF);
        
        
        List<String> keyList = Tool.toKeyList(thresholdTrainFeatureTF);
        
        for(Doc doc:trainDocs){
            List<String> oneDocFeatureList = PolarizedBigramFeature.toStringList(PolarizedBigramFeature.GetPolarizedBigramFeature(doc));
            
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
            List<String> oneDocFeatureList = PolarizedBigramFeature.toStringList(PolarizedBigramFeature.GetPolarizedBigramFeature(doc));
            
            kCrossUnit.testDocsRep.append( doc.polarity.getValue() + " ");
            for (String wordString : keyList) {
                if(oneDocFeatureList.contains(wordString))
                    kCrossUnit.testDocsRep.append(1 + " ");
                else
                    kCrossUnit.testDocsRep.append(0 + " ");
            }
            
            kCrossUnit.testDocsRep.append("\n");
        }
        
        Tool.Print("================End PolarizedBigram Feature Bi Rep=======================");
        
        
        return kCrossUnit;
    }
    
    
    public static KCrossUnit GetPolarizedBigramTFRep(KCrossUnit kCrossUnit){
        
        List<Doc> trainDocs = kCrossUnit.trainDocs;
        List<Doc> testDocs = kCrossUnit.testDocs;
        
        
        //ArrayList<String> allTrainFeatureList = new ArrayList<String>();
        Hashtable<String, Integer> allTrainFeatureTF = new Hashtable<String, Integer>();
        Hashtable<String, Integer> thresholdTrainFeatureTF = new Hashtable<String, Integer>();

        Tool.Print("================Begin PolarizedBigram Feature TF Rep=======================");

        for (Doc doc : trainDocs) {
            List<String> oneDocFeatureList = PolarizedBigramFeature.toStringList(PolarizedBigramFeature.GetPolarizedBigramFeature(doc));
            
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
            List<String> oneDocFeatureList = PolarizedBigramFeature.toStringList(PolarizedBigramFeature.GetPolarizedBigramFeature(doc));
            
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
            List<String> oneDocFeatureList = PolarizedBigramFeature.toStringList(PolarizedBigramFeature.GetPolarizedBigramFeature(doc));
            
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
        
        
        
        Tool.Print("================End PolarizedBigram Feature TF Rep=======================");
        
        
        return kCrossUnit;
    }
    
    
    
}
