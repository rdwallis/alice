package com.wallissoftware.alice.shared.place;

import java.util.HashSet;
import java.util.Set;

import com.gwtplatform.mvp.shared.proxy.PlaceTokenRegistry;

public class AllPlaceTokens implements PlaceTokenRegistry {

    public static Set<String> get() {
        return placeTokens;
    }

    private final static Set<String> placeTokens = new HashSet<String>();

    static {
        placeTokens.add(NameTokens.alice);
    }

    @Override
    public Set<String> getAllPlaceTokens() {
        return placeTokens;
    }
}
