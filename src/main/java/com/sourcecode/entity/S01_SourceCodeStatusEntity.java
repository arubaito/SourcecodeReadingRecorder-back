package com.sourcecode.entity;

import java.util.Date;

import lombok.Data;

@Data
public class S01_SourceCodeStatusEntity {

	private int sourcefileId;

	private String sourcefileName;

	private int categoryId;

	private int statusId;

	private Date completeDate;
}
