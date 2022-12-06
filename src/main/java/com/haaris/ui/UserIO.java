package com.haaris.ui;

public interface UserIO {
  String input();

  String inputCaseSensitive();

  void out(String out);

  void outln(String out);

  void close();
}
