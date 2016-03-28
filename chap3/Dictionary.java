/**
 * 3] Design a dictionary data structure in which
 * search, insertion, and deletion can all be processed
 * in O(1) time in the worst case.
 * You may assume the set elements are integers drawn from
 * a finite set 1, 2, .., n, and initialization can take O(n) time.
**/

public class Dictionary {
  final static int DEFAULT_KEY_SPACE = 100;
  LL[] data;
  int dataCount = 0;

  public Dictionary() {
    this.data = new LL[DEFAULT_KEY_SPACE];
  }

  public Dictionary(int keySpace) {
    this.data = new LL[keySpace];
  }

  public void insert(int value) {
    int hash = hash(value);

    data[hash] = new LL(data[hash], value);

    dataCount++;
  }

  public Boolean search(int value) {
    int hash = hash(value);
    Boolean exists = false;

    LL node = data[hash];

    while(node != null) {
      if (node.value == value) {
        exists = true;
        break;
      }
      node = node.nextNode;
    }

    return exists;
  }

  public Boolean delete(int value) {
    Boolean deleted = false;

    LL lastNode = null;
    LL currentNode = null;

    int hash = hash(value);
    currentNode = data[hash];

    while (currentNode != null) {
      if (currentNode.value == value) {
        if (lastNode == null) {
          data[hash] = currentNode.nextNode;
        } else {
          lastNode = currentNode.nextNode;
        }
        break;
      }
      lastNode = currentNode;
      currentNode = currentNode.nextNode;
    }

    return deleted;
  }

  public int hash(int value) {
    return value % data.length;
  }

  class LL {
    LL nextNode;
    int value;

    public LL(LL nextNode, int value) {
      this.nextNode = nextNode;
      this.value = value;
    }
  }
}

