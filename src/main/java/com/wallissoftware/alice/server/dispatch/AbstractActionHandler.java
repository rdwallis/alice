/**
 * Copyright 2012 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.wallissoftware.alice.server.dispatch;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Simple abstract super-class for
 * {@link com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler}
 * implementations that forces the
 * {@link com.gwtplatform.dispatch.shared.Action} class to be passed in as a
 * constructor to the handler.
 *
 * @param <A>
 *            The {@link com.gwtplatform.dispatch.shared.Action} type.
 * @param <R>
 *            The {@link com.gwtplatform.dispatch.shared.Result} type.
 */
public abstract class AbstractActionHandler<A extends Action<R>, R extends Result> implements ActionHandler<A, R> {
    private final Class<A> actionType;

    public AbstractActionHandler(final Class<A> actionType) {
        this.actionType = actionType;
    }

    public ResultWithRpcString<R> executeToRPCString(final A action, final ExecutionContext context) throws ActionException {
        final R result = execute(action, context);
        try {
            final String rpcString = RPC.encodeResponseForSuccess(getClass().getMethod("execute", Action.class, ExecutionContext.class), result).substring(4);
            return new ResultWithRpcString<R>(result, rpcString);
        } catch (NoSuchMethodException | SecurityException | SerializationException e) {
            return new ResultWithRpcString<R>(result, "error=" + e.getMessage());
        }
    }

    @Override
    public Class<A> getActionType() {
        return actionType;
    }

    @Override
    public void undo(final A action, final R result, final ExecutionContext context) throws ActionException {
    }

    protected void destroy() {
    }
}