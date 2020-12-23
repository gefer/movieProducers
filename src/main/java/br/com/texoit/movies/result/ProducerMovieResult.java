package br.com.texoit.movies.result;

import br.com.texoit.movies.data.ProducerMovie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProducerMovieResult extends Result {
    private List<ProducerMovie> min;
    private List<ProducerMovie> max;

    public static class Builder {
        private List<ProducerMovie> min;
        private List<ProducerMovie> max;

        public Builder withMinProducers(List<ProducerMovie> producerMovies) {
            this.min = producerMovies;
            return this;
        }

        public Builder withMaxProducers(List<ProducerMovie> producerMovies) {
            this.max = producerMovies;
            return this;
        }

        public ProducerMovieResult build() {
            return new ProducerMovieResult(this);
        }
    }

    private ProducerMovieResult(Builder builder) {
        this.min = builder.min;
        this.max = builder.max;
        super.setValid(true);
        super.setStatus(200);
    }
}
