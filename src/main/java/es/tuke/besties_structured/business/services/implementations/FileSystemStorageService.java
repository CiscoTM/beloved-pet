package es.tuke.besties_structured.business.services.implementations;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.tuke.besties_structured.business.services.StorageService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileSystemStorageService implements StorageService {

    private final HttpServletRequest request;

    public FileSystemStorageService(HttpServletRequest request) {
        this.request = request;
    }

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;


    @Override
    @PostConstruct
    public void init() throws IOException {

        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {

        try {

            if(file.isEmpty()){
            throw new RuntimeException("Failed to store empty file.");
        }
            /** Nombre del archivo recibido como parámetro*/
            String filename = file.getOriginalFilename();

            
            /* tipo MIME /**            
            //String mime = file.getContentType();


            /**path al lugar de almacenamiento del archivo*/
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
            .normalize().toAbsolutePath();
            
            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING); 
            }
            return filename;
        
        } catch (IOException e){

            throw new RuntimeException("Failed to store file.",e);
        }

    }



    @Override
    public Resource loadAsResource(String filename) {

        try {
                Path file = rootLocation.resolve(filename);
                Resource resource = new UrlResource((file.toUri()));

                if(resource.exists() || resource.isReadable()){
                
                    return resource;

                } else {
                    throw new RuntimeException("Could not read file.");
                }
            
        } catch (MalformedURLException e) {
        
            throw new RuntimeException("Could not read file: " + filename);


        }  
        
    }

    @Override
    public Map<String, String> uploadFile(String path) throws IOException{

        String nombre = path.substring(0,path.indexOf("."))
        .replace(" ", "");
        String extension = path.substring(path.indexOf("."),path.length());

        path = nombre + extension;


        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
        .fromHttpUrl(host)
        .path("/media/")
        .path(path)
        .toUriString();

        return Map.of("url",url);

    }
    
}
