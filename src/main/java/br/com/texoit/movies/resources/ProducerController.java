package br.com.texoit.movies.resources;

import br.com.texoit.movies.helpers.CsvHelper;
import br.com.texoit.movies.result.ProducerMovieResult;
import br.com.texoit.movies.result.Result;
import br.com.texoit.movies.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/")
public class ProducerController {

    private final String BASE_RESOURCE = "producers";

    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = BASE_RESOURCE, method = RequestMethod.GET)
    public ProducerMovieResult getProducers() {
        return producerService.getProducers();
    }

    @RequestMapping(value = BASE_RESOURCE + "/upload", method = RequestMethod.POST)
    public Result setMovies(@RequestParam("file") MultipartFile file) throws Exception {
        if (!CsvHelper.hasCSVFormat(file)) {
            return new Result.Builder().isNonValid().setMessage("Arquivo deve ser no formato csv!").build();
        }
        this.producerService.processMovies(file);
        return new Result.Builder().isValid().setMessage("Arquivo enviado e validado com sucesso!").build();
    }
}
