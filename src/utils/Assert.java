package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import addresser.Contact;


public interface Assert{



    static void passAssertion(
            String assertionDescription,
            String expected) {

       System.out.println(
                assertionDescription
                + "expected: \"" + expected + "\""
                + " PASSED");
    }



    static void failAssertion(
            String assertionDescription,
            String expected, 
            String actual){

        String[] failureMessageTokens = new String[] {
                assertionDescription,
                "expected: \"" + expected + "\"",
                "actual: \"" + actual + "\"",
                "FAILED" 
        };

       String failureMessage = Arrays.asList(failureMessageTokens)
               .stream()
               .collect(Collectors.joining(" | "));
       
       System.out.println(failureMessage);

        throw new AssertionError(failureMessage);
    }



    public static <T> void isCondition(
            String assertionDescription,
            T expected,
            T actual,
            BiPredicate<T, T> condition) {

        if (condition.test(
                Optional.ofNullable(expected)
                .orElseThrow( () -> new AssertionError("Expected value is null!") ),
                Optional.ofNullable(actual)
                .orElseThrow( () -> new AssertionError("Actual value is null!") ))
                ){
            passAssertion(assertionDescription, expected.toString());
        }
        else {
            failAssertion(assertionDescription, expected.toString(), "" + actual);
        }
    }



    public static List<AssertionError> assertAll(Runnable ...assertions) {

        List<AssertionError> errors = new ArrayList<>();

        Arrays.asList(assertions).stream()
        .forEach( x -> {

            try {
                x.run();
            }

            catch(AssertionError ae) {
                errors.add(ae);    
            }

        } );

        return errors;
    }



    public static void assertExceptions(List<AssertionError> assertionErrors) {
        if (! assertionErrors.isEmpty()) {            
            throw new AssertionError(
                    assertionErrors.stream()
                    .map(assertionError -> assertionError.getMessage())
                    .collect(Collectors.joining("\n"))
                    );
        }
    }



    public static void equals(
            String assertionDescription,
            String expected,            
            String actual){


        isCondition(assertionDescription, 
                expected, 
                actual, 
                (exp, act) -> exp.equals(act)
                );
    }



    public static void equalsIgnoreCase(
            String assertionDescription,
            String expected,
            String actual){

        isCondition(assertionDescription, 
                expected, 
                actual, 
                (exp, act) -> exp.equalsIgnoreCase(act)
                );
    }



    public static void equalsFloatStrings(
            String assertionDescription,
            String expected,
            String actual){

        isCondition(assertionDescription, 
                expected.replace(",", ""), 
                actual, 
                (exp, act) -> Float.parseFloat(exp) == Float.parseFloat(act)
                );
    }



    public static void actualContainsExpected(
            String assertionDescription,
            String expected,
            String actual){

        isCondition(assertionDescription, 
                expected, 
                actual, 
                (exp, act) -> act.contains(exp)
                );
    }



    public static void expectedContainsActual(
            String assertionDescription,
            String expected,
            String actual){

        actualContainsExpected(assertionDescription, 
                actual, 
                expected);
    }



    public static void assertTrue(
            String assertionDescription, 
            boolean isSuccessful){

        if( isSuccessful ){
           System.out.println("Assertion: \" " + assertionDescription + "\" - PASSED");
        }
        else {
           System.out.println("Assertion: \"" + assertionDescription + "\" - FAILED");
            throw new AssertionError(assertionDescription);
        }
    }



    public static void fail(String failureMessage){
       System.out.println("Assertion failed!" + failureMessage);
        throw new AssertionError(failureMessage);
    }


    
    public static void assertAllAndThrow(Runnable ...assertions) {
        assertExceptions(assertAll(assertions));
    }
    
    
    
    
    public static void assertEquals(Contact expectedContact, Contact actualContact) {

        assertAllAndThrow( 
                
                () -> equals("Check name: ", 
                        expectedContact.getName(), actualContact.getName()),

                () -> equals("Check address: ", 
                        expectedContact.getAddress(), actualContact.getAddress()),

                () -> equals("Check phone number: ", 
                        expectedContact.getPhoneNumber(), actualContact.getPhoneNumber()),

                () -> equals("Check email: ", 
                        expectedContact.getEmail(), actualContact.getEmail()),

                () -> equals("Check comment: ", 
                        expectedContact.getComments(), actualContact.getComments())
        );
    }
}
