package com.shivaji.poc;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class PassengerCountTest {

  @Test
  void countPassengers() {
    assertEquals(1, PassengerCount.countPassengers("++??-?+-??++++-?+++-?"));
  }
}