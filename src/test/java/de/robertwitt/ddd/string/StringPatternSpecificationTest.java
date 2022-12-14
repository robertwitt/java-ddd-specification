package de.robertwitt.ddd.string;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class StringPatternSpecificationTest {

  private StringPatternSpecification cut = new StringPatternSpecification("^[a-z]+$");

  @Test
  void creationSucceedsWithValidRegex() {
    assertDoesNotThrow(() -> new StringPatternSpecification("[a]+$"));
  }

  @Test
  void creationSucceedsWithEmptyStringAsRegex() {
    assertDoesNotThrow(() -> new StringPatternSpecification(""));
  }

  @Test
  void creationFailsWithInvalidRegex() {
    assertThrows(PatternSyntaxException.class, () -> new StringPatternSpecification("[a}"));
  }

  @Test
  void creationFailsWithNullAsPattern() {
    Pattern nullPattern = null;
    assertThrows(NullPointerException.class, () -> new StringPatternSpecification(nullPattern));
  }

  @Test
  void isSatisfiedByValidValue() {
    assertTrue(cut.isSatisfiedBy("hello"));
  }

  @ParameterizedTest
  @MethodSource("invalidValues")
  void isUnsatisfiedByInvalidValues(String value) {
    assertFalse(cut.isSatisfiedBy(value));
  }

  private static Stream<String> invalidValues() {
    return Stream.of("Hello", "", null);
  }

}
