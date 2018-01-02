package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import Test.EnterFood.NewEnterFoodController;
import Test.view.NewLoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
  private static Stage primaryStage;
  private AnchorPane mainLayout;
  private static String inputFoodDate;
  private static String inputExerciseDate;

  static Scanner input = null;

  /**
   * Sets up primary stage to show pages for the application
   * 
   * @throws IOException
   */
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("FitnessBuddy");
    showPainView();
  }

  /**
   * Setter for inputFoodDate
   */
  public static void setInputFoodDate(String date) {
    inputFoodDate = date;
  }

  /**
   * Getter for inputExerciseDate
   */
  public static String getInputExerciseDate() {
    return inputExerciseDate;
  }

  /**
   * Setter for inputExerciseDate
   */
  public static void setInputExerciseDate(String date) {
    inputExerciseDate = date;
  }

  /**
   * Getter for inputFoodDate
   */
  public static String getInputFoodDate() {
    return inputFoodDate;
  }

  /**
   * Sets up Sign in page to show
   * 
   * @throws IOException
   */
  private void showPainView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("view/NEWLOGIN.fxml"));
    mainLayout = loader.load();

    Scene scene = new Scene(mainLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Shows Sign-In Page
   * 
   * @throws IOException
   */
  public static void showSignIn() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("view/NEWLOGIN.fxml"));
    AnchorPane signIn = loader.load();

    Scene scene = new Scene(signIn);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * If enter button is pressed and username or password doesnt match, lets the
   * user know
   * 
   * @throws IOException
   */
  public static void showLogInError() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("view/NEWLOGINERROR.fxml"));
    AnchorPane menu = loader.load();

    Scene menuScene = new Scene(menu);
    primaryStage.setScene(menuScene);
    primaryStage.show();
  }

  /**
   * If enter button is pressed, jumps to the menu page
   * 
   * @throws IOException
   */
  public static void showMenuScene() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("Menu/NEWMENU.fxml"));
    AnchorPane menu = loader.load();

    Scene menuScene = new Scene(menu);
    primaryStage.setScene(menuScene);
    primaryStage.show();
  }

  /**
   * If sign up button is pressed, jumps to the sign up page
   * 
   * @throws IOException
   */
  public static void showSignUp() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("SignUp/SIGNUP.fxml"));
    AnchorPane menu = loader.load();

    Scene signUpScene = new Scene(menu);
    primaryStage.setScene(signUpScene);
    primaryStage.show();
  }

  /**
   * If sign up is pressed and passwords dont match, notifies user that they
   * need to match password
   * 
   * @throws IOException
   */
  public static void showSignUpError() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("SignUp/SIGNUPERROR.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If sign up is pressed and not all fields entered, lets user know
   * 
   * @throws IOException
   */
  public static void showSignUpErrorAllFields() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader
      .setLocation(Main.class.getResource("SignUp/SIGNUPNOTALLFIELDS.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If enter food button is pressed, jumps to the enter food page with
   * appropriate date chosen if applicable
   * 
   * @throws IOException
   */
  public static void showEnterFood() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("EnterFood/ENTERFOOD.fxml"));
    AnchorPane menu = loader.load();

    if (inputFoodDate == null) {
      Scene enterFoodScene = new Scene(menu);
      primaryStage.setScene(enterFoodScene);
      primaryStage.show();

    } else {

      try {
        input = new Scanner(new File(
          NewLoginController.createdUsername + inputFoodDate + "food.txt"));
      } catch (FileNotFoundException e) {
        Scene enterFoodScene = new Scene(menu);
        primaryStage.setScene(enterFoodScene);
        primaryStage.show();
        Label foodDate = new Label(inputFoodDate);
        foodDate.setTextFill(Color.RED);
        menu.setTopAnchor(foodDate, 21.0);
        menu.setLeftAnchor(foodDate, 485.0);
        menu.getChildren().add(foodDate);
      }
      try {
        input = new Scanner(new File(
          NewLoginController.createdUsername + inputFoodDate + "food.txt"));
        String line;
        int counter = 0;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          TextField foods = new TextField(
            st.nextToken() + " - " + st.nextToken() + " oz");
          menu.setTopAnchor(foods, 75.0 + 25 * counter);
          menu.setLeftAnchor(foods, 253.0);
          foods.setPrefWidth(585);
          foods.setAlignment(Pos.CENTER);
          foods.setEditable(false);
          menu.getChildren().add(foods);
          System.out.println("yo");
          counter++;
        }
      } catch (NoSuchElementException e) {
        System.out.println("test 2");
        Scene enterFoodScene = new Scene(menu);
        primaryStage.setScene(enterFoodScene);
        primaryStage.show();
        Label foodDate = new Label(inputFoodDate);
        foodDate.setTextFill(Color.RED);
        menu.setTopAnchor(foodDate, 21.0);
        menu.setLeftAnchor(foodDate, 485.0);
        menu.getChildren().add(foodDate);
      } catch (FileNotFoundException e2) {

      } catch (Exception e) {

      }
    }
  }

  /**
   * Shows input food page to enter new food data or create new food data
   * 
   * @throws IOException
   */

  public static void showInputFood() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("EnterFood/INPUTFOOD.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If enter exercise button is pressed, jumps to the enter exercise page
   * 
   * Also correctly produces the exercises previously entered by the user
   * 
   * @throws IOException
   */
  public static void showEnterExercise() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader
      .setLocation(Main.class.getResource("EnterExercise/ENTEREXLIGHT.fxml"));
    AnchorPane menu = loader.load();

    if (inputExerciseDate == null) {
      Scene enterFoodScene = new Scene(menu);
      primaryStage.setScene(enterFoodScene);
      primaryStage.show();

    } else {

      try {
        input = new Scanner(new File(NewLoginController.createdUsername
          + inputExerciseDate + "exercise.txt"));
      } catch (FileNotFoundException e) {
        Scene enterFoodScene = new Scene(menu);
        primaryStage.setScene(enterFoodScene);
        primaryStage.show();
        Label exerciseDate = new Label(inputExerciseDate);
        exerciseDate.setTextFill(Color.RED);
        menu.setTopAnchor(exerciseDate, 21.0);
        menu.setLeftAnchor(exerciseDate, 485.0);
        menu.getChildren().add(exerciseDate);
      }
      try {
        input = new Scanner(new File(NewLoginController.createdUsername
          + inputExerciseDate + "exercise.txt"));
        String line;
        int counter = 0;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          TextField exercise = new TextField("Exercise: " + st.nextToken()
            + "    -    Distance: " + st.nextToken() + "km"
            + "    -    Calories Burned: " + st.nextToken());
          menu.setTopAnchor(exercise, 75.0 + 25 * counter);
          menu.setLeftAnchor(exercise, 253.0);
          exercise.setPrefWidth(585);
          exercise.setAlignment(Pos.CENTER);
          exercise.setEditable(false);
          menu.getChildren().add(exercise);
          System.out.println("yo");
          counter++;
        }
      } catch (NoSuchElementException e) {
        System.out.println("test 2");
        Scene enterFoodScene = new Scene(menu);
        primaryStage.setScene(enterFoodScene);
        primaryStage.show();
        Label exerciseDate = new Label(inputExerciseDate);
        exerciseDate.setTextFill(Color.RED);
        menu.setTopAnchor(exerciseDate, 21.0);
        menu.setLeftAnchor(exerciseDate, 485.0);
        menu.getChildren().add(exerciseDate);
      } catch (FileNotFoundException e2) {

      } catch (Exception e) {

      }
    }

  }

  /**
   * Shows the add exercise page which allows the user to add an exercise to a
   * selected date
   * 
   * @throws IOException
   */

  public static void showAddExercise() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader
      .setLocation(Main.class.getResource("EnterExercise/ADDEXERCISE.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If Daily Nutritional Info is pressed, jumps to the Daily Nutritional Info
   * page
   * 
   * @throws IOException
   */
  public static void showDailyNutriInfo() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader
      .setLocation(Main.class.getResource("DailyNutritInfo/DNISELECTED.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If weight tracker is pressed, jumps to weight tracker page
   * 
   * @throws IOException
   */
  public static void showWeightTracker() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader
      .setLocation(Main.class.getResource("WeightTracker/WEIGHTTRACKER.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If Nutritional goals is pressed, jumps to nutritional goals page
   * 
   * @throws IOException
   */
  public static void showNutriGoals() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("NutriGoals/NUTRIGOALS.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If profile information is pressed, jumps to profile information page
   * 
   * @throws IOException
   */
  public static void showProfileInfo() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("ProfileInfo/PROFILEINFO.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * If sign out is pressed, notifies user that they have signed out
   * 
   * @throws IOException
   */
  public static void showSignOut() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader
      .setLocation(Main.class.getResource("SignOutPopUp/SIGNOUTPOPUP.fxml"));
    AnchorPane menu = loader.load();

    Scene enterFoodScene = new Scene(menu);
    primaryStage.setScene(enterFoodScene);
    primaryStage.show();
  }

  /**
   * Main method launches the program
   */
  public static void main(String[] args) {
    launch(args);
  }

}
