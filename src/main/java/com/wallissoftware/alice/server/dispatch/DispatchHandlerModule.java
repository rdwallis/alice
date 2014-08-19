package com.wallissoftware.alice.server.dispatch;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.wallissoftware.alice.shared.dispatch.FetchChapterAction;

public class DispatchHandlerModule extends HandlerModule {



    @Override
    protected void configureHandlers() {
        bindHandler(FetchChapterAction.class, FetchChapterHandler.class);
    }

}