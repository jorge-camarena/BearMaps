package bearmaps;


import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private class PriorityNode {
        private T item;
        private double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
        T getItem() {
            return this.item;
        }

        double getPriority() {
            return this.priority;
        }

        void setPriority(double otherPriority) {
            this.priority = otherPriority;
        }

    }

    private ArrayList<PriorityNode> minHeap;
    private int size;
    private HashSet<T> minHeapSet;
    private HashMap<T, Integer> hashIndex;


    public ArrayHeapMinPQ() {
        this.minHeap = new ArrayList<PriorityNode>();
        this.minHeapSet = new HashSet();
        this.hashIndex = new HashMap();
        this.minHeap.add(null);

    }

    private int parent(int k) {
        return Math.floorDiv(k, 2);
    }

    private int leftChild(int k) {
        return 2 * k;
    }

    private int rightChild(int k) {
        return 2 * k + 1;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private void swimUp(int k) {
        if (k > 1) {
            if (this.minHeap.get(k).priority < this.minHeap.get(parent(k)).priority) {
                PriorityNode childNode = this.minHeap.get(k);
                PriorityNode parentNode = this.minHeap.get(parent(k));
                this.minHeap.set(k, parentNode);
                this.minHeap.set(parent(k), childNode);
                this.hashIndex.replace(parentNode.item, k);
                this.hashIndex.replace(childNode.item, parent(k));
                swimUp(parent(k));
            }
        }
    }

    private void sinkDown(int k) {
        if (rightChild(k) <= this.size) {
            PriorityNode leftChild = this.minHeap.get(leftChild(k));
            PriorityNode rightChild = this.minHeap.get(rightChild(k));
            PriorityNode parent = this.minHeap.get(k);

            if (parent.priority > leftChild.priority || parent.priority > rightChild.priority) {
                if (rightChild.priority >= leftChild.priority) {
                    this.minHeap.set(k, leftChild);
                    this.minHeap.set(leftChild(k), parent);
                    this.hashIndex.replace(leftChild.item, k);
                    this.hashIndex.replace(parent.item, leftChild(k));
                    sinkDown(leftChild(k));
                } else {
                    this.minHeap.set(k, rightChild);
                    this.minHeap.set(rightChild(k), parent);
                    this.hashIndex.replace(rightChild.item, k);
                    this.hashIndex.replace(parent.item, rightChild(k));
                    sinkDown(rightChild(k));
                }
            }
        } else if (leftChild(k) <= this.size) {
            PriorityNode leftChild = this.minHeap.get(leftChild(k));
            PriorityNode parent = this.minHeap.get(k);
            if (parent.priority > leftChild.priority) {
                this.minHeap.set(k, leftChild);
                this.minHeap.set(leftChild(k), parent);
                this.hashIndex.replace(leftChild.item, k);
                this.hashIndex.replace(parent.item, leftChild(k));
                sinkDown(leftChild(k));
            }
        }


    }

    @Override
    public void add(T item, double priority) {
        if (!this.isEmpty() && this.contains(item)) {
            throw new IllegalArgumentException();
        }
        PriorityNode toAdd = new PriorityNode(item, priority);
        int positionToPlace = this.size + 1;
        this.minHeap.add(toAdd);
        this.hashIndex.put(item, positionToPlace);
        this.swimUp(positionToPlace);
        this.size++;
        this.minHeapSet.add(item);
    }

    @Override
    public boolean contains(T item) {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.minHeapSet.contains(item);
    }

    @Override
    public T getSmallest() {
        return this.minHeap.get(1).item;
    }

    @Override
    public T removeSmallest() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        T smallest = this.getSmallest();
        PriorityNode lastNode = this.minHeap.get(this.size);
        this.minHeap.set(1, lastNode);
        this.minHeap.remove(this.size);
        this.size--;
        this.hashIndex.remove(smallest);
        this.sinkDown(1);
        this.minHeapSet.remove(smallest);
        return smallest;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!this.contains(item)) {
            throw new NoSuchElementException();
        }
        int indexOfItem = this.hashIndex.get(item);
        PriorityNode nodeToChange = this.minHeap.get(indexOfItem);
        nodeToChange.setPriority(priority);
        PriorityNode parentNode = minHeap.get(parent(indexOfItem));
        if (nodeToChange.priority < parentNode.priority) {
            swimUp(indexOfItem);
        } else {
            sinkDown(indexOfItem);
        }

    }
}
