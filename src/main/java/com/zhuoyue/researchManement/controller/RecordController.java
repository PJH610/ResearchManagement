package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.bean.DownloadFile;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.Record;
import com.zhuoyue.researchManement.service.DownloadFileService;
import com.zhuoyue.researchManement.service.RecordService;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨 on 2018/5/23.
 */
@RestController
@RequestMapping("/api/record")
public class RecordController extends BaseApiController{
    @Autowired
    private RecordService recordService;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    FileUploadUtils fileUploadUtils;

    // 申报备案接口
    @PostMapping("/add")
    @Transactional
    public Map<String,Object> add(@RequestParam String name, @RequestParam String host, @RequestParam String unit,
                                  @DateTimeFormat(pattern = "yyyy") Date year, @RequestParam String pronum, @RequestParam String filenum,
                                  @RequestParam String subject, @RequestParam String classification,
                                  @RequestParam(required = false) CommonsMultipartFile thesis_proposal,
                                  @RequestParam(required = false) CommonsMultipartFile medium_report,
                                  @RequestParam(required = false) CommonsMultipartFile final_report)
    {

        if (name == null || name.trim().length() == 0) return onBadResp("课题名称不能为空");
        if (host == null || host.trim().length() == 0) return onBadResp("主持人不能为空");
        if (year == null) return onBadResp("日期格式不正确");
        if (unit == null || unit.trim().length() == 0) return onBadResp("单位不能为空");
        if (pronum == null || pronum.trim().length() ==0) return onBadResp("立项编号不能为空");
        if (filenum == null || filenum.trim().length() == 0) return onBadResp("立项文号不能为空");
        if (subject == null || subject.trim().length() ==0) return  onBadResp("所属学科不能为空");
        if (classification == null || classification.trim().length() == 0) return onBadResp("学科类别不能为空");

        Record record = new Record();
        record.setName(name.trim());
        record.setHost(host.trim());
        record.setYear(year);
        record.setUnit(unit.trim());
        record.setPronum(pronum.trim());
        record.setFilenum(filenum.trim());
        record.setSubject(subject.trim());
        record.setClassification(classification.trim());

        List<DownloadFile> files = new ArrayList<>();
        String thesisReportFilename = "";
        String mediumReportFilename = "";
        String finalReportFilename = "";
        if (thesis_proposal != null && !thesis_proposal.isEmpty()) {
            thesisReportFilename = fileUploadUtils.getWordPath(thesis_proposal);
            if (thesisReportFilename == null) return onBadResp("该文件不符合格式");

            record.setThesisProposal(new DownloadFile(thesisReportFilename, thesis_proposal.getOriginalFilename()));
            files.add(record.getThesisProposal());
        }
        if (medium_report != null && !medium_report.isEmpty()) {
            mediumReportFilename = fileUploadUtils.getWordPath(medium_report);
            if (mediumReportFilename == null) return onBadResp("该文件不符合格式");

            record.setMediumReport(new DownloadFile(mediumReportFilename, medium_report.getOriginalFilename()));
            files.add(record.getMediumReport());
        }
        if (final_report != null && !final_report.isEmpty()) {
            finalReportFilename = fileUploadUtils.getWordPath(final_report);
            if (finalReportFilename == null) return onBadResp("该文件不符合格式");

            record.setFinalReport(new DownloadFile(finalReportFilename, final_report.getOriginalFilename()));
            files.add(record.getFinalReport());
        }
        downloadFileService.insert(files.toArray(new DownloadFile[files.size()]));

        if (recordService.insert(record) > 0) {
            if (StringUtils.isNotEmpty(thesisReportFilename)) fileUploadUtils.saveFile(thesis_proposal, thesisReportFilename);
            if (StringUtils.isNotEmpty(mediumReportFilename)) fileUploadUtils.saveFile(medium_report,mediumReportFilename);
            if (StringUtils.isNotEmpty(finalReportFilename)) fileUploadUtils.saveFile(final_report,finalReportFilename);
            return onSuccessRep("申报成功");
        }
       return onBadResp("申报失败");

    }

    // 通过标题模糊查询
    @GetMapping ("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, String name) {
        PageHelper.startPage(page_num, page_size);
        return onDataResp(new MyPageInfo<Record>(recordService.listByName(name)));
    }

    // 通过id查询
    @GetMapping ("/show/{id}")
    public Map<String, Object> getId(@PathVariable Long id) {
        return onDataResp(recordService.selectById(id));
    }

    // 通过id修改备案
    @PostMapping("/update")
    @Transactional
    public Map<String,Object> update(@RequestParam Long id, String name, String host, String unit,
                                     @DateTimeFormat(pattern = "yyyy") Date year, String pronum,
                                     String filenum, String subject, String classification,
                                     @RequestParam(required = false) CommonsMultipartFile thesis_proposal,
                                     @RequestParam(required = false) CommonsMultipartFile medium_report,
                                     @RequestParam(required = false) CommonsMultipartFile final_report)
    {
        if (name != null && name.trim().length() == 0) return onBadResp("课题名称长度必须大于零");
        if (host != null && host.trim().length() == 0) return onBadResp("主持人不能为空");
        if (unit != null && unit.trim().length() == 0) return onBadResp("单位不能为空");
        if (pronum != null && pronum.trim().length() ==0) return onBadResp("立项编号不能为空");
        if (filenum != null && filenum.trim().length() == 0) return onBadResp("立项文号不能为空");
        if (subject != null && subject.trim().length() ==0) return  onBadResp("所属学科不能为空");
        if (classification != null && classification.trim().length() == 0) return onBadResp("学科类别不能为空");

        Record record =new Record();
        record.setId(id);
        if (name != null) record.setName(name.trim());
        if (host != null) record.setHost(host);
        if (year != null) record.setYear(year);
        if (unit != null) record.setUnit(unit);
        if (pronum != null) record.setPronum(pronum);
        if (filenum != null) record.setFilenum(filenum);
        if (subject != null) record.setSubject(subject);
        if (classification != null) record.setClassification(classification);

        List<DownloadFile> files = new ArrayList<>();
        String thesisReportFilename = "";
        String mediumReportFilename = "";
        String finalReportFilename = "";
        if (thesis_proposal != null && !thesis_proposal.isEmpty()) {
            thesisReportFilename = fileUploadUtils.getWordPath(thesis_proposal);
            if (thesisReportFilename == null) return onBadResp("该文件不符合格式");

            record.setThesisProposal(new DownloadFile(thesisReportFilename, thesis_proposal.getOriginalFilename()));
            files.add(record.getThesisProposal());
        }
        if (medium_report != null && !medium_report.isEmpty()) {
            mediumReportFilename = fileUploadUtils.getWordPath(medium_report);
            if (mediumReportFilename == null) return onBadResp("该文件不符合格式");

            record.setMediumReport(new DownloadFile(mediumReportFilename, medium_report.getOriginalFilename()));
            files.add(record.getMediumReport());
        }
        if (final_report != null && !final_report.isEmpty()) {
            finalReportFilename = fileUploadUtils.getWordPath(final_report);
            if (finalReportFilename == null) return onBadResp("该文件不符合格式");

            record.setFinalReport(new DownloadFile(finalReportFilename, finalReportFilename));
            files.add(record.getFinalReport());
        }
        downloadFileService.insert(files.toArray(new DownloadFile[files.size()]));

        if (record.getName() == null && record.getHost() == null && record.getYear() == null && record.getUnit() == null
                && record.getPronum() == null && record.getFilenum() == null && record.getSubject() == null && record.getClassification() == null
                && record.getThesisProposal() == null && record.getMediumReport() == null && record.getFinalReport() == null) {
            return onBadResp("参数不能为空");
        }

        recordService.updateById(record);

        if (StringUtils.isNotEmpty(thesisReportFilename)) fileUploadUtils.saveFile(thesis_proposal, thesisReportFilename);
        if (StringUtils.isNotEmpty(mediumReportFilename)) fileUploadUtils.saveFile(medium_report, mediumReportFilename);
        if (StringUtils.isNotEmpty(finalReportFilename)) fileUploadUtils.saveFile(final_report, finalReportFilename);
        return onSuccessRep("修改成功");
    }

    // 通过单个删除或者批量删除
    @PostMapping("/delete")
    public Map<String,Object> delete(@RequestParam Long[] id){
        recordService.deleteById(id);
        return onSuccessRep("删除成功");
    }
}
