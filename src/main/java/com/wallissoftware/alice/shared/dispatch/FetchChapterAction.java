package com.wallissoftware.alice.shared.dispatch;

public class FetchChapterAction extends DefaultActionImpl<FetchChapterResult> {

    int chapterNumber;

    public FetchChapterAction(final int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    @SuppressWarnings("unused")
    private FetchChapterAction() {
    }

    public int getChapterNumber() {
        return chapterNumber;
    }


    @Override
    public String getKey() {
        return "FCA" + chapterNumber;
    }

    @Override
    public Class<FetchChapterResult> getResultClass() {
        return FetchChapterResult.class;
    }

    @Override
    public boolean isSecured() {
        return false;
    }

}
