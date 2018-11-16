package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.annotation.SystemControllerLog;
import com.zhuoyue.researchManement.bean.Menu;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.MenuDataManager;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.*;
import com.zhuoyue.researchManement.service.*;
import com.zhuoyue.researchManement.util.SubjectOpenTimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by 12413 on 2018/5/14.
 */
@Controller
public class MainController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectPersonnelService subjectPersonnelService;

    @Autowired
    private SubjectRecommenderService subjectRecommenderService;

    @Autowired
    private SubjectApprovalService subjectApprovalService;

    @Autowired
    private SubjectProofService subjectProofService;

    @Autowired
    private SubjectFeasibilityService subjectFeasibilityService;

    @Autowired
    private SubjectScheduleService subjectScheduleService;

    @Autowired
    private SubjectResultService subjectResultService;

    @Autowired
    private UnitTreeService unitTreeService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private SubjectOpenTimeUtil subjectOpenTimeUtil;

    @Autowired
    private SubjectFundService subjectFundService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectSpecialistService specialistService;

    @Autowired
    private SubjectMediumService subjectMediumService;

    @Autowired
    private SubjectFinalService subjectFinalService;

    @Autowired
    private SubjectFinalAchievementService subjectFinalAchievementService;

    @Autowired
    private SubjectFinalFundService subjectFinalFundService;

    @Autowired
    private SubjectFinalApprovalService subjectFinalApprovalService;

    @Autowired
    private SubjectChangeService subjectChangeService;

    @GetMapping("/explain/{page}")
    public String explainpage(@PathVariable String page) {
        return "/explain/" + page;
    }

    @GetMapping("/register")
    @SystemControllerLog("注册页面")
    public String register() {
        return "/register";
    }

    @GetMapping("/login")
    @SystemControllerLog("登录页面")
    public String login() {
        return "/login";
    }

    @GetMapping("/logout")
    @SystemControllerLog("登出页面")
    public String logout(HttpServletRequest request, Model model) {
        return getPageURI(request, model);
    }

    @GetMapping(value = {"/home", "/notice/add", "/record/add", "/user/update/password",
            "/unit/tree"})
    @SystemControllerLog("主页面")
    @RequiresRoles(value = {RoleType.ADMIN, RoleType.EXPERT, RoleType.CITY_RESEARCH, RoleType.AREA_RESEARCH, RoleType.SCHOOL_RESEARCH, RoleType.SUBJECT_HOST}, logical = Logical.OR)
    public String mainPage(HttpServletRequest request, Model model) {
        return getPageURI(request, model);
    }

    @GetMapping(value = {"/notice/list",
            "/activity/list", "/activity/list/host", "/activity/list/check", "/activity/check/list",
            "/subject/list/", "/subject/list/mine", "/subject/list/school", "/subject/list/area",
            "/subject/list/city", "/subject/expert/list/check", "/subject/expert/list/recheck", "/subject/expert/list/log",
            "/subject/approval/list", "/subject/approval/list/activity", "/subject/approval/list/host", "/subject/approval/list/school", "/subject/approval/list/area", "/subject/approval/list/city",
            "/subject/thesis/list/host", "/subject/thesis/list/city",
            "/subject/medium/list/host", "/subject/medium/list/school", "/subject/medium/list/area", "/subject/medium/list/city",
            "/subject/final/list/host", "/subject/final/list/school", "/subject/final/list/area", "/subject/final/list/city",
            "/subject/change/list", "/subject/change/list/check",
            "/record/list", "/record/list/city", "/user/list",
            "/user/update/info", "/user/update/host", "/user/update/expert",})
    @SystemControllerLog("列表页面")
    @RequiresRoles(value = {RoleType.ADMIN, RoleType.EXPERT, RoleType.CITY_RESEARCH, RoleType.AREA_RESEARCH, RoleType.SCHOOL_RESEARCH, RoleType.SUBJECT_HOST}, logical = Logical.OR)
    public String list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, HttpServletRequest request, Model model) {
        model.addAttribute("pageNum", page_num);
        model.addAttribute("pageSize", page_size);
        return getPageURI(request, model);
    }

    @GetMapping("/notice/{notice_id}/show")
    public String noticeShow(@PathVariable Long notice_id, HttpServletRequest request, Model model) {
        model.addAttribute("notice", noticeService.selectById(notice_id));
        return getPageURI("/notice/show", request, model);
    }

    @GetMapping("/activity/{activity_id}/show")
    public String activityShow(@PathVariable Long activity_id, HttpServletRequest request, Model model) {
        model.addAttribute("activity", activityService.selectById(activity_id));
        return getPageURI("/activity/show", request, model);
    }

    @GetMapping("/subject/add")
    public String subjectAdd(HttpServletRequest request, Model model) {
        model.addAttribute("classifications", SubjectClassification.values());
        model.addAttribute("categorys", SubjectCategory.values());
        model.addAttribute("financialcategorys", SubjectFinancialcategory.values());
        return getPageURI(request, model);
    }

    @GetMapping("/subject/host")
    public String subjectHost(HttpServletRequest request, Model model) {
        model.addAttribute("opentime", subjectOpenTimeUtil.getOpenTime());
        return getPageURI("/subject/host", request, model);
    }

    @GetMapping("/subject/{subject_id}/show")
    public String subjectShow(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subject", subjectService.selectById(subject_id));
        return getPageURI("/subject/show", request, model);
    }

    @GetMapping("/subject/{subject_id}/show/main")
    public String subjectShowMain(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("subject", subjectService.selectById(subject_id));
        model.addAttribute("personnels", subjectPersonnelService.listBySubjectId(subject_id));
        return getPageURI("/subject/show/main", request, model);
    }

    @GetMapping("/subject/{subject_id}/show/recommender")
    public String subjectShowRecommender(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("recommender", subjectRecommenderService.selectBySubjectId(subject_id));
        return getPageURI("/subject/show/recommender", request, model);
    }

    @GetMapping("/subject/{subject_id}/show/approval")
    public String subjectShowApproval(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("approval", subjectApprovalService.selectBySubjectId(subject_id));
        return getPageURI("/subject/show/approval", request, model);
    }

    @GetMapping("/subject/{subject_id}/show/proof")
    public String subjectShowProof(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("proof", subjectProofService.selectBySubjectId(subject_id));
        return getPageURI("/subject/show/proof", request, model);
    }

    @GetMapping("/subject/{subject_id}/show/feasibility")
    public String subjectShowFeasibility(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("feasibility", subjectFeasibilityService.selectBySubjectId(subject_id));
        return getPageURI("/subject/show/feasibility", request, model);
    }

    @GetMapping("/subject/{subject_id}/show/schedule")
    public String subjectShowSchedule(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("schedules", subjectScheduleService.listBySubjectId(subject_id));
        model.addAttribute("results", subjectResultService.list(subject_id));
        return getPageURI("/subject/show/schedule", request, model);
    }

    @GetMapping("/subject/{subject_id}/show/fund")
    public String subjectShowFund(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("fund", subjectFundService.selectBySubjectId(subject_id));
        return getPageURI("/subject/show/fund", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/main")
    public String subjectEditMain(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("subject", subjectService.selectById(subject_id));
        model.addAttribute("classifications", SubjectClassification.values());
        model.addAttribute("categorys", SubjectCategory.values());
        model.addAttribute("financialcategorys", SubjectFinancialcategory.values());
        return getPageURI("/subject/edit/main", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/personnel")
    public String subjectEditPersonnel(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("personnels", subjectPersonnelService.listBySubjectId(subject_id));
        return getPageURI("/subject/edit/personnel", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/personnel/add")
    public String subjectEditPersonnelAdd(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/edit/personnel/add", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/personnel/{personnel_id}/update")
    public String subjectEditPersonnelEdit(@PathVariable Long subject_id, @PathVariable Long personnel_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("personnelId", personnel_id);
        model.addAttribute("personnel", subjectPersonnelService.selectById(personnel_id));
        return getPageURI("/subject/edit/personnel/update", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/recommender")
    public String subjectEditRecommender(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("recommender", subjectRecommenderService.selectBySubjectId(subject_id));
        return getPageURI("/subject/edit/recommender", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/proof")
    public String subjectEditProof(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("proof", subjectProofService.selectBySubjectId(subject_id));
        return getPageURI("/subject/edit/proof", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/feasibility")
    public String subjectEditFeasibility(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("feasibility", subjectFeasibilityService.selectBySubjectId(subject_id));
        return getPageURI("/subject/edit/feasibility", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/schedule")
    public String subjectEditSchedule(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("schedules", subjectScheduleService.listBySubjectId(subject_id));
        return getPageURI("/subject/edit/schedule", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/schedule/add")
    public String subjectEditScheduleAdd(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/edit/schedule/add", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/schedule/{schedule_id}/edit")
    public String subjectEditScheduleEdit(@PathVariable Long subject_id, @PathVariable Long schedule_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("schedule", subjectScheduleService.selectById(schedule_id));
        return getPageURI("/subject/edit/schedule/edit", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/result")
    public String subjectEditResult(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("results", subjectResultService.list(subject_id));
        return getPageURI("/subject/edit/result", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/result/add")
    public String subjectEditResultAdd(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/edit/result/add", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/result/{id}/edit")
    public String subjectEditResultEdit(@PathVariable Long subject_id, @PathVariable Long id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("result", subjectResultService.selectById(id));
        return getPageURI("/subject/edit/result/edit", request, model);
    }

    @GetMapping("/subject/{subject_id}/edit/fund")
    public String subjectEditFund(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("fund", subjectFundService.selectBySubjectId(subject_id));
        return getPageURI("/subject/edit/fund", request, model);
    }

    @GetMapping("/subject/{subject_id}/check/school")
    public String subjectCheckSchool(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("approval", subjectApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/check/school", request, model);
    }

    @GetMapping("/subject/{subject_id}/check/area")
    public String subjectCheckArea(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("approval", subjectApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/check/area", request, model);
    }

    @GetMapping("/subject/{subject_id}/check/returnback")
    public String subjectCheckCityReturnback(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("approval", subjectApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/check/returnback", request, model);
    }

    @GetMapping("/subject/{subject_id}/check/approval")
    public String subjectCheckApproval(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("approval", subjectApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/check/approval", request, model);
    }

    @GetMapping("/subject/{subject_id}/check/appoint")
    public String subjectCheckAppoint(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("approval", subjectApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("experts", userService.list(RoleTypeEnum.EXPERT));
        return getPageURI("/subject/check/appoint", request, model);
    }

    @GetMapping("/subject/{subject_id}/check/city")
    public String subjectCheckCity(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("approval", subjectApprovalService.selectBySubjectId(subject_id));
        return getPageURI("/subject/check/city", request, model);
    }

    @GetMapping("/subject/expert/{specialist_id}/check/subject")
    public String subjectCheckExpertSubject(@PathVariable Long specialist_id, HttpServletRequest request, Model model) {
        model.addAttribute("specialistId", specialist_id);
        model.addAttribute("specialist", specialistService.selectById(specialist_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/expert/check/subject", request, model);
    }

    @GetMapping("/subject/expert/{specialist_id}/check/medium")
    public String subjectCheckExpertMedium(@PathVariable Long specialist_id, HttpServletRequest request, Model model) {
        model.addAttribute("specialistId", specialist_id);
        model.addAttribute("specialist", specialistService.selectById(specialist_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/expert/check/medium", request, model);
    }

    @GetMapping("/subject/{subject_id}/thesis/upload")
    public String subjectThesisUpload(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/thesis/upload", request, model);
    }

    @GetMapping("/subject/{subject_id}/medium/edit")
    public String subjectMediumEdit(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("medium", subjectMediumService.selectBySubjectId(subject_id));
        return getPageURI("/subject/medium/edit", request, model);
    }

    @GetMapping("/subject/{subject_id}/medium/show")
    public String subjectMediumShow(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("medium", subjectMediumService.selectBySubjectId(subject_id));
        return getPageURI("/subject/medium/show", request, model);
    }

    @GetMapping("/subject/{subject_id}/medium/check/school")
    public String subjectMediumCheckSchool(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("medium", subjectMediumService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/medium/check/school", request, model);
    }

    @GetMapping("/subject/{subject_id}/medium/check/area")
    public String subjectMediumCheckArea(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("medium", subjectMediumService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/medium/check/area", request, model);
    }

    @GetMapping("/subject/{subject_id}/medium/check/city")
    public String subjectMediumCheckCity(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("medium", subjectMediumService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        model.addAttribute("experts", userService.list(RoleTypeEnum.EXPERT));
        return getPageURI("/subject/medium/check/city", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/main")
    public String subjectFinalEditMain(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("final", subjectFinalService.selectBySubjectId(subject_id));
        return getPageURI("/subject/final/edit/main", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/personnel")
    public String subjectFinalEditPersonnel(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("personnels", subjectPersonnelService.listBySubjectId(subject_id));
        return getPageURI("/subject/final/edit/personnel", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/personnel/add")
    public String subjectFinalEditPersonnelAdd(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/final/edit/personnel/add", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/personnel/{personnel_id}/update")
    public String subjectFinalEditPersonnelUpdate(@PathVariable Long subject_id, @PathVariable Long personnel_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("personnel", subjectPersonnelService.selectById(personnel_id));
        return getPageURI("/subject/final/edit/personnel/update", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/achievement")
    public String subjectFinalEditAchievement(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("achievements", subjectFinalAchievementService.listBySubjectId(subject_id));
        return getPageURI("/subject/final/edit/achievement", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/achievement/add")
    public String subjectFinalEditAchievementAdd(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/final/edit/achievement/add", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/achievement/{final_achievement_id}/update")
    public String subjectFinalEditAchievementUpdate(@PathVariable Long subject_id, @PathVariable Long final_achievement_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("achievement", subjectFinalAchievementService.selectById(final_achievement_id));
        return getPageURI("/subject/final/edit/achievement/update", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/fund")
    public String subjectFinalEditFund(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("funds", subjectFinalFundService.selectFunds(subject_id));
        return getPageURI("/subject/final/edit/fund", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/fund/add")
    public String subjectFinalEditFundsAdd(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/final/edit/fund/add", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/edit/fund/{finalFundId}/update")
    public String subjectFinalEditFundUpdate(@PathVariable Long subject_id, @PathVariable Long finalFundId, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("fund", subjectFinalFundService.selectById(finalFundId));
        return getPageURI("/subject/final/edit/fund/update", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/show/subject")
    public String subjectFinalShowSubject(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("subjectFinal", subjectFinalService.selectBySubjectId(subject_id));
        model.addAttribute("personnels", subjectPersonnelService.listBySubjectId(subject_id));
        return getPageURI("/subject/final/show/subject", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/show/achievement")
    public String subjectFinalShowAchievement(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("achievements", subjectFinalAchievementService.listBySubjectId(subject_id));
        return getPageURI("/subject/final/show/achievement", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/show/main")
    public String subjectFinalShowMain(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("subjectFinal", subjectFinalService.selectBySubjectId(subject_id));
        return getPageURI("/subject/final/show/main", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/show/fund")
    public String subjectFinalShowFund(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("finalFunds", subjectFinalFundService.selectFunds(subject_id));
        return getPageURI("/subject/final/show/fund", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/show/approval")
    public String subjectFinalShowApproval(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("finalApproval", subjectFinalApprovalService.selectBySubjectId(subject_id));
        return getPageURI("/subject/final/show/approval", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/check/school")
    public String subjectFinalCheckSchool(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("finalApproval", subjectFinalApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/final/check/school", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/check/area")
    public String subjectFinalCheckArea(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectIid", subject_id);
        model.addAttribute("finalApproval", subjectFinalApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/final/check/area", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/check/city")
    public String subjectFinalCheckCity(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("finalApproval", subjectFinalApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/final/check/city", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/check/returnback")
    public String subjectFinalCheckReturnback(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("finalApproval", subjectFinalApprovalService.selectBySubjectId(subject_id));
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/final/check/returnback", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/check/approval")
    public String subjectFinalCheckApproval(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/final/check/approval", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/check/firsttrial")
    public String subjectFinalCheckFirstTrial(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/final/check/firsttrial", request, model);
    }

    @GetMapping("/subject/{subject_id}/final/check/finaltrial")
    public String subjectFinalCheckFinalTrial(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("currentTime", new Date());
        return getPageURI("/subject/final/check/finaltrial", request, model);
    }

    @GetMapping("/subject/{subject_id}/approval/main")
    public String subjectApprovalMain(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("subject", subjectService.selectById(subject_id));

        User currentUser = (User) request.getSession().getAttribute(UserManager.CURRENT_USER);
        if (currentUser != null && currentUser.getRoleType() == RoleTypeEnum.CITY_RESEARCH) {
            model.addAttribute("edit", true);
        }

        return getPageURI("/subject/approval/main", request, model);
    }

    @GetMapping("/subject/{subject_id}/change/edit/add/completetime")
    public String subjectChangeEditAddCompletetime(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subject", subjectService.selectById(subject_id));
        return getPageURI("/subject/change/edit/add/completetime", request, model);
    }

    @GetMapping("/subject/{subject_id}/change/edit/add/finalresult")
    public String subjectChangeEditAddFinalresult(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subject", subjectService.selectById(subject_id));
        return getPageURI("/subject/change/edit/add/finalresult", request, model);
    }

    @GetMapping("/subject/{subject_id}/change/edit/update/{change_id}/completetime")
    public String subjectChangeEditUpdateCompletetime(@PathVariable Long subject_id, @PathVariable Long change_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("change", subjectChangeService.selectById(change_id));
        return getPageURI("/subject/change/edit/update/completetime", request, model);
    }

    @GetMapping("/subject/{subject_id}/change/edit/update/{change_id}/finalresult")
    public String subjectChangeEditUpdateFinalresult(@PathVariable Long subject_id, @PathVariable Long change_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        model.addAttribute("change", subjectChangeService.selectById(change_id));
        return getPageURI("/subject/change/edit/update/finalresult", request, model);
    }

    @GetMapping("/subject/{subject_id}/change/list/host")
    public String subjectChangeListHost(@PathVariable Long subject_id, HttpServletRequest request, Model model) {
        model.addAttribute("subjectId", subject_id);
        return getPageURI("/subject/change/list/host", request, model);
    }

    @GetMapping("/subject/opentime")
    public String subjectOpentime(HttpServletRequest request, Model model) {
        model.addAttribute("opentime", subjectOpenTimeUtil.getOpenTime());
        return getPageURI(request, model);
    }

    @GetMapping("/activity/add")
    public String activityAdd(HttpServletRequest request, Model model) {
        User currentUser = (User) request.getSession().getAttribute(UserManager.CURRENT_USER);
        if (currentUser != null) {
            model.addAttribute("subjects", subjectService.listASubjectWithName(currentUser.getId()));
        }
        return getPageURI("/activity/add", request, model);
    }

    @GetMapping("/activity/{id}/update")
    public String activityUpdate(@PathVariable Long id,  HttpServletRequest request, Model model) {
        model.addAttribute("activity", activityService.selectById(id));
        User currentUser = (User) request.getSession().getAttribute(UserManager.CURRENT_USER);
        if (currentUser != null) {
            model.addAttribute("subjects", subjectService.listASubjectWithName(currentUser.getId()));
        }
        return getPageURI("/activity/update", request, model);
    }

    @GetMapping("/record/{record_id}/show")
    public String recordShow(@PathVariable Long record_id, HttpServletRequest request, Model model) {
        model.addAttribute("record", recordService.selectById(record_id));
        return getPageURI("/record/show", request, model);
    }

    @GetMapping("/record/{record_id}/edit")
    public String recordEdit(@PathVariable Long record_id, HttpServletRequest request, Model model) {
        model.addAttribute("record", recordService.selectById(record_id));
        return getPageURI("/record/edit", request, model);
    }

    private String getPageURI(String uri, HttpServletRequest request, Model model) {
        uri = uri == null ? (request.getRequestURI() + "_include") : uri + "_include";

        model.addAttribute("currentUser", request.getSession().getAttribute(UserManager.CURRENT_USER));
        if (isPjax(request)) {
            return uri;
        } else {
            model.addAttribute("includePage", uri + ".html");

            List<Menu> menuList = null;

            Subject subject = SecurityUtils.getSubject();
            if (subject.hasRole(RoleType.ADMIN)) {
                menuList = MenuDataManager.getMenu(MenuDataManager.area_office);
            } else if (subject.hasRole(RoleType.EXPERT)) {
                menuList = MenuDataManager.getMenu(MenuDataManager.expert_guidance);
            } else if (subject.hasRole(RoleType.CITY_RESEARCH)) {
                menuList = MenuDataManager.getMenu(MenuDataManager.city_office);
            } else if (subject.hasRole(RoleType.AREA_RESEARCH)) {
                menuList = MenuDataManager.getMenu(MenuDataManager.area_office);
            } else if (subject.hasRole(RoleType.SCHOOL_RESEARCH)) {
                menuList = MenuDataManager.getMenu(MenuDataManager.school_manager);
            } else if (subject.hasRole(RoleType.SUBJECT_HOST)) {
                menuList = MenuDataManager.getMenu(MenuDataManager.subject_host);
            }
            model.addAttribute("menuList", menuList);

            return "/home";
        }
    }

    private String getPageURI(HttpServletRequest request, Model model) {
        return getPageURI(null, request, model);
    }

    /**
     * 判断pjax请求
     *
     * @param request
     * @return
     */
    private boolean isPjax(HttpServletRequest request) {
        return (request.getHeader("X-PJAX") != null
                && "true".equals(request.getHeader("X-PJAX")));
    }
}
