import java.util.*;

public class Unique {
  public static void main(String[] args) {
    int arrSize = 1000;
    int[] arr = new int[arrSize];

    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(1000);
    }

    int[] uniqueArr = getUniqueData(arr);
    Arrays.sort(arr);
    int count = 1;

    for (int i = 1; i < arr.length; i++) {
      if (arr[i] != arr[i-1]) {
        count++;
      }
    }

    if (uniqueArr.length != count) {
      System.out.println("failed");
    }

    System.out.println("unqiueArr length: " + uniqueArr.length);
    System.out.println("count: " + count);
  }

  public static int[] getUniqueData(int[] arr) {
    Set<Integer> set = new HashSet<>();

    for (int i = 0; i < arr.length; i++) {
      set.add(arr[i]);
    }

    int[] uniqueArr = new int[set.size()];
    int counter = 0;

    for (Integer i : set) {
      uniqueArr[counter] = i;
      counter++;
    }

    return uniqueArr;
  }
}