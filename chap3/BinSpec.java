public class BinSpec {
  public static void main(String[] args) {
    System.out.println("Test Started");
    double[] w1 = {
      0.2, 0.4, 0.1, 0.6, 0.9,
      0.4, 0.6, 0.2, 0.6, 0.3,
      0.1, 0.1, 0.9, 0.9, 0, 0.25
    };

    int bins = packNaive(w1);
    System.out.println(bins);



    bins = packFlexFit(w1, "best");
    System.out.println(bins);

    bins = packFlexFit(w1, "worst");
    System.out.println(bins);
  }

  public static int packFlexFit(double[] weights, String alg) {
    System.out.println(alg);
    Bin[] bins = new Bin[weights.length];
    for (int i = 0; i < bins.length; i++) {
      bins[i] = new Bin();
    }

    for (int i = 0; i < weights.length; i++) {
      // place into partially filled bin with
      // the smallest extra room after insertion

      // Sort bins by currentWeight descreasing
      // insert into first available bin
      if (alg == "best") {
        sortBins(bins, "asc");
      } else if (alg == "worst") {
        sortBins(bins, "desc");
      }


      // System.out.println("BinState");
      // for (int k = 0; k < bins.length; k++) {
      //   System.out.println(bins[k]);
      // }

      Boolean success = false;
      int currentBin = 0;
      for (int j = 0; j < bins.length; j++) {

      }
      while (!success && currentBin < bins.length) {
        success = bins[currentBin].add(weights[i]);

        if (!success) {
          currentBin++;
        }
      }
    }

    int binCount = 0;

    for (int i = 0; i < bins.length; i++) {
      if (bins[i].currentWeight > 0) {
        binCount++;
      }
    }

    return binCount;
  }



  /**
   * naive packing method
   * insert objects as they appear in the list
   * Use a new bin when obejct is too large
   **/
  public static int packNaive(double[] weights) {
    int binCount = 0;
    Bin[] bins = new Bin[weights.length];
    for (int i = 0; i < weights.length; i++) {
      if (bins[binCount] == null) {
        bins[binCount] = new Bin();
      }

      Bin currentBin = bins[binCount];
      Boolean added = currentBin.add(weights[i]);
      if (!added) {
        binCount++;
        i--;
      }
    }


    System.out.println("BinStateNaive");
    for (int k = 0; k < bins.length; k++) {
      System.out.println(bins[k]);
    }

    return binCount + 1;
  }

  public static void sortBins(Bin[] bins, String order) {
    for (int n = 0; n < bins.length - 1; n++) {
      for (int m = n; m < bins.length; m++) {
        Boolean criteria = false;
        if (order == "desc") {
          criteria = bins[m].currentWeight > bins[n].currentWeight;
        } else if (order == "asc") {
          criteria = bins[m].currentWeight < bins[n].currentWeight;
        }

        if (criteria && bins[m].currentWeight != 0) {
          Bin tmpBin = bins[n];
          bins[n] = bins[m];
          bins[m] = tmpBin;
        }

      }
    }
  }
}