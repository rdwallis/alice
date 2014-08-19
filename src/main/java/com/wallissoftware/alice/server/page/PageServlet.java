package com.wallissoftware.alice.server.page;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;
import com.wallissoftware.alice.server.dispatch.AbstractActionHandler;
import com.wallissoftware.alice.server.dispatch.FetchChapterHandler;
import com.wallissoftware.alice.server.dispatch.ResultWithRpcString;
import com.wallissoftware.alice.shared.dispatch.DefaultActionImpl;
import com.wallissoftware.alice.shared.dispatch.FetchChapterAction;
import com.wallissoftware.alice.shared.dispatch.FetchChapterResult;
import com.wallissoftware.alice.shared.place.NameTokens;

@Singleton
public class PageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @SuppressWarnings("rawtypes") private final Set<AbstractActionHandler> actionHandlers = new HashSet<AbstractActionHandler>();

    private final TokenFormatter tokenFormatter;

    @Inject
    PageServlet(final FetchChapterHandler fetchChapterHandler,  final TokenFormatter tokenFormatter) {
        this.tokenFormatter = tokenFormatter;
        actionHandlers.add(fetchChapterHandler);
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String nameToken = req.getRequestURI().substring(1);

        final Map<String, String> prefetchedDispatchActions = new HashMap<String, String>();

        if (nameToken.isEmpty()) {
            nameToken = NameTokens.alice;
        }

        final PlaceRequest request = tokenFormatter.toPlaceRequest(nameToken);

        final StringBuilder noScriptContent = new StringBuilder();


        if (request.matchesNameToken(NameTokens.alice)) {
            final int chapterNumber = Integer.valueOf(request.getParameter("chapterNumber", "1"));
            req.setAttribute("windowTitle", "Alice in Wonderland: Chapter " + chapterNumber);

            final FetchChapterResult fetchChapterResult = execute(prefetchedDispatchActions, new FetchChapterAction(chapterNumber));
            renderChapter(noScriptContent, fetchChapterResult.getChapter());
        }

        req.setAttribute("prefetchDispatch", new Gson().toJson(prefetchedDispatchActions));
        req.setAttribute("noScript", noScriptContent.toString());
        resp.setContentType("text/html");
        req.getRequestDispatcher("/Alice.jsp").include(req, resp);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <A extends DefaultActionImpl<R>, R extends Result> R execute(final Map<String, String> prefetchDispatchActions, final DefaultActionImpl<R> action) {
        for (final AbstractActionHandler actionHandler : actionHandlers) {
            if (actionHandler.getActionType().equals(action.getClass())) {
                try {
                    final ResultWithRpcString<R> resultWithRpcString = actionHandler.executeToRPCString(action, null);
                    prefetchDispatchActions.put(action.getKey(), resultWithRpcString.getRpcString());
                    return resultWithRpcString.getResult();
                } catch (final ActionException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        throw new RuntimeException("No Action Handler for: " + action.getServiceName());
    }

    private void renderChapter(final StringBuilder sb, final String chapterText) {
        for (final String para : chapterText.split("\n\n")) {
            sb.append("<p>").append(para).append("</p>");
        }
    }
}
