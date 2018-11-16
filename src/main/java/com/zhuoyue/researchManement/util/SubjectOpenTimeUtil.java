package com.zhuoyue.researchManement.util;

import com.google.gson.Gson;
import com.zhuoyue.researchManement.bean.SubjectOpenTime;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SubjectOpenTimeUtil {

    private static final String SUBJECT_OPENTIME_JSON = "subject_opentime.json";

    private SubjectOpenTime openTime;

    private String basePath;

    private synchronized void init() {
        if (openTime != null) return;
        try {
            File file = new File(basePath + SUBJECT_OPENTIME_JSON);
            if (file.exists()) openTime = new Gson().fromJson(FileUtils.readFileToString(file, "utf-8"), SubjectOpenTime.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (openTime == null) openTime = new SubjectOpenTime();
    }

    public SubjectOpenTime getOpenTime() {
        init();
        boolean open = false;
        long currentTime = System.currentTimeMillis();
        if (openTime.getStartTime() != null && openTime.getEndTime() != null
                && openTime.getStartTime().getTime() <= currentTime
                && openTime.getEndTime().getTime() + 24 * 60 * 60 * 1000 >= currentTime)
        {
            open = true;
        }

        return new SubjectOpenTime(openTime.getDesc(), openTime.getStartTime(), openTime.getEndTime(), open);
    }

    public synchronized void upadteOpenTime(SubjectOpenTime openTime) throws IOException {
        File dir = new File(basePath);
        if (!dir.exists()) dir.mkdirs();
        FileUtils.writeStringToFile(new File(basePath + SUBJECT_OPENTIME_JSON), new Gson().toJson(openTime));
        this.openTime = openTime;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
