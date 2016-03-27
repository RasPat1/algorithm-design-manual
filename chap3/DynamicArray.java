public class DynamicArray {
  int dataCount = 0;
  int[] data;
  final static int DEFAULT_SIZE = 10;
  final static double GROWTH_FACTOR = 2;
  final static int SHRINK_FACTOR = 4;

  public DynamicArray() {
    this.data = new int[DEFAULT_SIZE];
  }

  public Boolean insert(int value) {
    if (needsGrow()) {
      grow();
    }

    data[dataCount] = value;
    dataCount++;
    return true;
  }

  public int remove() { //throws DynamicArrayIndexOutOfBounds {
    if (dataCount <= 0) {
      // throw new DynamicArrayIndexOutOfBounds();
    }

    if (needsShrink()) {
      shrink();
    }

    int value = data[dataCount - 1];
    dataCount--;
    return value;
  }

  public int get(int index) { //throws DynamicArrayIndexOutOfBounds {
    if (index > dataCount || index < 0) {
      // throw new DynamicArrayIndexOutOfBounds();
    }

    return data[index];
  }

  public Boolean needsGrow() {
    return dataCount == maxSize() && maxSize() >= DEFAULT_SIZE;
  }

  public void grow() {
    int newSize = (int)(dataCount * GROWTH_FACTOR);
    newSize = newSize > DEFAULT_SIZE ? newSize : DEFAULT_SIZE;
    int[] newData = new int[newSize];

    for (int i = 0; i < dataCount; i++) {
      newData[i] = data[i];
    }

    this.data = newData;
  }

  public Boolean needsShrink() {
    return dataCount < maxSize() / SHRINK_FACTOR && maxSize() >= DEFAULT_SIZE;
  }

  public void shrink() {
    int newSize = maxSize() / SHRINK_FACTOR;
    newSize = newSize > DEFAULT_SIZE ? newSize : DEFAULT_SIZE;
    int[] newData = new int[newSize];

    for (int i = 0; i < dataCount; i++) {
      newData[i] = data[i];
    }

    this.data = newData;
  }

  public int maxSize() {
    return this.data.length;
  }

  class DynamicArrayIndexOutOfBounds extends Throwable {}
}