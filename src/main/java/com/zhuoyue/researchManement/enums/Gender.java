package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 12413 on 2018/5/9.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum Gender
{

	UNKNOWN(0, "未知"),
	male(1, "男"),
	female(2, "女");

	private int value;
	private String desc;

	Gender(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public static Gender getBindType(int value){
		for (Gender Gender : Gender.values()){
			if (Gender.value == value)
				return Gender;
		}
		return null;
	}
}
