package com.spark.model;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UploadProgressListenner implements ProgressListener {
    private HttpSession session;
    public void setSession(HttpSession session){
        this.session = session;
        ProgressEntity status = new ProgressEntity();
        session.setAttribute("status",status);

    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        ProgressEntity status = (ProgressEntity)session.getAttribute("status");
        status.setpBytesRead(pBytesRead);
        status.setpContentLength(pContentLength);
        status.setpItems(pItems);
    }
}
