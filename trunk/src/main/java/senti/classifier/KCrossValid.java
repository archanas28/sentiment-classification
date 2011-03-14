package senti.classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import senti.object.Doc;
import senti.util.Props;

public class KCrossValid {
    

    public static List<KCrossUnit> GetKCrossVaildData(List<Doc> allDocsList){
        int k = Integer.parseInt(new Props().getProperty("k_cross_validation"));
        List<KCrossUnit> kCrossUnitList = new ArrayList<KCrossUnit>(k);
        int testSize = allDocsList.size()/k;
        
        List<Doc> copyAllDocsList = new ArrayList<Doc>(allDocsList);
        ArrayList<ArrayList<Doc>> tmpDocArrayList = new ArrayList<ArrayList<Doc>>();
        
        for(int i = 0; i < k; i++){

            ArrayList<Doc> arrayList = new ArrayList<Doc>();
            
            while(arrayList.size() < testSize){
                Random random = new Random();
                int ranNum = random.nextInt(copyAllDocsList.size());
                arrayList.add(copyAllDocsList.get(ranNum));
                copyAllDocsList.remove(ranNum);
            }
            
            tmpDocArrayList.add(arrayList);
        }
        
        
        for(int i = 0; i < k; i++){
            List<Doc> trainDocs = new ArrayList<Doc>();
            for(int j = 0; j < k; j++){
                if( i == j) 
                    continue;
                trainDocs.addAll(tmpDocArrayList.get(j));
            }
            kCrossUnitList.add( new KCrossUnit(trainDocs, tmpDocArrayList.get(i)) );
        }
        
        return kCrossUnitList;
    }

}


