
public class IntStatistics {

    private int count = 0;
    private long min = Long.MAX_VALUE;
    private long max = -Long.MAX_VALUE;
    private long sum = 0;

    public void add(long value) {
        count++;
        if (value < min) {
            min = value;
        }
        if (value > max) {
            max = value;
        }
        sum += value;
    }

    public int getCount() {
        return count;
    }

    public long getMin() {
        return count > 0 ? min : 0;
    }

    public long getMax() {
        return count > 0 ? max : 0;
    }

    public double getAverage() {
        return count > 0 ? (double) sum / count : 0.0;
    }

    public long getSum() {
        return count > 0 ? sum : 0;
    }

}
