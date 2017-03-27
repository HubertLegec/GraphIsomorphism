package com.pw.eiti;


public class Edge {
    private String v1;
    private String v2;

    public Edge(String v1, String v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) obj;
        return v1.equals(other.v1) && v2.equals(other.v2);
    }

    @Override
    public int hashCode() {
        return v1.hashCode() + v2.hashCode();
    }

    @Override
    public String toString() {
        return "Edge{ v1='" + v1 + "', v2='" + v2 + "' }";
    }
}
