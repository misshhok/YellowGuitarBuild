package ru.bsu.yellowguitarbend.domain.exception;

public class InstrumentNameSizeException extends IllegalArgumentException {
  public InstrumentNameSizeException(int size) {
    super("Commercial name of musical instrument has non-valid size - " + size);
  }
}
