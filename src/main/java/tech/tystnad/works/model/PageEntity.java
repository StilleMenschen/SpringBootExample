package tech.tystnad.works.model;

public class PageEntity {
    private Integer current;
    private Integer range;
    private Integer max;
    private Integer size;
    private Integer pos = 0;

    public PageEntity() {
    }

    public PageEntity(Integer current, Integer range) {
        this.current = current;
        this.range = range;
        getPos();
    }

    public PageEntity(Integer current, Integer range, Integer size) {
        this.current = current;
        this.range = range;
        this.size = size;
        getPos();
        calculationPage();
    }

    private void calculationPage() {
        max = size / range;
        if (size % range > 0) {
            max++;
        }
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
        calculationPage();
    }

    public Integer getPos() {
        if (current > 1) {
            pos = (current - 1) * range;
        }
        return pos;
    }
}
