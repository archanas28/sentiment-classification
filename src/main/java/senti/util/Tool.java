package senti.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import senti.classifier.KCrossUnit;

public class Tool {
    
    public  static void Print(String str) {
        
        System.out.println(str);
        
    }
    
    
    public static List<String> toKeyList(Hashtable<String, Integer> hashtable){
        List<String> keyList = new ArrayList<String>();
        
        for (String keyString : hashtable.keySet()) {
            keyList.add(keyString);
        }
        
        return keyList;
    }
    
    
    public static void GenerateWekaInput(KCrossUnit kCrossUnit, String outputDir, int times, String fileName){
        
        //=============== Begin generate weka train input ===========================
        String contentString = kCrossUnit.trainDocsRep.toString();
        
        String[] docStrings = contentString.split("\n");
        int vectorSize = docStrings[0].split(" ").length - 1;
        
        StringBuilder writeContentString = new StringBuilder("@relation train_"+ times +"_" + fileName + "\n\n");
        String trainOutputFilePath = outputDir + "train_weka_"+ times +"_" + fileName + ".arff";
        for(int i = 0; i < vectorSize; i++)
            writeContentString.append("@attribute " + (i+1) + " real\n");
        
        writeContentString.append("@attribute Class {-1, 1} \n\n@data \n");
        
        for (int i = 0; i < docStrings.length; i++) {
            String[] tmpStrings = docStrings[i].split(" ");
            for (int j = 1; j < tmpStrings.length; j++) {
                writeContentString.append(tmpStrings[j] + ", ");
            }
            writeContentString.append(tmpStrings[0] + "\n");
        }
        
        FileOperation.WriteFile(trainOutputFilePath, writeContentString.toString());
        Tool.Print(trainOutputFilePath + " Done");
      //=============== End generate weka train input ===========================
        
        
        
        //=============== Begin generate weka test input ===========================
        contentString = kCrossUnit.testDocsRep.toString();
        
        docStrings = contentString.split("\n");
        vectorSize = docStrings[0].split(" ").length - 1;
        
        writeContentString = new StringBuilder("@relation test_"+ times +"_" + fileName + "\n\n");
        String testOutputFilePath = outputDir + "test_weka_"+ times +"_" + fileName + ".arff";
        for(int i = 0; i < vectorSize; i++)
            writeContentString.append("@attribute " + (i+1) + " real\n");
        
        writeContentString.append("@attribute Class {-1, 1} \n\n@data \n");
        
        for (int i = 0; i < docStrings.length; i++) {
            String[] tmpStrings = docStrings[i].split(" ");
            for (int j = 1; j < tmpStrings.length; j++) {
                writeContentString.append(tmpStrings[j] + ", ");
            }
            writeContentString.append(tmpStrings[0] + "\n");
        }
        
        FileOperation.WriteFile(testOutputFilePath, writeContentString.toString());
        
      //=============== End generate weka test input ===========================
        
        Tool.Print(testOutputFilePath + " Done");
    }


    public static void GenerateSVMLightInput(KCrossUnit kCrossUnit, String outputDir, int times, String fileName){

      //=============== Begin generate SVM_light train input ===========================
        String contentString = kCrossUnit.trainDocsRep.toString();
        
        String[] docStrings = contentString.split("\n");
        int size = docStrings.length;
        
        StringBuilder writeContentString = new StringBuilder();
        
        for(int i = 0; i < size; i++){
            String[] tmpStrings = docStrings[i].split(" ");
            writeContentString.append(tmpStrings[0] + " ");
            for (int j = 1; j < tmpStrings.length; j++) {
                if(!tmpStrings[j].equals("0"))
                    writeContentString.append(j + ":" + tmpStrings[j] + " ");
            }
            writeContentString.append("\n");
        }
        
        String trainOutputFilePath = outputDir + "train_svm_"+ times +"_" + fileName + ".dat";
        
        FileOperation.WriteFile(trainOutputFilePath, writeContentString.toString());
        Tool.Print(trainOutputFilePath + "Done");
      //=============== End generate SVM_light train input ===========================
        
        
        
        
      //=============== Begin generate SVM_light test input ===========================
        contentString = kCrossUnit.testDocsRep.toString();
        
        docStrings = contentString.split("\n");
        size = docStrings.length;
        
        writeContentString = new StringBuilder();
        
        for(int i = 0; i < size; i++){
            String[] tmpStrings = docStrings[i].split(" ");
            writeContentString.append(tmpStrings[0] + " ");
            for (int j = 1; j < tmpStrings.length; j++) {
                if(!tmpStrings[j].equals("0"))
                    writeContentString.append(j + ":" + tmpStrings[j] + " ");
            }
            writeContentString.append("\n");
        }
        
        String testOutputFilePath = outputDir + "test_svm_"+ times +"_" + fileName + ".dat";
        
        FileOperation.WriteFile(testOutputFilePath, writeContentString.toString());
        Tool.Print(testOutputFilePath + "Done");
      //=============== End generate SVM_light test input ===========================
        
        
    }

}
