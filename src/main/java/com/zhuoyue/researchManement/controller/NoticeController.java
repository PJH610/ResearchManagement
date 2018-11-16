package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.annotation.SystemControllerLog;
import com.zhuoyue.researchManement.bean.DownloadFile;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.Notice;
import com.zhuoyue.researchManement.enums.RoleType;
import com.zhuoyue.researchManement.service.DownloadFileService;
import com.zhuoyue.researchManement.service.NoticeService;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * Created by 12413 on 2018/5/8.
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController extends BaseApiController
{
	@Autowired
	NoticeService noticeService;

	@Autowired
	private DownloadFileService downloadFileService;

	@Autowired
	FileUploadUtils fileUploadUtils;

	@PostMapping("/add")
	@Transactional
	public Map<String, Object> insert(@RequestParam(required = false) CommonsMultipartFile file, @RequestParam String theme, @RequestParam String host,
									  @RequestParam String content, @RequestParam Boolean top)
	{

		if (theme == null || theme.trim().length() == 0) return onBadResp("主题名称不能为空");
		if (host == null || host.trim().length() == 0) return onBadResp("发布人名称不能为空");
		if (top == null) return onBadResp("置顶不能为空");
		if (content == null ||  content.trim().length() == 0) return onBadResp("内容不能为空");

		Notice notice = new Notice();
		notice.setTheme(theme.trim());
		notice.setHost(host.trim());
		notice.setDate(new Date());
		notice.setTop(top);
		notice.setClick(0);
		notice.setContent(content.trim());

		String filePath = "";
		if (file != null && !file.isEmpty()) {
			filePath = fileUploadUtils.getWordPath(file);
			if (filePath == null) return onBadResp("该文件不符合格式");

			notice.setFile(new DownloadFile(filePath, file.getOriginalFilename()));
			downloadFileService.insert(notice.getFile());
		}

		if (noticeService.insert(notice) > 0) {
			if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
			return onSuccessRep("添加成功");
		}
		return onBadResp("添加失败");
	}

	@GetMapping("/list")
	@SystemControllerLog("通知列表")
	@RequiresRoles(value = {RoleType.ADMIN, RoleType.EXPERT, RoleType.CITY_RESEARCH, RoleType.AREA_RESEARCH, RoleType.SCHOOL_RESEARCH, RoleType.SUBJECT_HOST}, logical = Logical.OR)
	public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size)
	{
		PageHelper.startPage(page_num, page_size);
		return onDataResp(new MyPageInfo<Notice>(noticeService.list()));
	}

	@GetMapping("/show/{id}")
	public Map<String, Object> show(@PathVariable Long id)
	{
		return onDataResp(noticeService.selectById(id));
	}

	@PostMapping("/update")
	@Transactional
	public Map<String, Object> update(@RequestParam Long id, String theme, String host, Boolean top, String content, @RequestParam(required = false) CommonsMultipartFile file) {
		if (theme != null && theme.trim().length() == 0) return onBadResp("主题名称不能为空");
		if (content != null && content.trim().length() == 0) return onBadResp("内容不能为空");

		Notice notice = new Notice();
		notice.setId(id);
		if (theme != null) notice.setTheme(theme.trim());
		if (host != null) notice.setHost(host);
		if (top != null) notice.setTop(top);
		if (content != null) notice.setContent(content.trim());

		String filePath = "";
		if (file != null && !file.isEmpty()) {
			filePath = fileUploadUtils.getWordPath(file);
			if (filePath == null) return onBadResp("该文件不符合格式");

			notice.setFile(new DownloadFile(filePath, file.getOriginalFilename()));
			downloadFileService.insert(notice.getFile());
		}

		if (notice.getTheme() == null && notice.getHost() == null && notice.getTop() == null && notice.getContent() == null && notice.getFile() == null) {
			return onBadResp("参数不能为空");
		}

		noticeService.updatebyId(notice,id);
		if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
		return onSuccessRep("修改成功");
	}

	@PostMapping("/delete")
	public Map<String, Object> delete(@RequestParam Long[] id)
	{
		noticeService.deleteById(id);
		return onSuccessRep("删除成功");
	}
}
