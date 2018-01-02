package Test.Menu;

import java.io.IOException;
import Test.Main;
import javafx.fxml.FXML;

public class NewMenuController {
  private Main main;
  
  @FXML
  private void goEnterFood() throws IOException {
    main.showEnterFood();
  }
  
  @FXML
  private void goEnterExercise() throws IOException {
    main.showEnterExercise();
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
