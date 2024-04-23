package com.sourcecode.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sourcecode.entity.S01_SourceCodeStatusEntity;

@Repository
public class S01_SourcecodeReadingRecorderDao {

	@Autowired
	JdbcTemplate template;

	/*
	 * フォルダに関係無く、すべての
	 * 「ソースファイル名」、「状態」、「種別」、「読了日」
	 * を取得する
	 */
	public List<S01_SourceCodeStatusEntity> getAllSourcefile() {

		// 返却するリスト
		List<S01_SourceCodeStatusEntity> sourcecodeList = new ArrayList<>();

		// 全ソースファイルを取得する
		String selectSql = """
					SELECT t1.file_id, t2.file_name, t3.category_id, t4.status_id, t5.complete_date
					FROM sourcecode_status t1
					INNER JOIN master_sourcecode_file t2 ON t1.file_id = t2.file_id
					INNER JOIN master_category t3 ON t2.catecory_id = t3.category_id
					INNER JOIN master_status t4 ON t1.status_id = t4.status_id
					LEFT OUTER JOIN sourcecode_complete_date t5 ON t1.file_id = t5.file_id
				""";

		sourcecodeList = template.query(selectSql,

				// 検索結果をEntityに変換するRowMapper
				new RowMapper<S01_SourceCodeStatusEntity>() {

					@Override
					public S01_SourceCodeStatusEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

						S01_SourceCodeStatusEntity entity = new S01_SourceCodeStatusEntity();
						entity.setSourcefileId(rs.getInt("file_id"));
						entity.setSourcefileName(rs.getString("file_name"));
						entity.setCategoryId(rs.getInt("category_id"));
						entity.setStatusId(rs.getInt("status_id"));
						entity.setCompleteDate(rs.getDate("complete_date"));

						return entity;
					}
				});

		return sourcecodeList;

	}

}
