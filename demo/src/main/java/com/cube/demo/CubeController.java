package com.cube.demo;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CubeController {

    @GetMapping("/solveCube")
    public CubeResponse solveCube(@RequestParam String colorInput, @RequestParam int stages) {
        Cube c = new Cube(colorInput);
        Solver solver[] = {null, null, new _2StageSolver(), new _3StageSolver(), new _4StageSolver()};
        return new CubeResponse(solver[stages].solve(c));
    }

    @GetMapping("/solve/CFOP/OLL")
    public CubeResponse solveOLL(@RequestParam String colorInput){
        Cube c = new Cube(colorInput);
        Solver solver = new OLL_Solver();
        return new CubeResponse(solver.solve(c));
    }

    @GetMapping("/solve/CFOP/PLL")
    public CubeResponse solvePLL(@RequestParam String colorInput){
        Cube c = new Cube(colorInput);
        Solver solver = new PLL_Solver();
        return new CubeResponse(solver.solve(c));
    }


}

