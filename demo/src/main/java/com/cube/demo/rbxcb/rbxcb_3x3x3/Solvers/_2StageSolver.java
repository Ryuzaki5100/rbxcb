package com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._2StageMask.*;

import java.util.ArrayList;

public class _2StageSolver extends Solver {

    private static final StageMasker[] masks = {
            new Stage1(),
            new Stage2()
    };

    private static final String[][] moveRestrictions = {
            {"R", "RR", "RRR", "U", "UU", "UUU", "F", "FF", "FFF", "L", "LL", "LLL", "B", "BB", "BBB", "D", "DD", "DDD"},
            {"U", "UU", "UUU", "D", "DD", "DDD", "RR", "FF", "LL", "BB"}
    };

    public ArrayList<String> solve(Cube c) {
        return super.solve(c, _2StageSolver.masks, _2StageSolver.moveRestrictions);
    }
}
