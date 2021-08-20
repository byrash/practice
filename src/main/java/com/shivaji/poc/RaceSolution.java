package com.shivaji.poc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RaceSolution {
  public static Map<String, Integer> getWinnerRaceNumber(String raceDataStr) {
    if (Objects.isNull(raceDataStr)) {
      return Collections.emptyMap();
    }
    var resultMap = new HashMap<String, RaceVO>();
    raceDataStr.lines().forEachOrdered(s -> {
      var raceVO = RaceVO.build(s.trim());
      if (raceVO == null) {
        System.err.println("Ignoring [" + s + "] as arid record");
      } else {
        if (resultMap.containsKey(raceVO.getName())) {
          if (raceVO.getTime() < resultMap.get(raceVO.getName()).getTime()) {
            resultMap.put(raceVO.getName(), raceVO);
          } else if (raceVO.getTime() == resultMap.get(raceVO.getName()).getTime()) {
              if(raceVO.getId() < resultMap.get(raceVO.getName()).getId()){
                resultMap.put(raceVO.getName(), raceVO);
              }
          }
        } else {
          resultMap.put(raceVO.getName(), raceVO);
        }
      }
    });

    var newResultmap = new HashMap<String, Integer>(resultMap.size());
    for (var entry : resultMap.entrySet()) {
      newResultmap.put(entry.getKey(), entry.getValue().getId());
    }
    return newResultmap;
  }
}

class RaceVO {
  private int id;
  private String name;
  private int time;

  @Override
  public String toString() {
    return "RaceVO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", time=" + time +
        '}';
  }

  private RaceVO(int id, String name, int time) {
    this.id = id;
    this.name = name;
    this.time = time;
  }

  public static RaceVO build(String s) {
    try {
      var firstSplit = s.split("-");
      if (firstSplit.length != 2) {
        return null;
      }
      var raceId = Integer.parseInt(firstSplit[0].trim());
      var secondSplit = firstSplit[1].trim().split(":");
      if (secondSplit.length != 2) {
        return null;
      }
      var raceName = secondSplit[0].trim().replaceAll("\"", "");
      var timeTaken = Integer.parseInt(secondSplit[1].trim());
      return new RaceVO(raceId, raceName, timeTaken);
    } catch (Exception e) {
//      System.err.println(e.getMessage());
    }
    return null;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getTime() {
    return time;
  }
}
