package com.wallissoftware.alice.client.application.home;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;
import com.wallissoftware.alice.client.application.ApplicationPresenter;
import com.wallissoftware.alice.client.dispatch.PrefetchDispatcher;
import com.wallissoftware.alice.shared.dispatch.FetchChapterAction;
import com.wallissoftware.alice.shared.dispatch.FetchChapterResult;
import com.wallissoftware.alice.shared.place.NameTokens;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy> {
    @ProxyStandard
    @NameToken(NameTokens.alice)
    interface MyProxy extends ProxyPlace<HomePagePresenter> {
    }

    interface MyView extends View {

        void setChapter(final String chapterText);

        void setPrevVisible(final boolean visible);

        void setNextVisible(final boolean visible);

        void setPrevTargetHistoryToken(final String nameToken);

        void setNextTargetHistoryToken(final String nameToken);
    }

    private final PrefetchDispatcher dispatcher;
    private TokenFormatter tokenFormatter;

    @Inject
    HomePagePresenter(final EventBus eventBus, final MyView view,
 final MyProxy proxy, final PrefetchDispatcher dispatcher, final TokenFormatter tokenFormatter) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);

        this.dispatcher = dispatcher;
        this.tokenFormatter = tokenFormatter;
    }

    @Override
    public void prepareFromRequest(final PlaceRequest request) {
        final int chapter = Integer.valueOf(request.getParameter("chapterNumber", "1"));
        getView().setPrevVisible(chapter > 1);
        getView().setNextVisible(chapter < 12);

        getView().setPrevTargetHistoryToken(getNameToken(chapter - 1));
        getView().setNextTargetHistoryToken(getNameToken(chapter + 1));

        dispatcher.execute(new FetchChapterAction(chapter), new ManualRevealCallback<FetchChapterResult>(this) {

            @Override
            public void onFailure(final Throwable caught) {
                Window.alert("Error: " + caught.getMessage());
                super.onFailure(caught);
            }

            @Override
            public void onSuccess(final FetchChapterResult result) {
                getView().setChapter(result.getChapter());
                super.onSuccess(result);
            }
        });

    }

    @Override
    public boolean useManualReveal() {
        return true;
    }

    private String getNameToken(final int chapterNumber) {
        final PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.alice).with("chapterNumber", chapterNumber + "").build();
        return tokenFormatter.toPlaceToken(placeRequest);
    }

}