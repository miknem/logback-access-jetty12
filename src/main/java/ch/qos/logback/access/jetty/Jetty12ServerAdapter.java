/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2015, QOS.ch. All rights reserved.
 * <p>
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 * <p>
 * or (per the licensee's choosing)
 * <p>
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.access.jetty;

import ch.qos.logback.access.spi.ServerAdapter;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * A Jetty 12.specific implementation of the {@link ServerAdapter} interface.
 *
 * @author S&eacute;bastien Pennec
 * @author Ceki Gulcu
 * @author Joakim Erdfelt
 */
public class Jetty12ServerAdapter extends JettyServerAdapter {


    public Jetty12ServerAdapter(Request jettyRequest, Response jettyResponse) {
        super(jettyRequest, jettyResponse);
    }

    @Override
    public long getContentLength() {
        return Response.getContentBytesWritten(response);
    }

    @Override
    public int getStatusCode() {
        return Response.getOriginalResponse(response).getStatus();
    }

    @Override
    public long getRequestTimestamp() {
        return Request.getTimeStamp(request);
    }

    @Override
    public Map<String, String> buildResponseHeaderMap() {
        Map<String, String> responseHeaderMap = new HashMap<String, String>();
        HttpFields httpFields = Response.getOriginalResponse(response).getHeaders().get();
        for (HttpField field : httpFields) {
            String key = field.getName();
            String value = field.getValue();
            responseHeaderMap.put(key, value);
        }
        return responseHeaderMap;
    }

}
