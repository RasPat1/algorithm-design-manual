package sorts;

import java.util.Arrays;

public class Mergesort {

  public static void sort(int[] data) {
    int[] result = sortImpl(data);
    for (int i = 0; i < data.length; i++) {
      data[i] = result[i];
    }
  }

  public static int[] sortImpl(int[] data) {
    if (data.length <= 1) {
      return data;
    }

    int[] result = new int[data.length];

    int mid = data.length / 2;
    int[] low = new int[mid];
    int[] high = new int[data.length - mid];

    // copy arrays O(n)
    for (int i = 0; i < data.length; i++) {
      if (i < mid) {
        low[i] = data[i];
      } else {
        high[i - mid] = data[i];
      }
    }

    low = sortImpl(low);
    high = sortImpl(high);

    merge(result, low, high);

    return result;
  }

  public static void merge(int[] result, int[] low, int[] high) {
    int lowPointer = 0;
    int highPointer = 0;

    for (int i = 0; i < result.length; i++) {
      if (lowPointer == low.length) {
        result[i] = high[highPointer];
        highPointer++;
      } else if (highPointer == high.length) {
        result[i] = low[lowPointer];
        lowPointer++;
      } else if (low[lowPointer] < high[highPointer]) {
        result[i] = low[lowPointer];
        lowPointer++;
      } else {
        result[i] = high[highPointer];
        highPointer++;
      }
    }

  }

}