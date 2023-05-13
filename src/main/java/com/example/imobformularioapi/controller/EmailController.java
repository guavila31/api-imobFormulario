package com.example.imobformularioapi.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.imobformularioapi.model.EmailRequest;

import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {

        try {
            // Criar objeto MimeMessage usando o JavaMailSender
            MimeMessage message = javaMailSender.createMimeMessage();

            // Usar MimeMessageHelper para configurar remetente, destinatário, assunto e
            // conteúdo da mensagem
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(request.getRemetente());
            helper.setTo(request.getDestinatario());
            helper.setSubject(request.getAssunto());
            helper.setText(request.getConteudo(), true);

            // Enviar mensagem usando o JavaMailSender
            javaMailSender.send(message);

            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

    // @PostMapping("/send-email")
    // public ResponseEntity<String> sendEmail(
    // @RequestParam("destinatario") String destinatario,
    // @RequestParam("remetente") String remetente,
    // @RequestParam("assunto") String assunto,
    // @RequestParam("conteudo") String conteudo,
    // @RequestParam("arquivoRGComprador") MultipartFile arquivoRGComprador) {

    // try {
    // // Seu código de envio de e-mail aqui
    // // Criar objeto MimeMessage usando o JavaMailSender
    // MimeMessage message = javaMailSender.createMimeMessage();

    // // Usar MimeMessageHelper para configurar remetente, destinatário, assunto e
    // // conteúdo da mensagem
    // MimeMessageHelper helper = new MimeMessageHelper(message, true);
    // helper.setFrom(remetente);
    // helper.setTo(destinatario);
    // helper.setSubject(assunto);
    // helper.setText(conteudo, true);

    // // Anexar o arquivo arquivoRGComprador
    // if (arquivoRGComprador != null && !arquivoRGComprador.isEmpty()) {
    // // helper.addAttachment(arquivoRGComprador.getOriginalFilename(),
    // // arquivoRGComprador.getInputStream());
    // DataSource dataSource = new
    // ByteArrayDataSource(arquivoRGComprador.getInputStream(),
    // arquivoRGComprador.getContentType());
    // // System.out.println("Data:>>>>>>>>" + dataSource);
    // // Obter o nome do arquivo
    // String nomeArquivo = arquivoRGComprador.getOriginalFilename();

    // // Obter o conteúdo do arquivo em bytes
    // byte[] conteudoArquivo = arquivoRGComprador.getBytes();

    // // Adicionar o anexo ao MimeMessageHelper
    // System.out.println("conteudoArquivo: "+conteudoArquivo);
    // System.out.println("conteudoArquivo 2: "+conteudoArquivo);

    // ByteArrayInputStream stream = new ByteArrayInputStream(conteudoArquivo);
    // System.out.println("stream: "+stream);
    // System.out.println("dataSource: "+dataSource);
    // // helper.addAttachment(nomeArquivo, new ByteArrayResource(conteudoArquivo));
    // helper.addAttachment(nomeArquivo, dataSource);
    // }

    // // Enviar mensagem usando o JavaMailSender
    // javaMailSender.send(message);

    // return ResponseEntity.ok("E-mail enviado com sucesso!");
    // } catch (

    // Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Erro ao enviar e-mail: " + e.getMessage());
    // }
    // }

    // @PostMapping("/send-email")
    // public ResponseEntity<String> sendEmail(
    // @RequestBody EmailRequest emailRequest,
    // @RequestParam("arquivoRGComprador") MultipartFile arquivoRGComprador
    // ) {

    // try {
    // // Criar objeto MimeMessage usando o JavaMailSender
    // MimeMessage message = javaMailSender.createMimeMessage();

    // // Usar MimeMessageHelper para configurar remetente, destinatário, assunto e
    // // conteúdo da mensagem
    // MimeMessageHelper helper = new MimeMessageHelper(message, true);
    // helper.setFrom(emailRequest.getRemetente());
    // helper.setTo(emailRequest.getDestinatario());
    // helper.setSubject(emailRequest.getAssunto());
    // helper.setText(emailRequest.getConteudo(), true);

    // // Anexar o arquivo arquivoRGComprador
    // if (arquivoRGComprador != null && !arquivoRGComprador.isEmpty()) {
    // helper.addAttachment(arquivoRGComprador.getOriginalFilename(),
    // arquivoRGComprador);
    // }

    // // Enviar mensagem usando o JavaMailSender
    // javaMailSender.send(message);

    // // Restante do código do envio de e-mail...

    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Erro ao enviar e-mail: " + e.getMessage());
    // }
    // }

    @GetMapping("/teste")
    public String olaMundo() {
        return "Bem vindo, voce esta na API IMOB FORMULARIO";
    }
}
