package de.robertwitt.ddd.impl;

import de.robertwitt.ddd.AbstractSpecification;

public class EvenIntegerSpecification extends AbstractSpecification<Integer> {

  @Override
  public boolean isSatisfiedBy(Integer candidate) {
    return candidate % 2 == 0;
  }

  @Override
  public void throwIfUnsatisfiedBy(Integer candidate) throws IllegalArgumentException {
    if (!isSatisfiedBy(candidate)) {
      throw new IllegalArgumentException();
    }
  }

}
