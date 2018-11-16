package com.zhuoyue.researchManement.common;

import com.zhuoyue.researchManement.bean.SubjectSpecialist;

public class SubjectSpecialistManager {

    public static int countScore(SubjectSpecialist specialist) {
        int count = 0;
        if (specialist != null) {
            count += add(specialist.getValueScore()) + add(specialist.getAchievementScore()) + add(specialist.getValidityScore())
                    + add(specialist.getScientificScore()) + add(specialist.getConditionScore());
        }
        return count;
    }

    private static int add(Integer score) {
        if (score == null) return 0;
        return score;
    }
}
