package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.JFXPanel;
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

    private String line = " ";
    private MyRectangle[] rectMY = new MyRectangle[100];
    public EnemyRectangle[] rectENEMY = new EnemyRectangle[100];
    private int targetIndex;

    /*
    *Класс GUI
    *Все постраение описывается в JAVA коде.
    *Файл FXML не используется
    *Изначально создан в Eclipse
    */
    private Object tt = new JFXPanel();
    private TextArea commonChat = new TextArea();
    private TextArea sendingMessage = new TextArea();
    private Button bStart = new Button("Start");
    private Button bsendMessage = new Button("Send");
    private Button fireButton = new Button("Огонь");
    private ToggleGroup group = new ToggleGroup();
    private RadioButton ranking = new RadioButton("Ranking");
    private RadioButton no = new RadioButton("NO");
    private ToggleGroup ships = new ToggleGroup();
    private RadioButton four = new RadioButton("Four 1 pcs.");
    private RadioButton three = new RadioButton("Three 2 pcs.");
    private RadioButton two = new RadioButton("Two 3 pcs.");
    private RadioButton one = new RadioButton("One 4 pcs.");
    private Counters count=new Counters();
    //boolean forCircle = false;
    // boolean followStep;

    private clientServerConnector connector;

    private GridPane mySeaField = new GridPane();
    private GridPane myPane = new GridPane();
    private GridPane enemySeaField = new GridPane();
    private GridPane shipType = new GridPane();
    private int i = 0;
    private int e = 0;
    private int a = 0;

    public static void main(String[] args) throws Exception {

        launch(args);

    }

    //Функция по закраске подпитых квадратиков
    public void workWithMyField(int x, int y) {
        if (rectMY[x + (y * 10)].getFill() == Color.BLUE) {
            rectMY[x + (y * 10)].getPrivateShip().impairment();
            System.out.println("(Gui.rectMY[x + (y * 10)].privateShip.isValidShip())" + (rectMY[x + (y * 10)].getPrivateShip().isValidShip()));

            if (rectMY[x + (y * 10)].getPrivateShip().isValidShip()) {
                rectMY[x + (y * 10)].setFill(Color.ORANGE);
                System.out.println("Gui.rectMY[x + (y * 10)].setFill(Color.ORANGE); " + (x + (y * 10)));

            } else if (!(rectMY[x + (y * 10)].getPrivateShip().isValidShip())) {

                rectMY[x + (y * 10)].setFill(Color.BLACK);

                int lX = rectMY[x + (y * 10)].getPrivateShip().getX1();
                int lY = rectMY[x + (y * 10)].getPrivateShip().getY1();
                if(lX+lY<=18) {
                    rectMY[lX + (lY * 10)].setFill(Color.BLACK);
                }
                lX = rectMY[x + (y * 10)].getPrivateShip().getX2();
                lY = rectMY[x + (y * 10)].getPrivateShip().getY2();
                if(lX+lY<=18) {
                    rectMY[lX + (lY * 10)].setFill(Color.BLACK);
                }
                lX = rectMY[x + (y * 10)].getPrivateShip().getX3();
                lY = rectMY[x + (y * 10)].getPrivateShip().getY3();
                if(lX+lY<=18) {
                    rectMY[lX + (lY * 10)].setFill(Color.BLACK);
                }
                lX = rectMY[x + (y * 10)].getPrivateShip().getX4();
                lY = rectMY[x + (y * 10)].getPrivateShip().getY4();
                if(lX+lY<=18) {
                    rectMY[lX + (lY * 10)].setFill(Color.BLACK);
                }



            }
        }


    }

    public void workWithEnemyField(int x, int y, String str, int ind1, int ind2, int ind3, int ind4) {
        if (str.equals("DAM")) {
            rectENEMY[x + (y * 10)].setFill(Color.ORANGE);
        } else if (str.equals("DESTROY")) {
            rectENEMY[x + (y * 10)].setFill(Color.BLACK);
            if (ind1 != 4400) {
                rectENEMY[ind1].setFill(Color.BLACK);
            }
            if (ind2 != 4400) {
                rectENEMY[ind2].setFill(Color.BLACK);
            }
            if (ind3 != 4400) {
                rectENEMY[ind3].setFill(Color.BLACK);
            }
            if (ind4 != 4400) {
                rectENEMY[ind4].setFill(Color.BLACK);
            }
        } else if (str.equals("MISS")) {
            rectENEMY[x + (y * 10)].setFill(Color.YELLOW);
        }


    }

    @Override
    public void start(Stage primaryStage) throws IOException,
            InterruptedException {


        commonChat.setEditable(false);
        commonChat.selectEndOfNextWord();
        commonChat.setPrefSize(200, 500);
        commonChat.setTooltip(new Tooltip("Чат Окно"));
        commonChat.setWrapText(true);


        try {
            commonChat.setText(commonChat.getText() + "\n" + line);
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

                connector =new clientServerConnector();
                System.out.println("After press Start connector " + connector.getValue());
                // Делаем слушателя на текстовое свойство

                connector.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {

                                whatDoWhenMessageDiliverd(connector);
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
                System.out.println("connector " + connector.getValue());
                final SendingTargetCoordinate sendMess = new SendingTargetCoordinate(Gui.this, connector);

                sendMess.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                                commonChat.setText(commonChat.getText().toString()+"\n");
                                commonChat.end();
                                commonChat.setText(commonChat.getText().toString() + sendMess.getMessage().toString());
                               // sendMess.getMessage().toString()

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
                System.out.println("connector " + connector.getValue());
                final SendingMessage sendMess = new SendingMessage(Gui.this, connector);

                sendMess.messageProperty().addListener(
                        new ChangeListener<String>() {

                            @Override
                            public void changed(
                                    ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                                commonChat.setText(commonChat.getText()+"\n");
                                commonChat.setText(commonChat.getText().toString() + sendMess.getMessage());
                                //commonChat.positionCaret(commonChat.get);
                                //setTextInCommonChat(getConnector());
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

        makeEnemyAndMyField();


        Scene scene = new Scene(myPane, 700, 700);

        scene.getStylesheets().add(
                getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //ДАЛЕЕ НАХОДИТСЯ ЭКСПОРТИРУЕМЫЕ ФУНКЦИИ
    private void makeEnemyAndMyField() {
        for (i = 0; i <= 99; ++i) {
            makeOneIterationRectMY(i);
            makeOneIterationRectENEMY(i);

        }
    }

    //makeOneIterationRectENEMY создает одну клетку(квадрат) поля для оппонента
    private void makeOneIterationRectENEMY(int i) {
        rectENEMY[i] = new EnemyRectangle(this, 10, 10, i);
        rectENEMY[i].setFill(Color.GREEN);

        int numLine = (int) (10 - (10 - i * 0.1));
        rectENEMY[i].setXenemyRect(i - numLine * 10);
        rectENEMY[i].setYenemyRect(numLine);


        enemySeaField.add(rectENEMY[i], (i - numLine * 10), numLine);
    }

    //makeOneIterationRectMY создает одну клетку(квадрат) поля для игрока
    private void makeOneIterationRectMY(int i) {

        rectMY[i] = new MyRectangle(this, count, 10, 10, i);
        rectMY[i].setFill(Color.GREEN);


        int numLine = (int) (10 - (10 - i * 0.1));


        rectMY[i].setXinMyRect(i - numLine * 10);
        rectMY[i].setYinMyRect(numLine);

        mySeaField.add(rectMY[i], (i - numLine * 10), numLine);
    }

    private void whatDoWhenMessageDiliverd(clientServerConnector taskClSr) {
        System.out.println("taskClSr.messageProperty().toString().charAt(0) " + taskClSr.messageProperty().toString().charAt(0));

        try {
            System.out.println("StartClientServer.line.toString().charAt(0) " + connector.getFirstCharOfLine());
            if (connector.getFirstCharOfLine() != '!' && connector.getFirstCharOfLine() != '#') {
                setTextInCommonChat(taskClSr);

            }
            if (connector.getFirstCharOfLine() == '#') {
                setTextInCommonChat(taskClSr);

                System.out.println("Проверка");
                int dX = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf("$") + 1, taskClSr.getMessage().indexOf("%")));
                int dY = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf("%") + 1, taskClSr.getMessage().indexOf("*")));
                //Сначала должна идти функция workWithMyField, затем SendingResultOfFire()
                workWithMyField(dX, dY);
                System.out.println("Before new SendingResultOfFire(this, connector).sendResult(dX, dY)");
                new SendingResultOfFire(this, connector).sendResult(dX, dY);
                System.out.println("After new SendingResultOfFire(this, connector).sendResult(dX, dY)");

            }
            if (connector.getFirstCharOfLine() == '!') {

                setTextInCommonChat(taskClSr);

                int index1=400;
                int index2=440;
                int index3=440;
                int index4=440;

                String result;
                int dX = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf("$") + 1, taskClSr.getMessage().indexOf("%")));
                int dY = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf("%") + 1, taskClSr.getMessage().indexOf("*")));
                result = taskClSr.getMessage().toString().substring(taskClSr.getMessage().indexOf("*") + 1, taskClSr.getMessage().indexOf(";"));
                if(result.equals("DESTROY")) {
                    index1 = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf(";") + 1, taskClSr.getMessage().indexOf("&")));
                    index2 = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf("&") + 1, taskClSr.getMessage().indexOf("@")));
                    index3 = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf("@") + 1, taskClSr.getMessage().indexOf("#")));
                    index4 = Integer.parseInt(taskClSr.getMessage().substring(taskClSr.getMessage().indexOf("#") + 1, taskClSr.getMessage().indexOf("~")));
                }
                System.out.println("--------------------result: " + result);

                workWithEnemyField(dX, dY, result, index1, index2, index3, index4);

            }
        } catch (StringIndexOutOfBoundsException e) {


        } catch (Exception e) {

        }
    }

    private void setTextInCommonChat(clientServerConnector taskClSr) {
        commonChat.setText(commonChat.getText() + "\n"
                + taskClSr.getMessage());
        commonChat.end();
    }
    //Сеттеры
    public void setTargetIndex(int i){
        this.targetIndex=i;
    }

    //Геттеры
    public clientServerConnector getConnector(){
        return connector;
    }
    public MyRectangle getMyRect(int x, int y){
        return rectMY[x+(10*y)];
    }
    public MyRectangle getMyRect(int i){
        return rectMY[i];
    }

    public int getTargetIndex(){
        return targetIndex;
    }
    public TextArea getSendingMessage(){
        return sendingMessage;
    }
    public RadioButton getNo(){
        return no;
    }
    public RadioButton getRanking(){ return ranking; }
    public RadioButton getOne(){ return one; }
    public RadioButton getTwo(){
        return two;
    }
    public RadioButton getThree(){
        return three;
    }
    public RadioButton getFour(){
        return four;
    }


}

