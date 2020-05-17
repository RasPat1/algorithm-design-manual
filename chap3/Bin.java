public class Bin {
    static int maxWeight = 1;
    double currentWeight = 0;

    public Boolean add(double objWeight) {
      Boolean success = false;

      if (objWeight + currentWeight <= maxWeight) {
        currentWeight += objWeight;
        success = true;
      }

      return success;
    }

    public String toString() {
      return "Weight: " + currentWeight;
    }
}