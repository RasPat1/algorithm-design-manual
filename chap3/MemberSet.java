/**
 * Balanced BST where
 * 3 functions in interface all taking O(lg n) time
 * insert, delete kth smallest, and membership
 */

public class MemberSet {
  Integer value = null;
  MemberSet leftSet = null;
  MemberSet rightSet = null;
  MemberSet parent = null;
  int nodesInLeftSubtree = 0;
  int nodesInRightSubtree = 0;

  // public MemberSet() {

  // }

  public void insert(int newValue) {
    if (value == null) {
      value = newValue;
    } else if (newValue > value) {
      if (rightSet == null) {
        rightSet = new MemberSet();
        rightSet.value = newValue;
        rightSet.parent = this;
        // No increase in parent node
      } else {
        rightSet.insert(newValue);
      }
      nodesInRightSubtree++;
    } else {
      if (leftSet == null) {
        leftSet = new MemberSet();
        leftSet.value = newValue;
        leftSet.parent = this;
      } else {
        leftSet.insert(newValue);
      }
      nodesInLeftSubtree++;
    }

  }

  public Boolean member(int memberValue) {
    Boolean inSet = false;
    if (value == memberValue) {
      inSet = true;
    } else if (leftSet == null && rightSet == null) {
      inSet = false;
    } else if (memberValue > value) {
      if (rightSet == null) {
        inSet = false;
      } else {
        inSet = rightSet.member(memberValue);
      }
    } else {
      if (leftSet == null) {
        inSet = false;
      } else {
        inSet = leftSet.member(memberValue);
      }

    }
     return inSet;
  }

  public MemberSet delete(int kthSmallest) {
    if (nodesInLeftSubtree == kthSmallest) {
      if (nodesInLeftSubtree > nodesInRightSubtree) {
        MemberSet min = leftSet.delete(0);
        parent.leftSet = min;
        min.leftSet = leftSet.leftSet;
        min.rightSet = leftSet.rightSet;
        min.nodesInLeftSubtree = min.leftSet.nodesInLeftSubtree + min.leftSet.nodesInRightSubtree + 1;
        min.nodesInRightSubtree = min.rightSet.nodesInLeftSubtree + min.rightSet.nodesInRightSubtree + 1;
        return this;
      } else {
        if (rightSet == null) {
          return this;
        }
        MemberSet min = rightSet.delete(0);
        if (parent == null) {
          this.leftSet = rightSet.leftSet;
          this.rightSet = rightSet.rightSet;
          this.nodesInLeftSubtree = this.leftSet.nodesInLeftSubtree + this.leftSet.nodesInRightSubtree + 1;
          this.nodesInRightSubtree = this.rightSet.nodesInLeftSubtree + this.rightSet.nodesInRightSubtree + 1;
          return this;
        } else {
          parent.rightSet = min;2
          min.leftSet = rightSet.leftSet;
          min.rightSet = rightSet.rightSet;
          min.nodesInLeftSubtree = min.leftSet.nodesInLeftSubtree + min.leftSet.nodesInRightSubtree + 1;
          min.nodesInRightSubtree = min.rightSet.nodesInLeftSubtree + min.rightSet.nodesInRightSubtree + 1;
          return this;
        }
      }
    } else if (nodesInLeftSubtree > kthSmallest) {
      nodesInLeftSubtree--;
      return leftSet.delete(kthSmallest);
    } else {
      nodesInRightSubtree--;
      return rightSet.delete(kthSmallest - nodesInLeftSubtree);
    }
  }

  public void rebalance() {

  }

}