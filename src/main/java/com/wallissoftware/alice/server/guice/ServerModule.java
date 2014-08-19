package com.wallissoftware.alice.server.guice;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.wallissoftware.alice.server.dispatch.DispatchHandlerModule;

public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        install(new DispatchHandlerModule());
    }
}