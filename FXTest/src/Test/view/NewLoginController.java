package Test.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Test.Main;
import Test.SignUp.SignUpController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NewLoginController {
  
  private Main main;
  
  Scanner logInScanner;
  SignUpController signUp = new SignUpController();
  
  public static String username;
  public static String password;
  public String compareUsername;
  public String comparePassword;
  public static String createdUsername;
  public static String createdPassword;
 
  @FXML
  private TextField usernameText;
  @FXML
  private PasswordField passwordText;

  
  @FXML
  private void goMenu() throws IOException {
    getText();
    System.out.println("1");
    createdUsername = getUsername();
    createdPassword = getPassword();
    System.out.println(createdUsername + "------" + createdPassword);
    if(username.equals(createdUsername) && password.equals(createdPassword)) {
      main.showMenuScene();
    }else {
      main.showLogInError();
    }
  }
  
  @FXML
  private void goSignUp() throws IOException {
    main.showSignUp();
  }
  
  @FXML
  private void getText() throws IOException {
    compareUsername = usernameText.getText();
    comparePassword = passwordText.getText();

    if(!compareUsername.equals(null) && !comparePassword.equals(null)) {
      username = compareUsername;
      password = comparePassword;
    }else {
      System.out.println("nothing inputed");
    }
  }
  
  @FXML
  private String getUsername() throws IOException {
    String retVal = null;
    try {
      logInScanner = new Scanner(new File("userSignInInfo.txt"));
      
      String existingUsername = logInScanner.next();
      
      
        try {
          while(!existingUsername.isEmpty()) {
            if(existingUsername.equals(username)) {
              retVal = username;
              existingUsername = logInScanner.next();
              existingUsername = logInScanner.next();
            }else {
              existingUsername = logInScanner.next();
              existingUsername = logInScanner.next();
            }
          }
        }catch(NoSuchElementException e) {
          return retVal;
        }
        
    }catch(NoSuchElementException e) {
      return retVal;
    }
    System.out.println("Username" + retVal);
    return retVal;
  }
  
  @FXML
  private String getPassword() throws IOException {
    String retVal = null;
    try {
      logInScanner = new Scanner(new File("userSignInInfo.txt"));
      
      String existingUsername = logInScanner.next();
      existingUsername = logInScanner.next();
      
        try {
          while(!existingUsername.isEmpty()) {
            if(existingUsername.equals(password)) {
              retVal = password;
              existingUsername = logInScanner.next();
              existingUsername = logInScanner.next();
            }else {
              existingUsername = logInScanner.next();
              existingUsername = logInScanner.next();
            }
          }
        }catch(NoSuchElementException e) {
          return retVal;
        }
        
    }catch(NoSuchElementException e) {
      return retVal;
    }
    System.out.println("Password" +retVal);
    return retVal;
  }
}
