package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.CFOP_Mask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.*;

import java.util.ArrayList;

public class OLL_Mask implements StageMasker {

    public ArrayList<Integer> mask(Cube c) {
        ArrayList<Integer> edgeMask = new ArrayList<>();
        ArrayList<Integer> cornerMask = new ArrayList<>();
        ArrayList<Integer> mask = new ArrayList<>();

        EdgePos edgePos = c.getEdge().getEdgePos();
        EdgeOrientation edgeOrientation = c.getEdge().getEdgeOrientation();

        CornerPos cornerPos = c.getCorner().getCornerPos();
        CornerOrientation cornerOrientation = c.getCorner().getCornerOrientation();

        // Edge Positions Masking :
        for (int i = 4; i < 12; i++)
            edgeMask.add((int) edgePos.getVal()[i]);

        // Edge Orientation Masking :
        for (int i = 0; i < 12; i++)
            edgeMask.add((int) edgeOrientation.getVal()[i]);

        // Corner Position Masking :
        for (int i = 4; i < 8; i++)
            cornerMask.add((int) cornerPos.getVal()[i]);

        // Corner Orientation Masking :
        for (int i = 0; i < 8; i++)
            cornerMask.add((int) cornerOrientation.getVal()[i]);

        mask.addAll(edgeMask);
        mask.addAll(cornerMask);

        return mask;
    }
}
