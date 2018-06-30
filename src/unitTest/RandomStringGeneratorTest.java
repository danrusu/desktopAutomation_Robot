package unitTest;

import org.junit.Assert;
import org.junit.Test;

import utils.RandomStringGenerator;

class RandomStringGeneratorTest {

    
    @Test
    void test100x2() {
        
        Assert.assertEquals(
                
                100,
                
                RandomStringGenerator.generateRandomUniqStrings(100, 2)
                .stream().count());       
    }
    
    
    
    @Test
    void test500x3() {
        
        Assert.assertEquals(
                
                500,
                
                RandomStringGenerator.generateRandomUniqStrings(500, 3)
                .stream().count());
        
    }

}
