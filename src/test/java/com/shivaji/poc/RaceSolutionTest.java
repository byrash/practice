package com.shivaji.poc;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class RaceSolutionTest {

  @Test
  void getWinnerRaceNumber_HappyPath() {
    var raceData="1 - \"SWIM\": 1200\n" +
        "2 - \"SWIM\": 2312\n" +
        "3 - \"SWIM\": 3222\n" +
        "\n" +
        "1 - \"RUN\": 9821\n" +
        "2 - \"RUN\": 2282\n" +
        "3 - \"RUN\": 8283\n" +
        "\n" +
        "1 - \"BIKE\": 1282\n" +
        "2 - \"BIKE\": 2282\n" +
        "3 - \"BIKE\": 2328";
    var resp = RaceSolution.getWinnerRaceNumber(raceData);
    assertEquals(3, resp.size());
    assertEquals(resp.get("SWIM"), 1);
    assertEquals(resp.get("RUN"), 2);
    assertEquals(resp.get("BIKE"), 1);
  }

  @Test
  void getWinnerRaceNumber_HappyPath_WithSameTimeInDifferentRace() {
    var raceData="1 - \"SWIM\": 1200\n" +
        "2 - \"SWIM\": 2312\n" +
        "3 - \"SWIM\": 3222\n" +
        "4 - \"SWIM\": 1200\n" +
        "\n" +
        "1 - \"RUN\": 9821\n" +
        "2 - \"RUN\": 2282\n" +
        "3 - \"RUN\": 8283\n" +
        "\n" +
        "1 - \"BIKE\": 1282\n" +
        "2 - \"BIKE\": 2282\n" +
        "3 - \"BIKE\": 1282\n" +
        "4 - \"BIKE\": 2328";
    var resp = RaceSolution.getWinnerRaceNumber(raceData);
    assertEquals(3, resp.size());
    assertEquals(resp.get("SWIM"), 1);
    assertEquals(resp.get("RUN"), 2);
    assertEquals(resp.get("BIKE"), 1);
  }

  @Test
  void getWinnerRaceNumber_InvalidRecords() {
    var raceData="1 - \"SWIM\": \n" +
        "2 - \"SWIM\": 2312\n" +
        "3 - \"SWIM\": 3222\n" +
        "4 - \"SWIM\": \n" +
        "\n" +
        "1 - \"RUN\": 9821\n" +
        "2 - \"RUN\": \"\"\n" +
        "3 - \"RUN\": 8283\n" +
        "\n" +
        "1 - \"BIKE\": 1282\n" +
        "2 - \"BIKE\": 2282\n" +
        "3 - \"BIKE\": \"\"\n" +
        "4 - \"BIKE\": 2328";
    var resp = RaceSolution.getWinnerRaceNumber(raceData);
    assertEquals(3, resp.size());
    assertEquals(resp.get("SWIM"), 2);
    assertEquals(resp.get("RUN"), 3);
    assertEquals(resp.get("BIKE"), 1);
  }

  @Test
  void getWinnerRaceNumber_WithNull() {
    var resp = RaceSolution.getWinnerRaceNumber(null);
    assertEquals(0, resp.size());
  }

  @Test
  void getWinnerRaceNumber_WithNoData() {
    var resp = RaceSolution.getWinnerRaceNumber(null);
    assertEquals(0, resp.size());
  }
}