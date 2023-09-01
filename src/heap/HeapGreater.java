package src.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 *
 * @param <T>
 */
public class HeapGreater<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<T> c) {
        this.comp = c;
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public List<T> getAllElements(){
        List<T> ans = new ArrayList<>();
        for(T temp : heap){
            ans.add(temp);
        }
        return ans;
    }

    /**
     * 插进去之后做一次heapInsert
     * @param obj
     */
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * 1.heap.get(0)直接返回
     * 2.indexmap直接remove
     * 3.把0位置和heapSize交换，，heap.remove heapSize--位置
     * 4.0位置做一下heapify
     * @return
     */
    public T pop() {
        T ans = heap.get(0);
        indexMap.remove(ans);
        swap(0, heapSize - 1);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    /**
     * 1.记录下来obj的index位置
     * 2.干掉heapSize-1位置的元素
     * 3.判断obj是否是刚刚被干掉的元素，不是的话，把replace的值弄回去index位置，resign一下
     * @param obj
     */
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }


    /**
     * heapinsert再heapify，以保证形成堆
     * @param obj
     */
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    public void heapInsert(int index) {
        while (comp.compare(heap.get((index - 1) / 2), heap.get(index)) < 0) {
            swap((index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int heapIndex = heapSize - 1;
        while (left <= heapIndex) {
            int best = right <= heapIndex && comp.compare(heap.get(left), heap.get(right)) < 0 ? right : left;
            int target = comp.compare(heap.get(best), heap.get(index)) < 0 ? index : best;
            if (target != index) {
                swap(target, index);
                index = target;
                left = 2 * index + 1;
                right = 2 * index + 2;
            }
            break;
        }
    }


    /**
     * indexMap和heap各更新2次
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
        heap.set(i, o2);
        heap.set(j, o1);
    }

}
