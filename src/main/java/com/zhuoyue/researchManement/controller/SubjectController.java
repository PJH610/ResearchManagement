package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.bean.*;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.*;
import com.zhuoyue.researchManement.service.*;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subject")
public class SubjectController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectApprovalService subjectApprovalService;

    @Autowired
    private SubjectRecommenderService subjectRecommenderService;

    @Autowired
    private SubjectProofService subjectProofService;

    @Autowired
    private SubjectFeasibilityService subjectFeasibilityService;

    @Autowired
    private SubjectFundService subjectFundService;

    @Autowired
    private UnitTreeService unitTreeService;

    @Autowired
    private SubjectSpecialistService subjectSpecialistService;

    @Autowired
    private FileUploadUtils fileUploadUtils;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    private SubjectMediumService subjectMediumService;

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(@RequestParam String name, @RequestParam SubjectClassification classification, @RequestParam SubjectCategory category,
                                   @RequestParam String final_result, @RequestParam Integer grants, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date complete_time,
                                   @RequestParam SubjectFinancialcategory financialcategory, @RequestParam String bankcard, HttpServletRequest request) {

        User currentUser = UserManager.getUser(request);

        if (name == null || name.trim().length() == 0) return onBadResp("课题名称不能为空");
        if (classification == null) return onBadResp("学科分类不能为空");
        if (classification == null ) return onBadResp("课题类别不能为空");
        if (final_result == null || final_result.trim().length() == 0) return onBadResp("最终成果不能为空");
        if (grants == null) return onBadResp("资助经费不能为空");
        if (complete_time == null) return onBadResp("完成时间不能为空");
        if (financialcategory == null) return onBadResp("经费类别不能为空");
        if (bankcard == null || (financialcategory == SubjectFinancialcategory.CATEGORY_A && bankcard.trim().length() == 0)) return onBadResp("当经费类别为“资助”时银行账户不能为空！");

        Subject subject = new Subject();
        subject.setName(name.trim());
        subject.setClassification(classification);
        subject.setCategory(category);
        subject.setState(SubjectState.PRESUBMIT);
        subject.setUserId(currentUser.getId());
        subject.setYear(new Date());
        subject.setFinalresult(final_result.trim());
        subject.setGrants(grants);
        subject.setCompleteTime(complete_time);
        subject.setFinancialcategory(financialcategory);
        subject.setProjectNumber("");
        subject.setProjectReference("");
        subject.setConclusionNumber("");
        subject.setSubjectClassification("");
        subject.setBankcard(bankcard);
        subjectService.insert(subject);

        SubjectRecommender recommender = new SubjectRecommender();
        recommender.setSubjectId(subject.getId());
        subjectRecommenderService.insert(recommender);

        SubjectProof proof = new SubjectProof();
        proof.setSubjectId(subject.getId());
        subjectProofService.insert(proof);

        SubjectFeasibility feasibility = new SubjectFeasibility();
        feasibility.setSubjectId(subject.getId());
        subjectFeasibilityService.insert(feasibility);

        SubjectFund fund = new SubjectFund();
        fund.setSubjectId(subject.getId());
        subjectFundService.insert(SubjectManager.initFund(fund));

        SubjectApproval approval = new SubjectApproval();
        approval.setSubjectId(subject.getId());
        subjectApprovalService.insert(approval);

        return onSuccessRep("信息保存成功！");
    }

    @GetMapping("/list")
    public Map<String, Object> list(String keyword, @RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, HttpServletRequest request) {
        keyword = keyword == null ? null : keyword.trim();

        Long[] unitIds;
        User currentUser = UserManager.getUser(request);
        switch (currentUser.getRoleType()) {
            case ADMIN:
                break;
            case EXPERT:
                break;
            case CITY_RESEARCH:
                PageHelper.startPage(page_num,page_size);
                return onDataResp(new MyPageInfo<Subject>(subjectService.listWithSpecialists(null, keyword, new SubjectState[] {SubjectState.IN_CITY_CHECK, SubjectState.IN_EXPERT_CHECK}, null)));
            case AREA_RESEARCH:
                unitIds = unitTreeService.listUnitIdByParentId(currentUser.getUnit().getId());
                if (unitIds.length == 0) return onDataResp(new MyPageInfo<>(new ArrayList<>()));
                PageHelper.startPage(page_num,page_size);
                return onDataResp(new MyPageInfo<Subject>(subjectService.list(null, keyword, new SubjectState[] {SubjectState.IN_AREA_CHECK}, unitIds)));
            case SCHOOL_RESEARCH:
                PageHelper.startPage(page_num,page_size);
                return onDataResp(new MyPageInfo<Subject>(subjectService.list(null, keyword, new SubjectState[] {SubjectState.IN_SCHOOL_CHECK}, new Long[] {currentUser.getUnit().getId()})));
            case SUBJECT_HOST:
                PageHelper.startPage(page_num,page_size);
                return onDataResp(new MyPageInfo<Subject>(subjectService.list(currentUser.getId(), keyword, null, null)));
        }
        return onBadResp("您没有权限查看信息");
    }

    @GetMapping("/list/thesis")
    public Map<String, Object> listThesis(String keyword, @RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, HttpServletRequest request) {
        PageHelper.startPage(page_num,page_size, "s.thesis_proposal_id is null DESC");
        return onDataResp(new MyPageInfo<Subject>(subjectService.list(null, keyword, new SubjectState[] {SubjectState.COMPLETE}, null)));
    }

    @GetMapping("/list/approval")
    public Map<String, Object> listApproval(HttpServletRequest request) {
        return onDataResp(subjectService.listASubjectWithName(UserManager.getUser(request).getId()));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectService.selectById(id));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long id, String name, SubjectCategory category, SubjectClassification classification,
                                      String finalresult, Integer grants, @DateTimeFormat(pattern="yyyy-MM-dd") Date complete_time,
                                      SubjectFinancialcategory financialcategory, String bankcard, String project_number, String project_reference,
                                      String conclusion_number, String subject_classification, HttpServletRequest request)
    {
        SubjectManager manager = new SubjectManager(id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        if (name != null && name.trim().length() == 0) return onBadResp("课题名称不能为空");
        if (finalresult != null && finalresult.trim().length() == 0) return onBadResp("最终成果不能为空");
        if (financialcategory != null && financialcategory == SubjectFinancialcategory.CATEGORY_A && (bankcard == null || bankcard.trim().length() == 0)){
            return onBadResp("当经费类别为“资助”时银行账户不能为空！");
        }

        Subject subject = new Subject();
        subject.setId(id);
        if (name != null) subject.setName(name.trim());
        if (category != null) subject.setCategory(category);
        if (classification != null) subject.setClassification(classification);
        if (finalresult != null) subject.setFinalresult(finalresult.trim());
        if (grants != null) subject.setGrants(grants);
        if (complete_time != null) subject.setCompleteTime(complete_time);
        if (financialcategory != null) subject.setFinancialcategory(financialcategory);
        if (bankcard != null) subject.setBankcard(bankcard.trim());
        if (project_number != null) subject.setProjectNumber(project_number.trim());
        if (project_reference != null) subject.setProjectReference(project_reference.trim());
        if (conclusion_number != null) subject.setConclusionNumber(conclusion_number.trim());
        if (subject_classification != null) subject.setSubjectClassification(subject_classification.trim());

        if (subject.getName() == null && subject.getCategory() == null && subject.getClassification() == null
                && subject.getFinalresult() == null && subject.getGrants() == null && subject.getCompleteTime() == null
                && subject.getFinancialcategory() == null && subject.getBankcard() == null && subject.getProjectNumber() == null
                && subject.getProjectReference() == null && subject.getConclusionNumber() == null && subject.getSubjectClassification() == null)
        {
            return onBadResp("参数不能为空");
        }

        User currentUser = UserManager.getUser(request);
        if (subjectService.updateById(subject, currentUser.getId(), new SubjectState[]{SubjectState.RETURN_BACk, SubjectState.PRESUBMIT}, null) > 0) {
            return onSuccessRep("修改成功");
        }
        return onBadResp("修改失败");
    }

    @PostMapping("/submit")
    @RequiresRoles(value = RoleType.SUBJECT_HOST, logical = Logical.OR)
    public Map<String, Object> submit(@RequestParam Long id, HttpServletRequest request) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setState(SubjectState.IN_SCHOOL_CHECK);

        if (subjectService.updateById(subject, UserManager.getUser(request).getId(), new SubjectState[]{SubjectState.RETURN_BACk, SubjectState.PRESUBMIT}, null) > 0) {
            return onSuccessRep("提交成功");
        }
        return onBadResp("提交失败");
    }

    @PostMapping("/returnback")
    @Transactional
    @RequiresRoles(value = RoleType.CITY_RESEARCH, logical = Logical.OR)
    public Map<String, Object> returnback(@RequestParam Long id, HttpServletRequest request) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setState(SubjectState.RETURN_BACk);

        if (subjectService.updateById(subject, null, new SubjectState[]{SubjectState.IN_CITY_CHECK}, null) > 0) {
            return onSuccessRep("退回成功");
        }
        return onBadResp("退回失败");
    }

    @PostMapping("/appoint")
    @Transactional
    @RequiresRoles(value = RoleType.CITY_RESEARCH, logical = Logical.OR)
    public Map<String, Object> appoint(@RequestParam Long subject_id, Long[] expert_id, HttpServletRequest request){
        if (expert_id == null || expert_id.length < 3 || expert_id.length % 2 == 0) return onBadResp("专家人数必须为3人以上且为单数！");

        Subject subject = new Subject();
        subject.setId(subject_id);
        subject.setState(SubjectState.IN_EXPERT_CHECK);

        if (subjectService.updateById(subject, null, new SubjectState[]{SubjectState.IN_CITY_CHECK}, null) > 0) {

            List<SubjectSpecialist> specialists = new ArrayList<>();
            for (Long userId : expert_id) {
                SubjectSpecialist specialist = new SubjectSpecialist();
                specialist.setSubjectId(subject_id);
                specialist.setUserId(userId);
                specialist.setType(SubjectSpecialistType.SUBJECT_CHECK);
                specialists.add(specialist);
            }
            subjectSpecialistService.deleteBySubjectId(subject_id, SubjectSpecialistType.SUBJECT_CHECK);
            subjectSpecialistService.insert(specialists);

            return onSuccessRep("指派成功");
        }

        return onBadResp("指派失败");
    }

    @PostMapping("/thesis/upload")
    @Transactional
    @RequiresRoles(value = RoleType.CITY_RESEARCH, logical = Logical.OR)
    public Map<String, Object> thesisUpload(@RequestParam Long id, @RequestParam CommonsMultipartFile thesis_proposal) {
        if (thesis_proposal == null || thesis_proposal.isEmpty()) return onBadResp("文件不能为空");

        String thesisReportFilename = fileUploadUtils.getWordPath(thesis_proposal);
        if (thesisReportFilename == null) return onBadResp("该文件不符合格式");

        Subject subject = new Subject();
        subject.setId(id);
        subject.setThesisProposal(new DownloadFile(thesisReportFilename, thesis_proposal.getOriginalFilename()));

        downloadFileService.insert(subject.getThesisProposal());

        if (subjectService.updateById(subject, null, new SubjectState[] {SubjectState.COMPLETE}, null) > 0) {
            if (subjectMediumService.selectBySubjectId(id) == null) {
                SubjectMedium medium = new SubjectMedium();
                medium.setSubjectId(id);
                medium.setState(SubjectMediumState.PRESUBMIT);
                subjectMediumService.insert(medium);
            }

            fileUploadUtils.saveFile(thesis_proposal, thesisReportFilename);
            return onSuccessRep("上传成功");
        }

        return onBadResp("上传失败");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long[] id ){
        subjectService.deleteById(id);
        return onSuccessRep("删除成功");
    }

}
