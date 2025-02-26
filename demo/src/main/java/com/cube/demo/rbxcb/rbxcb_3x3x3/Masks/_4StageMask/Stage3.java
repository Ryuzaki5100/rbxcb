package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._4StageMask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;

import java.util.ArrayList;

public class Stage3 implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        ArrayList<Integer> mask = new ArrayList<>();
        ArrayList<Integer> parityEdges = new ArrayList<>();
        ArrayList<Integer> parityCorners = new ArrayList<>();
        ArrayList<Integer> parityCornerPos = new ArrayList<>();

        for (int i = 0; i < 12; i++)
            parityEdges.add(0);

        for (int i = 0; i < 8; i++) {
            parityCorners.add(0);
            parityCornerPos.add(0);
        }

        for (int i = 0; i < 12; i++)
            parityEdges.set(c.getEdge().getEdgePos().getVal()[i], Cube.edgePossiblePlacesStage3[c.getEdge().getEdgePos().getVal()[i]][i]);

        for (int i = 0; i < 8; i++) {
            parityCorners.set(c.getCorner().getCornerPos().getVal()[i], Cube.cornerPossiblePlacesStage3[c.getCorner().getCornerPos().getVal()[i]][i]);
            parityCornerPos.set(c.getCorner().getCornerPos().getVal()[i], i / 4);
        }

        for (int i = 0; i < 4; i++)
            mask.add(parityEdges.get(i));

        for (int i = 8; i < 12; i++)
            mask.add(parityEdges.get(i));

        mask.addAll(parityCorners);

        mask.addAll(parityCornerPos);

        for (byte i : c.getCorner().getCornerPos().getVal())
            mask.add((int) i);

        return mask;
    }
}
