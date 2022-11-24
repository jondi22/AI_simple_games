package com.example.demo10.connect4_classes;

public class State {
    public static final short RED = -1;
    public static final short EMPTY = 0;
    public static final short YELLOW = 1;

    private int [][] disksArray = new int [7][6];
    private int [] columnsPointers = new int [7];

    private int lastMoveX = -1;
    private int lastMoveY = -1;



}
