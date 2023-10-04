package med.voll.api.model.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    /**
     * Aqui devolveremos um Json simplificado  ao invés da pilha que o spring envia por padrão.
     *
     * @param exception caso a exceção seja 400 (Bad Request).
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorsValidation::new).toList());
    }

    /**
     *
     * @param field campo do erro
     * @param mesage mensagem do erro
     */
    private record DataErrorsValidation (String field, String mesage){
        public DataErrorsValidation(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

    }

}
