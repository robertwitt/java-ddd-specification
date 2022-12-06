package de.robertwitt.ddd;

/**
 * A specification is the implementation of a business rule.
 */
public interface Specification<T> {

  /**
   * Check whether the given `candidate` is fulfilling this specification.
   * 
   * @param candidate a value
   * @return `true` if specification is fulfilled by `candidate`, `false`
   *         otherwise
   */
  boolean isSatisfiedBy(T candidate);

  /**
   * Throw an exception if the given `candidate` does not fulfill this
   * specification.
   * This is a convenience function for:
   * ```
   * if (!isSatisfiedBy(candidate)) {
   * throw new IllegalArgumentException();
   * }
   * ```
   * 
   * @param candidate a value
   * @throws IllegalArgumentException
   */
  void throwIfUnsatisfiedBy(T candidate) throws IllegalArgumentException;

  /**
   * Combine this specification with another specification by logical AND.
   * 
   * @param other another specification
   * @return the conjoint specification
   */
  Specification<T> and(Specification<T> other);

}
