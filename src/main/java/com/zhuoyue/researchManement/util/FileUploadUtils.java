package com.zhuoyue.researchManement.util;

import com.zhuoyue.researchManement.exception.FileSaveException;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class FileUploadUtils {

    private static final String DOWNLOAD_PATH = "/download";
    public static final String DOWNLOAD_IMAGE_PATH = DOWNLOAD_PATH + "/image/";
    public static final String DOWNLOAD_WORD_PATH = DOWNLOAD_PATH + "/word/";
    public static final String DOWNLOAD_FILE_PATH = DOWNLOAD_PATH + "/file/";
    public static final String DATA_PATH = "/data/";

    private static final String[] IMAGE_SUFFIX = {"jpeg", "jpg", "png", "gif"};
    private static final String[] WORD_SUFFIX = {"txt", "doc", "docx"};
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private String basePath = "";

    public static MultipartFile getMultipartFile(String name, HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (name.equals(file.getName())) return file;
            }

        }
        return null;
    }

   public static void checkDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            synchronized (FileUtils.class) {
                dir.mkdirs();
            }
        }
    }

    public void saveFile(MultipartFile file, String path) {
        File saveFile = new File(basePath + path);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new FileSaveException(e.getMessage());
        }
    }

    /**
     * 生成文件相对路径
     * @param file
     * @param savePath 文件在basePath下的相对储存路径
     * @param suffixs 规定文件的后缀名
     * @return 返回null代表该文件不符合格式
     */
    public String getFilePath(MultipartFile file, String savePath, @Nullable String[] suffixs) {
        checkDir(basePath + savePath);
        String fileName = file.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        boolean access = false;
        if (suffixs == null) {
            access = true;
        } else {
            for (String suffix : suffixs) {
                if (suffix.equals(fileSuffix)) {
                    access = true;
                    break;
                }
            }
        }

        if (access) {
            int randNum = new Random(System.nanoTime()).nextInt(1000000);
            String newFilePath = savePath + sdf.format(new Date()) + "_" + String.format("%06d", randNum) + "." + fileSuffix;
            return newFilePath;
        }
        return null;
    }

    /**
     * 生成文件储存相对路径
     * @param file
     * @param savePath 文件在basePath下的相对储存路径
     * @return 返回null代表该文件不符合格式
     */
    public String getFilePath(MultipartFile file, String savePath) {
        return getFilePath(file, savePath, null);
    }

    /**
     * 生成图片储存相对路径
     * @param file
     * @return 返回null代表该文件不符合格式
     */
    public String getImagePath(MultipartFile file) {
        return getFilePath(file, DOWNLOAD_IMAGE_PATH, IMAGE_SUFFIX);
    }

    /**
     * 生成文档储存相对路径
     * @param file
     * @return 返回null代表该文件不符合格式
     */
    public String getWordPath(MultipartFile file) {
        return getFilePath(file, DOWNLOAD_WORD_PATH, WORD_SUFFIX);
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
