package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.DownloadFile;
import com.zhuoyue.researchManement.bean.SubjectProof;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.service.DownloadFileService;
import com.zhuoyue.researchManement.service.SubjectProofService;
import com.zhuoyue.researchManement.service.SubjectService;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/proof")
public class SubjectProofController extends BaseApiController{

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectProofService subjectProofService;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    FileUploadUtils fileUploadUtils;

    @GetMapping("/show/{subject_id}")
    public Map<String, Object> show(@PathVariable Long subject_id) {
        return onDataResp(subjectProofService.selectBySubjectId(subject_id));
    }

    @PostMapping("/update")
    @Transactional
    public Map<String, Object> update(@RequestParam Long subject_id, String value, String target, String method,
                                      @RequestParam(required = false) CommonsMultipartFile file, HttpServletRequest request){
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        SubjectProof proof = new SubjectProof();
        proof.setSubjectId(subject_id);
        if (value != null) proof.setValue(value);
        if (target != null) proof.setTarget(target);
        if (method != null) proof.setMethod(method);

        if (proof.getValue() == null && proof.getTarget() == null && proof.getMethod() == null) {
            return onBadResp("参数不能为空");
        }

        String filePath = "";
        if (file != null && !file.isEmpty()) {
            filePath = fileUploadUtils.getWordPath(file);
            if (filePath == null) return onBadResp("该文件不符合格式");

            proof.setFile(new DownloadFile(filePath, file.getOriginalFilename()));

            downloadFileService.insert(proof.getFile());
        }

        if (proof.getValue() == null && proof.getValue() == null && proof.getTarget() == null && proof.getMethod() == null
                && proof.getFile() == null)
        {
            return onBadResp("参数不能为空");
        }

        if (subjectService.selectById(subject_id) != null && subjectProofService.updateBySubjectId(proof) == 0) {
            subjectProofService.insert(proof);
        }

        if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);

        return onSuccessRep("修改成功");
    }

}
