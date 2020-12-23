package br.com.texoit.movies.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FilesUtils {

    public static File multipartToFile(MultipartFile multipart, String fileName) throws Exception {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }
}
