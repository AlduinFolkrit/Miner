import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Miner extends Game {

    private boolean isGameStopped;
    private int mineCounter = 0;
    private int flagCounter = 0;
    private final GameObject[][] array = new GameObject[SIDE][SIDE];
    public static final String MINE = "\uD83D\uDCA3";

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
        gameObjectInit();
        countMineNeighbors();
    }

    private void gameObjectInit() {
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                if (setRandom() == 5) {
                    array[y][x] = new GameObject(x,y);
                    array[y][x].isMine= true;
                    mineCounter++;
                    flagCounter++;
//                    setColor(x, y, Color.RED);
                } else {
                    array[y][x] = new GameObject(x,y);
//                    setColor(x, y, Color.GREEN);
                }
            }
        }
    }

    @Override
    void rightMouseClick(int x, int y) {
        if (!isGameStopped && !array[y][x].isOpen) {
            setFlag(x,y);
        }
    }

    @Override
    void leftMouseClick(int x, int y) {
        if (!isGameStopped)
            openTile(x,y);
        else gameRestart();
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
        for (int vertical = y - 1; vertical < y + 2; vertical++) {
            for (int horizontal = x - 1; horizontal < x + 2; horizontal++) {
                if (!(horizontal < 0 || vertical < 0 || horizontal > (SIDE - 1) || vertical > (SIDE - 1) || (horizontal == x && vertical == y)))
                    list.add(array[vertical][horizontal]);
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
                        setColor(x, y, Color.RED);
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

    void gameObjectReset(){
        for (int x = 0; x < SIDE; x++) {
            for (int y = 0; y < SIDE; y++) {
                array[y][x].isMine = false;
                array[y][x].isOpen =false;
                array[y][x].countMineNeighbors = 0;
                array[y][x].isFlag = false;
                setColor(x,y, Color.GRAY);
                setValueCell(x,y,"");
            }
        }
    }

    void setFlag(int x, int y){
        if (!array[y][x].isFlag && flagCounter != 0) {
            array[y][x].isFlag = true;
            setValueCell(x, y, "\ud83c\udfc1");
            flagCounter -- ;
        }else{
            array[y][x].isFlag = false;
            setValueCell(x, y, "");
            flagCounter ++ ;
        }
    }

    void gameOver() {
        isGameStopped = true;
    }

    void gameRestart(){
        isGameStopped = false;
        gameObjectReset();
        gameObjectInit();
        countMineNeighbors();

    }
}

