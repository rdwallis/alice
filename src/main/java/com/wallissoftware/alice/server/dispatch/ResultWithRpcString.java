package com.wallissoftware.alice.server.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;

public class ResultWithRpcString<R extends Result> {

    private final R result;
    private final String rpcString;

    ResultWithRpcString(final R result, final String rpcString) {
        this.result = result;
        this.rpcString = rpcString;
    }

    public R getResult() {
        return result;
    }

    public String getRpcString() {
        return rpcString;
    }

}
