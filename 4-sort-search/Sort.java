package sorts;

import java.util.Random;
import java.util.Arrays;

public class Sort {
  static Random random = new Random();

  public static void main(String[] args) {
    Boolean success = true;
    int testCount = 1000;
    int minDataSize = 0;
    int maxDataSize = 1000;

    long totalTime = 0;

    for (int i = 0; i < testCount; i++) {
      int[] data = getRandomData(minDataSize, maxDataSize);
      long start = System.currentTimeMillis();
      // Quicksort.sort(data);
      Heapsort.sort(data);
      // Mergesort.sort(data);
      long end = System.currentTimeMillis();

      totalTime += (end - start);
      Boolean sorted = isSorted(data);

      if (!sorted) {
        System.out.println("failed");
        success = false;
        break;
      }
    }

    if (success) {
      System.out.println(testCount + " tests passed");
      System.out.println("Time Taken: " + totalTime);
    }

  }

  public static int[] getRandomData(int min, int max) {
    int dataLength = random.nextInt(max + 1 - min) + min;
    int[] data = new int[dataLength];

    for (int i = 0; i < data.length; i++) {
      data[i] = random.nextInt();
    }

    return data;
  }

  public static Boolean isSorted(int[] data) {
    for (int i = 0; i < data.length - 1; i++) {
      if (data[i] > data[i+1]) {
        return false;
      }
    }

    return true;
  }
}