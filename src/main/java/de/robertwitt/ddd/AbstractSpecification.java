package de.robertwitt.ddd;

/**
 * Abstract implementation of a specification.
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

  @Override
  public void throwIfUnsatisfiedBy(T candidate) throws IllegalArgumentException {
    if (!isSatisfiedBy(candidate)) {
      throw new IllegalArgumentException(String.format("Specification not satisfied by value '%s'", candidate));
    }
  }

  @Override
  public Specification<T> and(Specification<T> other) {
    return new AndSpecification<>(this, other);
  }

}
