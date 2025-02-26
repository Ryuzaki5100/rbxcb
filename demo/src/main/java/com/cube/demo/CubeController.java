package com.cube.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CubeController {

    @GetMapping("/solveCube")
    public String solveCube(@RequestParam String colorInput, @RequestParam int stages) {
        return null;
    }
}

