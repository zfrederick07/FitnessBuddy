package Test.EnterFood;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import Test.Main;
import Test.view.NewLoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NewEnterFoodController {
  private Main main;
  @FXML
  private DatePicker enterFoodDatePicker;
  private String enterFoodDate;
  private String foodString;
  
  @FXML
  private static AnchorPane foodAnchorPane;

  @FXML
  private Button clear;
  
  @FXML
  private void setEnterFoodDate() throws IOException {
    LocalDate foodDiaryDate = enterFoodDatePicker.getValue();
    System.out.println(foodDiaryDate.toString());
    Main.setInputFoodDate(foodDiaryDate.toString());
    main.showEnterFood();
  }
  
  @FXML
  public void clearFood() throws IOException {
    
    try {
      
      foodString = "";

      Files.write(
        Paths.get(NewLoginController.createdUsername
          + Main.getInputFoodDate() + "food.txt"),
        foodString.getBytes());
      
      
      } catch (Exception e ) {
        
    }
    main.showEnterFood();
  }
  
  private String getEnterFoodDate() throws IOException {
    
    return enterFoodDate;
    
  }
  @FXML
  private void goEnterFood() throws IOException {
    main.showEnterFood();
  }
  
  @FXML
  private void goEnterExercise() throws IOException {
    main.showEnterExercise();
  }
  
  @FXML
  private void goInputFood() throws IOException {
    main.showInputFood();
  }
  
  @FXML
  private void goDailyNutriInfo() throws IOException {
    main.showDailyNutriInfo();
  }
  
  @FXML
  private void goWeightTracker() throws IOException {
    main.showWeightTracker();
  }
  
  @FXML
  private void goNutriGoals() throws IOException {
    main.showNutriGoals();
  }
  
  @FXML
  private void goProfileInfo() throws IOException {
    main.showProfileInfo();
  }
  
  @FXML
  private void goSignOutPopUp() throws IOException {
    main.showSignOut();
  }
  
  @FXML
  private void goSignInPage() throws IOException {
    main.showSignIn();
  }
}