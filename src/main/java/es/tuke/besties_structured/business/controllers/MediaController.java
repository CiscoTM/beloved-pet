package es.tuke.besties_structured.business.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import es.tuke.besties_structured.business.services.StorageService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("media")
@AllArgsConstructor
public class MediaController {
    
    private final StorageService storageService;


    @PostMapping("upload")
    public Map<String, String> uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException{

        /**
         * Nombre del archivo y su extension recibido como parámetro.
         */
        String path = storageService.store(multipartFile);        
        
        /**
         * Map con la url del archivo una vez guardado.
         */
        return storageService.uploadFile(path);

    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException{

        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, contentType)
        .body(file);
        
    }

}
