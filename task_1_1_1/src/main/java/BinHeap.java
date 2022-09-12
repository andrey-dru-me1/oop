import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinHeap {

    List<Integer> binHeap;

    public BinHeap() {
        binHeap = new ArrayList<>();
    }

    public Integer get(int i) {
        return binHeap.get(i);
    }

    public Integer size() {
        return binHeap.size();
    }

    private void siftUp(int i) {
        for (int j = i; j > 0 && this.get(j) < this.get((j - 1) / 2); j = (j - 1) / 2) {
            Collections.swap(binHeap, j, (j - 1) / 2);
        }
    }

    private void siftDown() {
        Collections.swap(binHeap, 0, binHeap.size() - 1); //swaps root and the last element
        binHeap.remove(binHeap.size() - 1);            //removes the last elem which used to be root

        //calculates the minimum for sons of the root
        int min;                                                                //creates helper variable

        //Sift-down algorithm itself
        for (int j = 0; j * 2 + 1 < binHeap.size(); j = min) {

            if (j * 2 + 2 >= binHeap.size()) min = j * 2 + 1;                                //if there is only one son
            else
                min = (binHeap.get(j * 2 + 1) < binHeap.get(j * 2 + 2)) ? j * 2 + 1 : j * 2 + 2;   //if there are both of sons

            if (binHeap.get(j) <= binHeap.get(min)) break;   //stops iterations when the value gets its position

            Collections.swap(binHeap, j, min);  //swaps current elem which used to be the last with one of his sons

        }
    }

    public void add(int value) {
        binHeap.add(value);
        siftUp(binHeap.size() - 1);
    }

    public int extractMin() {
        int result = binHeap.get(0);
        this.siftDown();
        return result;
    }

}
