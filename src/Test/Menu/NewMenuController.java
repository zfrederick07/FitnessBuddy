package Test.Menu;

import java.io.IOException;
import Test.Main;
import javafx.fxml.FXML;

public class NewMenuController {
  private Main main;
  
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