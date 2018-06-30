package utils;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.stream.IntStream;

public class Robot extends java.awt.Robot {

    
    public int MIN_DELAY = 50;


    public Robot() throws AWTException {
        super();
        super.delay(1000);
    }



    public void pressAndRelease(int keyEvent) {
        super.keyPress(keyEvent);
        super.keyRelease(keyEvent);
    }

    
    
    public void pressAndReleaseMultiple(int keyEvent, int pressCount) {
        IntStream.rangeClosed(1, pressCount).forEach( i -> pressAndRelease(keyEvent));
    }

    
        
    public void enter() {
        pressAndRelease(KeyEvent.VK_ENTER); 
        delay();
    }       


    
    public void tab(int count) {
        pressAndReleaseMultiple(KeyEvent.VK_TAB, count);
        delay();
    }

    
    
    public void tab() {
        tab(1);
    }



    public void downArrow(int count) {
        pressAndReleaseMultiple(KeyEvent.VK_DOWN, count);
        delay();
    }
    
    
    
    public void downArrow() {
        downArrow(1);  
    }
    
    
    
    public void upArrow(int count) {
        pressAndReleaseMultiple(KeyEvent.VK_UP, count);
        delay();
    }
    
    
    
    public void upArrow() {
        upArrow(1);  
    }

    
    
    public void backspace() {
        pressAndRelease(KeyEvent.VK_BACK_SPACE);         
    }
    
    
    
    public void pageUp() {
        pressAndRelease(KeyEvent.VK_PAGE_UP);         
    }
    

    
    public String copy() {
        
        selectAll();
        
        super.keyPress(KeyEvent.VK_CONTROL);
        super.keyPress(KeyEvent.VK_C);
        super.keyRelease(KeyEvent.VK_C);
        super.keyRelease(KeyEvent.VK_CONTROL);
        
        delay();
        
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
 
        
        try {
            return clipboard.getData(DataFlavor.stringFlavor).toString();
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return "could not clip text";
        }
        finally{
            deselectAll();
        }
    }
    
    
    
    public void paste() {
        super.keyPress(KeyEvent.VK_CONTROL);
        super.keyPress(KeyEvent.VK_V);
        super.keyRelease(KeyEvent.VK_V);
        super.keyRelease(KeyEvent.VK_CONTROL);
        delay();
    }
    
    
    

    public void clip(String characters) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection( characters );
        clipboard.setContents(stringSelection, stringSelection);
        delay();
    }
    

    
    public void selectAll() {
        super.keyPress(KeyEvent.VK_CONTROL);
        super.keyPress(KeyEvent.VK_A);
        super.keyRelease(KeyEvent.VK_A);
        super.keyRelease(KeyEvent.VK_CONTROL);   
        delay();
    }

    
    
    public void deselectAll() {
        super.keyPress(KeyEvent.VK_CONTROL);
        super.keyPress(KeyEvent.VK_UP);
        super.keyRelease(KeyEvent.VK_UP);
        super.keyRelease(KeyEvent.VK_CONTROL);
        delay();
    }
    
    
    
    public void type(String characters) {
        selectAll();
        backspace();
        
        clip(characters);
        
        paste();
    }
    

    
    public void delay() {
        delay(MIN_DELAY);
    }

}

