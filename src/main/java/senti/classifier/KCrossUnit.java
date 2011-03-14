package senti.classifier;

import java.util.ArrayList;
import java.util.List;

import senti.object.Doc;
import senti.util.FileOperation;

public class KCrossUnit{
    
    public List<Doc> trainDocs = new ArrayList<Doc>();
    
    public List<Doc> testDocs = new ArrayList<Doc>();
    
    public StringBuilder trainDocsRep = new StringBuilder();
    
    public StringBuilder testDocsRep = new StringBuilder();
    
    public KCrossUnit(List<Doc> trainDocs, List<Doc> testDocs){
        this.testDocs = testDocs;
        this.trainDocs = trainDocs;
    }
    
    public void clear(){
        this.trainDocsRep = new StringBuilder();
        this.testDocsRep = new StringBuilder();
    }
    
    public void writeDocVector(String filePath, String attribute,int times){
        
        String writePathString = "";
        
        writePathString = filePath + "train_" + times + "_" + attribute + ".txt";
        FileOperation.WriteFile(writePathString, this.trainDocsRep.toString());
        
        writePathString = filePath + "test_" + times + "_" + attribute + ".txt";
        FileOperation.WriteFile(writePathString, this.testDocsRep.toString());
    }
    
}