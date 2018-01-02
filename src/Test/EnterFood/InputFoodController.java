package Test.EnterFood;

import java.awt.List;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Test.Main;
import Test.view.NewLoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class InputFoodController {
  private Main main;

  private boolean customCreate = true;

  private Scanner input;

  ObservableList<String> foodOptionList;

  private double foodAmount;
  private double calories;
  private double carbs;
  private double protein;
  private double fat;
  private double fiber;
  private double sodium;

  private String foodString;
  private String customFoodString;

  @FXML
  private TextField foodAmountField;

  @FXML
  private TextField caloriesField;

  @FXML
  private TextField carbsField;

  @FXML
  private TextField proteinField;

  @FXML
  private TextField fatField;

  @FXML
  private TextField fiberField;

  @FXML
  private TextField sodiumField;

  // Set up for Food ComboBox
  @FXML
  private ComboBox foodCombo;

  @FXML
  private Button submitFoodButton;

  @FXML
  Button cancelFoodButton;

  @FXML
  private AnchorPane mainInputFoodPane;

  /**
   * Reads the amount of a food consumed and generates the nutrition data for
   * that amount of food
   * 
   * @throws IOException
   */
  @FXML
  private void enterAmountConsumed() throws IOException {

    foodAmount = Double.parseDouble(foodAmountField.getText());
    System.out.println(foodAmount);

    // if chicken
    try {
      input = new Scanner(new File("FoodMacroList.txt"));

      String line;
      customCreate = true;
      while ((line = input.nextLine()) != null) {
        StringTokenizer st = new StringTokenizer(line);

        if (st.nextToken().equals(foodCombo.getValue())) {
          caloriesField.setText(st.nextToken());
          carbsField.setText(st.nextToken());
          proteinField.setText(st.nextToken());
          fatField.setText(st.nextToken());
          fiberField.setText(st.nextToken());
          sodiumField.setText(st.nextToken());

          customCreate = false;

        }
      }

    } catch (FileNotFoundException e) {
      System.out.println("Error openning file..");
      System.exit(1);
    } catch (NoSuchElementException e) {

    }
    if (customCreate == true) {
      customFoodString = foodCombo.getValue() + " " + caloriesField.getText()
        + " " + carbsField.getText() + " " + proteinField.getText() + " "
        + fatField.getText() + " " + fiberField.getText() + " "
        + sodiumField.getText() + "\r\n";

      Files.write(Paths.get("FoodMacroList.txt"), customFoodString.getBytes(),
        StandardOpenOption.APPEND);
    }

    calories = Math.floor(
      foodAmount * Double.parseDouble(caloriesField.getText()) * 100) / 100;
    carbs = Math
      .floor(foodAmount * Double.parseDouble(carbsField.getText()) * 100) / 100;
    protein = Math.floor(
      foodAmount * Double.parseDouble(proteinField.getText()) * 100) / 100;
    fat = Math.floor(foodAmount * Double.parseDouble(fatField.getText()) * 100)
      / 100;
    fiber = Math
      .floor(foodAmount * Double.parseDouble(fiberField.getText()) * 100) / 100;
    sodium = Math.floor(
      foodAmount * Double.parseDouble(sodiumField.getText()) * 100) / 100;

    caloriesField.setText(Double.toString(calories));
    carbsField.setText(Double.toString(carbs));
    proteinField.setText(Double.toString(protein));
    fatField.setText(Double.toString(fat));
    fiberField.setText(Double.toString(fiber));
    sodiumField.setText(Double.toString(sodium));

    foodString = foodCombo.getValue() + " " + Double.toString(foodAmount) + " "
      + Double.toString(calories) + " " + Double.toString(carbs) + " "
      + Double.toString(protein) + " " + Double.toString(fat) + " "
      + Double.toString(fiber) + " " + Double.toString(sodium) + "\r\n";
    System.out.println(foodString);

    initialize();
  }

  /**
   * Submits the food data generated from the enter amount consumed method into
   * the enter food stage
   * 
   * @throws IOException
   */
  @FXML
  private void submitFood() throws IOException {

    Writer writer = null;
    try {
      Files.write(Paths.get(NewLoginController.createdUsername
        + Main.getInputFoodDate() + "food.txt"), foodString.getBytes(),
        StandardOpenOption.APPEND);
      initialize();
      main.showEnterFood();
    } catch (NullPointerException e1) {
      System.out.println("1");
      Label noFoodInput = new Label("No food Input");
      noFoodInput.setTextFill(Color.RED);
      mainInputFoodPane.setTopAnchor(noFoodInput, 353.0);
      mainInputFoodPane.setLeftAnchor(noFoodInput, 400.0);
      mainInputFoodPane.getChildren().add(noFoodInput);

    }

    catch (NoSuchFileException e) {
      try {
        writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(NewLoginController.createdUsername
            + Main.getInputFoodDate() + "food.txt"),
          "UTF-8"));
        writer.write(foodString);
        writer.close();
        initialize();
        main.showEnterFood();
      } catch (IOException ex) {
        System.out.println("ioexcept");
      } catch (NullPointerException e1) {

        Label noFoodInput = new Label("No food Input");
        noFoodInput.setTextFill(Color.RED);
        mainInputFoodPane.setTopAnchor(noFoodInput, 353.0);
        mainInputFoodPane.setLeftAnchor(noFoodInput, 400.0);
        mainInputFoodPane.getChildren().add(noFoodInput);

      }
    }
  }

  /**
   * Initializes the stage with all appropriate elements
   * 
   * @throws IOException
   */
  @FXML
  private void initialize() throws IOException {

    try {
      input = new Scanner(new File("FoodMacroList.txt"));

      String line;

      while ((line = input.nextLine()) != null) {
        StringTokenizer st = new StringTokenizer(line);

        ArrayList<String> foodList = new ArrayList<String>();
        foodList.add(st.nextToken());
        foodOptionList = FXCollections.observableArrayList(foodList);
        foodCombo.getItems().addAll(foodOptionList);
      }

    } catch (NoSuchElementException e) {

    }
  }

}
