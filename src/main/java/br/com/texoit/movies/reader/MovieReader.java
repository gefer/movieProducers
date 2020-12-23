package br.com.texoit.movies.reader;

import br.com.texoit.movies.data.Producer;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class MovieReader {

    public static List<Producer> readMovies(File file) throws Exception {
        return new CsvToBeanBuilder(new FileReader(file))
                .withType(Producer.class)
                .withIgnoreQuotations(true)
                .withSeparator(';')
                .withThrowExceptions(false)
                .build()
                .parse();
    }
}
