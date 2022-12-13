package de.robertwitt.ddd.string;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.jupiter.api.Test;

public class StringPatternSpecificationTest {

  @Test
  void testCreation() {
    assertDoesNotThrow(() -> new StringPatternSpecification("[a]+$"));
    assertDoesNotThrow(() -> new StringPatternSpecification(""));
    assertThrows(PatternSyntaxException.class, () -> new StringPatternSpecification("[a}"));

    Pattern nullPattern = null;
    assertThrows(NullPointerException.class, () -> new StringPatternSpecification(nullPattern));
  }

  @Test
  void testIsSatisfiedBy() {
    var cut = new StringPatternSpecification("^[a-z]+$");
    assertTrue(cut.isSatisfiedBy("hello"));
    assertFalse(cut.isSatisfiedBy("Hello"));
    assertFalse(cut.isSatisfiedBy(""));
    assertFalse(cut.isSatisfiedBy(null));
  }

}
