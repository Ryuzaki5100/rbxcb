package com.cube.demo;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.*;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.CFOP_Solver.OLL_Solver;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.CFOP_Solver.PLL_Solver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CubeController {

    @GetMapping("/solve/rbxcb3x3/cube")
    public CubeResponse solveCube(@RequestParam String colorInput, @RequestParam int stages) {
        Cube c = new Cube(colorInput);
        Solver[] solver = {null, null, new _2StageSolver(), new _3StageSolver(), new _4StageSolver()};
        return new CubeResponse(solver[stages].solve(c));
    }

    @GetMapping("/solve/rbxcb3x3/CFOP/OLL")
    public CubeResponse solveOLL(@RequestParam String colorInput) {
        Cube c = new Cube(colorInput);
        Solver solver = new OLL_Solver();
        return new CubeResponse(solver.solve(c));
    }

    @GetMapping("/solve/rbxcb3x3/CFOP/PLL")
    public CubeResponse solvePLL(@RequestParam String colorInput) {
        Cube c = new Cube(colorInput);
        Solver solver = new PLL_Solver();
        return new CubeResponse(solver.solve(c));
    }


}

