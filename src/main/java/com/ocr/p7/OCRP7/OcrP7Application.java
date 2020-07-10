package com.ocr.p7.OCRP7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class OcrP7Application {

    public static void main(String[] args) {
        SpringApplication.run(OcrP7Application.class, args);
    }

}
