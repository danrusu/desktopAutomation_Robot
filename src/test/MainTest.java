package test;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MainTest {
    
    
    private enum TestConfig{
        addeditdelete,
        add100, 
        all
    }

    public static void main(String[] args) {
        
        
        JUnitCore junit = new JUnitCore();
        
        
        TestConfig testConfig = TestConfig.valueOf(
                Optional.ofNullable(System.getProperty("test"))
                .orElse(TestConfig.addeditdelete.name()).toLowerCase());
        

        Class<?>[] testClasses = new Class<?>[] { 
            AddEditDeleteContactTest.class };
        
        
        switch (testConfig) {

            case addeditdelete:
                // this is the default
                break;
                
            case add100:
                testClasses = new Class<?>[] { 
                    AddresserAdd100ContactsTest.class };
                break;
                
            case all:  
                testClasses = new Class<?>[] { 
                    AddEditDeleteContactTest.class, 
                    AddresserAdd100ContactsTest.class };
                break;
            
        }
        
        
        System.out.println("Running tests: " 
                + Arrays.asList(testClasses)
                .stream()
                .map(className -> className.getSimpleName())
                .collect(Collectors.joining(", ")));
        
        Result result = junit.run(testClasses);
        
        System.out.println(String.join(" | ",
                        "\n*** Result",
                        " total: " + result.getRunCount(),
                        "passed/failed: " 
                                + (result.getRunCount() - result.getFailureCount()) 
                                + "/" + result.getFailureCount(),
                        "runTime: " + Long.valueOf(result.getRunTime()).floatValue()/1000 + "s"));
        
    }

}

