package de.robertwitt.ddd;

/**
 * Abstract implementation of a specification.
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

  @Override
  public Specification<T> and(Specification<T> other) {
    return new AndSpecification<>(this, other);
  }

}
