package tech.tystnad.works.model;

public class PageEntity {
    private int current;
    private int range;
    private int max;
    private int size;

    public PageEntity() {
    }

    public PageEntity(int current, int range, int size) {
        this.current = current;
        this.range = range;
        this.size = size;
        calculationPage();
    }

    private void calculationPage() {
        max = size / range;
        if (size % range > 0) {
            max++;
        }
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
