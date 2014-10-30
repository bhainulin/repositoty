package com.epam.module4.functionality.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.module4.model.BankObject;

public class BankModelFileReader {
	public static final String SEPARATOR = "@";
	public static final HashMap<String, AbstractParser> FILE_PARSERS = new HashMap<>();

	static {
		FILE_PARSERS.put("Bank", new BankParser());
		FILE_PARSERS.put("Currency", new CurrencyParser());
		FILE_PARSERS.put("Person", new PersonParser());
		FILE_PARSERS.put("Account", new AccountParser());
	}

	private String fileName;

	private Map<String, List<BankObject>> entities;

	public BankModelFileReader(String fileName) {
		this.fileName = fileName;
		entities = new HashMap<>(4);
	}
	
	public Map<String, List<BankObject>> getEntities(){
		return entities;
	}

	public static void main(String[] args) throws IOException {
		String fileName = "D:\\BankModel.txt";
		BankModelFileReader fr = new BankModelFileReader(fileName);
		// fr.readAllLines();

		fr.parseValuesFromFile(fr.readAllLines());
		
		System.out.println(fr.getEntities());
		System.out.println(fr.getEntities().size());
		//System.out.println(entities);
	}

	public List<String> readAllLines() throws IOException {
		List<String> allLines = new ArrayList<>();

		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String fileString = null;
		while ((fileString = br.readLine()) != null) {
			System.out.println(fileString);
			allLines.add(fileString);
		}

		return allLines;
	}

	public void parseValuesFromFile(List<String> allLines) {
		for (String current : allLines) {
			String[] values = current.split(SEPARATOR);
			if (values != null && values.length > 1) {
				String type = values[0];
				if (FILE_PARSERS.containsKey(type)) {
					AbstractParser parser = FILE_PARSERS.get(type);
					BankObject object = parser.parse(values);
					if (entities.containsKey(type)) {
						List<BankObject> list = entities.get(type);
						list.add(object);
					} else {
						List<BankObject> list = new ArrayList<>();
						list.add(object);
						entities.put(type, list);
					}

				}else{
					throw new IllegalArgumentException("Incorrect type in file");
				}

			}else{
				throw new IllegalArgumentException("Incorrect row in file.");
			}
		}

	}
}
