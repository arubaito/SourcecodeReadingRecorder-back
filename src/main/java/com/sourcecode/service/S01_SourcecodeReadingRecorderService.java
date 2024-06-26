package com.sourcecode.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourcecode.dao.S01_SourcecodeReadingRecorderDao;
import com.sourcecode.entity.S01_PackageEntity;
import com.sourcecode.entity.S01_SourceCodeStatusEntity;
import com.sourcecode.resource.S01_PackageResource;
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

	/*
	 * 引数に渡された「ソースファイルID」の状態を「状態ID」で更新する
	 */
	public void updateStatus(String sourceFileId, String statusId) {

		// 更新
		int sourceFileIdInt = Integer.valueOf(sourceFileId);
		int statusIdInt = Integer.valueOf(statusId);

		int updateCount = dao.updateStatus(sourceFileIdInt, statusIdInt);

		// ログ出力（ロガー面倒なのでとりあえず標準出力に出力）
		if (updateCount == 0) {

			System.out.println("更新0件");
		} else {

			System.out.println("ソースファイルID%sの状態を更新".formatted(sourceFileId));
		}
	}

	/*
	 * 全ての「パッケージID」と「パッケージ名」を取得してリソースを返却する
	 */
	public List<S01_PackageResource> getAllPackage() {

		// APIの呼び出し元に変換するリソース
		List<S01_PackageResource> resourceList = new ArrayList<S01_PackageResource>();

		// DBからパッケージのリストを取得
		List<S01_PackageEntity> entityList = dao.getAllPackage();

		// DBから取得したEntityのリストをResourceのリストに変換する
		entityList.forEach(e -> {

			S01_PackageResource resource = new S01_PackageResource();

			resource.setPackageId(e.getPackageId());
			resource.setPackageName(e.getPackageName());

			resourceList.add(resource);
		});

		return resourceList;
	}

	/*
	 * 引数に渡された「ソースファイルID」の完了日を更新する
	 */
	public void updateCompleteDate(String sourceFileId, String completeDateString) {

		// 更新
		int sourceFileIdInt = Integer.valueOf(sourceFileId);
		java.sql.Date completeDate = java.sql.Date.valueOf(completeDateString);

		int updateCount = dao.updateCompleteDate(sourceFileIdInt, completeDate);

		// ログ出力（ロガー面倒なのでとりあえず標準出力に出力）
		if (updateCount == 0) {

			System.out.println("更新0件");
		} else {

			System.out.println("ソースファイルID%sの完了日を更新".formatted(sourceFileId));
		}
	}

	/*
	 * 指定されたパッケージIDの
	 * 「ソースファイル名」、「状態」、「種別」、「読了日」
	 * を取得する
	 * 
	 */
	public List<S01_SourcecodeStatusResource> getSourcefileByPackageId(String packageId) {

		// APIの呼び出し元に返却するリソース
		List<S01_SourcecodeStatusResource> resourceList = new ArrayList<>();

		// パッケージIDのソースファイルを取得。
		// パッケージIDが0の場合は全て取得する。
		int packageIdInt = Integer.valueOf(packageId);
		List<S01_SourceCodeStatusEntity> entityList;

		if (packageIdInt == 0) {

			entityList = dao.getAllSourcefile();
		} else {

			entityList = dao.getSourcefileByPackageId(packageIdInt);
		}

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
