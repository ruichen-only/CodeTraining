package cache;

public class LFUBySmallHeap {
    final static int MAX_LEN = 100;
    private int queue[] = new int[MAX_LEN];
    private int size;

    public void add(int e) {
        if(size >= MAX_LEN) {
            System.err.println("over flow");
            return;
        }
        int s = size++;
        shiftUp(s, e);
    }

    public int size() {
        return size;
    }

    private void shiftUp(int s, int e) {
        while (s > 0) {
            int parent = (s - 1)/2;
            if (queue[parent] < e) {
                break;
            }
            queue[s] = queue[parent];
            s = parent;
        }
        queue[s] = e;
    }

    public int poll() {
        if(size <= 0) return -1;

        int ret = queue[0];
        int s = size - 1;
        shiftDown(0, queue[s]);
        queue[s] = 0;
        return ret;
    }

    private void shiftDown(int i, int e) {
        int half = size / 2;
        while (i < half) {
            int child = 2*i + 1;
            int right = child + 1;
            if(right < size && queue[child] > queue[right]) {
                child = right;
            }
            if(e < queue[child]) {
                break;
            }
            queue[i] = queue[child];
            i = child;
        }
        queue[i] = e;
    }
}
