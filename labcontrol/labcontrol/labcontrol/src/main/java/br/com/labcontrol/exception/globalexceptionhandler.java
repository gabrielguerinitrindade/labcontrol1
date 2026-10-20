package br.com.labcontrol.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class globalexceptionhandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(
            MethodArgumentNotValidException ex) {

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("status", 400);
        resposta.put("erro", "Dados inválidos");

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .findFirst()
                .orElse("Verifique os dados enviados.");

        resposta.put("mensagem", mensagem);

        return ResponseEntity.badRequest().body(resposta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> tratarConflito(
            IllegalArgumentException ex) {

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("status", 409);
        resposta.put("erro", "Conflito");
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(resposta);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> tratarIntegridade(
            DataIntegrityViolationException ex) {

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("status", 409);
        resposta.put("erro", "Conflito");
        resposta.put("mensagem",
                "O patrimônio já existe ou os dados violam uma restrição do banco.");

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(resposta);
    }
    @ExceptionHandler(equipamentonaoencontradoexception.class)
    public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(
            equipamentonaoencontradoexception ex) {

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("status", 404);
        resposta.put("erro", "Não encontrado");
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(resposta);
    }
}