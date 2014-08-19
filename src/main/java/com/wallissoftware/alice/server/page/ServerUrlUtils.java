package com.wallissoftware.alice.server.page;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.gwtplatform.common.shared.UrlUtils;

public class ServerUrlUtils implements UrlUtils {

	@Override
	public String decodePathSegment(final String encodedPathSegment) {
		try {
			return URLDecoder.decode(encodedPathSegment, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			//THIS won't happen
			return null;
		}
	}

	@Override
	public String decodeQueryString(final String encodedUrlComponent) {
		try {
			return URLDecoder.decode(encodedUrlComponent, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			//THIS won't happen
			return null;
		}
	}

	@Override
	public String encodePathSegment(final String decodedPathSegment) {
		try {
			return URLEncoder.encode(decodedPathSegment, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			//THIS won't happen
			return null;
		}
	}

	@Override
	public String encodeQueryString(final String decodedUrlComponent) {
		try {
			return URLEncoder.encode(decodedUrlComponent, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			//THIS won't happen
			return null;
		}
	}

}
