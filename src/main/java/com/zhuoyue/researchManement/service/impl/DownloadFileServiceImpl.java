package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.DownloadFile;
import com.zhuoyue.researchManement.dao.DownloadFileDao;
import com.zhuoyue.researchManement.service.DownloadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadFileServiceImpl implements DownloadFileService {

    @Autowired
    private DownloadFileDao downloadFileDao;

    @Override
    public int insert(DownloadFile... file) {
        if (file == null || file.length == 0) return 0;
        return downloadFileDao.insert(file);
    }

    @Override
    public DownloadFile selectById(Long id) {
        return downloadFileDao.selectById(id);
    }

    @Override
    public DownloadFile selectByFileUri(String fileUri) {
        return downloadFileDao.selectByFileUri(fileUri);
    }

    @Override
    public int deleteByFileUri(String... fileUri) {
        if (fileUri == null || fileUri.length == 0) return 0;
        return downloadFileDao.deleteByFileUri(fileUri);
    }
}
