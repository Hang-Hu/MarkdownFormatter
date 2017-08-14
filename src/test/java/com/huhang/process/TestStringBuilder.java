package com.huhang.process;

import org.junit.Test;

/**
 * Created by joanna on 5/20/17.
 */

public class TestStringBuilder {
  @Test
  public void test(){
    String s = new StringBuilder("hello world!").insert(3, '#').toString();
    System.out.println(s);
  }
}
