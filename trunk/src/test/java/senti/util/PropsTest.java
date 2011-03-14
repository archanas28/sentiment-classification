package senti.util;

import org.junit.Test;
import junit.framework.TestCase;

public class PropsTest extends TestCase {
    
    @Test
    public void testGetProperty(){
        
        String actual = new Props().getProperty("opennlp_sent_path");
        String expected = "/opennlp/en-sent.bin";
        assertEquals(expected, actual);
    }

}
