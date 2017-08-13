package xyz.jeevan.api.access;

import xyz.jeevan.api.key.ResourceKey;

/**
 * @author jeevan
 */
public interface AccessResolver<T extends ResourceKey> {

  boolean check(T authKey);
}
