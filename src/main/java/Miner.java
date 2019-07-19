import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Miner extends Game {

    private boolean isGameStopped;
    private GameObject[][] array = new GameObject[SIDE][SIDE];
    public static final String MINE = "\uD83D\uDCA3";

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (setRandom() == 5) {
                    array[y][x] = new GameObject(x,y);
                    array[y][x].isMine= true;
//                    setColor(x, y, Color.RED);
                } else {
                    array[y][x] = new GameObject(x,y);
//                    setColor(x, y, Color.GREEN);
                }
            }
        }
        countMineNeighbors();
    }

    @Override
    void rightMouseClick(int x, int y) {
        super.rightMouseClick(x, y);
//        setValueCell(x,y,"\ud83c\udfc1");
    }

    @Override
    void leftMouseClick(int x, int y) {
        super.leftMouseClick(x, y);
//        setValueCell(x,y,"\uD83D\uDCA3");
        openTile(x,y);
    }

    private void countMineNeighbors() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (!array[y][x].isMine) {
                    List<GameObject> neighbors = getNeighbors(array[y][x]);
                    for (GameObject game : neighbors) {
                        if (game.isMine) {
                            array[y][x].countMineNeighbors++;
//                            setCellNumber(x, y, gameField[y][x].countMineNeighbors);
                        }

                    }
                }
            }
        }
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> list = new ArrayList<>();
        int x = gameObject.x;
        int y = gameObject.y;
        for (int v = y - 1; v < y + 2; v++) {
            for (int h = x - 1; h < x + 2; h++) {
                if (!(h < 0 || v < 0 || h > (SIDE - 1) || v > (SIDE - 1) || (h == x && v == y)))
                    list.add(array[v][h]);
            }
        }
        return list;
    }

    private void openTile(int x, int y) {
        if (!isGameStopped) {
            if (!array[y][x].isOpen) {
                if (!array[y][x].isFlag) {
                    if (array[y][x].isMine ) {
                        setValueCell(x,y,MINE);
//                        setCellValueEx(x, y, Color.RED, MINE);
                        gameOver();
                    }
                    if (!array[y][x].isMine && array[y][x].countMineNeighbors != 0) {
                        array[y][x].isOpen = true;
//                        countClosedTiles--;
//                        score+=5;
//                        setScore(score);
                        setColor(x, y, Color.LIGHTBLUE);
                        setValueCell(x, y, array[y][x].countMineNeighbors);
                    }
                    if (!array[y][x].isMine && array[y][x].countMineNeighbors == 0) {
                        array[y][x].isOpen = true;
//                        countClosedTiles--;
//                        score+=5;
//                        setScore(score);
                        setValueCell(x, y, "");
                        setColor(x, y, Color.LIGHTBLUE);
                        List<GameObject> list = getNeighbors(array[y][x]);
                        for (GameObject g : list) {
                            if (!g.isOpen) {
                                openTile(g.x, g.y);
                            }
                        }
                    }
                }
            }
        }
//        if (countClosedTiles==countMinesOnField)
//            win();
    }

    void gameOver() {

    }
}

