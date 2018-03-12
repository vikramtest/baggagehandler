package com.bb.sample.dao;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CSVFileLoaderTest {
	

	public static final String fileName = "bagsInfo.csv";
	
	@Test
	public void readCSVFileLoadTest()
		throws IOException,FileNotFoundException
	{
		
		CSVFileLoader csvLoader = new CSVFileLoader();
		List<String[]> fileData = csvLoader.readCSVFile(fileName);
		assertNotNull(fileData);
	}

	@Test(expected=FileNotFoundException.class)
	public void readIncorrectCSVFileLoadTest()
		throws IOException,FileNotFoundException
	{
		CSVFileLoader csvLoader = new CSVFileLoader();
		List<String[]> fileData = csvLoader.readCSVFile("someName");
		
	}
}
