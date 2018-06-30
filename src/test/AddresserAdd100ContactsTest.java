package test;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import addresser.Addresser;
import addresser.Contact;
import utils.Assert;
import utils.RandomStringGenerator;



public class AddresserAdd100ContactsTest {

    private Addresser addresser;
    private final int totalAddedContacts = 100;



    @Before
    public void before() throws AWTException, IOException {
        addresser = new Addresser();
    }



    @After
    public void after() {
        addresser.exit();
    }



    @Test
    public void add100ContactsTest() throws IOException{

        System.out.println("add100ContactsTest:");

        List<String> randomStrings = RandomStringGenerator
                .generateRandomUniqStrings(totalAddedContacts, 2);
        
        List<String> addedContacts = new ArrayList<>();

        IntStream.range(0, totalAddedContacts).forEach( index -> {

            Contact newContact = Contact.newIndexedContact(
                    index, 
                    randomStrings, 
                    TestData.TestContact1);
            
            addedContacts.add(newContact.toText());

            addresser.openAddContactDialog()

                .fillAndSaveAddContactDialog(newContact);

                        
        });

        addresser.exportContacts();
        
        List<String> exportedContacts = addresser.getExportedContacts(totalAddedContacts);
        
        IntStream.range(0, totalAddedContacts).forEach( index ->
            Assert.equals("Check contact " + index + ": ",
                    addedContacts.get(index),
                    exportedContacts.get(index)));
    }

}

