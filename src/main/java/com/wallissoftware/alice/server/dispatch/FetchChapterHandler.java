package com.wallissoftware.alice.server.dispatch;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.wallissoftware.alice.shared.dispatch.FetchChapterAction;
import com.wallissoftware.alice.shared.dispatch.FetchChapterResult;

public class FetchChapterHandler extends AbstractActionHandler<FetchChapterAction, FetchChapterResult> {

    FetchChapterHandler() {
        super(FetchChapterAction.class);
    }

    @Override
    public FetchChapterResult execute(final FetchChapterAction action, final ExecutionContext context) throws ActionException {
        try {
            final FileInputStream fstream = new FileInputStream("WEB-INF/book/" + action.getChapterNumber() + ".txt");
            final DataInputStream in = new DataInputStream(fstream);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            final StringBuilder sb = new StringBuilder();
            String strLine;
            while ((strLine = br.readLine()) != null) {
                sb.append(strLine).append("\n");
            }

            in.close();
            return new FetchChapterResult(sb.toString());
        } catch (final IOException e) {
            return null;
        }

    }

}
