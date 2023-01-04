package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.TSV;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Controller
@RequestMapping("/tsv")
public class TsvReadController {

	@GetMapping("")
	public String index() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(TSV.class).withHeader().withColumnSeparator('\t');
		Path path = Paths.get("data/train.tsv");
		try (BufferedReader br = Files.newBufferedReader(path)) {
			MappingIterator<TSV> it = mapper.readerFor(TSV.class).with(schema).readValues(br);
			List<TSV> itemList = it.readAll();
			System.out.println(itemList.size());
		}
		return "item";
	}

}
