package user.application.userapplication.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class DownloadFilesController {

    @Value("${files.upload.path}")
    private String uploadPath;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(uploadPath + "/" + fileName));
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        String formattedFileName = fileName.substring(37);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +formattedFileName + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(fileContent.length)
                .body(resource);
    }


//    @GetMapping("/download/{fileName}")
//    public void downloadFile(@PathVariable String fileName, HttpServletResponse response) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(() -> {
//            try {
//                byte[] fileContent = Files.readAllBytes(Paths.get(uploadPath + "/" + fileName));
//                ByteArrayResource resource = new ByteArrayResource(fileContent);
//
//                String formattedFileName = fileName.substring(37);
//                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + formattedFileName + "\"");
//                response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//                response.setContentLength(fileContent.length);
//
//                OutputStream outputStream = response.getOutputStream();
//                outputStream.write(fileContent);
//                outputStream.flush();
//                outputStream.close();
//            } catch (IOException e) {
//                // handle exception
//            }
//        });
//
//        executorService.shutdown();
//    }
}
