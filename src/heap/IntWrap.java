package src.heap;

import java.util.Comparator;

public class IntWrap {
    int num;

    public IntWrap(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        IntWrap num1 = new IntWrap(1);
        IntWrap num2 = new IntWrap(2);
        IntWrap num3 = new IntWrap(3);
        HeapGreater<IntWrap> heapGreater = new HeapGreater(new Comparator<IntWrap>() {
            @Override
            public int compare(IntWrap o1, IntWrap o2) {
                return o1.num - o2.num;
            }
        });

        heapGreater.push(num1);
        heapGreater.push(num2);
        heapGreater.push(num3);

    }
}
