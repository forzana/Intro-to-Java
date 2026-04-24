package partI;

public final class WindowStats {
    public final double[] avg;
    public final double[] min;
    public final double[] max;

    public WindowStats(double[] avg, double[] min, double[] max) {
        this.avg = avg;
        this.min = min;
        this.max = max;
    }
}