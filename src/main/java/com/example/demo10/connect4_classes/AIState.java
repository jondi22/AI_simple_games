package com.example.demo10.connect4_classes;

import java.util.ArrayList;

public class AIState extends State{

    private int redPossibilities;
    private int yellowPossibilities;

    public AIState(){
        this.redPossibilities = 43;
        this.yellowPossibilities = 43;
    }

    public int getScore(){
        return yellowPossibilities - redPossibilities;

    }
    public int evaluateState(){
        int winning = isAWinningState();
        if(winning == YELLOW) return Short.MAX_VALUE;
        else if(winning == RED) return Short.MIN_VALUE;
        else{
            int lastMoveColor = this.getLastMoveColor();
            int noOfBlockedFromThisMove = 0;
            for(int i = 0; i < 4; i++){
                int j; int k;
                //horizontal block
                for(j = getLastMoveX() - 3 + i, k = 0; k < 4; j++, k++){
                    if(j < 0 || j >= 7 || getDisksArray()[j][getLastMoveY()] == lastMoveColor) break;
                }
                if(k == 4) noOfBlockedFromThisMove++;

                //vertical block
                for(j = getLastMoveY() - 3 + i, k = 0; k < 4; j++, k++){
                    if(j < 0 || j >= 6 || getDisksArray()[getLastMoveX()][j] == lastMoveColor) break;
                }
                if(k == 4) noOfBlockedFromThisMove++;

                int l;
                //top right block
                for(l = getLastMoveX() - 3 + i, j = getLastMoveY() - 3 + i, k = 0; k < 4; l++, j++, k++){
                    if(j < 0 || j >= 6 || l < 0 || l >=7 || getDisksArray()[k][j] == lastMoveColor) break;
                }
                if(k == 4) noOfBlockedFromThisMove++;

                //top left block
                for(l = getLastMoveX() - 3 + i, j = getLastMoveY() + 3 - i, k = 0; k < 4; l++, j--, k++){
                    if(j < 0 || j >= 6 || l < 0 || l >=7 || getDisksArray()[k][j] == lastMoveColor) break;
                }
                if(k == 4) noOfBlockedFromThisMove++;
            }

            if(lastMoveColor == RED) yellowPossibilities -= noOfBlockedFromThisMove;
            else redPossibilities -= noOfBlockedFromThisMove;
            return getScore();
        }
    }

    public ArrayList<AIState> getAllPossibleChildren(){
        if(this.tieGame() || isAWinningState() != 0) return null;
        ArrayList<AIState> aiStates = new ArrayList<>();
        for(short i = 0; i < 7; i++){
            if(this.columnsPointers[i] == 6) continue;
            AIState aiState = this.instantiate();
            aiState.insertADisk((short) -getLastMoveColor(), i);
            aiStates.add(aiState);
        }
        return aiStates;

    }

    public AIState instantiate(){
        AIState aiState = new AIState();
        aiState.lastMoveX = this.lastMoveX;
        aiState.lastMoveY = this.lastMoveY;
        aiState.yellowPossibilities = this.yellowPossibilities;
        aiState.redPossibilities = this.redPossibilities;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++) aiState.disksArray[i][j] = this.disksArray[i][j];
            aiState.columnsPointers[i] = this.columnsPointers[i];
        }
        return aiState;
    }



}
