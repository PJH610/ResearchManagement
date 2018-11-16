package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.DownloadFile;
import com.zhuoyue.researchManement.service.DownloadFileService;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    private FileUploadUtils fileUploadUtils;

    @GetMapping("/**")
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response) {
        String fileUri = request.getRequestURI();
        DownloadFile file = downloadFileService.selectByFileUri(fileUri);
        if (file == null) return null;

        response.reset();
        try {
            // 传入文件UUID形成的文件名filenameIdname，再加入本件本身的名字filename
            return downloadFile(fileUploadUtils.getBasePath() + file.getFileUri(), file.getFilename(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResponseEntity<byte[]> downloadFile(String fileUri, String filename, HttpServletResponse response) {
        File file = new File(fileUri);
        HttpHeaders headers = new HttpHeaders();

        try {
            //下载显示的文件名，解决文件乱码问题
//            String downlaodFilename = URLEncoder.encode(filename); //new String(filename.getBytes("UTF-8"), "iso-8859-1");
            String downlaodFilename = new String(filename.getBytes("UTF-8"), "iso-8859-1");

            //通知浏览器以attachment（下载方式）
            headers.setContentDispositionFormData("attachment", downlaodFilename);
            //二进制流数据
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + downlaodFilename);
            return new ResponseEntity<byte []>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
