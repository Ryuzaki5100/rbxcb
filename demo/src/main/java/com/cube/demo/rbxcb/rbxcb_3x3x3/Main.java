package com.cube.demo.rbxcb.rbxcb_3x3x3;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.Solver;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers._2StageSolver;

public class Main {
    public static void main(String[] args) {
        // ygywbrrgwrbwyooyyygrbgwwgbgoboorroowbybwyobggrywrgbowr
//        Cube c = new Cube("gbbbbbgbbywroooyoowoowwwwwrwyrrrryrryroyyywyoggggggbgb");
        Cube c = new Cube("ygywbrrgwrbwyooyyygrbgwwgbgoboorroowbybwyobggrywrgbowr");
        Solver s = new _2StageSolver();
        System.out.println(s.solve(c));

//        Scanner sc = new Scanner(System.in);
//        String colorInput;
//
//        while (true) {
//            colorInput = sc.nextLine();
//            Cube c = new Cube();
//            c = Cube.execute(c, colorInput);
//            ArrayList<String> sol = Cube.solveCube(c, 2);
//            System.out.println(sol + " - " + sol.size());
//        }
//        System.out.println(Cube.solveCube_4Stage(c));
//        c = Cube.execute(c,"BDDDFLLFFFFFFFFFRRRRRRRRRUUUUUUUUUBBBBBBRRR");
//        String sol1=c.solveStage(0,Cube.stagedMoveRestrictions_2Stage,Cube.maskStages_2Stage[0]);
//        System.out.println(Cube.getAlgorithm(sol1));
//        c = Cube.execute(c,sol1);
//        String sol = c.solveStage(1,Cube.stagedMoveRestrictions_2Stage,Cube.maskStages_2Stage[1]);
//        System.out.println(Cube.getAlgorithm(sol));
//        c = Cube.execute(c,sol);
//        System.out.println(c);
//        System.out.println(Cube.solveCube_2Stage(c));
//        String sol = c.solveStage(0, Cube.stagedMoveRestrictions_3Stage, Cube.maskStages_3Stage[0]);
//        System.out.println(Cube.getAlgorithm(sol));
//        System.out.println(Cube.maskStage1_3Stage(c));

    }
}