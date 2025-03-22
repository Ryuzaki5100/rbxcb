package com.cube.demo;

import java.util.ArrayList;

public class CubeResponse {
    private String solution;
    private int numberOfMoves;

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public CubeResponse(ArrayList<String> moves) {
        this.solution = "";

        for (String move : moves)
            this.solution += move + " ";

        this.solution = this.solution.substring(0, this.solution.length() - 1);
        this.numberOfMoves = moves.size();
    }
}
