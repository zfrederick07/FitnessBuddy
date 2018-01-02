package Test.ProfileInfo;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

import javax.swing.filechooser.FileFilter;

import Test.Main;
import Test.view.NewLoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class NewProfileInfoController {
  private Main main;
  ObservableList<String> feetOptionsList = FXCollections
    .observableArrayList("4", "5", "6", "7");
  ObservableList<String> inchesOptionsList = FXCollections.observableArrayList(
    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");

  Scanner input;

  @FXML
  Button bodyFatSubmit;

  @FXML
  ToggleGroup genderGroup;

  private String firstName;
  private String lastName;
  private String feetString;
  private String inchesString;
  private String weightString;
  private String genderString;
  private String profileString;

  private String waistString;
  private String hipString;
  private String neckString;
  private String bodyFatPercentageString;
  private String bodyMeasurementString;

  private double waistMeasure;
  private double hipMeasure;
  private double neckMeasure;
  private double bodyFatMeasure;

  private int feet;
  private int inches;
  private double height;
  private double weight;

  @FXML
  AnchorPane profileInfoPane;

  @FXML
  private ComboBox feetComboBox;
  @FXML
  private ComboBox inchesComboBox;

  @FXML
  TextField waistTextField;

  @FXML
  TextField hipTextField;

  @FXML
  TextField neckTextField;

  @FXML
  TextField bodyFatTextField;

  @FXML
  TextField userFirstName;

  @FXML
  TextField userLastName;

  @FXML
  TextField userWeight;

  @FXML
  RadioButton maleRadioButton;

  @FXML
  RadioButton femaleRadioButton;

  @FXML
  RadioButton otherRadioButton;

  @FXML
  Tab BodyFatTab;

  @FXML
  TabPane profileTabPane;

  @FXML
  Label validationLabel = new Label();

  /**
   * Sets the bodyFatTab to selected and shows what's under the tab
   * 
   * @throws IOException
   */
  @FXML
  void selectBodyFatTab() {

    initialize();
    profileTabPane.getSelectionModel().select(BodyFatTab);

  }

  /**
   * Initializes the profile information stage with the appropriate elements
   * 
   * @throws IOException
   */
  @FXML
  private void initialize() {
    feetComboBox.setItems(feetOptionsList);
    inchesComboBox.setItems(inchesOptionsList);

    try {
      input = new Scanner(
        new File(NewLoginController.createdUsername + "bodyFat.txt"));

      try {

        String line;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          waistString = st.nextToken();
          hipString = st.nextToken();
          neckString = st.nextToken();
          bodyFatPercentageString = st.nextToken();

        }
      } catch (NoSuchElementException e) {

      } catch (Exception e) {

      }
      try {
        waistTextField.setText(waistString);
        hipTextField.setText(hipString);
        neckTextField.setText(neckString);
        bodyFatTextField.setText(bodyFatPercentageString);

      } catch (NoSuchElementException e) {

      } catch (Exception e) {

      }
    } catch (FileNotFoundException e) {
    }

    try {
      input = new Scanner(
        new File(NewLoginController.createdUsername + "ProfileInfo.txt"));

      try {

        String line;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          firstName = st.nextToken();
          lastName = st.nextToken();
          feetString = st.nextToken();
          inchesString = st.nextToken();
          weightString = st.nextToken();
          genderString = st.nextToken();

        }
      } catch (NoSuchElementException e) {

      } catch (Exception e) {

      }
      try {
        userFirstName.setText(firstName);
        userLastName.setText(lastName);
        feetComboBox.setValue(feetString);
        inchesComboBox.setValue(inchesString);
        userWeight.setText(weightString);

        if (genderString.equals("male")) {
          maleRadioButton.setSelected(true);
        }
        if (genderString.equals("female")) {
          femaleRadioButton.setSelected(true);
        }
        if (genderString.equals("other")) {
          otherRadioButton.setSelected(true);
        }
      } catch (Exception e) {

      }
    } catch (FileNotFoundException e) {
    }
  }

  /**
   * Clears all profile informaiton when the clear button is pressed
   * 
   * @throws IOException
   */
  @FXML
  private void clearProfile() throws IOException {

    profileString = "";

    Files.write(
      Paths.get(NewLoginController.createdUsername + "ProfileInfo.txt"),
      profileString.getBytes());

    initialize();
    main.showProfileInfo();
  }

  /**
   * Writes profile information to a text file and saves it for later use
   * 
   * @throws IOException
   */
  @FXML
  private void setProfileInformation() throws IOException {

    try {
      firstName = userFirstName.getText();
      lastName = userLastName.getText();
      feetString = feetComboBox.getValue() + "";
      inchesString = inchesComboBox.getValue() + "";
      weightString = userWeight.getText();

      feet = Integer.parseInt(feetString);
      inches = Integer.parseInt(inchesString);
      weight = Double.parseDouble(weightString);

      if (maleRadioButton.isSelected()) {
        genderString = "male";
      } else if (femaleRadioButton.isSelected()) {
        genderString = "female";
      } else if (otherRadioButton.isSelected()) {
        genderString = "other";
      }

      height = feet * 12 + inches;

      profileString = firstName + " " + lastName + " " + feetString + " "
        + inchesString + " " + weightString + " " + genderString;

      Writer writer = null;
      try {
        Files.write(
          Paths.get(NewLoginController.createdUsername + "ProfileInfo.txt"),
          profileString.getBytes());
        initialize();

      } catch (NullPointerException e1) {

        validationLabel.setText("Invalid Input");
        validationLabel.setTextFill(Color.RED);
        profileInfoPane.setTopAnchor(validationLabel, 33.0);
        profileInfoPane.setLeftAnchor(validationLabel, 150.0);
        try {
          profileInfoPane.getChildren().add(validationLabel);
        } catch (IllegalArgumentException e2) {

        }

      }

      catch (NoSuchFileException e) {
        try {
          writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(
              NewLoginController.createdUsername + "ProfileInfo.txt"),
            "UTF-8"));
          writer.write(profileString);
          writer.close();
          initialize();

        } catch (IOException ex) {
          System.out.println("ioexcept");
        } catch (NullPointerException e1) {

          validationLabel.setText("Invalid Input");
          validationLabel.setTextFill(Color.RED);
          profileInfoPane.setTopAnchor(validationLabel, 33.0);
          profileInfoPane.setLeftAnchor(validationLabel, 150.0);
          try {
            profileInfoPane.getChildren().add(validationLabel);
          } catch (IllegalArgumentException e2) {

          }

        }
      }
      initialize();
      validationLabel.setText("Profile Submitted");
      validationLabel.setTextFill(Color.RED);
      profileInfoPane.setTopAnchor(validationLabel, 33.0);
      profileInfoPane.setLeftAnchor(validationLabel, 150.0);
      try {
        profileInfoPane.getChildren().add(validationLabel);
      } catch (IllegalArgumentException e2) {

      }
    } catch (Exception e) {
      System.out.println("test");
      initialize();
      validationLabel.setText("Invalid Input");
      validationLabel.setTextFill(Color.RED);
      profileInfoPane.setTopAnchor(validationLabel, 33.0);
      profileInfoPane.setLeftAnchor(validationLabel, 150.0);
      try {
        profileInfoPane.getChildren().add(validationLabel);
      } catch (IllegalArgumentException e2) {

      }
    }
  }

  /**
   * Takes user entered data for body fat measurements and generates a body fat
   * percentage and saves the entered elements to a text file
   * 
   * @throws IOException
   */
  @FXML
  private void enterBodyFatMeasurements() throws IOException {
    Writer writer = null;
    try {
      input = new Scanner(
        new File(NewLoginController.createdUsername + "ProfileInfo.txt"));

      String line;

      while ((line = input.nextLine()) != null) {

        StringTokenizer st = new StringTokenizer(line);

        st.nextToken();
        st.nextToken();
        feetString = st.nextToken();
        inchesString = st.nextToken();
        weightString = st.nextToken();
        genderString = st.nextToken();

        height = Double.parseDouble(feetString) * 12
          + Double.parseDouble(inchesString);
        weight = Double.parseDouble(weightString);

      }
    } catch (NoSuchElementException e) {

    } catch (FileNotFoundException e) {

    } catch (Exception e) {

    }

    try {

      waistString = waistTextField.getText();
      hipString = hipTextField.getText();
      neckString = neckTextField.getText();

      waistMeasure = Double.parseDouble(waistString);
      hipMeasure = Double.parseDouble(hipString);
      neckMeasure = Double.parseDouble(neckString);

      if (genderString.equals("female")) {
        bodyFatMeasure = (163.205
          * Math.log10(waistMeasure + hipMeasure - neckMeasure)
          - 97.684 * Math.log10(height)) - 78.387;
      } else {
        bodyFatMeasure = (86.010 * Math.log10(waistMeasure - neckMeasure)
          - 70.041 * Math.log10(height)) + 36.76;

        bodyFatMeasure = Math.floor(bodyFatMeasure * 100) / 100;

      }

      bodyFatPercentageString = (Double.toString(bodyFatMeasure));
      bodyFatTextField.setText(bodyFatPercentageString);

      bodyMeasurementString = waistString + " " + hipString + " " + neckString
        + " " + bodyFatPercentageString;
    } catch (Exception e) {
      System.out.println("test");
    }

    try {
      Files.write(Paths.get(NewLoginController.createdUsername + "bodyFat.txt"),
        bodyMeasurementString.getBytes());

    } catch (NoSuchFileException e) {
      try {
        writer = new BufferedWriter(
          new OutputStreamWriter(
            new FileOutputStream(
              NewLoginController.createdUsername + "ProfileInfo.txt"),
            "UTF-8"));
        writer.write(profileString);
        writer.close();
        initialize();

      } catch (IOException ex) {
      }
    }
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