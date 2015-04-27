package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {

    private TextArea commonChat = new TextArea();
    private TextArea sendingMessage = new TextArea();
    private Button bStart = new Button("Connect");
    private Button bsendMessage = new Button("Send");
    private Button fireButton = new Button("Fire");
    private Button readyButton = new Button("Ready?");
    private ToggleGroup group = new ToggleGroup();
    private RadioButton ranking = new RadioButton("Ranking");
    private RadioButton no = new RadioButton("NO");
    private ToggleGroup ships = new ToggleGroup();
    private RadioButton four = new RadioButton("Four 1 pcs.");
    private RadioButton three = new RadioButton("Three 2 pcs.");
    private RadioButton two = new RadioButton("Two 3 pcs.");
    private RadioButton one = new RadioButton("One 4 pcs.");
    private Label whomStep = new Label();

    private Engine engine = new Engine(this);
    private ClientServerConnector connector;

    private GridPane mySeaField = new GridPane();
    private GridPane myPane = new GridPane();
    private GridPane enemySeaField = new GridPane();
    private GridPane shipType = new GridPane();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        commonChat.setEditable(false);
        commonChat.setPrefSize(200, 500);
        commonChat.setTooltip(new Tooltip("Чат Окно"));
        commonChat.setWrapText(true);
        commonChat.clear();

        ranking.setToggleGroup(group);
        no.setToggleGroup(group);
        ranking.setSelected(true);
        four.setToggleGroup(ships);
        three.setToggleGroup(ships);
        two.setToggleGroup(ships);
        one.setToggleGroup(ships);
        one.setSelected(true);

        myPane.setAlignment(Pos.CENTER_LEFT);
        myPane.setHgap(10);
        myPane.setVgap(10);
        myPane.setPadding(new Insets(25, 25, 25, 25));

        mySeaField.setAlignment(Pos.CENTER_LEFT);
        mySeaField.setHgap(1);
        mySeaField.setVgap(1);
        mySeaField.setPadding(new Insets(5, 5, 5, 5));

        enemySeaField.setAlignment(Pos.CENTER_RIGHT);
        enemySeaField.setVgap(1);
        enemySeaField.setHgap(1);
        enemySeaField.setPadding(new Insets(5, 5, 5, 5));

        shipType.setAlignment(Pos.CENTER_LEFT);
        shipType.setPadding(new Insets(0, 0, 0, 0));
        shipType.setHgap(20);
        shipType.add(four, 0, 0, 1, 1);
        shipType.add(three, 1, 0, 1, 1);
        shipType.add(two, 2, 0, 1, 1);
        shipType.add(one, 3, 0, 1, 1);

        myPane.add(mySeaField, 0, 0, 1, 1);
        myPane.add(enemySeaField, 1, 0, 1, 1);
        myPane.add(ranking, 0, 1, 1, 1);
        myPane.add(no, 1, 1, 1, 1);
        myPane.add(shipType, 0, 2, 2, 1);
        myPane.add(readyButton, 0, 3, 1, 1);
        myPane.add(fireButton, 1, 3, 1, 1);
        myPane.add(whomStep, 2, 3, 1, 1);
        myPane.add(commonChat, 0, 11, 2, 1);
        myPane.add(sendingMessage, 0, 12, 2, 1);
        myPane.add(bsendMessage, 0, 13, 1, 1);
        myPane.add(bStart, 0, 14, 5, 1);

        ranking.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                if (ranking.isSelected()) {
                    four.setDisable(false);
                    three.setDisable(false);
                    two.setDisable(false);
                    one.setDisable(false);
                } else if (!ranking.isSelected()) {
                    four.setDisable(true);
                    three.setDisable(true);
                    two.setDisable(true);
                    one.setDisable(true);
                }

            }
        });

        bStart.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                // Создание класса Task, существующий для работы с JavaFX
                commonChat.setText("Begin connection");
                connector = new ClientServerConnector();

                connector.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {

                                String tempString = connector.getMessage();
                                Gui.this.setTextInCommonChat(connector.getMessage());
                                new GuiWorkWithIncomingMessage(engine, connector).main(tempString);
                                statusLabelOfStep();

                            }
                        });
                Service service = new Service<Void>() {

                    @Override
                    protected Task<Void> createTask() {
                        return connector;
                    }

                };
                service.start();
            }

        });

        fireButton.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                final SendingTargetCoordinate sendMess = new SendingTargetCoordinate(engine, connector);
                sendMess.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                                commonChat.setText(commonChat.getText().toString() + "\n");
                                commonChat.setText(commonChat.getText().toString() + sendMess.getMessage().toString());
                                commonChat.end();
                            }
                        });


                Service service = new Service<Void>() {

                    @Override
                    protected Task<Void> createTask() {
                        // TODO Auto-generated method stub
                        return sendMess;
                    }

                };
                service.start();
            }
        });

        bsendMessage.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                final SendingMessage sendMess = new SendingMessage(Gui.this, connector);

                sendMess.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                                commonChat.setText(commonChat.getText() + "\n");
                                commonChat.setText(commonChat.getText().toString() + sendMess.getMessage());
                                commonChat.end();
                            }
                        });

                Service service = new Service<Void>() {

                    @Override
                    protected Task<Void> createTask() {
                        // TODO Auto-generated method stub
                        return sendMess;
                    }

                };
                service.start();
            }
        });

        readyButton.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(engine.isAllShipInstall()){
                    engine.getStatus().setReady(true);
                    readyButton.setDisable(true);
                    readyButton.setText("READY!");
                }
            }
        });

        one.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                one.setSelected(true);

            }

        });
        two.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                two.setSelected(true);

            }

        });
        three.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                three.setSelected(true);

            }

        });
        four.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                four.setSelected(true);

            }

        });
        engine.getRects().makeEnemyAndMyField();


        Scene scene = new Scene(myPane, 500, 600);

        scene.getStylesheets().add(
                getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void statusLabelOfStep() {
        if (engine.getStatus().isFollowStep()) {
            whomStep.setText("You step!");
            whomStep.setTextFill(Color.GREEN);
        } else {
            whomStep.setText("Enemy step!");
            whomStep.setTextFill(Color.RED);
        }
    }

    public void addMySeaField(MyRectangle rectangle, int i, int numline) {
        mySeaField.add(rectangle, i, numline);
    }

    public void addEnemySeaField(EnemyRectangle rectangle, int i, int numLine) {
        enemySeaField.add(rectangle, i, numLine);
    }


    public void setTextInCommonChat(String message) {

        commonChat.setText(commonChat.getText() + "\n"
                + message);
        commonChat.end();
    }

    public TextArea getSendingMessage() {
        return sendingMessage;
    }

    public Settings getSettings() {
        return new Settings() {
            @Override
            public boolean isRanking() {
                return ranking.isSelected();
            }

            @Override
            public boolean isNo() {
                return no.isSelected();
            }

            @Override
            public boolean isOne() {
                return one.isSelected();
            }

            @Override
            public boolean isTwo() {
                return two.isSelected();
            }

            @Override
            public boolean isThree() {
                return three.isSelected();
            }

            @Override
            public boolean isFour() {
                return four.isSelected();
            }
        };
    }


}

