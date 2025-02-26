package com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.StageSolver;

import java.util.ArrayList;

public abstract class Solver {

    public ArrayList<String> solve(Cube c, StageMasker[] masks, String[][] moveRestrictions) {
        StringBuilder solution = new StringBuilder();

        int n = masks.length;

        for (int i = 0; i < n; i++) {
            String sol = StageSolver.solve(c, moveRestrictions[i], masks[i]);
            c = Cube.execute(c, sol);
            solution.append(sol);
        }

        return Cube.getAlgorithm(solution.toString());
    }

    public abstract ArrayList<String> solve(Cube c);

}
