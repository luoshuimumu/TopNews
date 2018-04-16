package com.example.luoshuimumu.TopNews;

import org.junit.Test;

/**
 * Created by luoshuimumu on 2018/1/17.
 */

public class SortUtil {
    @Test
    public void main() {
        SortUtil util = new SortUtil();
        Integer[] a = {3, 2, 5, 1, 0, 4, 4, 4, 4, 6};
//        util.quickSort(a, 0, 9);
        int[] arr = {3, 2, 5, 1, 0, 4, 4, 4, 4, 6};
        for (Integer integer : util.quickSort3(arr, 0, 9)) {
            System.out.print(integer);

        }

        String[] er = new String[]{"", ""};
    }

    public <T extends Comparable<? super T>>
    T[] quickSort(T[] targetArr, int start, int end) {
        System.out.println("count:" + ++count);
        int i = start + 1, j = end;
        T key = targetArr[start];
        if (start >= end) return targetArr;

        while (true) {
            //从后开始越过比key大的值，最终停在从后往前数第一个不大于key的值上
            while (targetArr[j].compareTo(key) > 0) j--;
            //最终停在从前往后数第一个不小于key的值上
            while (targetArr[i].compareTo(key) < 0 && i < j) i++;
            //若这里找到一个分界点，若分界点恰好能按key大小左右两边分开
            //则中断循环
            if (i >= j) break;
            //否则交换ij的元素，继续直到一趟的目的结束，左右分开
            swap(targetArr, i, j);
            if (targetArr[i] == key) {
                j--;
            } else {
                i++;
            }
        }

        swap(targetArr, start, j);
        //左侧不是只有一个元素
        if (start < i - 1) {
            this.quickSort(targetArr, start, i - 1);
        }
        //右侧不是只有一个元素
        if (j + 1 < end) {
            this.quickSort(targetArr, j + 1, end);
        }
        for (T integer : targetArr) {
            System.out.print(integer);
        }
        System.out.println();
        return targetArr;
    }

    @Test
    public void testSwap() {
        SortUtil util = new SortUtil();
        Integer[] a = {1, 2};
        util.swap(a, 0, 1);
        for (Integer integer : a) {
            System.out.print(integer);
        }
    }

    public <T extends Comparable<? super T>> void swap(T[] targetArr, int left, int right) {
        T temp = targetArr[left];
        targetArr[left] = targetArr[right];
        targetArr[right] = temp;
    }

    public void swap(int[] targetArr, int left, int right) {
        int temp = targetArr[left];
        targetArr[left] = targetArr[right];
        targetArr[right] = temp;
    }

    public <T extends Comparable<? super T>> T[]
    quickSort2(T[] targetArr, int start, int end) {
        System.out.println("count:" + ++count);
        int i = start, j = end;
        T key = targetArr[start];
        while (i < j) {
            //后往前遍历，找到第一个小于key的元素，与i位置元素置换
            while (j > i && targetArr[j].compareTo(key) >= 0) j--;
            if (i < j) {
                targetArr[i] = targetArr[j];
                i++;
            }
            //前往后遍历，找到第一个大于key的元素，与j位置元素置换
            while (i < j && targetArr[i].compareTo(key) <= 0) i++;
            if (i < j) {
                targetArr[j] = targetArr[i];
                j--;
            }
        }
        //一趟比对结束之后，置换key与i位置的元素，此时i==j
        targetArr[i] = key;
        for (T integer : targetArr) {
            System.out.print(integer);
        }
        System.out.println();
        if (start < i - 1)
            this.quickSort2(targetArr, start, i - 1);
        if (end > j + 1)
            this.quickSort2(targetArr, j + 1, end);

        return targetArr;
    }

    int count = 0;

    /**
     * 划分函数，划分并返回 pivot 位置
     *
     * @param arr
     * @param left
     * @param right
     */
    public int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        //小于基准的子数组的最后一个元素索引，它的初始值是在数组之外的
        int tail = left - 1;
        for (int i = left; i < right; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, ++tail);
            }
        }
        //全部替换后将pivot替换到tail后一个位置
        swap(arr, right, tail + 1);

        return tail + 1;
    }

    public int[] quickSort3(int[] arr, int left, int right) {
        String[] array = {"", "'",};//仅能在声明的同时使用
        array = new String[]{"", "",};
        array = new String[4];

        if (right < left) return arr;
        System.out.println("count:" + ++count);
        int pivot = partition(arr, left, right);
        quickSort3(arr, left, pivot - 1);
        quickSort3(arr, pivot + 1, right);
        return arr;
    }

    public enum Spiciness {
        NOT, MILD, MEDIUM, HOT, FLAMING
    }
}
