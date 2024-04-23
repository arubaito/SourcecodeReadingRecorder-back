package com.sourcecode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sourcecode.resource.S01_PackageResource;
import com.sourcecode.resource.S01_SourcecodeStatusResource;
import com.sourcecode.service.S01_SourcecodeReadingRecorderService;

@RestController
public class S01_SourceCodeReadingRecorderController {

	// Logger
	Logger logger = LoggerFactory.getLogger(S01_SourceCodeReadingRecorderController.class);

	@Autowired
	S01_SourcecodeReadingRecorderService service;

	/*
	 * 「種別」「ソースファイル名」「状態」「完了日」を返却
	 */
	@GetMapping("/get-all-sourcefile")
	public List<S01_SourcecodeStatusResource> getSourceCodeStatus() {

		List<S01_SourcecodeStatusResource> sourcecodeList = service.getAllSourcefile();

		return sourcecodeList;
	}

	/*
	 * ソースコードの状態を更新する
	 */
	@GetMapping("/update-status")
	public String updateStatus(@RequestParam String sourceFileId, @RequestParam String statusId) {

		service.updateStatus(sourceFileId, statusId);

		return "200";
	}

	/*
	 * 「パッケージ名」と「パッケージID」を返却
	 */
	@GetMapping("/get-all-package")
	public List<S01_PackageResource> getAllPackage() {

		List<S01_PackageResource> packageList = service.getAllPackage();

		return packageList;
	}
}