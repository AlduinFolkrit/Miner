class GameObject {

    int x,y;
    boolean isMine;
    boolean isOpen;
    boolean isFlag;
    int countMineNeighbors;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
