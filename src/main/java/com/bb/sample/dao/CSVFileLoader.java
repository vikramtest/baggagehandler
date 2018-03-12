package com.bb.sample.dao;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
 
import com.bb.sample.constant.BaggageHandlerConstant;
 
public class CSVFileLoader {
 
                String cvsSplitBy = BaggageHandlerConstant.CSV_SPLITTER_DELIM;
               
                public List<String[]> readCSVFile(String csvFilePath)
                                throws FileNotFoundException,IOException
                {
                               
                                String line = "";
                                List<String[]> fileContent = new LinkedList<>();
                                BufferedReader br = null;
                                try
                                {
                                                ClassLoader classLoader = getClass().getClassLoader();
                                               
                                                URL fileURL = classLoader.getResource(csvFilePath);
                                               
                                                //Check for File NULL
                                                if(fileURL==null)
                                                                throw new FileNotFoundException("File "+csvFilePath+" Not Found");
                                               
                                                File file = new File(fileURL.getFile());
               
                                                br = new BufferedReader(new FileReader(file));
                                                while ((line = br.readLine()) != null) {
                                                                // use comma as separator
                                                                String[] nodes = line.split(cvsSplitBy);
                                                                fileContent.add(nodes);
                                                }
                                }
                               catch (FileNotFoundException e) {
                                               e.printStackTrace();
                                               throw e;
                               } catch (IOException e) {
                                               e.printStackTrace();
                                               throw e;
                               } finally {
         if (br != null) {
             try {
                 br.close();
             } catch (IOException e) {
                 e.printStackTrace();
                }
                }
                               }
                                return fileContent;         
                               
                               
                }
               
}