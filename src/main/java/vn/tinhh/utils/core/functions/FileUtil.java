package vn.tinhh.utils.core.functions;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.FileNameMap;
import java.net.URLConnection;

public class FileUtil {

    private FileNameMap fileNameMap = URLConnection.getFileNameMap();

    @SneakyThrows
    public File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @SneakyThrows
    public String getContentTypeByName(String name) {
        return fileNameMap.getContentTypeFor(name);
    }
}
