package com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.CFOP_Mask.OLL_Mask;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;

import java.util.ArrayList;

public class OLL_Solver extends Solver {

    private static final StageMasker[] masks = {
            new OLL_Mask()
    };

    private static final String[][] moveRestrictions = {
            {"R", "RR", "RRR", "U", "UU", "UUU", "F", "FF", "FFF", "L", "LL", "LLL", "B", "BB", "BBB", "D", "DD", "DDD"}
    };

    public ArrayList<String> solve(Cube c) {
        return super.solve(c, OLL_Solver.masks, OLL_Solver.moveRestrictions);
    }
}
