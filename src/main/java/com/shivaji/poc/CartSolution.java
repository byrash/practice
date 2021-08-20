package com.shivaji.poc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CartSolution {

  public static boolean isCartsTotalMatching(String cartList, String totalList) {
    if (Objects.isNull(cartList) || Objects.isNull(totalList)) {
      return false;
    }
    Map<Integer, Integer> cartVOTotalMap = new HashMap<>();
    cartList.lines().forEachOrdered(s -> {
      CartVO cartVO = CartVO.build(s.trim());
      if (cartVO != null) {
        if (cartVOTotalMap.containsKey(cartVO.getCartId())) {
          cartVOTotalMap.put(cartVO.getCartId(), cartVOTotalMap.get(cartVO.getCartId()) + cartVO.getTotalCost());
        } else {
          cartVOTotalMap.put(cartVO.getCartId(), cartVO.getTotalCost());
        }
      } else {
        System.err.println("Ignoring [" + s + "] as either header or arid record");
      }
    });

//    System.out.println(cartVOTotalMap);

    Map<Integer, Integer> cartTotalsMap = new HashMap<>();
    totalList.lines().forEachOrdered(s -> {
      CartTotals cartTotals = CartTotals.build(s.trim());
      if (cartTotals != null) {
        if (cartTotalsMap.containsKey(cartTotals.getCartId())) {
          cartTotalsMap.put(cartTotals.getCartId(), cartTotalsMap.get(cartTotals.getCartId()) + cartTotals.getTotalCostOfItems());
        } else {
          cartTotalsMap.put(cartTotals.getCartId(), cartTotals.getTotalCostOfItems());
        }
      } else {
        System.err.println("Ignoring [" + s + "] as either header or arid record");
      }
    });

//    System.out.println(cartTotalsMap);

    boolean isAnyMisMatched = true;
    for (var entrySet : cartTotalsMap.entrySet()) {
      if(cartVOTotalMap.containsKey(entrySet.getKey())) {
        isAnyMisMatched = isAnyMisMatched && cartVOTotalMap.get(entrySet.getKey()).equals(entrySet.getValue());
      }
    }

    return isAnyMisMatched && !cartVOTotalMap.isEmpty() && !cartTotalsMap.isEmpty();
  }

}

class CartTotals {
  private int cartId;
  private int totalCostOfItems;

  private CartTotals(int cartId, int totalCostOfItems) {
    this.cartId = cartId;
    this.totalCostOfItems = totalCostOfItems;
  }

  public static CartTotals build(String s) {
    try {
      var splits = s.split("\\s");
      if(splits.length != 2){
        return null;
      }
      var cId = Integer.parseInt(splits[0].trim());
      var total = Integer.parseInt(splits[1].trim());
      return new CartTotals(cId, total);
    } catch (Exception e) { // Ignore Arid records
//      System.out.println(e.getMessage());
    }
    return null;
  }

  public int getCartId() {
    return cartId;
  }

  public int getTotalCostOfItems() {
    return totalCostOfItems;
  }
}

class CartVO {
  private int cartId;
  private int teamId;
  private int totalCost;

  public static CartVO build(String s) {
    try {
      var splits = s.split("\\s");
      if(splits.length != 3){
        return null;
      }
      var cId = Integer.parseInt(splits[0].trim());
      var tId = Integer.parseInt(splits[1].trim());
      var total = Integer.parseInt(splits[2].trim());
      return new CartVO(cId, tId, total);
    } catch (NumberFormatException e) { // Ignore Arid records
//      System.out.println(e.getMessage());
    }
    return null;
  }

  private CartVO(int cartId, int teamId, int totalCost) {
    this.cartId = cartId;
    this.teamId = teamId;
    this.totalCost = totalCost;
  }

  public int getCartId() {
    return cartId;
  }

  public int getTeamId() {
    return teamId;
  }

  public int getTotalCost() {
    return totalCost;
  }
}
