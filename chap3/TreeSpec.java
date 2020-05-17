public class TreeSpec {
    public static void main(String[] args) {
      System.out.println("Tree Spec Started");

      Tree t = new Tree();

      assert t.member(10) == false;
      t.add(10);
      assert t.member(10) == true;

      assert t.member(9) == false;
      t.add(9);
      assert t.member(9) == true;

      assert t.member(11) == false;
      t.add(11);
      assert t.member(11) == true;

      assert t.member(8) == false;
      t.add(8);
      assert t.member(8) == true;

      // walk around the tree
      assert t.left.value == 9;
      Tree t1 = t.left;
      assert t1.value == 9;
      assert t1.left.value == 8;
      assert t1.left.parent.value == 9;
      assert t1.parent.value == 10;
      assert t1.parent.right.value == 11;

      // t.removeKthSmallest(0);
      // assert t.member(9) == false;


      System.out.println("All Tests Passed");
    }
}