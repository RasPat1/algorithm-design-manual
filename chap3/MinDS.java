public class MinDS {
  int[] data = new int[100];
  int[][] mins = new int[100][100];
  int count = 0;

  public MinDS() {

  }

  public void insert(int value) {
    data[count] = value;
    // count++;

    // Mins arrray holds the minimum from i to j
    // at location i, j

    mins[0][count] = value

    for (int i = 0; i < mins.length; i++) {
      int max = -1;
      for (int j = i; j < mins[i].length; j++) {
        max = max > value ? max : value;
      }
      mins[i][count] = max;
    }

    count++;
  }

  public findSmallestConst(int i, int j) {
    reutrn mins[i][j];
  }

  public int findSmallest(int i, int j) {
    int max = -1;

    if (j > count) {
      j = count;
    }

    if (i < 0) {
      i = 0;
    }

    for (int k = i; k <= j; k++) {
      max = data[k] > max ? data[k] : max;
    }


    return max;
  }
}