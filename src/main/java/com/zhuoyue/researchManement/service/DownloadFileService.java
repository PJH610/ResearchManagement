package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.DownloadFile;

public interface DownloadFileService {

    int insert(DownloadFile... file);

    DownloadFile selectById(Long id);

    DownloadFile selectByFileUri(String fileUri);

    int deleteByFileUri(String... fileUri);
}
