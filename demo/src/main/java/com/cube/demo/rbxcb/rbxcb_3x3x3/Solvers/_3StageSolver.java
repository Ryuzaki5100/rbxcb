package com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._3StageMask.*;

import java.util.ArrayList;

public class _3StageSolver extends Solver {

    private static final StageMasker[] masks = {
            new Stage1(),
            new Stage2(),
            new Stage3()
    };

    private static final String[][] moveRestrictions = {
            {"U", "UU", "UUU", "R", "RR", "RRR", "F", "FF", "FFF", "B", "BB", "BBB", "L", "LL", "LLL", "D", "DD", "DDD"},
            {"U", "UU", "UUU", "RR", "FF", "BB", "LL", "D", "DD", "DDD"},
            {"UU", "RR", "FF", "BB", "LL", "DD"}
    };

    public ArrayList<String> solve(Cube c) {
        return super.solve(c, _3StageSolver.masks, _3StageSolver.moveRestrictions);
    }
}
