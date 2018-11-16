package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.Menu;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.MenuDataManager;
import com.zhuoyue.researchManement.common.UserManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController extends BaseApiController {

    @GetMapping()
    public Map<String, Object> data(HttpServletRequest request) {
        User user = UserManager.getUser(request);
        if (user != null) {
            switch (user.getRoleType()) {
                case ADMIN:
                    return onDataResp(MenuDataManager.getMenu(MenuDataManager.economy_manager));
                case EXPERT:
                    return onDataResp(MenuDataManager.getMenu(MenuDataManager.expert_guidance));
                case CITY_RESEARCH:
                    return onDataResp(MenuDataManager.getMenu(MenuDataManager.city_office));
                case AREA_RESEARCH:
                    return onDataResp(MenuDataManager.getMenu(MenuDataManager.area_office));
                case SCHOOL_RESEARCH:
                    return onDataResp(MenuDataManager.getMenu(MenuDataManager.school_manager));
                case SUBJECT_HOST:
                    return onDataResp(MenuDataManager.getMenu(MenuDataManager.subject_host));
            }
        }
        return onBadResp("没有菜单选项");
    }
}
