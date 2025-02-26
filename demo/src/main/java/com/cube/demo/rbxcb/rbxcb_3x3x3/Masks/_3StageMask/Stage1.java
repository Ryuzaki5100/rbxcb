package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._3StageMask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Stage1 implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        ArrayList<ArrayList<Integer>> edgeMask = new ArrayList<>();
        ArrayList<Integer> cornerMask = new ArrayList<>();
        ArrayList<Integer> mask = new ArrayList<>();

        EdgePos edgePos = c.getEdge().getEdgePos();
        EdgeOrientation edgeOrientation = c.getEdge().getEdgeOrientation();

        CornerPos cornerPos = c.getCorner().getCornerPos();
        CornerOrientation cornerOrientation = c.getCorner().getCornerOrientation();

        for (int i = 0; i < 12; i++)
            edgeMask.add(new ArrayList<>(Arrays.asList(0, 0)));

        for (int i = 0; i < 8; i++)
            cornerMask.add(0);

        for (int i = 0; i < 4; i++) {
            edgeMask.get(edgePos.getVal()[i]).set(0, 0);
            edgeMask.get(edgePos.getVal()[i]).set(1, (int) edgeOrientation.getVal()[i]);
        }

        for (int i = 4; i < 8; i++) {
            edgeMask.get(edgePos.getVal()[i]).set(0, 1);
            edgeMask.get(edgePos.getVal()[i]).set(1, (int) edgeOrientation.getVal()[i]);
        }

        for (int i = 8; i < 12; i++) {
            edgeMask.get(edgePos.getVal()[i]).set(0, 0);
            edgeMask.get(edgePos.getVal()[i]).set(1, (int) edgeOrientation.getVal()[i]);
        }

        for (int i = 0; i < 8; i++)
            cornerMask.set(cornerPos.getVal()[i], (int) cornerOrientation.getVal()[i]);

        for (ArrayList<Integer> i : edgeMask) {
            mask.add(i.get(0));
            mask.add(i.get(1));
        }

        mask.addAll(cornerMask);

        return mask;
    }
}
