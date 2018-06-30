package addresser;

import java.awt.AWTException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import utils.Robot;

public class Addresser {

    private Path executableFilePath = Paths.get(
            System.getProperty("user.dir"), 
            "resources", 
            "Addresser.exe");
    
    private Path exportedContactsFilePath = Paths.get(
            System.getProperty("user.dir"), 
            "Exported_contacts.txt");


    private Robot robot;
    private Process process;



    public Addresser() throws AWTException, IOException {

        process = new ProcessBuilder(executableFilePath.toString()).start();
        
        robot = new Robot();
    }



    public Addresser openFileMenu() {
        robot.tab();
        robot.enter();
        return this;
    }



    public Addresser openAddContactDialog() {
        openFileMenu();       
        robot.enter();
        return this;
    }



    public Addresser openEditContactDialog() {
        openFileMenu();        
        robot.downArrow();
        robot.enter();
        return this;
    }



    public Addresser fillAndSaveAddContactDialog(Contact contact) {

        robot.tab();
        robot.type(contact.getName());

        robot.tab();
        robot.type(contact.getPhoneNumber());

        robot.tab();
        robot.type(contact.getAddress());

        robot.tab();
        robot.type(contact.getEmail());

        robot.tab();
        robot.type(contact.getComments());

        robot.tab(2);
        robot.enter();

        robot.tab(2);
        return this;
    }


    
    public Addresser fillAndSaveEditContactDialog(Contact contact) {

        robot.tab();
        robot.type(contact.getName());
        
        robot.tab();
        robot.type(contact.getAddress());

        robot.tab();
        robot.type(contact.getPhoneNumber());        

        robot.tab();
        robot.type(contact.getEmail());

        robot.tab();
        robot.type(contact.getComments());

        robot.tab();
        robot.enter();
        
        robot.tab();
        return this;
    }



    public Contact getEditContactDialogInfo() {

        robot.tab();
        String name = robot.copy();


        robot.tab();
        String address = robot.copy();

        robot.tab();
        String phoneNumber = robot.copy();

        robot.tab();
        String email = robot.copy();

        robot.tab();
        String comments = robot.copy();


        robot.tab(2);
        robot.enter();

        robot.tab();
        return new Contact(name, address, phoneNumber, email, comments);

    }


    
    public Addresser deleteContact() {
        openFileMenu();       
        robot.downArrow(2);
        robot.enter();
        robot.tab();
        return this;
    }
    
    
    
    public Addresser exportContacts() {
        openFileMenu();       
        robot.downArrow(3);
        robot.enter();
        robot.delay(1000);
        return this;
    }
    

    public Addresser selectContactRow(int contactRowIndex) {        
        selectTopRow();
        robot.pageUp();
        robot.downArrow(contactRowIndex-1);
        return this;
    }
    
    
    
    public Addresser selectTopRow() {        
        robot.tab(2);
        robot.pageUp();        
        return this;
    }



    public Addresser delay(int ms) {
        robot.delay(ms);
        return this;
    }
    
    
    
    public void exit() {
        process.destroy();
    }

    
    
    public List<String> getExportedContacts(int count) throws IOException {
        return Files.lines(exportedContactsFilePath)
                .limit(count)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
    
}


