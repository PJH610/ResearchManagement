package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectRecommender;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.service.SubjectRecommenderService;
import com.zhuoyue.researchManement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/recommender")
public class SubjectRecommenderController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRecommenderService subjectRecommenderService;

    @GetMapping("/show/{subject_id}")
    public Map<String, Object> show(@PathVariable Long subject_id) {
        return onDataResp(subjectRecommenderService.selectBySubjectId(subject_id));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, String name, String position, String profession, String unit, String subname, String recommend,
                                      String name_s, String position_s, String profession_s, String unit_s, String subname_s, String recommend_s,
                                      HttpServletRequest request) {

        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        SubjectRecommender recommender = new SubjectRecommender();
        recommender.setSubjectId(subject_id);
        if (name != null) recommender.setName(name.trim());
        if (position != null) recommender.setPosition(position.trim());
        if (profession != null) recommender.setProfession(profession.trim());
        if (unit != null) recommender.setUnit(unit.trim());
        if (subname != null) recommender.setSubname(subname);
        if (recommend != null) recommender.setRecommend(recommend);
        if (name_s != null) recommender.setNameS(name_s.trim());
        if (position_s != null) recommender.setPositionS(position_s.trim());
        if (profession_s != null) recommender.setProfessionS(profession_s.trim());
        if (unit_s != null) recommender.setUnitS(unit_s.trim());
        if (subname_s != null) recommender.setSubnameS(subname_s);
        if (recommend_s != null) recommender.setRecommendS(recommend_s);

        if (recommender.getName() == null && recommender.getPosition() == null && recommender.getProfession() == null
                && recommender.getUnit() == null && recommender.getSubname() == null && recommender.getRecommend() == null
                && recommender.getNameS() == null && recommender.getPositionS() == null && recommender.getProfessionS() == null
                && recommender.getUnitS() == null && recommender.getSubnameS() == null && recommender.getRecommendS() == null)
        {
            return onBadResp("参数不能为空");
        }

        if (subjectService.selectById(subject_id) != null && subjectRecommenderService.updateBySubjectId(recommender) == 0) {
            subjectRecommenderService.insert(recommender);
        }
        return onSuccessRep("修改成功");
    }

}
