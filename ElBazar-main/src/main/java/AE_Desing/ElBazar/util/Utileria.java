package AE_Desing.ElBazar.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Utileria {
	
	@Value("${ElBazar-main.ruta.imagenes}")
	private String ruta;

	public String uploadImage(MultipartFile file) {
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path uploadDir = Paths.get(ruta);
			if (!Files.exists(uploadDir)) {
				Files.createDirectories(uploadDir);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Path filePath = uploadDir.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			}
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException("Error al cargar la imagen", e);
		}
	}
}
