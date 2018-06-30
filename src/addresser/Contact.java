package addresser;

import java.util.List;

public class Contact {
    
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String comments;

    
    
    public Contact(String name, 
            String address, 
            String phoneNumber, 
            String email, 
            String comments) {
        
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.comments = comments;
    }

    

    public String toText() {
        return String.join(";",
                name,
                address,
                email,
                phoneNumber,
                comments);
    }
    
    

    public String getName() {
        return name;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }



    public String getAddress() {
        return address;
    }



    public String getEmail() {
        return email;
    }



    public String getComments() {
        return comments;
    }
    
    
    
    public static Contact newIndexedContact(
            int index, 
            List<String> randomStrings, 
            Contact contact) {
 
        String numbersIndex = index + "_";
        String lettersIndex = " " + randomStrings.get(index); 
        
        return new Contact(
                contact.getName() + lettersIndex,
                numbersIndex + contact.getAddress(),
                index + contact.getPhoneNumber(),
                numbersIndex + contact.getEmail(),
                numbersIndex + contact.getComments()                               
                );
    }

}

