package sorts;

public class Quicksort {

  public static void sort(int[] data) {
    if (data.length <=1) {
      return;
    }

    sortImpl(data, 0, data.length - 1);
  }

  public static void sortImpl(int[] data, int low, int high) {

    if (high - low > 0) {
      int pIndex = partition(data, low, high);
      sortImpl(data, low, pIndex - 1);
      sortImpl(data, pIndex + 1, high);
    }

  }

  public static int partition(int[] data, int low, int high) {
    int pIndex = high;
    int firstHighNum = low;

    for (int i = low; i < high; i++) {
      if (data[i] < data[pIndex]) {
        swap(data, i, firstHighNum);
        firstHighNum++;
      }
    }
    swap(data, firstHighNum, pIndex);

    return firstHighNum;
  }

  public static void swap(int[] data, int i1, int i2) {
    int tmp = data[i1];
    data[i1] = data[i2];
    data[i2] = tmp;
  }
}