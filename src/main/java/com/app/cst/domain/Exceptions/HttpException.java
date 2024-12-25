package com.app.cst.domain.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HttpException extends RuntimeException {
     private HttpStatus status;

     public HttpException(HttpStatus status, String message) {
          super(message);
          this.status = status;
     }
}
