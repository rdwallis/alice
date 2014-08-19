package com.wallissoftware.alice.client.dispatch;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public final class PrefetchCache {

    public static JSONObject get() {
        return new JSONObject(getNative());
    }

    private static final native JavaScriptObject getNative() /*-{
        return $wnd.PREFETCH_DISPATCH || {};
    }-*/;

}
