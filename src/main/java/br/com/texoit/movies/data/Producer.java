package br.com.texoit.movies.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
public class Producer {
    private int year;
    private String title;
    private String studios;
    private String producers;
    private String winner;

    @Override
    public String toString() {
        return getProducers();
    }
}
