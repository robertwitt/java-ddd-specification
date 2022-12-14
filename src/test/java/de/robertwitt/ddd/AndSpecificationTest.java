package de.robertwitt.ddd;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.robertwitt.ddd.impl.EvenIntegerSpecification;
import de.robertwitt.ddd.impl.NonNegativeIntegerSpecification;

public class AndSpecificationTest {

  private static final NonNegativeIntegerSpecification NON_NEGATIVE = new NonNegativeIntegerSpecification();
  private static final EvenIntegerSpecification EVEN = new EvenIntegerSpecification();

  private AndSpecification<Integer> cut = new AndSpecification<>(NON_NEGATIVE, EVEN);

  @Test
  void isSatisfiedByValidValue() {
    assertTrue(cut.isSatisfiedBy(4));
  }

  @ParameterizedTest
  @MethodSource("invalidValues")
  void isUnsatisfiedByInvalidValue(Integer value) {
    assertFalse(cut.isSatisfiedBy(value));
  }

  @Test
  void doesNotThrowForSatisfyingValue() {
    assertDoesNotThrow(() -> cut.throwIfUnsatisfiedBy(4));
  }

  @ParameterizedTest
  @MethodSource("invalidValues")
  void throwsForUnsatisfyingValue(Integer value) {
    assertThrows(IllegalArgumentException.class, () -> cut.throwIfUnsatisfiedBy(value));
  }

  private static Stream<Integer> invalidValues() {
    return Stream.of(-2, 3, 0);
  }

  @Nested
  class WhenLeftIsNull {

    private AndSpecification<Integer> cut = new AndSpecification<>(null, EVEN);

    @ParameterizedTest
    @MethodSource("validValues")
    void isSatisfiedForValidValue(Integer value) {
      assertTrue(cut.isSatisfiedBy(value));
    }

    private static Stream<Integer> validValues() {
      return Stream.of(4, -2, 0);
    }

    @Test
    void isUnsatisfiedByInvalidValue() {
      assertFalse(cut.isSatisfiedBy(3));
    }

  }

  @Nested
  class WhenRightIsNull {

    AndSpecification<Integer> cut = new AndSpecification<>(NON_NEGATIVE, null);

    @ParameterizedTest
    @MethodSource("validValues")
    void isSatisfiedForValidValue(Integer value) {
      assertTrue(cut.isSatisfiedBy(value));
    }

    private static Stream<Integer> validValues() {
      return Stream.of(4, 3);
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    void isSatisfiedForInalidValue(Integer value) {
      assertFalse(cut.isSatisfiedBy(value));
    }

    private static Stream<Integer> invalidValues() {
      return Stream.of(-2, 0);
    }

  }

}
