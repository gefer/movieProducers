package br.com.texoit.movies.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Result {
    private boolean isValid;
    private String message;
    private int status;

    public static class Builder {

        private boolean isValid;
        private String message;
        private int status;

        public Builder isValid() {
            isValid = true;
            return this;
        }

        public Builder isNonValid() {
            isValid = false;
            return this;
        }

        public Builder setStatusOk() {
            this.status = 200;
            return this;
        }

        public Builder setStatusError(int status) {
            this.status = status;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }

    private Result(Builder builder) {
        this.message = builder.message;
    }
}
