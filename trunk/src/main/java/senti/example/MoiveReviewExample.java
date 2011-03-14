package senti.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import senti.classifier.KCrossUnit;
import senti.classifier.KCrossValid;
import senti.feature.AdjAdvFeature;
import senti.feature.AdjFeature;
import senti.feature.NGramFeature;
import senti.feature.PolarizedBigramFeature;
import senti.feature.PolarizedLexicalFeature;
import senti.feature.ThreeAdjAdvFeature;
import senti.object.Constants.Polarity;
import senti.object.Doc;
import senti.util.FileListFilter;
import senti.util.Props;
import senti.util.Tool;

public class MoiveReviewExample {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String neg_path = Props.getDatasetProperty("moive_review_data_neg");
        String pos_path = Props.getDatasetProperty("moive_review_data_pos");
        String projectPath = new File("").getAbsolutePath();

        List<Doc> allDocsList = new ArrayList<Doc>();

        
        File neg_file = new File(projectPath + neg_path);
        File pos_file = new File(projectPath + pos_path);
        
        Tool.Print(projectPath + neg_path);

        Tool.Print("================Begin Read NEG Dir=======================");
        if(neg_file.isDirectory()){
            File[] files = neg_file.listFiles(new FileListFilter());
            for (File file : files) 
                allDocsList.add(new Doc(file.getAbsolutePath(), Polarity.NEG));
        }

        Tool.Print("================Begin Read POS Dir=======================");
        if(pos_file.isDirectory()){
            File[] files = pos_file.listFiles(new FileListFilter());
            for (File file : files) 
                allDocsList.add(new Doc(file.getAbsolutePath(), Polarity.POS));
        }

        List<KCrossUnit> kCrossVaildData = KCrossValid.GetKCrossVaildData(allDocsList);

        //=============================================================
        //          All the I/Os finish! Most time consuming! allDocsList contains all the information
        //=============================================================

        String binaryFileDir = new File("").getAbsolutePath() + Props.getDatasetProperty("binary_vector_output_dir");
        String tfFileDir = new File("").getAbsolutePath() + Props.getDatasetProperty("tf_vector_output_dir");
        
        for(int i = 0; i < kCrossVaildData.size(); i++){
            
            KCrossUnit kCrossUnit = kCrossVaildData.get(i);
            
            //============================================================
            //         Using Unigram Feature
            //============================================================
            kCrossUnit = NGramFeature.GetNGramBiRep(kCrossUnit, 1);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "1GramBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "1GramBiRep");
            kCrossUnit.clear();
            Tool.Print("------Unigram Bi Rep finish!-------");
            
            kCrossUnit = NGramFeature.GetNGramTFRep(kCrossUnit, 1);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "1GramTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "1GramTFRep");
            kCrossUnit.clear();
            Tool.Print("------Unigram TF Rep finish!-------");
            
            
            //============================================================
            //         Using Bigram Feature
            //============================================================
            kCrossUnit = NGramFeature.GetNGramBiRep(kCrossUnit, 2);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "2GramBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "2GramBiRep");
            kCrossUnit.clear();
            Tool.Print("------Bigram Bi Rep finish!-------");
            
            kCrossUnit = NGramFeature.GetNGramTFRep(kCrossUnit, 2);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "2GramTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "2GramTFRep");
            kCrossUnit.clear();
            Tool.Print("------Bigram TF Rep finish!-------");
            
            
            //============================================================
            //         Using Trigram Feature
            //============================================================
            kCrossUnit = NGramFeature.GetNGramBiRep(kCrossUnit, 3);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "3GramBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "3GramBiRep");
            kCrossUnit.clear();
            Tool.Print("------Trigram Bi Rep finish!-------");
            
            kCrossUnit = NGramFeature.GetNGramTFRep(kCrossUnit, 3);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "3GramTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "3GramTFRep");
            kCrossUnit.clear();
            Tool.Print("------Trigram TF Rep finish!-------");

            //============================================================
            //         Using 4gram Feature
            //============================================================
            kCrossUnit = NGramFeature.GetNGramBiRep(kCrossUnit, 4);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "4GramBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "4GramBiRep");
            kCrossUnit.clear();
            Tool.Print("------4gram Bi Rep finish!-------");
            
            kCrossUnit = NGramFeature.GetNGramTFRep(kCrossUnit, 4);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "4GramTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "4GramTFRep");
            kCrossUnit.clear();
            Tool.Print("------4gram TF Rep finish!-------");
            
            //============================================================
            //         Using 5gram Feature
            //============================================================
            kCrossUnit = NGramFeature.GetNGramBiRep(kCrossUnit, 5);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "5GramBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "5GramBiRep");
            kCrossUnit.clear();
            Tool.Print("------5gram Bi Rep finish!-------");
            
            kCrossUnit = NGramFeature.GetNGramTFRep(kCrossUnit, 5);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "5GramTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "5GramTFRep");
            kCrossUnit.clear();
            Tool.Print("------5gram TF Rep finish!-------");
            
            
            //============================================================
            //         Using 6gram Feature
            //============================================================
            kCrossUnit = NGramFeature.GetNGramBiRep(kCrossUnit, 6);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "6GramBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "6GramBiRep");
            kCrossUnit.clear();
            Tool.Print("------6gram Bi Rep finish!-------");
            
            kCrossUnit = NGramFeature.GetNGramTFRep(kCrossUnit, 6);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "6GramTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "6GramTFRep");
            kCrossUnit.clear();
            Tool.Print("------6gram TF Rep finish!-------");
            
            
            //============================================================
            //         Using AdjAdv Feature
            //============================================================
            kCrossUnit = AdjAdvFeature.GetAdjAdvBiRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "AdjAdvBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "AdjAdvBiRep");
            kCrossUnit.clear();
            Tool.Print("------AdjAdv Bi Rep finish!-------");
            
            kCrossUnit = AdjAdvFeature.GetAdjAdvTFRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "AdjAdvTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "AdjAdvTFRep");
            kCrossUnit.clear();
            Tool.Print("------AdjAdv TF Rep finish!-------");
            
            //============================================================
            //         Using Adj Feature
            //============================================================
            kCrossUnit = AdjFeature.GetAdjBiRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "AdjBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "AdjBiRep");
            kCrossUnit.clear();
            Tool.Print("------Adj Bi Rep finish!-------");
            
            kCrossUnit = AdjFeature.GetAdjTFRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "AdjTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "AdjTFRep");
            kCrossUnit.clear();
            Tool.Print("------Adj TF Rep finish!-------");
            
            
            //============================================================
            //         Using PolarizedBigram Feature
            //============================================================
            kCrossUnit = PolarizedBigramFeature.GetPolarizedBigramBiRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "PolarizedBigramBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "PolarizedBigramBiRep");
            kCrossUnit.clear();
            Tool.Print("------PolarizedBigram Bi Rep finish!-------");
            
            kCrossUnit = PolarizedBigramFeature.GetPolarizedBigramTFRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "PolarizedBigramTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "PolarizedBigramTFRep");
            kCrossUnit.clear();
            Tool.Print("------PolarizedBigram TF Rep finish!-------");
            
            
            //============================================================
            //         Using PolarizedLexical Feature
            //============================================================
            kCrossUnit = PolarizedLexicalFeature.GetPolarizedLexicalBiRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "PolarizedLexicalBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "PolarizedLexicalBiRep");
            kCrossUnit.clear();
            Tool.Print("------PolarizedLexical Bi Rep finish!-------");
            
            kCrossUnit = PolarizedLexicalFeature.GetPolarizedLexicalTFRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "PolarizedLexicalTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "PolarizedLexicalTFRep");
            kCrossUnit.clear();
            Tool.Print("------PolarizedLexical TF Rep finish!-------");
            
            
            //============================================================
            //         Using ThreeAdjAdv Feature
            //============================================================
            kCrossUnit = ThreeAdjAdvFeature.GetThreeAdjAdvBiRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, binaryFileDir, i, "ThreeAdjAdvBiRep");
            Tool.GenerateSVMLightInput(kCrossUnit, binaryFileDir, i, "ThreeAdjAdvBiRep");
            kCrossUnit.clear();
            Tool.Print("------ThreeAdjAdv Bi Rep finish!-------");
            
            kCrossUnit = ThreeAdjAdvFeature.GetThreeAdjAdvTFRep(kCrossUnit);
            Tool.GenerateWekaInput(kCrossUnit, tfFileDir, i, "ThreeAdjAdvTFRep");
            Tool.GenerateSVMLightInput(kCrossUnit, tfFileDir, i, "ThreeAdjAdvTFRep");
            kCrossUnit.clear();
            Tool.Print("------ThreeAdjAdv TF Rep finish!-------");
            
            
            
            /**
             * Add some combinations like "Improving Blog Polarity Classification via Topic Analysis and Adaptive Methods" 
             */
            
            
            
        }// end of for(int i = 0; i < kCrossVaildData.size(); i++)
    }

}
