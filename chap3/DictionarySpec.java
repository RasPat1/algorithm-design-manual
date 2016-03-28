import java.util.Random;

public class DictionarySpec {
  static Random random;
  public static void main(String[] args) {
    initializeTests();
    testInsertion();
    testSearch();
    testDelete();
    testInsertionPerformance();
    testSearchPerformance();
    testDeletePerformance();
    testRandomLoad();
    System.out.println("Check performance test by inspection");
  }

  public static void initializeTests() {
    System.out.println("Start Tests");
    random = new Random();
  }

  public static void testInsertion() {
    Dictionary d = new Dictionary();
    int maxKey = d.DEFAULT_KEY_SPACE;

    int ops = (int) Math.pow(10, 5);

    for (int i = 0; i < ops; i++) {
      int rand = random.nextInt(maxKey);
      d.insert(rand);
    }

    assert d.dataCount == ops;
    System.out.println("testInsertion passed!");
  }

  public static void testSearch() {
    Dictionary d = new Dictionary();
    Boolean inDict;

    d.insert(10);
    inDict = d.search(10);
    assert inDict == true;

    inDict = d.search(9);
    assert inDict == false;

    System.out.println("testSearch passed!");
  }

  public static void testDelete() {
    Dictionary d = new Dictionary();

    d.insert(10);
    assert d.search(10) == true;
    d.delete(10);
    assert d.search(10) == false;

    System.out.println("testDelete passed!");
  }

  /**
  * Make sure cost of Get cost of each section of n additions
  * Make sure cost is approx equal after
  * n additions and 10n additions for various sized dictionaries
  */
  public static void testInsertionPerformance() {

    int tests = 5;
    int sections = 10;
    int ops = 10000;
    int keySpace = (int) Math.pow(10, 5);
    long[][] timers = new long[tests][sections];

    for (int i = 0; i < tests; i++) {
      // int dictionarySize = (int) Math.pow(10, i + 1);
      Dictionary dict = new Dictionary(keySpace);
      System.out.println("DictionarySize:" + dict.data.length);
      for (int j = 0; j < sections; j++) {
        for (int k = 0; k < ops; k++) {
          int val = random.nextInt(keySpace);
          Long startTime = System.nanoTime();
          dict.insert(val);
          Long endTime = System.nanoTime();
          Long totalTime = endTime - startTime;
          timers[i][j] += totalTime;
        }
        System.out.print(timers[i][j] / 1000 + ", ");
      }
      System.out.println("");
    }
    System.out.println("Test Insertion Performance By Inspection");
  }

  /**
  *  Fill a Dict with random values below x (where x >> DictSize)
  *  Test search perf for ints 1 to x
  *  Add more values and repeat
  *  Ensure each chunk of searches takes roughly the same amount of time
  */
  public static void testSearchPerformance() {
    int tests = 5;
    int sections = 10;
    int ops = 10000;
    int keySpace = (int) Math.pow(10, 5);
    long[][] timers = new long[tests][sections];

    for (int i = 0; i < tests; i++) {
      int dictionarySize = (int) Math.pow(10, i + 1);
      Dictionary dict = new Dictionary(keySpace);
      System.out.println("DictionarySize:" + dict.data.length);

      for (int j = 0; j < sections; j++) {
        for (int k = 0; k < ops; k++) {
          int insertVal = random.nextInt(keySpace);
          dict.insert(insertVal);
        }
        Long startTime = System.nanoTime();
        for (int searchVal = 0; searchVal < keySpace; searchVal++) {
          dict.search(searchVal);
        }
        Long endTime = System.nanoTime();
        Long totalTime = endTime - startTime;
        timers[i][j] = totalTime;
        System.out.print(timers[i][j] / 1000 + ", ");
      }
      System.out.println("");
    }
    System.out.println("Test Search Performance By Inspection");
  }

  public static void testDeletePerformance() {

  }

  public static void testRandomLoad() {

  }


}