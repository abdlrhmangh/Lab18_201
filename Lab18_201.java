package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class Main extends  Application {
    Label text;

    CheckBox checkBold = new CheckBox("bold");
    CheckBox checkItalic = new CheckBox("Italic");
    //declaring checkboxes so we can use it in the whole class

    ComboBox<Integer> combFontSize = new ComboBox<>();
    ComboBox<String> combFontType = new ComboBox<>();
    //declaring comboBoxes to set the size and fontType

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        text = new Label("Hello World");

        BorderPane pane = new BorderPane();
        BorderPane pane2 = new BorderPane();

        pane.setTop(pane2);



        HBox bottomHbox = new HBox(checkItalic, checkBold);
        bottomHbox.setMargin(checkBold, new Insets(170, 20, 20, 30));
        bottomHbox.setMargin(checkItalic, new Insets(170, 20, 20, 230));
        //bottom horizontal box in the top half to put the two checkBoxes
        checkBold.setOnAction(e -> update());
        checkItalic.setOnAction(e -> update());
        //pressing each box will update the text depending on the property chosen


        ComboBox fontSize = new ComboBox();

        fontSize.getItems().addAll(generator());

        Label x = new Label("Font Type");
        Label y = new Label("Font Size");
        //variables to store the label text

        ComboBox fontType = new ComboBox();
        fontType.getItems().addAll(Font.getFontNames());

        HBox topHbox = new HBox(x, combFontType, y, combFontSize);
        //top horizontal box on the top half

        topHbox.setMargin(x, new Insets(10, 20, 20, 20));
        topHbox.setMargin(y, new Insets(10, 20, 20, 20));
        //setting the margin for the labels

        topHbox.setMargin(combFontType, new Insets(10, 20, 20, 10));
        topHbox.setMargin(combFontSize, new Insets(10, 20, 20, 10));
        //setting the margin for the comboBoxes



            combFontType.getItems().addAll(Font.getFamilies());
            combFontType.setValue(text.getFont().getFamily());
            combFontType.setOnAction(e -> update());
            //will invoke the update method to change the font type


            combFontSize.getItems().addAll(generator());
            combFontSize.setValue((int)text.getFont().getSize());
            combFontSize.setOnAction(e -> {
                update();
                primaryStage.sizeToScene();
            });
            //will invoke the update method to update the text and change the text size





            StackPane stackPane = new StackPane(text);
            stackPane.getChildren();
            //assigning the label to the stackPane



            VBox vbox = new VBox(topHbox, stackPane, bottomHbox);
            //vbox that will contain the hbox and the stackPane and the bottomHbox which we created above

            pane.setTop(pane2);
            pane2.setCenter(vbox);


            Slider redScroll = new Slider();
            Slider greenScroll = new Slider();
            Slider blueScroll = new Slider();
            Slider obacityScroll = new Slider();
            //declaring the 4 sliders that we will use to edit the text


            GridPane gridPane = new GridPane();
            pane.setBottom(gridPane);
            //setting the gridPane that will have the 4 slider and their label

            gridPane.add(new Label("Red     "), 1, 0);
            gridPane.add(redScroll, 2, 0);
            gridPane.add(new Label("Green     "), 1, 1);
            gridPane.add(greenScroll, 2, 1);
            gridPane.add(new Label("Blue     "), 1, 3);
            gridPane.add(blueScroll, 2, 3);
            gridPane.add(new Label("Obacity   "), 1, 4);
            gridPane.add(obacityScroll, 2, 4);
            //assigning each place of the gridPane to sliders and it's labels

            redScroll.setMax(1);
            redScroll.setMin(0);
            redScroll.setValue(0);
            //making the first slider edit the red color with max value of 1 and default of 0
            redScroll.valueProperty().addListener(ov -> {
                text.setTextFill(new Color(redScroll.getValue(), greenScroll.getValue(), blueScroll.getValue(), obacityScroll.getValue()));
            });
            //whenever the slider is moved it will update the red color shade

            greenScroll.setMax(1);
            greenScroll.setMin(0);
            greenScroll.setValue(0);
            //making the second slider edit the green color with max value of 1 and default of 0
            greenScroll.valueProperty().addListener(ov ->{
                text.setTextFill(new Color(redScroll.getValue(),greenScroll.getValue(),blueScroll.getValue(),obacityScroll.getValue()));
            });
              //whenever the slider is moved it will update the green color shade

            blueScroll.setMax(1);
            blueScroll.setMin(0);
            blueScroll.setValue(0);
            //making the third slider edit the blue color with max value of 1 and default of 0
            blueScroll.valueProperty().addListener(ov ->{
                text.setTextFill(new Color(redScroll.getValue(),greenScroll.getValue(),blueScroll.getValue(),obacityScroll.getValue()));
            });
             //whenever the slider is moved it will update the blue color shade

            obacityScroll.setMax(1);
            obacityScroll.setMin(0);
            obacityScroll.setValue(1);
             //making the fourth slider edit the obacity with max value of 1 and default of 0
            obacityScroll.valueProperty().addListener(ov ->{
            text.setTextFill(new Color(redScroll.getValue(),greenScroll.getValue(),blueScroll.getValue(),obacityScroll.getValue()));
            });
            //whenever the slider is moved it will update the obacity

            gridPane.setVgap(1);
            gridPane.setHgap(1);


            pane.setBottom(gridPane);


            gridPane.setAlignment(Pos.CENTER);


            primaryStage.setScene(new Scene(pane, 600, 470));
            primaryStage.show();
        }



    private Integer[] generator() {
        //this method generate integers from 0 to 100

        Integer[] result = new Integer[100];

        for (int i = 1; i < result.length + 1; i++) {
            result[i - 1] = i;

        }
        return result;
    }

    private void update() {
        FontWeight fontWeight = (checkBold.isSelected()) ? FontWeight.BOLD : FontWeight.NORMAL;
        FontPosture fontPosture = (checkItalic.isSelected()) ? FontPosture.ITALIC : FontPosture.REGULAR;
        String fontFamily = combFontType.getValue();
        double size = combFontSize.getValue();
        text.setFont(Font.font(fontFamily, fontWeight, fontPosture, size));

    }

    public static void main(String[] args) {
        launch(args);
    }


}
