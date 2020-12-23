package br.com.texoit.movies.helpers;

import org.springframework.web.multipart.MultipartFile;

public class CsvHelper {

    public static boolean hasCSVFormat(MultipartFile file) {
        return file.getContentType().equals("text/csv");
    }
}
