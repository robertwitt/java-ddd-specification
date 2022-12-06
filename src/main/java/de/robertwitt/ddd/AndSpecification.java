package de.robertwitt.ddd;

class AndSpecification<T> implements Specification<T> {

  private Specification<T> left;
  private Specification<T> right;

  public AndSpecification(Specification<T> left, Specification<T> right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean isSatisfiedBy(T candidate) {
    var isLeftSatisfied = left == null || left.isSatisfiedBy(candidate);
    var isRightSatisfied = right == null || right.isSatisfiedBy(candidate);
    return isLeftSatisfied && isRightSatisfied;
  }

  @Override
  public void throwIfUnsatisfiedBy(T candidate) throws IllegalArgumentException {
    if (left != null) {
      left.throwIfUnsatisfiedBy(candidate);
    }
    if (right != null) {
      right.throwIfUnsatisfiedBy(candidate);
    }
  }

  @Override
  public Specification<T> and(Specification<T> other) {
    return new AndSpecification<>(this, other);
  }

}
