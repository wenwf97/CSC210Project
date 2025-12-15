package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class PizzaFXProject extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    // MAIN STAGE
    @Override
    public void start(Stage Stage) throws Exception
    {
        Stage.setTitle("Pizza Store");

        //  LABELS
        Label Username = new Label("Username");
        TextField txtUser = new TextField();

        Label Password = new Label("Password");
        TextField txtPass = new TextField();

        Label loginPlease = new Label("Login");

        //  LOGIN BUTTON
        Button enterButton = new Button("Enter");

        //  EVENT BUTTON
        enterButton.setOnAction(event ->
        {
            // Get the User and Password
            String user = txtUser.getText();
            String pass = txtPass.getText();

            //  Pass it to PizzaStore class
            PizzaStore pizzaStore = new PizzaStore();

            if (!pizzaStore.getUsername(user))
            {
                loginPlease.setText("Incorrect Username");
            }
            else if (!pizzaStore.getPassword(pass))
            {
                loginPlease.setText("Incorrect Password");
            }
            else
            {
                Stage.setScene(MenuScene());
            }
        });

        //  LAYOUT
        HBox hbox1 = new HBox(10, Username, txtUser);
        HBox hbox2 = new HBox(10, Password, txtPass);
        VBox vbox = new VBox(10, loginPlease, hbox1, hbox2, enterButton);

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        //  SET SCENE
        Scene scene = new Scene(vbox);
        Stage.setScene(scene);
        Stage.show();
    }

    //   MENU SCENE METHOD
    private Scene MenuScene()
    {
        PizzaStore pizzaStore = new PizzaStore();

        String getCheesePrice = Double.toString(pizzaStore.getPrice("cheese"));
        String getPepperoniPrice = Double.toString(pizzaStore.getPrice("pepperoni"));
        String getMargaritaPrice = Double.toString(pizzaStore.getPrice("margarita"));
        String getSodaPrice = Double.toString(pizzaStore.getPrice("soda"));

        Label itemHeader = new Label("ITEM");
        Label priceHeader = new Label("PRICE");
        Label qtyHeader = new Label("QTY");

        Label cheesePrice = new Label(getCheesePrice);
        Label pepperoniPrice = new Label(getPepperoniPrice);
        Label margaritaPrice = new Label(getMargaritaPrice);
        Label sodaPrice = new Label(getSodaPrice);

        Label subTotalLabel = new Label();
        Label taxLabel = new Label();
        Label totalLabel = new Label();

        //  ITEM CHECK BOXES
        CheckBox cheeseCheckBox = new CheckBox("Cheese Pizza");
        CheckBox pepperoniCheckBox = new CheckBox("Pepperoni");
        CheckBox margaritaCheckBox = new CheckBox("Margarita");
        CheckBox sodaCheckBox = new CheckBox("Soda");

        //  QTY TEXT FIELDS
        TextField cheeseTextField = new TextField();
        cheeseTextField.setPromptText("Qty");
        cheeseTextField.setVisible(false);

        TextField pepperoniTextField = new TextField();
        pepperoniTextField.setPromptText("Qty");
        pepperoniTextField.setVisible(false);

        TextField margaritaTextField = new TextField();
        margaritaTextField.setPromptText("Qty");
        margaritaTextField.setVisible(false);

        TextField sodaTextField = new TextField();
        sodaTextField.setPromptText("Qty");
        sodaTextField.setVisible(false);

        //  CHECKBOX ACTION
        cheeseCheckBox.setOnAction(event ->
        {
            boolean selected = cheeseCheckBox.isSelected();
            cheeseTextField.setVisible(selected);
        });
        pepperoniCheckBox.setOnAction(event ->
        {
            boolean selected = pepperoniCheckBox.isSelected();
            pepperoniTextField.setVisible(selected);
        });
        margaritaCheckBox.setOnAction(event ->
        {
            boolean selected = margaritaCheckBox.isSelected();
            margaritaTextField.setVisible(selected);
        });
        sodaCheckBox.setOnAction(event ->
        {
            boolean selected = sodaCheckBox.isSelected();
            sodaTextField.setVisible(selected);
        });

        //  ORDER BUTTON  CALCULATION
        Button orderButton = new Button("ORDER");
        orderButton.setOnAction(actionEvent ->
        {
            double cheeseQty = 0;
            double pepperoniQTY = 0;
            double margaritaQTY = 0;
            double sodaQTY = 0;

            if(cheeseCheckBox.isSelected() &&
                    !cheeseTextField.getText().isEmpty())
            {
                cheeseQty = Double.parseDouble(cheeseTextField.getText());
            }
            if(pepperoniCheckBox.isSelected() &&
                    !pepperoniTextField.getText().isEmpty())
            {
                pepperoniQTY = Double.parseDouble(cheeseTextField.getText());
            }
            if(margaritaCheckBox.isSelected() &&
                    !margaritaTextField.getText().isEmpty())
            {
                margaritaQTY = Double.parseDouble(margaritaTextField.getText());
            }
            if(sodaCheckBox.isSelected() &&
                    !sodaTextField.getText().isEmpty())
            {
                sodaQTY = Double.parseDouble(sodaTextField.getText());
            }

            double subTotal = pizzaStore.getCost(
                    cheeseQty,
                    pepperoniQTY,
                    margaritaQTY,
                    sodaQTY);
            double tax = pizzaStore.getTax(subTotal);
            double total = pizzaStore.getTotal(subTotal);

            subTotalLabel.setText(String.format("Subtotal: $%.2f", subTotal));
            taxLabel.setText(String.format("Tax: $%.2f", tax));
            totalLabel.setText(String.format("Total: $%.2f", total));
        });

        //  SCENE
        HBox cheeseHbox = new HBox(10, cheeseCheckBox, cheesePrice, cheeseTextField);
        HBox pepperoniHbox = new HBox(10, pepperoniCheckBox, pepperoniPrice, pepperoniTextField);
        HBox margaritaHbox = new HBox(10, margaritaCheckBox, margaritaPrice, margaritaTextField);
        HBox sodaHbox = new HBox(10, sodaCheckBox, sodaPrice, sodaTextField);
        VBox orderSummary = new VBox(subTotalLabel, taxLabel, totalLabel);

        //  GRID PANE
        GridPane grid = new GridPane();

        grid.add(itemHeader,0,0);
        grid.add(cheeseHbox,0,1);
        grid.add(pepperoniHbox,0,2);
        grid.add(margaritaHbox,0,3);
        grid.add(sodaHbox,0,4);
        grid.add(orderButton,0,5);
        grid.add(orderSummary,0,6);

        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(30));
        grid.setVgap(30);

        return new Scene(grid);
    }
}



