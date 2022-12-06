package de.robertwitt.ddd.impl;

import de.robertwitt.ddd.AbstractSpecification;

public class EvenIntegerSpecification extends AbstractSpecification<Integer> {
  @Override
  public boolean isSatisfiedBy(Integer candidate) {
    return candidate % 2 == 0;
  }
}
