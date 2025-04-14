package com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.CFOP_Mask.PLL_Mask;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;

import java.util.ArrayList;

public class PLL_Solver extends Solver {
    Solver s = new _2StageSolver();

    private static final StageMasker[] masks = {
            new PLL_Mask()
    };

    private static final String[][] moveRestrictions = {
            {"U", "UU", "UUU", "R", "RR", "RRR", "F", "FF", "FFF", "B", "BB", "BBB", "L", "LL", "LLL", "D", "DD", "DDD"}
    };

    public ArrayList<String> solve(Cube c) {
        return s.solve(c);
    }



}
