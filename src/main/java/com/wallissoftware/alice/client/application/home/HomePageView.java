package com.wallissoftware.alice.client.application.home;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomePageView> {
    }

    @UiField HasHTML chapterHtml;

    @UiField Hyperlink nextChapter, prevChapter;

    @Inject
    HomePageView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setChapter(final String chapterText) {
        final StringBuilder sb = new StringBuilder();
        for (final String para : chapterText.split("\n\n")) {
            sb.append("<p>").append(para).append("</p>");
        }
        chapterHtml.setHTML(sb.toString());
    }

    @Override
    public void setNextTargetHistoryToken(final String nameToken) {
        nextChapter.setTargetHistoryToken(nameToken);

    }

    @Override
    public void setNextVisible(final boolean visible) {
        nextChapter.setVisible(visible);

    }

    @Override
    public void setPrevTargetHistoryToken(final String nameToken) {
        prevChapter.setTargetHistoryToken(nameToken);

    }

    @Override
    public void setPrevVisible(final boolean visible) {
        prevChapter.setVisible(visible);

    }
}