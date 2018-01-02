package Test.WeightTracker;

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
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Test.Main;
import Test.view.NewLoginController;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class NewWeightTrackerController {
  private Main main;

  Scanner input;

  private String weightString;
  private String dataPointDate;
  private String dataPointString;

  private String weightDataPoint;
  private String dateDataPoint;

  @FXML
  DatePicker date;

  @FXML
  Button updateGraphButton;

  @FXML
  TextField weightTextField;

  @FXML
  CategoryAxis x;

  @FXML
  NumberAxis y;

  @FXML
  LineChart<?, ?> weightOverTimeChart;

  @FXML
  private void initialize() throws IOException {
    XYChart.Series series = new XYChart.Series();
    try {
      input = new Scanner(
        new File(NewLoginController.createdUsername + "WOTG.txt"));

      try {

        String line;

        while ((line = input.nextLine()) != null) {

          StringTokenizer st = new StringTokenizer(line);

          dateDataPoint = st.nextToken();
          weightDataPoint = st.nextToken();

          series.getData().add(
            new XYChart.Data(dateDataPoint, Integer.parseInt(weightDataPoint)));
          System.out.println("test");
        }
      } catch (NoSuchElementException e) {

      } catch (Exception e) {

      }
      weightOverTimeChart.getData().addAll(series);
    } catch (FileNotFoundException e) {
    }

    catch (Exception e) {

    }
  }

  @FXML
  private void submitWeightDataPoint() throws IOException {

    Writer writer = null;

    try {

      LocalDate graphDate = date.getValue();
      weightString = weightTextField.getText();

      dataPointString = graphDate + " " + weightString + "\r\n";

      try {
        Files.write(Paths.get(NewLoginController.createdUsername + "WOTG.txt"),
          dataPointString.getBytes(), StandardOpenOption.APPEND);
        initialize();

      } catch (NullPointerException e1) {

      } catch (IllegalArgumentException e2) {

      }

    }

    catch (NoSuchFileException e) {
      try {
        writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(NewLoginController.createdUsername + "WOTG.txt"),
          "UTF-8"));
        writer.write(dataPointString);
        writer.close();
        initialize();

      } catch (Exception e2) {

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
