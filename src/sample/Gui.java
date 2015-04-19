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
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {


    private int targetIndex;

    /*
    *Класс GUI
    *Все постраение описывается в JAVA коде.
    *Файл FXML не используется
    *Изначально создан в Eclipse
    */
    private TextArea commonChat = new TextArea();
    private TextArea sendingMessage = new TextArea();
    private Button bStart = new Button("Connect");
    private Button bsendMessage = new Button("Send");
    private Button fireButton = new Button("Fire");
    private ToggleGroup group = new ToggleGroup();
    private RadioButton ranking = new RadioButton("Ranking");
    private RadioButton no = new RadioButton("NO");
    private ToggleGroup ships = new ToggleGroup();
    private RadioButton four = new RadioButton("Four 1 pcs.");
    private RadioButton three = new RadioButton("Three 2 pcs.");
    private RadioButton two = new RadioButton("Two 3 pcs.");
    private RadioButton one = new RadioButton("One 4 pcs.");
    private Counters count = new Counters();

    private ClientServerConnector connector;
    private Status status = new Status(count);
    Rects rects = new Rects(this, count);

    private GridPane mySeaField = new GridPane();
    private GridPane myPane = new GridPane();
    private GridPane enemySeaField = new GridPane();
    private GridPane shipType = new GridPane();
    private int i = 0;


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        commonChat.setEditable(false);
        commonChat.selectEndOfNextWord();
        commonChat.setPrefSize(200, 500);
        commonChat.setTooltip(new Tooltip("Чат Окно"));
        commonChat.setWrapText(true);
        try {
            commonChat.setText(commonChat.getText() + "\n");
        } catch (NullPointerException e) {

        }

        GridPane chatPain = new GridPane();
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
        mySeaField.setHgap(10);
        mySeaField.setVgap(10);
        mySeaField.setPadding(new Insets(10, 10, 10, 10));

        enemySeaField.setAlignment(Pos.CENTER_RIGHT);
        enemySeaField.setVgap(10);
        enemySeaField.setHgap(10);
        enemySeaField.setPadding(new Insets(10, 10, 10, 10));

        shipType.setAlignment(Pos.CENTER_LEFT);
        shipType.setPadding(new Insets(0, 0, 0, 0));
        shipType.setHgap(50);
        shipType.add(four, 0, 0, 1, 1);
        shipType.add(three, 1, 0, 1, 1);
        shipType.add(two, 2, 0, 1, 1);
        shipType.add(one, 3, 0, 1, 1);

        myPane.add(mySeaField, 0, 0, 1, 1);
        myPane.add(enemySeaField, 1, 0, 1, 1);
        myPane.add(ranking, 0, 1, 1, 1);
        myPane.add(no, 1, 1, 1, 1);
        myPane.add(shipType, 0, 2, 2, 1);
        myPane.add(fireButton, 0, 3, 1, 1);
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
                connector = new ClientServerConnector(Gui.this);
                connector.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {

                                //whatDoWhenMessageDiliverd(connector);
                                new GuiWorkWithIncomingMessage(Gui.this, connector, status).main();
                                System.out.println("TYK TYK");
                            }
                        });
                Service service = new Service<Void>() {

                    @Override
                    protected Task<Void> createTask() {
                        // TODO Auto-generated method stub
                        return connector;
                    }

                };
                service.start();
            }

        });

        fireButton.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                final SendingTargetCoordinate sendMess = new SendingTargetCoordinate(Gui.this, connector, status);
                sendMess.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                                commonChat.setText(commonChat.getText().toString() + "\n");
                                commonChat.end();
                                commonChat.setText(commonChat.getText().toString() + sendMess.getMessage().toString());
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
        rects.makeEnemyAndMyField();


        Scene scene = new Scene(myPane, 700, 700);

        scene.getStylesheets().add(
                getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    //ДАЛЕЕ НАХОДИТСЯ ЭКСПОРТИРУЕМЫЕ ФУНКЦИИ
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

    //Сеттеры
    public void setTargetIndex(int i) {
        this.targetIndex = i;
    }

    public Rects getRects() {
        return rects;
    }


    public int getTargetIndex() {
        return targetIndex;
    }

    public TextArea getSendingMessage() {
        return sendingMessage;
    }

    public RadioButton getNo() {
        return no;
    }

    public RadioButton getRanking() {
        return ranking;
    }

    public RadioButton getOne() {
        return one;
    }

    public RadioButton getTwo() {
        return two;
    }

    public RadioButton getThree() {
        return three;
    }

    public RadioButton getFour() {
        return four;
    }
}

