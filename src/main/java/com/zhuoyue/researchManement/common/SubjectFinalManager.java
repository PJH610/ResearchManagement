package com.zhuoyue.researchManement.common;

import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.bean.SubjectFinalFund;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import com.zhuoyue.researchManement.service.SubjectFinalService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class SubjectFinalManager {

    private SubjectFinal subjectFinal;

    public SubjectFinalManager(Long subjectId, SubjectFinalService service) {
        subjectFinal = service.selectBySubjectId(subjectId);
    }

    public boolean isEditable(HttpServletRequest request) {
        return subjectFinal != null
                && subjectFinal.getSubject().getUser().getId() == UserManager.getUser(request).getId()
                && (subjectFinal.getState() == SubjectFinalState.RETURN_BACk || subjectFinal.getState() == SubjectFinalState.PRESUBMIT);
    }

    public SubjectFinal getSubjectFinal() {
        return subjectFinal;
    }

    public static void initFundTotal(SubjectFinalFund finalFund) {
        if (finalFund != null) {
            BigDecimal total = new BigDecimal(0);
            total = total.add(finalFund.getFund()).add(finalFund.getOtherFund());
            finalFund.setTotalFund(total);
        }
    }

    public static BigDecimal countFundsTotal(List<SubjectFinalFund> finalFunds) {
        BigDecimal total = new BigDecimal(0);
        if (finalFunds != null) {
            for (SubjectFinalFund finalFund : finalFunds) {
                total = total.add(finalFund.getTotalFund());
            }
        }
        return total;
    }
}
