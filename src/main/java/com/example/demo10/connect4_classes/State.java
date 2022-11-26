package com.example.demo10.connect4_classes;

public class State {
    public static final short RED = -1;
    public static final short EMPTY = 0;
    public static final short YELLOW = 1;

    public short getLastMoveColor(){
        return this.disksArray[lastMoveX][lastMoveY];
    }

    protected short [][] disksArray = new short [7][6];
    protected int [] columnsPointers = new int [7];

    public short[][] getDisksArray() {
        return disksArray;
    }

    public int[] getColumnsPointers() {
        return columnsPointers;
    }

    public int getLastMoveX() {
        return lastMoveX;
    }

    public int getLastMoveY() {
        return lastMoveY;
    }

    protected int lastMoveX = -1;
    protected int lastMoveY = -1;

    public void insertADisk(short color, short column){
        if(columnsPointers[column] == 6) return;
        disksArray[column][columnsPointers[column]] = color;
        lastMoveY = columnsPointers[column];
        lastMoveX = column;
        columnsPointers[column]++;
    }

    public int isAWinningState(){
        short color = disksArray[lastMoveX][lastMoveY];
        int xAdjacentsNumber = 1;
        for(int i = lastMoveX + 1; i < 7 && i <= lastMoveX+3; i++){
            if(disksArray[i][lastMoveY] == color) xAdjacentsNumber++;
            else break;
        }
        for(int i = lastMoveX - 1; i >= 0 && i >= lastMoveX - 3; i--){
            if(disksArray[i][lastMoveY] == color) xAdjacentsNumber++;
            else break;
        }
        if(xAdjacentsNumber >= 4) return color;

        int yAdjacentsNumber = 1;
        for(int i = lastMoveY + 1; i < 6 && i <= lastMoveY + 3; i++){
            if(disksArray[lastMoveX][i] == color) yAdjacentsNumber++;
            else break;
        }
        for(int i = lastMoveY - 1; i >= 0 && i >= lastMoveY - 3; i--){
            if(disksArray[lastMoveX][i] == color) yAdjacentsNumber++;
            else break;
        }
        if(yAdjacentsNumber >= 4) return color;


        int topRightDiagonalAdjacents = 1;
        for(int i = lastMoveX + 1, j = lastMoveY + 1; i < 7 && j < 6 && i <= lastMoveX + 3 && j <= lastMoveY + 3; i++, j++){
            if(disksArray[i][j] == color) topRightDiagonalAdjacents++;
            else break;
        }
        for(int i = lastMoveX - 1, j = lastMoveY - 1; i >= 0 && j >= 0 && i < 7 && i <= lastMoveX + 3 &&
                j <= lastMoveY + 3; i--, j--){
            if(disksArray[i][j] == color) topRightDiagonalAdjacents++;
            else break;
        }
        if(topRightDiagonalAdjacents >= 4) return color;

        int topLeftDiagonalAdjacents = 1;
        for(int i = lastMoveX - 1, j = lastMoveY + 1; i >= 0 && i < 7 && j < 6
                && i <= lastMoveX + 3 &&j <= lastMoveY + 3; i--, j++){
            if(disksArray[i][j] == color) topLeftDiagonalAdjacents++;
            else break;
        }
        for(int i = lastMoveX + 1, j = lastMoveY - 1; j >= 0 && i < 7 && i <= lastMoveX + 3 &&j <= lastMoveY + 3; i++, j--){
            if(disksArray[i][j] == color) topLeftDiagonalAdjacents++;
            else break;
        }
        if(topLeftDiagonalAdjacents >= 4) return color;

        return 0;
    }
    public boolean tieGame(){
        for(int i : columnsPointers){
            if(i != 6) return false;
        }
        return true;
    }


}
