public class Tree {
    Tree left;
    Tree right;
    Tree parent;
    Integer value = null;

    public Tree(int value) {
      this.value = value;
      this.left = new Tree();
      this.right = new Tree();
    }

    public Tree() {

    }

    public void add(int newValue) {
      if (this.value == null) {
        this.value = newValue;
      } else if (newValue <= this.value) {
        if (this.left == null) {
          this.left = new Tree(newValue);
          this.left.parent = this;
        } else {
          this.left.add(newValue);
        }
      } else if (newValue > this.value) {
        if (this.right == null) {
          this.right = new Tree(newValue);
          this.right.parent = this;
        } else {
          this.right.add(newValue);
        }
      }
    }

    public Boolean member(int newValue) {
      if (this.value == null) {
        return false;
      } else if (newValue == this.value) {
        return true;
      } else if (newValue < this.value) {
        if (this.left == null) {
          return false;
        } else {
          return left.member(newValue);
        }
      } else {
        if (this.right == null) {
          return false;
        } else {
          return right.member(newValue);
        }
      }
    }

    /**
     * Removes the kth smallest element in the tree
     *
     */
    public void removeSmallest(int k) {
      //this.delete(this);
    }

    public void delete(Tree tree) {

    }

}