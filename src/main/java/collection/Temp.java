package collection;

public class Temp implements Comparable<Temp> {
    private String name;
    private int score;

    public Temp(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Temp o) {
        if(o.score < this.score) {
            return 1;
        } else if(o.score > this.score) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {
    }
}
