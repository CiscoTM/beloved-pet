package es.tuke.besties_structured.business.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init() throws IOException;
    String store(MultipartFile file);
    Resource loadAsResource(String filename);
    public Map<String, String> uploadFile(String path) throws IOException;
    
}
