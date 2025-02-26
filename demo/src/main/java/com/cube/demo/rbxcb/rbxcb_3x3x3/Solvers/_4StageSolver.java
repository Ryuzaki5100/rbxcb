package com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._4StageMask.*;

import java.util.ArrayList;

public class _4StageSolver extends Solver {

    private static final StageMasker[] masks = {
            new Stage1(),
            new Stage2(),
            new Stage3(),
            new Stage4()
    };

    private static final String[][] moveRestrictions = {
            {"U", "UU", "UUU", "R", "RR", "RRR", "F", "FF", "FFF", "B", "BB", "BBB", "L", "LL", "LLL", "D", "DD", "DDD"},
            {"U", "UU", "UUU", "R", "RR", "RRR", "FF", "BB", "L", "LL", "LLL", "D", "DD", "DDD"},
            {"U", "UU", "UUU", "RR", "FF", "BB", "LL", "D", "DD", "DDD"},
            {"UU", "RR", "FF", "BB", "LL", "DD"}
    };

    public ArrayList<String> solve(Cube c) {
        return super.solve(c, _4StageSolver.masks, _4StageSolver.moveRestrictions);
    }
}
