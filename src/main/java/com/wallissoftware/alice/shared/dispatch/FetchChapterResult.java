package com.wallissoftware.alice.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;

public class FetchChapterResult implements Result {

    private static final long serialVersionUID = 1L;

    private String chapter;

    public FetchChapterResult(final String chapter) {
        this.chapter = chapter;
    }

    @SuppressWarnings("unused")
    private FetchChapterResult() {

    }

    public String getChapter() {
        return chapter;
    }

}
