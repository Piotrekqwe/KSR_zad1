package ksr.pl.kw.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import ksr.pl.kw.classification.Country;
import ksr.pl.kw.classification.Method;
import ksr.pl.kw.classification.StringComparisonMethod;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;


public class FxUserInterfaceController implements Initializable {

    List<ClassifiedArticle> classifiedArticles;

    public Button btn1, calculateBtn;
    public Text loadText;
    public TextField trainSetSize;

    public TextField sentencesAmountWeight;
    public TextField digitsAmountWeight;
    public TextField shortWordsAmountWeight;
    public TextField longWordsAmountWeight;
    public TextField textLengthWeight;
    public TextField largestAmountCitiesCountryWeight;
    public TextField currencyWeight;
    public TextField dateFormatWeight;
    public TextField lengthUnitWeight;
    public TextField temperatureUnitWeight;
    public Text accuracyDisplay;
    public Text precisionDisplay;
    public Text recallDisplay;
    public Text F1Display;
    public ToggleGroup methodToggle;
    public RadioButton euclidesBtn;
    public RadioButton manhattanBtn;
    public RadioButton chebyshevBtn;
    public ToggleGroup countryToggle;
    public RadioButton WestGermanyBtn;
    public RadioButton USABtn;
    public RadioButton FranceBtn;
    public RadioButton UKBtn;
    public RadioButton CanadaBtn;
    public RadioButton JapanBtn;
    public ToggleGroup stringComparisonMethodToggle;
    public RadioButton defaultBtn;
    public RadioButton nGramBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numbersOnlyTextField(trainSetSize);
        numbersOnlyTextField(sentencesAmountWeight);
        numbersOnlyTextField(digitsAmountWeight);
        numbersOnlyTextField(shortWordsAmountWeight);
        numbersOnlyTextField(longWordsAmountWeight);
        numbersOnlyTextField(textLengthWeight);
        numbersOnlyTextField(largestAmountCitiesCountryWeight);
        numbersOnlyTextField(currencyWeight);
        numbersOnlyTextField(dateFormatWeight);
        numbersOnlyTextField(lengthUnitWeight);
        numbersOnlyTextField(temperatureUnitWeight);
    }

    private void numbersOnlyTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void keyPress(KeyEvent key) {
    }

    public void keyRelease(KeyEvent key) {
    }

    public void readDataFromFile() {
        new Thread(() -> {
            loadText.setText("Wczytywanie");
            Configuration.getExtractionService().extract();
            loadText.setText("Zakończone");
        }).start();
    }

    public void calculate() {
        Method method;
        StringComparisonMethod stringComparisonMethod;

        Configuration.getExtractionService().split(Integer.parseInt(trainSetSize.getText()));

        Toggle stringToggle = stringComparisonMethodToggle.getSelectedToggle();
        if (nGramBtn.equals(stringToggle)) {
            stringComparisonMethod = StringComparisonMethod.N_GRAM;
        } else {
            stringComparisonMethod = StringComparisonMethod.DEFAULT;
        }

        Toggle selectedToggle = methodToggle.getSelectedToggle();
        if (euclidesBtn.equals(selectedToggle)) {
            method = Method.EUCLIDES;
        } else if (manhattanBtn.equals(selectedToggle)) {
            method = Method.MANHATTAN;
        } else {
            method = Method.CHEBYSHEV;
        }

        double[] weights = new double[10];
        weights[0] = Double.parseDouble(sentencesAmountWeight.getText());
        weights[1] = Double.parseDouble(digitsAmountWeight.getText());
        weights[2] = Double.parseDouble(shortWordsAmountWeight.getText());
        weights[3] = Double.parseDouble(longWordsAmountWeight.getText());
        weights[4] = Double.parseDouble(textLengthWeight.getText());
        weights[5] = Double.parseDouble(largestAmountCitiesCountryWeight.getText());
        weights[6] = Double.parseDouble(currencyWeight.getText());
        weights[7] = Double.parseDouble(dateFormatWeight.getText());
        weights[8] = Double.parseDouble(lengthUnitWeight.getText());
        weights[9] = Double.parseDouble(temperatureUnitWeight.getText());

        classifiedArticles = Configuration.getClassificationService().classify(method, 10, weights, stringComparisonMethod);
        calculateValues();
    }

    private static final DecimalFormat df3 = new DecimalFormat("#.###");

    public void calculateValues() {
        int t = 0;
        int N = classifiedArticles.size();
        int TP = 0;
        int FP = 0;
        int FN = 0;
        double accuracy;
        double precision;
        double recall;
        double F1;
        Toggle selectedToggle = countryToggle.getSelectedToggle();
        Country country;
        if (WestGermanyBtn.equals(selectedToggle)) {
            country = Country.WEST_GERMANY;
        } else if (USABtn.equals(selectedToggle)) {
            country = Country.USA;
        } else if (FranceBtn.equals(selectedToggle)) {
            country = Country.FRANCE;
        } else if (UKBtn.equals(selectedToggle)) {
            country = Country.UK;
        } else if (CanadaBtn.equals(selectedToggle)) {
            country = Country.CANADA;
        } else {
            country = Country.JAPAN;
        }

        for (ClassifiedArticle cArticle : classifiedArticles) {
            if (cArticle.article.getCountry().equals(cArticle.country)) {
                t++;
            }
            if (cArticle.country.equals(country)) {
                if (cArticle.article.getCountry().equals(cArticle.country)) {
                    TP++;
                } else {
                    FP++;
                }
            }
            if (cArticle.article.getCountry().equals(country)) {
                if (!cArticle.article.getCountry().equals(cArticle.country)) {
                    FN++;
                }
            }
        }
        accuracy = (double) t / N;
        precision = (double) TP / (TP + FP);
        recall = (double) TP / (TP + FN);
        F1 = 2 * precision * recall / (precision + recall);

        accuracyDisplay.setText("Accuracy = " + df3.format(accuracy));
        precisionDisplay.setText("Precision = " + df3.format(precision));
        recallDisplay.setText("Recall = " + df3.format(recall));
        F1Display.setText("F1 = " + df3.format(F1));

    }
}
