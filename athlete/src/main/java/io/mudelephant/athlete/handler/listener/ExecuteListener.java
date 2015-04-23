package io.mudelephant.athlete.handler.listener;

import io.mudelephant.athlete.resource.ServiceInfo;

public interface ExecuteListener {

    void before(ServiceInfo entry);

    void success(ServiceInfo entry);

    void error(ServiceInfo entry, Exception e);

}
