package com.sourcecode.resource;

import java.util.Date;

import lombok.Data;

@Data
public class S01_SourcecodeStatusResource {

	private int sourcefileId;

	private String sourcefileName;

	private int categoryId;

	private int statusId;

	private Date completeDate;
}
