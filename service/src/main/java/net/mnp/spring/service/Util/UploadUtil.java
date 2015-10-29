package net.mnp.spring.service.Util;

import com.sksamuel.diffpatch.DiffMatchPatch;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dmitry Natalenko on 29.10.2015.
 */
@Component
public class UploadUtil {

    public List getDiffFromFiles(MultipartFile multipart1, MultipartFile multipart2) throws IOException {
        File file1 = multipartToFile(multipart1);
        File file2 = multipartToFile(multipart2);
        return diff(fileToString(file1), fileToString(file2));
    }

    public List diff(String str1, String str2){
        DiffMatchPatch difference = new DiffMatchPatch();
        LinkedList<DiffMatchPatch.Diff> deltas = difference.diff_main(str1, str2);
        return deltas;
    }

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException
    {
        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

    public String fileToString(File file) throws IOException {
       return FileUtils.readFileToString(file);
    }
}
