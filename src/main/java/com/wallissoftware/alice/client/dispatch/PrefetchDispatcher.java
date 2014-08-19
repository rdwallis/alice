package com.wallissoftware.alice.client.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.seanchenxi.gwt.storage.client.serializer.StorageSerializer;
import com.wallissoftware.alice.shared.dispatch.DefaultActionImpl;

public class PrefetchDispatcher {
    private final DispatchAsync dispatchAsync;
    private final StorageSerializer storageSerializer;

    @Inject
    PrefetchDispatcher(final DispatchAsync dispatchAsync, final StorageSerializer storageSerializer) {
        this.dispatchAsync = dispatchAsync;
        this.storageSerializer = storageSerializer;
    }

    public <A extends DefaultActionImpl<R>, R extends Result> void execute(final A action, final AsyncCallback<R> callback) {
        final R result = getPrefetchResult(action);
        if (result != null) {
            callback.onSuccess(result);
        } else {
            dispatchAsync.execute(action, callback);
        }
    }

    private <A extends DefaultActionImpl<R>, R extends Result> R getPrefetchResult(final A action) {
        try {

            if (PrefetchCache.get().containsKey(action.getKey())) {
                final String preFetchedString = PrefetchCache.get().get(action.getKey()).isString().stringValue();
                // only use prefetch for first call all others go to the server
                PrefetchCache.get().put(action.getKey(), null);
                return storageSerializer.deserialize(action.getResultClass(), preFetchedString);
            }
        } catch (final SerializationException e) {
        }
        return null;
    }


}
