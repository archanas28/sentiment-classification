package senti.object;

import senti.object.Constants.Polarity;

public class NGram {

    public Word[] words;
    
    public int position;
    
    public Polarity polarity = Polarity.UNKNOWN;
    
    public NGram(int n, int position){
        this.words = new Word[n];
        this.position = position;
    }
    
}
