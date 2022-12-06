package de.robertwitt.ddd.impl;

import de.robertwitt.ddd.AbstractSpecification;

public class NonNegativeIntegerSpecification extends AbstractSpecification<Integer> {

  @Override
  public boolean isSatisfiedBy(Integer candidate) {
    return candidate > 0;
  }

  @Override
  public void throwIfUnsatisfiedBy(Integer candidate) throws IllegalArgumentException {
    if (!isSatisfiedBy(candidate)) {
      throw new IllegalArgumentException();
    }
  }

}
