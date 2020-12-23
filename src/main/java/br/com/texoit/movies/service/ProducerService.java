package br.com.texoit.movies.service;

import br.com.texoit.movies.data.Producer;
import br.com.texoit.movies.data.ProducerMovie;
import br.com.texoit.movies.reader.MovieReader;
import br.com.texoit.movies.result.ProducerMovieResult;
import br.com.texoit.movies.utils.FilesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    List<Producer> producers = new ArrayList<>();

    public void processMovies(MultipartFile multipartFile) throws Exception {
        this.producers = MovieReader.readMovies(FilesUtils.multipartToFile(multipartFile, "movieslist.csv"));
    }

    public ProducerMovieResult getProducers() {
        return new ProducerMovieResult
                .Builder()
                .withMinProducers(getProducersWithMinInterval())
                .withMaxProducers(getProducersWithMaxInterval())
                .build();
    }

    private List<ProducerMovie> getProducersWithMaxInterval() {
        List<ProducerMovie> producers = new ArrayList(getMapProducers().values());
        return producers.stream().filter(p -> p.getInterval() == producers.stream().mapToInt(ProducerMovie::getInterval).max().getAsInt()).collect(Collectors.toList());
    }

    private List<ProducerMovie> getProducersWithMinInterval() {
        List<ProducerMovie> producers = new ArrayList(getMapProducers().values());
        return producers.stream().filter(p -> p.getInterval() == producers.stream().mapToInt(ProducerMovie::getInterval).min().getAsInt()).collect(Collectors.toList());
    }

    private List<Producer> getWinners() {
        return producers.stream().filter(p -> p.getWinner().equalsIgnoreCase("yes")).collect(Collectors.toList());
    }

    private HashMap<String, ProducerMovie> getMapProducers() {
        HashMap<String, ProducerMovie> mapProducers = new HashMap<>();

        getWinners().forEach(p -> {
            if (!mapProducers.containsKey(p.getProducers())) {
                mapProducers.put(p.getProducers(), new ProducerMovie.Builder()
                        .addProducer(p.getProducers())
                        .addInterval(0)
                        .addPreviousWin(0)
                        .addFollowingWin(p.getYear())
                        .build()
                );
            } else {
                ProducerMovie producerMovieOld = mapProducers.get(p.getProducers());
                mapProducers.put(p.getProducers(), new ProducerMovie.Builder()
                        .addPreviousWin(producerMovieOld.getFollowingWin())
                        .addFollowingWin(p.getYear())
                        .addInterval(p.getYear() - producerMovieOld.getFollowingWin())
                        .build()
                );
            }
        });

        return mapProducers;
    }
}
