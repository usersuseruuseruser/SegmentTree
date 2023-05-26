package test;

import java.util.Arrays;

class SegmentTree {

    private int[] tree;
    private int n;
    private int[] arr;
    public long operations;

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public SegmentTree(int[] arr) {
        // The max size of segment tree is about 2*n - 1.
        this.arr = Arrays.copyOf(arr, arr.length);
        int x = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, x) - 1;
        tree = new int[maxSize];
        n = arr.length;
        build(arr, 0, n - 1, 0);
    }

    private void build(int[] arr, int start, int end, int treeIndex) {
        operations++;
        if (start == end) {
            tree[treeIndex] = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        build(arr, start, mid, 2 * treeIndex + 1);
        build(arr, mid + 1, end, 2 * treeIndex + 2);
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    public void update(int index, int value) {
        updateUtil(0, 0, n - 1, index, value);
    }

    private void updateUtil(int treeIndex, int start, int end, int index, int value) {
        operations++;
        if (start == end) {
            tree[treeIndex] = value;
            return;
        }
        int mid = start + (end - start) / 2;
        if (index <= mid) {
            updateUtil(2 * treeIndex + 1, start, mid, index, value);
        } else {
            updateUtil(2 * treeIndex + 2, mid + 1, end, index, value);
        }
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    public int query(int left, int right) {
        return queryUtil(0, 0, n - 1, left, right);
    }

    private int queryUtil(int treeIndex, int start, int end, int left, int right) {
        operations++;
        if (left <= start && right >= end) {
            return tree[treeIndex];
        }
        if (end < left || start > right) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        return queryUtil(2 * treeIndex + 1, start, mid, left, right) + queryUtil(2 * treeIndex + 2, mid + 1, end, left, right);
    }
    public void delete(int index) {
        operations++;
        int[] newArray = new int[n - 1];
        for (int i = 0, j = 0; i < n; i++) {
            if (i == index) continue;
            newArray[j++] = arr[i];
        }
        this.n = n - 1;
        int x = (int) Math.ceil(Math.log(newArray.length) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, x) - 1;
        this.tree = new int[maxSize];
        this.arr = newArray;
        build(newArray, 0, n - 1, 0);
    }
    public void insert(int index, int value) {
        operations++;
        int[] newArray = new int[n + 1];
        for (int i = 0, j = 0; i < n; i++, j++) {
            if (i == index) newArray[j++] = value;
            newArray[j] = arr[i];
        }
        if (index == n) newArray[n] = value;
        this.arr = newArray;
        this.n = newArray.length;
        int x = (int) Math.ceil(Math.log(n) / Math.log(2));
        int maxSize = 2 * (int) Math.pow(2, x) - 1;
        tree = new int[maxSize];
        build(newArray, 0, n - 1, 0);
    }
    public int findElem(int elem){
        operations++;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == elem){
                return i;
            }
        }
        return  -1;
    }
    @Override
    public String toString() {
        return "SegmentTree{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }
}