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
  
  /**
   * sets the enterFoodDate variable to what the user entered in the datepicker
   * 
   * @throws IOException
   */
  @FXML
  private void setEnterFoodDate() throws IOException {
    LocalDate foodDiaryDate = enterFoodDatePicker.getValue();
    System.out.println(foodDiaryDate.toString());
    Main.setInputFoodDate(foodDiaryDate.toString());
    main.showEnterFood();
  }
  
  /**
   * Clears all food entered for the date selected in the datepicker
   * 
   * @throws IOException
   */
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
  
  /**
   * Getter for enterFoodDate
   * 
   * @throws IOException
   */
  private String getEnterFoodDate() throws IOException {
    
    return enterFoodDate;
    
  }
  /**
   * If enter food button is pressed takes the user to the enter food stage
   * 
   * @throws IOException
   */
  @FXML
  private void goEnterFood() throws IOException {
    main.showEnterFood();
  }

  /**
   * if enter exercise button is pressed takes the user to the enter exercise
   * stage
   * 
   * @throws IOException
   */
  @FXML
  private void goEnterExercise() throws IOException {
    main.showEnterExercise();
  }

  /**
   * if daily nutritional information button is pressed takes the user to the
   * daily nutritional information stage
   * 
   * @throws IOException
   */
  @FXML
  private void goDailyNutriInfo() throws IOException {
    main.showDailyNutriInfo();
  }

  /**
   * if weight tracker button is pressed takes the user to the weight tracker
   * stage
   * 
   * @throws IOException
   */
  @FXML
  private void goWeightTracker() throws IOException {
    main.showWeightTracker();
  }

  /**
   * if nutritional goals button is pressed takes the user to the nutritional
   * goals stage
   * 
   * @throws IOException
   */
  @FXML
  private void goNutriGoals() throws IOException {
    main.showNutriGoals();
  }

  /**
   * if profile information button is pressed takes the user to the profile
   * information stage
   * 
   * @throws IOException
   */
  @FXML
  private void goProfileInfo() throws IOException {
    main.showProfileInfo();
  }

  /**
   * if sign out is pressed takes the user back to the sign in stage
   * 
   * @throws IOException
   */
  @FXML
  private void goSignOutPopUp() throws IOException {
    main.showSignOut();
  }
}