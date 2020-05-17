public class MemberSetSpec {
  public static void main(String[] args) {
    System.out.println("Tests Started");
    testOperations();
  }

  public static void testOperations() {
    MemberSet ms = new MemberSet();
    ms.insert(10);
    assert ms.value == 10;
    assert ms.member(10) == true;

    assert ms.member(9) == false;
    ms.insert(9);
    assert ms.member(9) == true;
    assert ms.nodesInLeftSubtree == 1;

    assert ms.member(11) == false;
    ms.insert(11);
    assert ms.member(11) == true;

    assert ms.member(10) == true;
    ms.delete(1);
    assert ms.member(10) == false;
    ms.insert(10);

    assert ms.member(9) == true;
    ms.delete(0);
    assert ms.member(9) == false;
    ms.insert(9);

    assert ms.member(11) == true;
    ms.delete(2);
    assert ms.member(11) == false;
    ms.insert(11);

    assert ms.member(11) == true;
    ms.insert(11);
    assert ms.member(11) == true;

    ms.delete(11);
    assert ms.member(11) == true;

    ms.delete(11);
    assert ms.member(11) == false;

    System.out.println("General Operations: PASS");
  }
}