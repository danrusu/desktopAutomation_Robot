package test;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import addresser.Addresser;
import utils.Assert;



public class AddEditDeleteContactTest {

    private Addresser addresser;
    

    
    @Before
    public void before() throws AWTException, IOException {
        addresser = new Addresser();
    }



    @After
    public void after() {
        addresser.exit();
    }



    @Test
    public void addContactTest(){
        
        System.out.println("*** addContactTest:");
        
        Assert.assertEquals(
                
                TestData.TestContact1,
                
                addresser.openAddContactDialog()
                
                    .fillAndSaveAddContactDialog(TestData.TestContact1)
                    
                    .selectTopRow()
                    
                    .openEditContactDialog()
                    
                    .getEditContactDialogInfo()
        );        
    }

    

    @Test
    public void editContactTest(){
        
        System.out.println("*** editContactTest:");
        
        Assert.assertEquals(
                
                TestData.TestContact1,
                
                addresser.selectContactRow(1)
                
                    .openEditContactDialog()
                    
                    .fillAndSaveEditContactDialog(TestData.TestContact1)
                    
                    .selectTopRow()
                    
                    .openEditContactDialog()
                    
                    .getEditContactDialogInfo()
         );              
    }



    @Test
    public void deleteContactTest(){
        
        System.out.println("*** deleteContactTest:");
        
        Assert.assertEquals(
                
                TestData.DefaultContact2,
                
                addresser.selectContactRow(1)
                
                    .deleteContact()
                    
                    .selectTopRow()
                    
                    .openEditContactDialog()
                    
                    .getEditContactDialogInfo()
        );
    }
    
}   

