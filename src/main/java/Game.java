
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;


import java.util.Random;

public class Game extends Application {

    private GameField[][] matrix = new GameField[9][9];
    private FlowPane rootNode = new FlowPane(1, 1);
    private Random rdm = new Random();

    class GameField extends TextFlow {

        int x, y;

        GameField(int x, int y, Node... children) {

            super(children);
            this.x = x;
            this.y = y;

            setPrefSize(60, 60);
//            setBackground(new Background(new BackgroundFill(Color.BROWN,null,null)));
            addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton()== MouseButton.SECONDARY)
                        rightMouseClick(x,y);
                    else
                        leftMouseClick(x,y);

                }
            });
            setTextAlignment(TextAlignment.CENTER);
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

        primaryStage.show();
    }

    void setColor(int x, int y, Color color) {
        matrix[x][y].setBackground(new Background(new BackgroundFill(color, null, null)));

    }

    void setValueCell(int x, int y, String s) {
        final ObservableList<Node> childrens = matrix[y][x].getChildren();
        for (Node children : childrens)
            if (children instanceof Text) {
                Text text = (Text) children;
                text.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40));
                text.setText(s);
            }

    }

    int setRandom() {
        return rdm.nextInt(10);
    }

    private void initialize() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                matrix[y][x] = new GameField(x,y, new Text(" ") );
                rootNode.getChildren().add(matrix[y][x]);
            }
        }
    }

    void rightMouseClick(int x, int y){

    }

    void leftMouseClick(int x, int y){

    }

}
