package com.sourcecode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourcecode.dao.S01_SourcecodeReadingRecorderDao;
import com.sourcecode.entity.S01_SourceCodeStatusEntity;
import com.sourcecode.resource.S01_SourcecodeStatusResource;

@Service
public class S01_SourcecodeReadingRecorderService {

	@Autowired
	S01_SourcecodeReadingRecorderDao dao;

	/*
	 * フォルダに関係無く、すべての
	 * 「ソースファイル名」、「状態」、「種別」、「読了日」
	 * を取得する
	 * 
	 */
	public List<S01_SourcecodeStatusResource> getAllSourcefile() {

		// APIの呼び出し元に返却するリソース
		List<S01_SourcecodeStatusResource> resourceList = new ArrayList<>();

		// ソースコードの読み込み状態を全て取得
		List<S01_SourceCodeStatusEntity> entityList = dao.getAllSourcefile();

		entityList.forEach(e -> {

			// リストに追加するリソース
			S01_SourcecodeStatusResource resource = new S01_SourcecodeStatusResource();

			// エンティティからリソースに変換
			resource.setSourcefileId(e.getSourcefileId());
			resource.setSourcefileName(e.getSourcefileName());
			resource.setCategoryId(e.getCategoryId());
			resource.setStatusId(e.getStatusId());
			resource.setCompleteDate(e.getCompleteDate());

			// リストに追加
			resourceList.add(resource);
		});

		return resourceList;
	}

}
