package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

import java.util.List;
import java.util.Map;

public class Cube implements Cloneable {

    private static final Map<Character, EdgePos> nextEdgePos = Map.ofEntries(
            Map.entry('R', new EdgePos(new int[]{0, 1, 13, 9, 4, 5, 6, 7, 8, 18, 2, 11, 12, 19, 3, 15, 16, 17, 14, 10, 20, 21, 22, 23})),
            Map.entry('U', new EdgePos(new int[]{2, 3, 4, 5, 6, 7, 0, 1, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})),
            Map.entry('F', new EdgePos(new int[]{0, 1, 2, 3, 14, 10, 6, 7, 8, 9, 20, 4, 12, 13, 21, 5, 16, 17, 18, 19, 15, 11, 22, 23})),
            Map.entry('B', new EdgePos(new int[]{12, 8, 2, 3, 4, 5, 6, 7, 16, 0, 10, 11, 17, 1, 14, 15, 13, 9, 18, 19, 20, 21, 22, 23})),
            Map.entry('L', new EdgePos(new int[]{0, 1, 2, 3, 4, 5, 15, 11, 6, 9, 10, 22, 7, 13, 14, 23, 16, 17, 18, 19, 20, 21, 12, 8})),
            Map.entry('D', new EdgePos(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 22, 23, 16, 17, 18, 19, 20, 21})),
            Map.entry('r', new EdgePos(new int[]{0, 17, 2, 3, 1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20, 18, 19, 4, 21, 22, 23})),
            Map.entry('u', new EdgePos(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 8, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})),
            Map.entry('f', new EdgePos(new int[]{0, 1, 2, 19, 4, 5, 3, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 22, 20, 21, 6, 23})),
            Map.entry('b', new EdgePos(new int[]{0, 1, 7, 3, 4, 5, 6, 23, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 2, 19, 20, 21, 22, 18})),
            Map.entry('l', new EdgePos(new int[]{5, 1, 2, 3, 4, 21, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0, 17, 18, 19, 20, 16, 22, 23})),
            Map.entry('d', new EdgePos(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 15, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23}))
    );

    private static final Map<Character, CornerPos> nextCornerPos = Map.ofEntries(
            Map.entry('R', new CornerPos(new int[]{0, 5, 1, 3, 4, 6, 2, 7})),
            Map.entry('U', new CornerPos(new int[]{1, 2, 3, 0, 4, 5, 6, 7})),
            Map.entry('F', new CornerPos(new int[]{0, 1, 6, 2, 4, 5, 7, 3})),
            Map.entry('B', new CornerPos(new int[]{4, 0, 2, 3, 5, 1, 6, 7})),
            Map.entry('L', new CornerPos(new int[]{3, 1, 2, 7, 0, 5, 6, 4})),
            Map.entry('D', new CornerPos(new int[]{0, 1, 2, 3, 7, 4, 5, 6})),
            Map.entry('r', new CornerPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7})),
            Map.entry('u', new CornerPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7})),
            Map.entry('f', new CornerPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7})),
            Map.entry('b', new CornerPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7})),
            Map.entry('l', new CornerPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7})),
            Map.entry('d', new CornerPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7}))
    );

    private static final Map<Character, CenterPos> nextCenterPos = Map.ofEntries(
            Map.entry('R', new CenterPos(new int[]{0, 1, 2, 3, 4, 5, 14, 6, 8, 9, 10, 11, 12, 13, 15, 7, 16, 17, 18, 19, 20, 21, 22, 23})),
            Map.entry('U', new CenterPos(new int[]{1, 2, 3, 0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})),
            Map.entry('F', new CenterPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 16, 8, 10, 11, 12, 13, 14, 15, 17, 9, 18, 19, 20, 21, 22, 23})),
            Map.entry('B', new CenterPos(new int[]{0, 1, 2, 3, 12, 4, 6, 7, 8, 9, 10, 11, 13, 5, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})),
            Map.entry('L', new CenterPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 18, 10, 12, 13, 14, 15, 16, 17, 19, 11, 20, 21, 22, 23})),
            Map.entry('D', new CenterPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 23, 20, 21, 22})),
            Map.entry('r', new CenterPos(new int[]{0, 13, 5, 3, 4, 21, 6, 7, 1, 9, 10, 11, 12, 22, 14, 15, 2, 17, 18, 19, 20, 16, 8, 23})),
            Map.entry('u', new CenterPos(new int[]{0, 1, 2, 3, 6, 7, 8, 9, 10, 11, 4, 5, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})),
            Map.entry('f', new CenterPos(new int[]{0, 1, 15, 7, 4, 5, 6, 22, 8, 9, 2, 11, 12, 13, 14, 23, 16, 17, 3, 19, 20, 21, 18, 10})),
            Map.entry('b', new CenterPos(new int[]{19, 11, 2, 3, 4, 5, 0, 7, 8, 9, 10, 20, 12, 13, 1, 15, 16, 17, 18, 21, 14, 6, 22, 23})),
            Map.entry('l', new CenterPos(new int[]{9, 1, 2, 17, 3, 5, 6, 7, 8, 23, 10, 11, 0, 13, 14, 15, 16, 20, 18, 19, 4, 21, 22, 12})),
            Map.entry('d', new CenterPos(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 18, 19, 12, 13, 14, 15, 16, 17, 20, 21, 22, 23}))
    );

    private static final Map<Character, List<Map<Integer, Integer>>> nextEdgeOrientation = Map.of(
            'R', List.of(
                    Map.of(3, 3, 2, 2),
                    Map.of(3, 3, 2, 2),
                    Map.of(3, 2, 1, 1),
                    Map.of(3, 2, 1, 1),
                    Map.of(3, 3, -2, -2),
                    Map.of(3, 3, -2, -2),
                    Map.of(3, 3, -1, -1),
                    Map.of(3, 3, -1, -1),
                    Map.of(2, 2, -1, -1),
                    Map.of(2, -3, 1, 1),
                    Map.of(-2, 3, 1, 1),
                    Map.of(-2, -2, -1, -1),
                    Map.of(2, 2, -1, -1),
                    Map.of(2, -3, 1, 1),
                    Map.of(-2, 3, 1, 1),
                    Map.of(-2, -2, -1, -1),
                    Map.of(2, 2, -3, -3),
                    Map.of(2, 2, -3, -3),
                    Map.of(1, 1, -3, -2),
                    Map.of(1, 1, -3, -2),
                    Map.of(-3, -3, -2, -2),
                    Map.of(-3, -3, -2, -2),
                    Map.of(-3, -3, -1, -1),
                    Map.of(-3, -3, -1, -1)
            ),
            'U', List.of(
                    Map.of(3, 3, 2, 1),
                    Map.of(3, 3, 2, 1),
                    Map.of(3, 3, 1, -2),
                    Map.of(3, 3, 1, -2),
                    Map.of(3, 3, -2, -1),
                    Map.of(3, 3, -2, -1),
                    Map.of(3, 3, -1, 2),
                    Map.of(3, 3, -1, 2),
                    Map.of(2, 2, -1, -1),
                    Map.of(2, 2, 1, 1),
                    Map.of(-2, -2, 1, 1),
                    Map.of(-2, -2, -1, -1),
                    Map.of(2, 2, -1, -1),
                    Map.of(2, 2, 1, 1),
                    Map.of(-2, -2, 1, 1),
                    Map.of(-2, -2, -1, -1),
                    Map.of(2, 2, -3, -3),
                    Map.of(2, 2, -3, -3),
                    Map.of(1, 1, -3, -3),
                    Map.of(1, 1, -3, -3),
                    Map.of(-3, -3, -2, -2),
                    Map.of(-3, -3, -2, -2),
                    Map.of(-3, -3, -1, -1),
                    Map.of(-3, -3, -1, -1)
            ),
            'F', List.of(

            )



    );


}

