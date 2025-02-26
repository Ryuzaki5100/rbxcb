package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._4StageMask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.EdgeOrientation;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.EdgePos;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;

import java.util.ArrayList;

public class Stage1 implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        ArrayList<Integer> mask = new ArrayList<>();

        for (int i = 0; i < 12; i++)
            mask.add(0);

        EdgePos edgePos = c.getEdge().getEdgePos();
        EdgeOrientation edgeOrientation = c.getEdge().getEdgeOrientation();

        for (int i = 0; i < 12; i++) {
            int edgePiecePosition = edgePos.getVal()[i];
            int edgePieceOrientation = edgeOrientation.getVal()[i];

            if (edgePiecePosition < 4 || edgePiecePosition > 7) {
                if (Math.abs(edgePieceOrientation) == 3)
                    mask.set(edgePiecePosition, 1);
            } else {
                if (Math.abs(edgePieceOrientation) == 2)
                    mask.set(edgePiecePosition, 1);
            }
        }

        return mask;
    }
}
