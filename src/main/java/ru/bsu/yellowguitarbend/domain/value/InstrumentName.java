package ru.bsu.yellowguitarbend.domain.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.bsu.yellowguitarbend.domain.exception.InstrumentNameSizeException;

@Getter
@ToString
@NoArgsConstructor
public class InstrumentName {
  private static final int MAX_LENGTH = 127;
  @ToString.Exclude private String value;

  public InstrumentName(String value) {
    this.value = value;
  }

  public static InstrumentName of(String name) {
    if (name.length() <= MAX_LENGTH) {
      return new InstrumentName(name);
    } else {
      throw new InstrumentNameSizeException(name.length());
    }
  }
}
