package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.DownloadFile;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadFileDao {

    int insert(DownloadFile... files);

    DownloadFile selectById(Long id);

    DownloadFile selectByFileUri(String fileUri);

    int deleteByFileUri(String... fileUri);
}
