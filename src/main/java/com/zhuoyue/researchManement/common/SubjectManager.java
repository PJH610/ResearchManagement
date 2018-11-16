package com.zhuoyue.researchManement.common;

import com.zhuoyue.researchManement.bean.*;
import com.zhuoyue.researchManement.enums.SubjectState;
import com.zhuoyue.researchManement.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class SubjectManager {

    private Subject subject;

    public SubjectManager(Long subjectId, SubjectService service) {
        subject = service.selectById(subjectId);
    }

    public boolean isEditable(HttpServletRequest request) {
        return subject != null
                && subject.getUser().getId() == UserManager.getUser(request).getId()
                && (subject.getState() == SubjectState.PRESUBMIT || subject.getState() == SubjectState.RETURN_BACk);
    }

    public Subject getSubject() {
        return subject;
    }

    public static SubjectFund initFund(SubjectFund fund) {
        if (fund == null) return null;

        BigDecimal decimal = new BigDecimal("0");
        if (fund.getData() == null) fund.setData(decimal);
        if (fund.getTravel() == null) fund.setTravel(decimal);
        if (fund.getMeeting() == null) fund.setMeeting(decimal);
        if (fund.getEquipment() == null) fund.setEquipment(decimal);
        if (fund.getService() == null) fund.setService(decimal);
        if (fund.getPrint() == null) fund.setPrint(decimal);
        if (fund.getIdentification() == null) fund.setIdentification(decimal);
        if (fund.getOther() == null) fund.setOther(decimal);
        if (fund.getFunding() == null) fund.setFunding(decimal);
        if (fund.getSelfraised() == null) fund.setSelfraised(decimal);
        return fund;
    }

    public static SubjectApproval initApproval(SubjectApproval approval) {
        if (approval == null) return null;

        if (approval.getSchoolNote() == null) approval.setSchoolNote("");
        if (approval.getSchoolLeaderName() == null) approval.setSchoolLeaderName("");
        if (approval.getAreaNote() == null) approval.setAreaNote("");
        if (approval.getAreaLeaderName() == null) approval.setAreaLeaderName("");
        if (approval.getCityNote() == null) approval.setCityNote("");
        if (approval.getCityLeaderName() == null) approval.setCityLeaderName("");

        return approval;
    }

    public static void initFundsTotal(SubjectFund fund) {
        if (fund != null) {
            BigDecimal total = new BigDecimal(0);
            total = total.add(fund.getData()).add(fund.getTravel()).add(fund.getMeeting()).add(fund.getEquipment())
                    .add(fund.getService()).add(fund.getPrint()).add(fund.getIdentification())
                    .add(fund.getOther()).add(fund.getSelfraised());
            fund.setTotal(total);
        }
    }

    public static Boolean canApproval(List<SubjectSpecialist> specialists) {
        if (specialists == null) return null;
        int agreeCount = 0;
        for (SubjectSpecialist specialist : specialists) {
            if (specialist.getCheckState() == null) return null;
            if (specialist.getCheckState()) agreeCount++;
        }
        return agreeCount * 2 > specialists.size();
    }
}
