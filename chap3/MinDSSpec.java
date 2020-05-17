public class MinDSSpec {
  public static void main(String[] args) {
    System.out.println("Tests Started");
    MinDS m1 = new MinDS();
    for (int i = 0; i < 10; i++) {
      m1.insert(i + 1);
    }
    System.out.println(m1.count);

    int s1 = m1.findSmallest(-4, 100);
    System.out.println(s1);
  }
}