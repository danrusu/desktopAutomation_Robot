package utils;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface RandomStringGenerator {

    
    static final String lowerCaseletters = "abcdefghijklmnopqrstuvwxyz";
    static final String upperCaseletters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    // use only upper case letters because Addresser.exe has different sort than String
    static final String letters = upperCaseletters;
    
    
    
    public static String randomString(int length){
       
        SecureRandom random = new SecureRandom();
        
        StringBuilder sb = new StringBuilder(length);
       
       for(int i = 0; i < length; i++) { 
          sb.append(letters.charAt(
                  random.nextInt(letters.length())));          
       }
       
       return sb.toString();
    }
    
    
    
    public static List<String> generateRandomUniqStrings(int stringsCount, int stringLength) {
        
        // avoid duplication
        Set<String> randomStrings = new HashSet<>();
        
        for(int i=0; i<2*stringsCount; i++) {
            randomStrings.add(randomString(stringLength));
            if (randomStrings.size() == stringsCount) {
                break;
            }
        }
        
        //reverse order to have the new contact on top row
        return randomStrings.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
    
    
}
