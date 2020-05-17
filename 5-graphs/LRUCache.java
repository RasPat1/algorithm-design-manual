// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.

/*
LRUCache
- capacity (100)
- add
- get
- remove
-

"http://example.com/img.png" => ImageData
*/


// create ac ache with size n
// add - add if nto full, otherwise evict LRU until can add
// Element - LRU score

import java.util.*;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache lc = new LRUCache(100);
        lc.add(100);
    }
}

public class LRUCache {
  LinkedList<Object> list;
  Map<Object, LinkedList<Object>> cache;
  // one list to determine recenyScore
  // one hash for quick lookup

  int nextOpenSpot = 0;
  int maxSize;

  public LRUCache(int n) {
    this.maxSize = n;
    this.cache = new HashMap<>();
  }

  public void add(Object el) {

  }

  public void get(Object el) {
    LinkedList<Object> node = cache.get(el);
    appendToEnd(node);
    return node.data;
  }

  public void appendToEnd(LinkedList<Object> node) {
    node.previous.next = node.next;
    node.next = list;
    list = node;
  }
}
