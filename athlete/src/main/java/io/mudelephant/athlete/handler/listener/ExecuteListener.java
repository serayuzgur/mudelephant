package io.mudelephant.athlete.handler.listener;

import io.mudelephant.athlete.resource.MethodEntry;

/**
 * Created by serayuzgur on 26/03/15.
 */
public interface ExecuteListener {

    void before(MethodEntry entry);

    void success(MethodEntry entry);

    void error(MethodEntry entry, Exception e);

}
