package org.acme;

public class PageRequest {

    private int offset;

    private int elCount;

    public PageRequest() {
    }

    public PageRequest(int offset, int elCount) {
        this.offset = offset;
        this.elCount = elCount;
    }

    public int getOffset() {
        return offset;
    }

    public int getElCount() {
        return elCount;
    }

    public void setElCount(int elCount) {
        this.elCount = elCount;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
