package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by 杨 on 2018/5/23.
 */
@JsonIgnoreProperties(value = {"handler"})
public class Record {
    private Long id;
    private String name;
    private String host;
    @JsonFormat(pattern="yyyy",timezone = "GMT+8")
    private Date year;
    private String unit;
    private String pronum;
    private String filenum;
    private String subject;
    private String classification;
    private DownloadFile thesisProposal;
    private DownloadFile mediumReport;
    private DownloadFile finalReport;

    public Record(){

    }

    public Record(Long id, String name, String host, Date year, String unit, String pronum, String filenum, String subject, String classification, DownloadFile thesisProposal, DownloadFile mediumReport, DownloadFile finalReport) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.year = year;
        this.unit = unit;
        this.pronum = pronum;
        this.filenum = filenum;
        this.subject = subject;
        this.classification = classification;
        this.thesisProposal = thesisProposal;
        this.mediumReport = mediumReport;
        this.finalReport = finalReport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPronum() {
        return pronum;
    }

    public void setPronum(String pronum) {
        this.pronum = pronum;
    }

    public String getFilenum() {
        return filenum;
    }

    public void setFilenum(String filenum) {
        this.filenum = filenum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public DownloadFile getThesisProposal() {
        return thesisProposal;
    }

    public void setThesisProposal(DownloadFile thesisProposal) {
        this.thesisProposal = thesisProposal;
    }

    public DownloadFile getMediumReport() {
        return mediumReport;
    }

    public void setMediumReport(DownloadFile mediumReport) {
        this.mediumReport = mediumReport;
    }

    public DownloadFile getFinalReport() {
        return finalReport;
    }

    public void setFinalReport(DownloadFile finalReport) {
        this.finalReport = finalReport;
    }
}
