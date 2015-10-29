package net.mnp.spring.view;

import net.mnp.spring.service.Util.UploadUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Controller
public class MainPageController {

    private static Logger LOG = Logger.getLogger(MainPageController.class.getName());

    @Autowired
    private UploadUtil uploadUtil;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String defaultPage() {
        return "index.page";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List handleFileUpload(@RequestParam("file1") MultipartFile file1,
                                 @RequestParam("file2") MultipartFile file2) {
        List deltas = new LinkedList<>();
        try {
            deltas =  uploadUtil.getDiffFromFiles(file1, file2);
        } catch (IOException e) {
            LOG.error("IOException in /upload ", e);
        }
        return deltas;
    }


    @RequestMapping(value = "/exception")
    public String showExceptionPage() {
        return "exception.page";
    }

}
