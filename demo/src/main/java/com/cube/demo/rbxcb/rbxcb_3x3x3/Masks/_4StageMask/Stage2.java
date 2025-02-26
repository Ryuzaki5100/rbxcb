package com.cube.demo.rbxcb.rbxcb_3x3x3.Masks._4StageMask;

import com.cube.demo.rbxcb.rbxcb_3x3x3.Model.Cube;
import com.cube.demo.rbxcb.rbxcb_3x3x3.Masks.StageMasker;

import java.util.ArrayList;

public class Stage2 implements StageMasker {
    public ArrayList<Integer> mask(Cube c) {
        ArrayList<Integer> mask = new ArrayList<>();
        ArrayList<Integer> cornerMask = new ArrayList<>();
        ArrayList<Integer> edgeMask = new ArrayList<>();

        for (int i = 0; i < 8; i++)
            cornerMask.add(0);

        for (int i = 0; i < 12; i++)
            edgeMask.add(0);

        for (int i = 0; i < 8; i++) {
            int cornerPiecePosition = c.getCorner().getCornerPos().getVal()[i];
            int cornerPieceOrientation = c.getCorner().getCornerOrientation().getVal()[i];

            cornerMask.set(cornerPiecePosition, cornerPieceOrientation);
        }

        for (int i = 0; i < 12; i++) {
            int edgePiecePosition = c.getEdge().getEdgePos().getVal()[i];

            boolean positionGroup = edgePiecePosition < 4 || edgePiecePosition > 7;
            boolean pieceGroup = i < 4 || i > 7;

            edgeMask.set(edgePiecePosition, positionGroup != pieceGroup ? 1 : 0);
        }

        mask.addAll(cornerMask);

        mask.addAll(edgeMask);

        return mask;
    }
}
