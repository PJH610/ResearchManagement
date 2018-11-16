package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by 12413 on 2018/5/8.
 */
@JsonIgnoreProperties(value = {"handler"})
public class Notice {
	private Long id;
	private String theme;
	private String host;
	private Date date;
	private Integer click;
	private String content;
	private Boolean top;
	private DownloadFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}

	public DownloadFile getFile() {
		return file;
	}

	public void setFile(DownloadFile file) {
		this.file = file;
	}
}
