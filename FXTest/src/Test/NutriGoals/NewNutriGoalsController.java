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
  
  @FXML
  private void clearProfile() throws IOException {

    goalString = "";

    Files.write(
      Paths.get(NewLoginController.createdUsername + "goal.txt"),
      goalString.getBytes());

    initialize();
    main.showNutriGoals();
  }

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
  private void goSignInPage() throws IOException {
    main.showSignIn();
  }

  @FXML
  private void goSignOutPopUp() throws IOException {
    main.showSignOut();
  }
}
