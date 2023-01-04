package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.TSV;
import com.example.service.InsertItemService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * DB登録を操作するコントローラ.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/jdbc")
public class JDBCController {

	@Autowired
	private InsertItemService inserService;

	@GetMapping("/item")
	public String itemInsert() throws SQLException, IOException {

		// originalテーブルとcategoryテーブルを結合したやつを取得
		List<Item> itemList = inserService.findAll();
		System.out.println("全件検索完了");
		System.out.println(itemList.get(100));

		Connection c = DriverManager.getConnection("jdbc:mysql://mysql:3306/mercari", "root", "root");
		PreparedStatement p = c.prepareStatement("INSERT INTO items VALUES( ?, ?, ?, ?, ?, ?, ?, ? )");
		int count = 0;
		int listCnt = itemList.size();
		System.out.println(listCnt);
		for (int i = 0; i < itemList.size(); i++) {
			p.setInt(1, itemList.get(i).getId());
			p.setString(2, itemList.get(i).getName());
			if (itemList.get(i).getCondition_id() == null) {
				p.setObject(3, null);
			} else {
				p.setInt(3, itemList.get(i).getCondition_id());
			}
			if (itemList.get(i).getCategory_id() == null) {
				p.setObject(4, null);
			} else {
				p.setInt(4, itemList.get(i).getCategory_id());
			}
			p.setString(5, itemList.get(i).getBrand());
			p.setDouble(6, itemList.get(i).getPrice());
			p.setInt(7, itemList.get(i).getShipping());
			p.setString(8, itemList.get(i).getDescription());
			p.addBatch();
			count++;
			if (count % 5000 == 0 || count == listCnt) {// ここを変更
				p.executeBatch();
				System.out.println(i + "件終了");
			}
		}

		p.close();
		c.commit();
		c.close();

		return "jdbc";
	}

	public List<TSV> readTSV() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(TSV.class).withHeader().withColumnSeparator('\t');
		Path path = Paths.get("data/train.tsv");
		try (BufferedReader br = Files.newBufferedReader(path)) {
			MappingIterator<TSV> it = mapper.readerFor(TSV.class).with(schema).readValues(br);
			List<TSV> itemList = it.readAll();
			System.out.println("読み込み完了");
			return itemList;
		}
	}

	@GetMapping("")
	public String jdbc() throws SQLException, IOException {

		// TSVファイル読み込み
		List<TSV> itemList = readTSV();

		Connection c = DriverManager.getConnection("jdbc:mysql://mysql:3306/mercari", "root", "root");
		PreparedStatement p = c.prepareStatement("INSERT INTO original VALUES( ?, ?, ?, ?, ?, ?, ?, ? )");
		int count = 0;
		int listCnt = itemList.size();
		System.out.println(listCnt);
		for (int i = 0; i < itemList.size(); i++) {
			p.setString(1, itemList.get(i).getTrain_id());
			p.setString(2, itemList.get(i).getName());
			p.setString(3, itemList.get(i).getItem_condition_id());
			p.setString(4, itemList.get(i).getCategory_name());
			p.setString(5, itemList.get(i).getBrand_name());
			p.setString(6, itemList.get(i).getPrice());
			p.setString(7, itemList.get(i).getShipping());
			p.setString(8, itemList.get(i).getItem_description());
			p.addBatch();
			count++;
			if (count == listCnt || i % 5000 == 0) {// ここを変更
				p.executeBatch();
				System.out.println(i + "件終了");
			}
		}

		p.close();
		c.commit();
		c.close();

		return "jdbc";
	}

}
