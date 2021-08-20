package com.shivaji.poc;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PassengerCount {
  public static int countPassengers(String dataStr) {
    if (Objects.isNull(dataStr)) {
      return 0;
    }
    var chars = dataStr.trim().split("");
    AtomicInteger count = new AtomicInteger();
    AtomicReference<String> previousAction = new AtomicReference<>();
    Arrays.stream(chars).forEachOrdered(s -> {
      switch (s) {
        case "+":
        case "-":
          previousAction.set(s);
          break;
        case "?":
          if (previousAction.get().equals("+")) {
            count.getAndIncrement();
          } else {
            count.getAndDecrement();
          }
          break;
        default:
          System.err.println("Unknown character [" + s + "]");
      }
    });
    return count.get();
  }
}
