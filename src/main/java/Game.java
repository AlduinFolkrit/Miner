
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;


import java.util.Optional;
import java.util.Random;

public class Game extends Application {

    static final int SIDE = 9;
    private final GameField[][] matrix = new GameField[SIDE][SIDE];
    private final FlowPane rootNode = new FlowPane(1, 1);
    private final Random rdm = new Random();

    class GameField extends TextFlow {

        int x, y;

        GameField(int x, int y, Node... children) {

            super(children);
            this.x = x;
            this.y = y;

            setPrefSize(60, 60);
            setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
            addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.SECONDARY)
                        rightMouseClick(x, y);
                    else
                        leftMouseClick(x, y);
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
        matrix[y][x].setBackground(new Background(new BackgroundFill(color, null, null)));

    }

    void setValueCell(int x, int y, String s) {
        final ObservableList<Node> children = matrix[y][x].getChildren();
        for (Node node : children)
            if (node instanceof Text) {
                Text text = (Text) node;
                text.setFont(Font.font("Helvetica", FontPosture.ITALIC, 45));
                text.setText(s);
            }
    }

    void setValueCell(int x, int y, int i) {
        String s = String.valueOf(i);
        setValueCell(x, y, s);
    }

    int setRandom() {
        return rdm.nextInt(10);
    }

    private void initialize() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                matrix[y][x] = new GameField(x, y, new Text(" "));
                rootNode.getChildren().add(matrix[y][x]);
            }
        }
    }

    void rightMouseClick(int x, int y) {
    }

    void leftMouseClick(int x, int y) {
    }

    ButtonType showInformation() {
         String s = "Вы проиграли!!!!";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Результат игры");
        alert.setHeaderText(s);
        ButtonType restart = new ButtonType("Еще раз сыграть", ButtonBar.ButtonData.OK_DONE);
        ButtonType exit = new ButtonType("Выход", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(restart,exit);
        return alert.showAndWait().orElse(exit);
    }

}
