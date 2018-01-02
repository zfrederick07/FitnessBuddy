package Test.NutriGoals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Test.Main;
import Test.view.NewLoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class NewNutriGoalsController {
  private Main main;

  Scanner input;

  private String loseGainString;
  private String amountString;
  private String goalString;

  @FXML
  RadioButton loseWeight;

  @FXML
  RadioButton gainWeight;

  @FXML
  TextField amountTextField;

  @FXML
  Button clear;

  @FXML
  Button submit;

  @FXML
  ToggleGroup loseGain;

  /**
   * Initializes the stage with the appropriate elements
   * 
   * @throws IOException
   */
  @FXML
  private void initialize() {

    try {
      input = new Scanner(
        new File(NewLoginController.createdUsername + "goal.txt"));

      try {

        String line;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          loseGainString = st.nextToken();
          amountString = st.nextToken();

        }
      } catch (NoSuchElementException e) {

      } catch (Exception e) {

      }
    } catch (FileNotFoundException e) {
    }
    try {
      amountTextField.setText(amountString);

      if (loseGainString.equals("gain")) {
        gainWeight.setSelected(true);
      }
      if (loseGainString.equals("lose")) {
        loseWeight.setSelected(true);
      }

    } catch (Exception e) {
    }
  }
  
  /**
   * Clears the user goal information if the clear button is pressed
   * 
   * @throws IOException
   */
  @FXML
  private void clearProfile() throws IOException {

    goalString = "";

    Files.write(
      Paths.get(NewLoginController.createdUsername + "goal.txt"),
      goalString.getBytes());

    initialize();
    main.showNutriGoals();
  }

  /**
   * Submits entered goal information if the submit button is pressed
   * 
   * @throws IOException
   */
  @FXML
  private void submitGoals() throws IOException {

    if (loseWeight.isSelected()) {
      loseGainString = "lose";
    } else if (gainWeight.isSelected()) {
      loseGainString = "gain";

    }
    amountString = amountTextField.getText();

    goalString = loseGainString + " " + amountString;

    Writer writer = null;
    try {
      Files.write(Paths.get(NewLoginController.createdUsername + "goal.txt"),
        goalString.getBytes());
      // initialize();

    } catch (NullPointerException e1) {

    } catch (NoSuchFileException e) {
      try {
        writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(NewLoginController.createdUsername + "goal.txt"),
          "UTF-8"));
        writer.write(goalString);
        writer.close();
        // initialize();

      } catch (IOException ex) {
        System.out.println("ioexcept");
      } catch (NullPointerException e1) {

      }
    }
    initialize();
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