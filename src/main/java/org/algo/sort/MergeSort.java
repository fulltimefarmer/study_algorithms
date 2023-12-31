package org.algo.sort;

import org.algo.DataUtil;

import java.util.Arrays;

public class MergeSort {

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        process(arr, l, m);
        process(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            temp[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= m) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[i++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int testTime = 5_000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = DataUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = DataUtil.copyArray(arr1);
            mergeSort(arr1);
            Arrays.sort(arr2);
            if (!DataUtil.isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                DataUtil.printArray(arr1);
                DataUtil.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
