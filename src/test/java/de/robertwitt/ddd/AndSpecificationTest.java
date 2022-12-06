package de.robertwitt.ddd;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.robertwitt.ddd.impl.EvenIntegerSpecification;
import de.robertwitt.ddd.impl.NonNegativeIntegerSpecification;

public class AndSpecificationTest {

  private static final NonNegativeIntegerSpecification NON_NEGATIVE = new NonNegativeIntegerSpecification();
  private static final EvenIntegerSpecification EVEN = new EvenIntegerSpecification();

  private AndSpecification<Integer> cut = new AndSpecification<>(NON_NEGATIVE, EVEN);

  @Test
  void testIsSatisfiedBy() {
    assertTrue(cut.isSatisfiedBy(4));
    assertFalse(cut.isSatisfiedBy(-2));
    assertFalse(cut.isSatisfiedBy(3));
    assertFalse(cut.isSatisfiedBy(0));
  }

  @Test
  void testThrowIfUnsatisfiedBy() {
    assertDoesNotThrow(() -> cut.throwIfUnsatisfiedBy(4));
    assertThrows(IllegalArgumentException.class, () -> cut.throwIfUnsatisfiedBy(-2));
    assertThrows(IllegalArgumentException.class, () -> cut.throwIfUnsatisfiedBy(3));
    assertThrows(IllegalArgumentException.class, () -> cut.throwIfUnsatisfiedBy(0));
  }

  @Test
  void testLeftIsNull() {
    var cut = new AndSpecification<>(null, EVEN);

    assertTrue(cut.isSatisfiedBy(4));
    assertTrue(cut.isSatisfiedBy(-2));
    assertFalse(cut.isSatisfiedBy(3));
    assertTrue(cut.isSatisfiedBy(0));

    assertDoesNotThrow(() -> cut.throwIfUnsatisfiedBy(4));
    assertDoesNotThrow(() -> cut.throwIfUnsatisfiedBy(-2));
    assertThrows(IllegalArgumentException.class, () -> cut.throwIfUnsatisfiedBy(3));
    assertDoesNotThrow(() -> cut.throwIfUnsatisfiedBy(0));
  }

  @Test
  void testRightIsNull() {
    var cut = new AndSpecification<>(NON_NEGATIVE, null);

    assertTrue(cut.isSatisfiedBy(4));
    assertFalse(cut.isSatisfiedBy(-2));
    assertTrue(cut.isSatisfiedBy(3));
    assertFalse(cut.isSatisfiedBy(0));

    assertDoesNotThrow(() -> cut.throwIfUnsatisfiedBy(4));
    assertThrows(IllegalArgumentException.class, () -> cut.throwIfUnsatisfiedBy(-2));
    assertDoesNotThrow(() -> cut.throwIfUnsatisfiedBy(3));
    assertThrows(IllegalArgumentException.class, () -> cut.throwIfUnsatisfiedBy(0));
  }
}
