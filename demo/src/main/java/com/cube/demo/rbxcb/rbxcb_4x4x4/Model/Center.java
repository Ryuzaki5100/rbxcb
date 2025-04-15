package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

public class Center implements Cloneable {
    private CenterPos CenterPos;
    private CenterOrientation CenterOrientation;

    @Override
    public Center clone() {
        return new Center(this.getCenterPos().clone(), this.getCenterOrientation().clone());
    }

    public Center() {
        this.CenterPos = new CenterPos();
        this.CenterOrientation = new CenterOrientation();
    }

    public Center(Center e) {
        this.setCenterPos(new CenterPos(e.getCenterPos()));
        this.setCenterOrientation(new CenterOrientation(e.getCenterOrientation()));
    }

    public Center(CenterPos CenterPos, CenterOrientation CenterOrientation) {
        this.CenterPos = CenterPos;
        this.CenterOrientation = CenterOrientation;
    }

    @Override
    public String toString() {
        return "Center{\n" +
                "CenterPos=" + CenterPos.toString() +
                ",\nCenterOrientation=" + CenterOrientation.toString() +
                "\n}";
    }

    public CenterPos getCenterPos() {
        return CenterPos;
    }

    public CenterOrientation getCenterOrientation() {
        return CenterOrientation;
    }

    public void setCenterOrientation(CenterOrientation CenterOrientation) {
        this.CenterOrientation = CenterOrientation;
    }

    public void setCenterPos(CenterPos CenterPos) {
        this.CenterPos = CenterPos;
    }
}
