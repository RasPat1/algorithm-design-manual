import java.util.Random;

/******************************************************************************
 javac DynamicArray.java DynamicArraySpec.java && java -Xmx256M -ea DynamicArraySpec
 ******************************************************************************/

public class DynamicArraySpec {
  static Random random = new Random();

  public static void main(String[] args) {
    initializeTest();

    testCreateEmptyArray();
    testArrayWithValues();

    testOverflow();
    testUnderflow();

    testOverflowPerformance();
    testRandomLoadPerformance();
  }

  public static void initializeTest() {
    System.out.println("Begin Test");
  }

  public static void testCreateEmptyArray() {
    DynamicArray da1 = new DynamicArray();
    assert da1 != null;
    assert da1.dataCount == 0;
    System.out.println("testCreateEmptyArray passed");
  }

  public static void testArrayWithValues() {
    DynamicArray da1 = new DynamicArray();
    int valueCount = da1.DEFAULT_SIZE / 5; // prob won't trigger resize

    for (int i = 0; i < valueCount; i++) {
      int randInt = random.nextInt(100);
      da1.insert(randInt);
    }

    assert da1.dataCount == valueCount;
    System.out.println("testArrayWithValues passed");
  }

  public static void testOverflow() {
    DynamicArray da1 = new DynamicArray();
    int currentMax = da1.maxSize();
    int overflowAmount = currentMax + 1;

    for (int i = 0; i < overflowAmount; i++) {
      int randInt = random.nextInt(100);
      da1.insert(randInt);
    }

    assert da1.dataCount == overflowAmount;
    assert da1.maxSize() > currentMax; // Resizing happened
    System.out.println("testOverflow passed");
  }

  public static void testUnderflow() {
    DynamicArray da1 = new DynamicArray();
    int overflowAmount = da1.maxSize() * 2;

    for (int i = 0; i < overflowAmount; i++) {
      da1.insert(random.nextInt(100));
    }

    int maxAfterInsertion = da1.maxSize();

    for (int i = overflowAmount - 1; i >= 0; i--) {
      da1.remove();
    }

    assert da1.dataCount == 0;
    assert da1.maxSize() < maxAfterInsertion;
    assert da1.maxSize() >= da1.DEFAULT_SIZE;
    System.out.println("testUnderflow passed");
  }

  public static void testOverflowPerformance() {
    int tests = 5;
    int factor = (int) Math.pow(10, 6);

    DynamicArray[] daList = new DynamicArray[tests];
    int[] times = new int[tests];
    int[] testSize = new int[tests];

    for (int i = 0; i < daList.length; i++) {
      daList[i] = new DynamicArray();
      testSize[i] = i + 1;
    }

    for (int i = 0; i < tests; i++) {
      DynamicArray da = daList[i];
      int insertions = factor * testSize[i];
      Long time = 0L;

      for (int j = 0; j < insertions; j++) {
        int val = random.nextInt(10);
        Long start = System.nanoTime();
        da.insert(val);
        Long end = System.nanoTime();
        Long loopTime = end - start;
        time += loopTime;
      }

      times[i] = (int)(time / testSize[i]);
      System.out.println(time / testSize[i] / 1000);
    }

    assertAveragesInRange(times);

    System.out.println("testOverflowPerformance passed");

  }

  public static void assertAveragesInRange(int[] arr) {
    int sum = 0;
    int avg = 0;
    int lowerBound = 0;
    int upperBound = 0;
    int range = 0;

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
    }

    avg = sum / arr.length;
    range = avg / 2;
    lowerBound = avg - range;
    upperBound = avg + range;

    for (int i = 0; i < arr.length; i++) {
      assert lowerBound < arr[i];
      assert upperBound > arr[i];
    }
  }

  public static void testRandomLoadPerformance() {
    int testCount = (int)Math.pow(10, 2);
    int ops = random.nextInt((int)Math.pow(10, 6));

    int[] insertionTime = new int[testCount];
    int[] removalTime = new int[testCount];

    for (int i = 0; i < testCount; i++) {
      DynamicArray test = new DynamicArray();

      Long iCount = 0L;
      Long rCount = 0L;
      Long iTime = 0L;
      Long rTime = 0L;

      for (int j = 0; j < 5; j++) {
        test.insert(j);
      }

      for (int j = 0; j < ops; j++) {

        int opType = random.nextInt(2);

        if (opType >= 1) {
          int val = random.nextInt(100);
          Long start = System.nanoTime();
          test.insert(val);
          Long loopTime = System.nanoTime() - start;
          iTime += loopTime;
          iCount++;
        } else if (test.dataCount > 0) {
          Long start = System.nanoTime();
          test.remove();
          Long loopTime = System.nanoTime() - start;
          rTime += loopTime;
          rCount++;
        }

      }

      insertionTime[i] = (int)(iTime / (iCount));
      removalTime[i] = (int)(rTime / (rCount));
    }

    assertAveragesInRange(insertionTime);
    assertAveragesInRange(removalTime);

    System.out.println("testRandomLoadPerformance passed");
  }
}