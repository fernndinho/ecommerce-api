package me.fernndinho.shop.shared.error;


import me.fernndinho.shop.shared.error.exceptions.ConflictException;
import me.fernndinho.shop.shared.error.exceptions.BadRequestException;
import me.fernndinho.shop.shared.error.exceptions.NotAuthorizedException;
import me.fernndinho.shop.shared.error.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ResponseError> handleNotFound(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResponseError response = new ResponseError(e.getLocalizedMessage());

        return ResponseEntity.status(status).body(response);
    }


    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ResponseError> handleBadRequest(BadRequestException e) {
        ResponseError response = new ResponseError(e.getLocalizedMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<ResponseError> handleConflictExist(ConflictException e) {
        ResponseError response = new ResponseError(e.getLocalizedMessage());

        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(value = {NotAuthorizedException.class})
    public ResponseEntity<ResponseError> handleNotAuthorized(NotAuthorizedException e) {
        ResponseError response = new ResponseError(e.getLocalizedMessage());

        return ResponseEntity.status(401).body(response);
    }


}
