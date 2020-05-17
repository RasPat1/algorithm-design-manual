package sorts;

import java.util.Arrays;

public class Heapsort {
  public static void sort(int[] data) {
    Heap minHeap = new Heap(data);
    for (int i = 0; i < data.length; i++) {
      try {
        data[i] = minHeap.extractMin();
      } catch (Exception e) {
        System.out.println("Empty Heap Exception");
      }
    }
  }

}

class Heap {
  int[] data;
  int count = 0;

  public Heap(int[] data) {
    this.data = new int[data.length];
    for (int i = 0; i < data.length; i++) {
      count++;
      this.data[i] = data[i];
      bubbleUp(i);
    }
  }

  public void bubbleDown(int i) {
    int left = getLeftIndex(i);
    int right = getRightIndex(i);

    int minIndex = i;

    if (left < count && data[minIndex] > data[left]) {
      minIndex = left;
    }

    if (right < count && data[minIndex] > data[right]) {
      minIndex = right;
    }
    if (minIndex != i) {
      swap(i, minIndex);
      bubbleDown(minIndex);
    }

  }

  public void bubbleUp(int i) {
    // ensure heap invariant for all nodes above in the heap
    if (i == 0) {
      return;
    }

    int parentI = getParent(i);

    if (data[parentI] > data[i]) {
      swap(parentI, i);
      bubbleUp(parentI);
    }

  }

  public int getParent(int i) {
    return (i - 1) / 2;
  }

  public int getLeftIndex(int i) {
    return (i + 1) * 2 - 1;
  }

  public int getRightIndex(int i) {
    return getLeftIndex(i) + 1;
  }

  public void swap(int i1, int i2) {
    int tmp = data[i1];
    data[i1] = data[i2];
    data[i2] = tmp;
  }

  public void heapify() {
    for (int i = count - 1; i >= 0; i--) {
      int left = (i + 1) * 2 - 1;
      int right = (i + 1) * 2;

      if (left < count) {
        if (data[i] > data[left]) {
          int tmp = data[i];
          data[i] = data[left];
          data[left] = tmp;
          heapify();
        }
      }

      if (right < count) {
        if (data[i] > data[right]) {
          int tmp = data[i];
          data[i] = data[right];
          data[right] = tmp;
          heapify();
        }
      }
    }
  }

  public int extractMin() throws Exception {
    if (count == 0) {
      throw new Exception();
    }
    int min = data[0];
    data[0] = data[count - 1];
    data[count - 1] = 0;
    count--;
    bubbleDown(0);
    return min;
  }
}