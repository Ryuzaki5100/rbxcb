package com.cube.demo;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers.Solver;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers._2StageSolver;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers._3StageSolver;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Solvers._4StageSolver;
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
}

