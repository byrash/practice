package com.shivaji.poc;

import static org.junit.jupiter.api.Assertions.*;

class CartSolutionTest {

  @org.junit.jupiter.api.Test
  void isCartsTotalMatching_HappyPath() {
    var cartList ="CartId ItemId ItemCost\n" +
        "1 1 150\n" +
        "1 1 150\n" +
        "1 2 75\n" +
        "2 1 150\n" +
        "2 3 10\n" +
        "2 7 55\n" +
        "3 3 10\n" +
        "3 8 120\n" +
        "3 1 150";
    var cartTotals = "CartId TotalCostOfItems\n" +
        "1 375\n" +
        "2 215\n" +
        "3 280";
    assertEquals(true, CartSolution.isCartsTotalMatching(cartList,cartTotals));
  }

  @org.junit.jupiter.api.Test
  void isCartsTotalMatching_InvalidFormat() {
    var cartList ="CartId ItemId ItemCost\n" +
        "1 1\n" +
        "1 1\n" +
        "1 2 75\n" +
        "2 1 150\n" +
        "2 3 10\n" +
        "2 7 55\n" +
        "3 3 10\n" +
        "3 8 120\n" +
        "3 1 150";
    var cartTotals = "CartId TotalCostOfItems\n" +
        "1 375\n" +
        "2 215\n" +
        "3 280";
    assertEquals(false, CartSolution.isCartsTotalMatching(cartList,cartTotals));
  }

  @org.junit.jupiter.api.Test
  void isCartsTotalMatching_CartsDidntMatch() {
    var cartList ="CartId ItemId ItemCost\n" +
        "1 1 150\n" +
        "1 1 150\n" +
        "1 2 75\n" +
        "2 1 150\n" +
        "2 3 10\n" +
        "2 7 55\n" +
        "3 3 10\n" +
        "3 8 120\n" +
        "3 1 150";
    var cartTotals = "CartId TotalCostOfItems\n" +
        "1 475\n" +
        "2 215\n" +
        "3 280";
    assertEquals(false, CartSolution.isCartsTotalMatching(cartList,cartTotals));
  }


  @org.junit.jupiter.api.Test
  void isCartsTotalMatching_NoItems() {
    var cartList ="CartId ItemId ItemCost\n" ;
    var cartTotals = "CartId TotalCostOfItems\n" +
        "1 475\n" +
        "2 215\n" +
        "3 280";
    assertEquals(false, CartSolution.isCartsTotalMatching(cartList,cartTotals));
  }

  @org.junit.jupiter.api.Test
  void isCartsTotalMatching_NullPassed() {
    assertEquals(false, CartSolution.isCartsTotalMatching(null,null));
  }

}