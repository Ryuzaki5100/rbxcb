package com.cube.demo;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Cube;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CubeController {

    @GetMapping("/solveCube")
    public String solveCube(@RequestParam String colorInput, @RequestParam int stages) {
        return null;
    }
}

// L2 U2 B2 R2 F2 R' F2 L' B2 R F2 B D B2 F' L2 U' R2 F' U
// wbobbwobbbwwgooyrobyrgwyyyryogorygbywrrrywgwgbgwggoorr
