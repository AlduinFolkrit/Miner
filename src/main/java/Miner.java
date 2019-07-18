import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Miner extends Game {

    private GameObject[][] array = new GameObject[9][9];

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (setRandom() == 5) {
                    array[y][x] = new GameObject();
                    setColor(x, y, Color.RED);
                } else {
                    array[y][x] = new GameObject();
                    setColor(x, y, Color.GREEN);
                }

            }
        }
    }

    @Override
    void rightMouseClick(int x, int y) {
        super.rightMouseClick(x, y);
//        System.out.println("x  "+x+"y  "+y);
        setValueCell(x,y,"4");
    }

    @Override
    void leftMouseClick(int x, int y) {
        super.leftMouseClick(x, y);
//        System.out.println("x  "+x+"y  "+y);
        setValueCell(x,y,"6");
    }
}

