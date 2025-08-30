package com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.CFOP_Solver;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.CFOP_Mask.OLL_Mask;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.Solver;

import java.util.ArrayList;

public class OLL_Solver extends Solver {

    private static final StageMasker[] masks = {
            new OLL_Mask()
    };

    private static final String[][] moveRestrictions = {
            {"R", "RR", "RRR", "U", "UU", "UUU", "F", "FF", "FFF", "B", "BB", "BBB", "L", "LL", "LLL"}
    };

    public ArrayList<String> solve(Cube c) {
        return super.solve(c, OLL_Solver.masks, OLL_Solver.moveRestrictions);
    }
}
