package xyz.jeevan.api.utils;

import java.util.UUID;

/**
 * Created by jeevan on 10/6/17.
 */
public class UniqueIdGenerator {

  public static String generate() {
    return UUID.randomUUID().toString();
  }
}
