package com.wallissoftware.alice.server.page;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.gwtplatform.common.shared.UrlUtils;
import com.gwtplatform.mvp.shared.proxy.PlaceTokenRegistry;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;
import com.wallissoftware.alice.shared.place.AllPlaceTokens;

public class PageModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(UrlUtils.class).to(ServerUrlUtils.class).in(Singleton.class);
        bind(TokenFormatter.class).to(RouteTokenFormatter.class).in(Singleton.class);
        bind(PlaceTokenRegistry.class).to(AllPlaceTokens.class).in(Singleton.class);

        serve("/").with(PageServlet.class);
        for (final String token : AllPlaceTokens.get()) {
            serveNameToken(token).with(PageServlet.class);

        }
    }

    private ServletKeyBindingBuilder serveNameToken(String token) {
        final int firstSlash = token.indexOf("/", 1);
        token = firstSlash == -1 ? token : token.substring(0, firstSlash);
        return serve(token + "*");
    }

}
