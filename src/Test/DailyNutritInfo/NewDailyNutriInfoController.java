package Test.DailyNutritInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Test.Main;
import Test.view.NewLoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class NewDailyNutriInfoController {

  Scanner input;

  @FXML
  private PieChart macroPieChart;

  private String calorieString;
  private String carbString;
  private String proteinString;
  private String fatString;
  private String fiberString;
  private String sodiumString;
  private String weightString;
  private String feetString;
  private String inchesString;
  private String genderString;
  private String goalString;

  private double calories;
  private double carbs;
  private double protein;
  private double fat;
  private double fiber;
  private double sodium;
  private double basalMetabolicRate;
  private double totalMacros;
  private double height;
  private double weight;
  private double recommendedCarbs;
  private double recommendedProtein;
  private double recommendedFat;

  double caloriePercent;
  double carbPercent;
  double proteinPercent;
  double fatPercent;

  @FXML
  TextField dailyCaloriesTextField;

  @FXML
  TextField dailyCarbsTextField;

  @FXML
  TextField dailyProteinTextField;

  @FXML
  TextField dailyFatTextField;

  @FXML
  TextField dailyFiberTextField;

  @FXML
  TextField dailySodiumTextField;

  @FXML
  TextField recommendedCaloriesTextField;

  @FXML
  TextField recommendedCarbsTextField;

  @FXML
  TextField recommendedProteinTextField;

  @FXML
  TextField recommendedFatTextField;

  @FXML
  TextField recommendedFiberTextField;

  @FXML
  TextField recommendedSodiumTextField;

  private Main main;

  @FXML
  DatePicker inputNutritionDate;

  @FXML
  DatePicker inputNutritionDataDate;

  /**
   * Takes user profile information and creates nutrition data which coorilates
   * to it.
   * 
   * @throws IOException
   */
  @FXML
  private void createNutritionData() throws IOException {
    calories = 0;
    carbs = 0;
    protein = 0;
    fat = 0;
    fiber = 0;
    sodium = 0;

    LocalDate nutritionDate = inputNutritionDataDate.getValue();

    try {
      input = new Scanner(new File(NewLoginController.createdUsername
        + nutritionDate.toString() + "food.txt"));

      String line;

      while ((line = input.nextLine()) != null) {

        StringTokenizer st = new StringTokenizer(line);

        st.nextToken();
        st.nextToken();
        calorieString = st.nextToken();
        carbString = st.nextToken();
        proteinString = st.nextToken();
        fatString = st.nextToken();
        fiberString = st.nextToken();
        sodiumString = st.nextToken();

        calories = calories + Double.parseDouble(calorieString);
        carbs = carbs + Double.parseDouble(carbString);
        protein = protein + Double.parseDouble(proteinString);
        fat = fat + Double.parseDouble(fatString);
        fiber = fiber + +Double.parseDouble(fiberString);
        sodium = sodium + +Double.parseDouble(sodiumString);

        System.out.println(carbs);

      }

    } catch (NoSuchElementException e) {

    } catch (FileNotFoundException e2) {

    } catch (Exception e) {

    }

    dailyCaloriesTextField.setText(Double.toString(calories));
    dailyCarbsTextField.setText(Double.toString(carbs));
    dailyProteinTextField.setText(Double.toString(protein));
    dailyFatTextField.setText(Double.toString(fat));
    dailyFiberTextField.setText(Double.toString(fiber));
    dailySodiumTextField.setText(Double.toString(sodium));

    try {
      input = new Scanner(
        new File(NewLoginController.createdUsername + "ProfileInfo.txt"));

      try {

        String line;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          st.nextToken();
          st.nextToken();
          feetString = st.nextToken();
          inchesString = st.nextToken();
          weightString = st.nextToken();
          genderString = st.nextToken();

        }
      } catch (NoSuchElementException e) {

      } catch (Exception e) {

      }
    } catch (FileNotFoundException e) {

    } catch (Exception e) {

    }

    try {

      if (genderString.equals("female")) {
        basalMetabolicRate = 655.1
          + (9.6 * 0.453592 * Double.parseDouble(weightString))
          + (1.8 * 2.54 * (Double.parseDouble(feetString) * 12
            + Double.parseDouble(inchesString)))
          - (4.7 * 25);
        basalMetabolicRate = Math.floor(basalMetabolicRate * 1.3 * 100) / 100;
      } else {
        basalMetabolicRate = 66.47
          + (13.7 * 0.453592 * Double.parseDouble(weightString))
          + (5 * 2.54 * (Double.parseDouble(feetString) * 12
            + Double.parseDouble(inchesString)))
          - (6.8 * 25);
        basalMetabolicRate = Math.floor(basalMetabolicRate * 1.3 * 100) / 100;
      }

      try {
        input = new Scanner(
          new File(NewLoginController.createdUsername + "goal.txt"));

        try {

          String line;

          while ((line = input.nextLine()) != null) {

            StringTokenizer st = new StringTokenizer(line);

            goalString = st.nextToken();
            st.nextToken();
          }

        } catch (NoSuchElementException e) {

        } catch (Exception e) {

        }
        if (goalString.equals("lose")) {
          basalMetabolicRate = basalMetabolicRate - 500;
        }
        if (goalString.equals("gain")) {
          basalMetabolicRate = basalMetabolicRate + 500;
        }
      } catch (FileNotFoundException e) {

      } catch (Exception e) {

      }

      recommendedCarbs = Math.floor((basalMetabolicRate * .4) * 100) / 400;
      recommendedProtein = Math.floor((basalMetabolicRate * .3) * 100) / 400;
      recommendedFat = Math.floor((basalMetabolicRate * .3) * 100) / 900;

      recommendedCaloriesTextField
        .setText(Double.toString(Math.floor(basalMetabolicRate * 100) / 100));
      recommendedCarbsTextField
        .setText(Double.toString(Math.floor(recommendedCarbs * 100) / 100));
      recommendedProteinTextField
        .setText(Double.toString(Math.floor(recommendedProtein * 100) / 100));
      recommendedFatTextField
        .setText(Double.toString(Math.floor(recommendedFat * 100) / 100));
      recommendedFiberTextField.setText("28");
      recommendedSodiumTextField.setText("1500");

    } catch (Exception e) {

    }
  }

  /**
   * Creates a pie chart out of the nutrition data entered by the user for a
   * specific date
   * 
   * @throws IOException
   */
  @FXML
  private void makePieChart() throws IOException {
    calories = 0;
    carbs = 0;
    protein = 0;
    fat = 0;
    totalMacros = 0;
    LocalDate nutritionDate = inputNutritionDate.getValue();
    try {
      input = new Scanner(new File(NewLoginController.createdUsername
        + nutritionDate.toString() + "food.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("test");
    }
    try {
      input = new Scanner(new File(NewLoginController.createdUsername
        + nutritionDate.toString() + "food.txt"));

      String line;

      while ((line = input.nextLine()) != null) {

        StringTokenizer st = new StringTokenizer(line);

        st.nextToken();
        st.nextToken();
        calorieString = st.nextToken();
        carbString = st.nextToken();
        proteinString = st.nextToken();
        fatString = st.nextToken();
        st.nextToken();

        calories = calories + Double.parseDouble(calorieString);
        carbs = carbs + Double.parseDouble(carbString);
        protein = protein + Double.parseDouble(proteinString);
        fat = fat + Double.parseDouble(fatString);

        System.out.println(carbs);

      }

    } catch (NoSuchElementException e) {

    } catch (FileNotFoundException e2) {

    } catch (Exception e) {

    }

    totalMacros = carbs + protein + fat;
    carbPercent = Math.floor((carbs / totalMacros) * 100 * 100) / 100;
    proteinPercent = Math.floor((protein / totalMacros) * 100 * 100) / 100;
    fatPercent = Math.floor((fat / totalMacros) * 100 * 100) / 100;

    ObservableList<PieChart.Data> macros = FXCollections.observableArrayList();

    macros.addAll(new PieChart.Data("Carbs: " + carbPercent + "%", carbPercent),
      new PieChart.Data("Protein: " + proteinPercent + "%", proteinPercent),
      new PieChart.Data("Fat: " + fatPercent + "%", fatPercent));

    macroPieChart.setData(macros);
    macroPieChart.setTitle("Macronutrients for " + nutritionDate.toString());

    macroPieChart.setLabelsVisible(false);

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