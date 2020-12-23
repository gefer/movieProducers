package br.com.texoit.movies.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProducerMovie {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public static class Builder {

        private String producer;
        private int interval;
        private int previousWin;
        private int followingWin;

        public Builder addProducer(String producer) {
            this.producer = producer;
            return this;
        }

        public Builder addInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder addPreviousWin(int previousWin) {
            this.previousWin = previousWin;
            return this;
        }

        public Builder addFollowingWin(int followingWin) {
            this.followingWin = followingWin;
            return this;
        }

        public ProducerMovie build() {
            return new ProducerMovie(this);
        }
    }

    private ProducerMovie(Builder builder) {
        this.producer = builder.producer;
        this.interval = builder.interval;
        this.previousWin = builder.previousWin;
        this.followingWin = builder.followingWin;
    }
}
