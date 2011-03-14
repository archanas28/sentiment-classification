package senti.object;

public class Constants {

    public enum Polarity {
        
        NEG (-1), POS(1), Both(2), NEU(0), UNKNOWN(10);
        
        private final int value;
        
        private Polarity(int value){
            this.value = value;
        }
       
        public int getValue(){
            return this.value;
        }
    }
    
    
    public enum SubjType{
        STRONG(1), WEAK(-1), UNKNOWN(10);
        
        private final int value;
        
        private SubjType(int value){
            this.value = value;
        }
       
        public int getValue(){
            return this.value;
        }
        
    }
    
    public enum Subjectivity{
        Subj(1), Obj(-1), UNKNOWN(10);
        
        private final int value;
        
        private Subjectivity(int value){
            this.value = value;
        }
       
        public int getValue(){
            return this.value;
        }
    }
    
}
