package com.spark.model;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
public class CustomMultipartResolver extends CommonsMultipartResolver {

    @Autowired
    private UploadProgressListenner uploadProgressListenner;
    private final Logger logger = LoggerFactory.getLogger(CustomMultipartResolver.class);
    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        uploadProgressListenner.setSession(request.getSession());
        fileUpload.setProgressListener(uploadProgressListenner);

        try {
            List<FileItem> fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
            return parseFileItems(fileItems,encoding);
        }catch (FileUploadBase.SizeLimitExceededException es){
            logger.error(es.getMessage());

        }catch (FileUploadBase.FileSizeLimitExceededException e2){
            logger.error(e2.getMessage());
        }
        catch (FileUploadException e3){
            logger.error(e3.getMessage());
        }

        return super.parseRequest(request);
    }
}
