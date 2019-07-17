package MyMiner;

import javafx.application.Application;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Game extends Application {

    private GameField[][] matrix = new GameField[9][9];
    private FlowPane rootNode = new FlowPane(1, 1);
    private Random rdm = new Random();

    class GameField extends Pane {
        GameField() {
            setPrefSize(60, 60);
//            setBackground(new Background(new BackgroundFill(Color.BROWN,null,null)));
//            addEventHandler(MouseEvent.MOUSE_CLICKED,);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("MinerTest"); //pomostki
        //kornevoy uzel


        rootNode.setAlignment(Pos.CENTER);
        // sozdanie scene

        primaryStage.setScene(new Scene(rootNode, 550, 550));

        primaryStage.setResizable(false);

        initialize();


//        ToggleButton bSecond = new ToggleButton("second");

//        bFirst.setOnAction(a-> label.setText("koko"));
//        bSecond.setOnAction(a->{
//            if (bSecond.isSelected())
//                label.setText("dudu");
//            else
//                label.setText("DUDU");}
//        );

        primaryStage.show();
    }

    void setColor(int x, int y, Color color) {
        matrix[x][y].setBackground(new Background(new BackgroundFill(color, null, null)));

    }

    void setValueCell(int x, int y, String s) {
        matrix[x][y].setAccessibleText(s);
    }

    int setRandom() {
        return rdm.nextInt(10);
    }

    private void initialize() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                matrix[y][x] = new GameField();
                rootNode.getChildren().add(matrix[y][x]);
            }
        }
    }

    void setMouseClick(){

    }

}
