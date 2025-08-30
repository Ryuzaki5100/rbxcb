package com.cube.demo.rbxcb.rbxcb_4x4x4.Model;

public class Edge implements Cloneable {
    private EdgePos edgePos;
    private EdgeOrientation edgeOrientation;

    @Override
    public Edge clone() {
        Edge edge;
        try{
            edge = (Edge) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        edge.setEdgePos(this.getEdgePos().clone());
        edge.setEdgeOrientation(this.getEdgeOrientation().clone());
        return edge;
    }

    public Edge() {
        this.edgePos = new EdgePos();
        this.edgeOrientation = new EdgeOrientation();
    }

    public Edge(Edge e) {
        this.setEdgePos(new EdgePos(e.getEdgePos()));
        this.setEdgeOrientation(new EdgeOrientation(e.getEdgeOrientation()));
    }

    public Edge(EdgePos edgePos, EdgeOrientation edgeOrientation) {
        this.edgePos = edgePos;
        this.edgeOrientation = edgeOrientation;
    }

    @Override
    public String toString() {
        return "Edge{\n" +
                "edgePos=" + edgePos.toString() +
                ",\nedgeOrientation=" + edgeOrientation.toString() +
                "\n}";
    }

    public EdgePos getEdgePos() {
        return edgePos;
    }

    public EdgeOrientation getEdgeOrientation() {
        return edgeOrientation;
    }

    public void setEdgeOrientation(EdgeOrientation edgeOrientation) {
        this.edgeOrientation = edgeOrientation;
    }

    public void setEdgePos(EdgePos edgePos) {
        this.edgePos = edgePos;
    }
}
