package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectOpenTime;
import com.zhuoyue.researchManement.util.SubjectOpenTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/opentime")
public class SubjectOpenTimeController extends BaseApiController {

    @Autowired
    private SubjectOpenTimeUtil subjectOpenTimeUtil;

    @GetMapping("/info")
    public Map<String, Object> info() {
        return onDataResp(subjectOpenTimeUtil.getOpenTime());
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam String desc,
                                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
                                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time) throws IOException {
        if (desc == null || desc.trim().length() == 0) return onBadResp("描述不能为空");
        if (start_time == null) return onBadResp("开始时间不能为空");
        if (end_time == null) return onBadResp("结束时间不能为空");
        if (start_time.getTime() > end_time.getTime()) return onBadResp("设置时间段不正确");

        subjectOpenTimeUtil.upadteOpenTime(new SubjectOpenTime(desc.trim(), start_time, end_time, null));
        return onSuccessRep("修改成功");
    }
}
