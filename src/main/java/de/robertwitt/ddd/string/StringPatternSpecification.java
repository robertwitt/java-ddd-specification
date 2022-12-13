package de.robertwitt.ddd.string;

import java.util.Objects;
import java.util.regex.Pattern;

import de.robertwitt.ddd.AbstractSpecification;

/**
 * This specification validates whether simple strings conform to a specified
 * regular expression.
 */
public class StringPatternSpecification extends AbstractSpecification<String> {

  private Pattern pattern;

  /**
   * Instantiate a new specification with a given pattern.
   * 
   * @param pattern
   */
  public StringPatternSpecification(Pattern pattern) {
    this.pattern = Objects.requireNonNull(pattern);
  }

  /**
   * Instantiate a new specification with a pattern compiled from a given string.
   * 
   * @param regex
   */
  public StringPatternSpecification(String regex) {
    this(Pattern.compile(Objects.requireNonNull(regex)));
  }

  public Pattern getPattern() {
    return pattern;
  }

  @Override
  public boolean isSatisfiedBy(String candidate) {
    if (candidate == null) {
      return false;
    }
    return pattern.matcher(candidate).matches();
  }

}
