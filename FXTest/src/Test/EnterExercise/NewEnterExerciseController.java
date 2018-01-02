package Test.EnterExercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Test.Main;
import Test.view.NewLoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class NewEnterExerciseController {
  private Main main;

  private double weight;
  private double caloriesBurned;
  private double distance;

  private String distanceString;
  private String exerciseString;
  private String submitExerciseString;
  private String caloriesBurnedString;

  Scanner input;

  @FXML
  DatePicker enterExerciseDatePicker;

  @FXML
  Button addExerciseButton;

  @FXML
  Button submit;

  @FXML
  Button back;
  
  @FXML
  Button clear;

  @FXML
  TextField distanceTextField;

  @FXML
  RadioButton walking;

  @FXML
  RadioButton running;

  @FXML
  RadioButton erging;

  @FXML
  RadioButton swimming;

  @FXML
  ToggleGroup exercise;

  @FXML
  private void setEnterExerciseDate() throws IOException {
    LocalDate exerciseDiaryDate = enterExerciseDatePicker.getValue();
    System.out.println(exerciseDiaryDate.toString());
    Main.setInputExerciseDate(exerciseDiaryDate.toString());
    main.showEnterExercise();
  }

  @FXML
  private void clearExercise() throws IOException {
    try {
   
    exerciseString = "";

    Files.write(
      Paths.get(NewLoginController.createdUsername
        + Main.getInputExerciseDate() + "exercise.txt"),
      exerciseString.getBytes());
    main.showEnterExercise();
    
    } catch (Exception e ) {
      
  }
  }
  
  @FXML
  private void sumbitExercise() throws IOException {

    try {
      input = new Scanner(
        new File(NewLoginController.createdUsername + "ProfileInfo.txt"));

      try {

        String line;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          st.nextToken();
          st.nextToken();
          st.nextToken();
          st.nextToken();
          weight = Double.parseDouble(st.nextToken());
          st.nextToken();

        }
      } catch (NoSuchElementException e) {

      } catch (Exception e) {

      }
    } catch (Exception e) {

    }
    try {
      distanceString = distanceTextField.getText();
      distance = Double.parseDouble(distanceString);

      if (walking.isSelected()) {

        caloriesBurned = distance * .621371 * weight * .53;
        exerciseString = "Walk";

      } else if (running.isSelected()) {

        caloriesBurned = distance * .621371 * weight * .57;
        exerciseString = "Run";

      } else if (erging.isSelected()) {

        caloriesBurned = distance * .621371 * weight * .84;
        exerciseString = "Erg";

      } else if (swimming.isSelected()) {

        caloriesBurned = distance * .621371 * weight * 1.25;
        exerciseString = "Swim";

      } else {

      }
      caloriesBurned = Math.floor(caloriesBurned*100)/100;
      caloriesBurnedString = Double.toString(caloriesBurned);
      submitExerciseString = exerciseString + " " + distanceString + " "
        + caloriesBurnedString + "\r\n";

    } catch (Exception e) {

    }

    Writer writer = null;
    try {
      Files.write(
        Paths.get(NewLoginController.createdUsername
          + Main.getInputExerciseDate() + "exercise.txt"),
        submitExerciseString.getBytes(), StandardOpenOption.APPEND);

      main.showEnterExercise();
    } catch (NullPointerException e1) {
      System.out.println("null pointer Exception");
      // Label noFoodInput = new Label("No food Input");
      // noFoodInput.setTextFill(Color.RED);
      // mainInputFoodPane.setTopAnchor(noFoodInput, 353.0);
      // mainInputFoodPane.setLeftAnchor(noFoodInput, 400.0);
      // mainInputFoodPane.getChildren().add(noFoodInput);

    }

    catch (NoSuchFileException e) {
      try {
        writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(NewLoginController.createdUsername
            + Main.getInputExerciseDate() + "exercise.txt"),
          "UTF-8"));
        writer.write(submitExerciseString);
        writer.close();

        main.showEnterExercise();
      } catch (IOException ex) {
        System.out.println("ioexcept");
      } catch (NullPointerException e1) {

        // Label noFoodInput = new Label("No food Input");
        // noFoodInput.setTextFill(Color.RED);
        // mainInputFoodPane.setTopAnchor(noFoodInput, 353.0);
        // mainInputFoodPane.setLeftAnchor(noFoodInput, 400.0);
        // mainInputFoodPane.getChildren().add(noFoodInput);

      }
    }

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
  private void goAddExercise() throws IOException {
    main.showAddExercise();
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
