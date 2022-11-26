package com.example.demo10.connect4_classes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AlphaBetaAlgorithm {
    public static final short HARD_DIFFICULTY = 0;
    public static final short MEDIUM_DIFFICULTY = 1;
    public static final short EASY_DIFFICULTY = 2;

    public static final short MAX_DEPTH = 6;

    private short difficulty;

    private int nextColumnMove;
    private Random random;
    public AlphaBetaAlgorithm(short difficulty){
        this.difficulty = difficulty;
        random =new Random();
    }
    private int alphaBetaRun(AIState state, int depth, int alpha, int beta, boolean isMaximizer){
        if(depth == 0 || state.isAWinningState() != 0 || state.tieGame()) return state.evaluateState();
        List <AIState> nextStates = state.getAllPossibleChildren();
        if(difficulty != HARD_DIFFICULTY) {
            int randomNumber = random.nextInt((nextStates.size() * difficulty) / 3);
            for (int i = 0; i < randomNumber; i++) {
                nextStates.remove(random.nextInt(nextStates.size()));
            }
        }
        if(isMaximizer){
            int v = Short.MIN_VALUE;
            for(AIState aiState : nextStates){
                int newV = alphaBetaRun(aiState.instantiate(), depth - 1, alpha, beta, false);
                if(newV > v){
                    v = newV;
                    if(depth == MAX_DEPTH)nextColumnMove = aiState.lastMoveX;
                }
                alpha = Math.max(v, alpha);
                if(alpha >= beta) break;
            }
            return v;
        }
        else{
            int v = Short.MAX_VALUE;
            for(AIState aiState : nextStates){
                v = Math.min(v, alphaBetaRun(aiState.instantiate(), depth - 1, alpha, beta, true));
                beta = Math.min(v, beta);
                if(alpha >= beta) break;
            }
            return v;

        }

    }
    public int nextMove(AIState state){
        alphaBetaRun(state.instantiate(), MAX_DEPTH, Short.MIN_VALUE, Short.MAX_VALUE, true);

        return nextColumnMove;
    }

}
