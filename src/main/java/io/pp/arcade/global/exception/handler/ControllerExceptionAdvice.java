package io.pp.arcade.global.exception.handler;

import io.pp.arcade.global.exception.AccessException;
import io.pp.arcade.global.exception.BusinessException;
import io.pp.arcade.global.exception.entity.ExceptionResponse;
import io.pp.arcade.global.util.ApplicationYmlRead;
import io.pp.arcade.global.util.AsyncMailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class ControllerExceptionAdvice {
    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;
    private final AsyncMailSender asyncMailSender;
    private final ApplicationYmlRead applicationYmlRead;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> constraintViolationExceptionHandle(ConstraintViolationException ex) {
        log.info("ConstraintViolationException", ex);
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            String message = messageSource.getMessage(filter(violation.getMessage()), null, Locale.KOREA);
            ExceptionResponse response = ExceptionResponse.from("E0001", "????????? ???????????????.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /**
     * enum type ???????????? ?????? binding ?????? ?????? ??????
     * ?????? @RequestParam enum?????? binding ????????? ?????? ??????
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> method???ArgumentTypeMismatchExceptionHandle(MethodArgumentTypeMismatchException ex) {
        log.error("MethodArgumentTypeMismatchException", ex);
        ExceptionResponse response = ExceptionResponse.from("E0001", "????????? ???????????????");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     *  javax.validation.Valid or @Validated ?????? binding error ????????? ????????????.
     *  HttpMessageConverter ?????? ????????? HttpMessageConverter binding ???????????? ??????
     *  ?????? @RequestBody, @RequestPart ????????????????????? ??????
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException ex) {
        log.info("MethodArgumentNotValidException", ex);
        ExceptionResponse response = ExceptionResponse.from("E0001", "????????? ???????????????");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * ???????????? ?????? HTTP method ?????? ??? ?????? ??????
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ExceptionResponse> httpRequestMethodNotSupportedExceptionHandle(HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException", ex);
        ExceptionResponse response = ExceptionResponse.from("405");
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ExceptionResponse> bindExceptionHandle(BindException ex) {
        log.error("BindException", ex);
        ExceptionResponse response = ExceptionResponse.from("E0001", "????????? ???????????????");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> httpRequestMethodNotSupportedExceptionHandle(BusinessException ex) throws MessagingException {
        log.error("BusinessException", ex);
        //String message = messageSource.getMessage(filter(ex.getMessage()), null, Locale.KOREA);
        ExceptionResponse response = ExceptionResponse.from(ex.getMessage());
//        sendMail(response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessException.class)
    protected ResponseEntity<ExceptionResponse> customAccessExceptionHandle(AccessException ex) throws URISyntaxException {
        //String message = messageSource.getMessage(filter(ex.getRedirectUrl()), null, Locale.KOREA);
        URI redirectUri = new URI(applicationYmlRead.getFrontLoginUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    private String filter(String message){
        message = message.replace("{", "");
        message = message.replace("}", "");
        return message;
    }

    private void sendMail(String err) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("???????????????????????? ??? ??????");
        helper.setTo("42seoularcade@gmail.com");
        helper.setText("New ERROR!!!!\n" +
                "\t" + err + "\nYou Have New ERROR in 42PingPong!");
        asyncMailSender.send(message);
    }
}
