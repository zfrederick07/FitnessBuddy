package Test.SignUp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Test.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SignUpController {
  private Main main;
  public static String username;
  public static String userPassword;
  public static String password;
  public static String confirmPassword;
  private static BufferedWriter signUpWriter = null;
  private static Scanner signUpScanner = null;
  private static  Boolean usernameExists = false;
  
  
  @FXML
  private TextField usernameText;
  @FXML
  private PasswordField passwordText;
  @FXML
  private PasswordField confirmPasswordText;
 
  
  @FXML
  private void goMenu() throws IOException {  
    username = usernameText.getText();
    password = passwordText.getText();
    confirmPassword = confirmPasswordText.getText();
    
    System.out.println(username + "," + password + "," + confirmPassword);
    
    if(password.equals(confirmPassword) && !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
      getText();
      main.showMenuScene();
    }else if(!password.equals(confirmPassword)) {
      main.showSignUpError();
    }else {
//      main.showSignUpErrorAllFields(); 
    }
  }
  
  @FXML
  private void getText() throws IOException {
    try {
      signUpScanner = new Scanner(new File("userSignInInfo.txt"));
      
      String existingUsername = signUpScanner.next();
      String textLine;
      
      if(password.equals(confirmPassword)) {
        try {
          while(!existingUsername.isEmpty()) {
            if(!existingUsername.equals(username)) {
              userPassword = password;
              usernameExists = false;
                existingUsername = signUpScanner.next();
                existingUsername = signUpScanner.next();
            }else {
              usernameExists = true;
              existingUsername = signUpScanner.next();
              existingUsername = signUpScanner.next();
            }
          }
        }catch(NoSuchElementException e) {
          if(!usernameExists) {
            Files.write(Paths.get("userSignInInfo.txt"),
                (username + " " + userPassword + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
          }
        }  
      }
    }catch(FileNotFoundException e) {
      if(password.equals(confirmPassword)) {
        userPassword = password;
        System.out.println("2");
        signUpWriter = new BufferedWriter(new FileWriter("userSignInInfo.txt", true)); 
        signUpWriter.write(username + " "); 
        signUpWriter.write(userPassword + System.lineSeparator());
        signUpWriter.close();
        
        
      }else {
        System.out.println("Doesnt match");
      }
    }catch(NoSuchElementException e) {
      
    }
  }
  
  @FXML
  private void backSubmit() throws IOException {
    main.showSignIn();
  }
  
  @FXML
  private void goSignIn() throws IOException {
    {  
      username = usernameText.getText();
      password = passwordText.getText();
      confirmPassword = confirmPasswordText.getText();
      
      System.out.println(username + "," + password + "," + confirmPassword);
      
      if(password.equals(confirmPassword) && !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
        getText();
        main.showSignIn();
      }else if(!password.equals(confirmPassword)) {
        main.showSignUpError();
      }else {
       main.showSignUpErrorAllFields(); 
      }
    }
   
  }
}