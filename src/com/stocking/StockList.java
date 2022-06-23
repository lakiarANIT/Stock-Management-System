package com.stocking;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class StockList {

    //public String name;
    private final String filename;
    int i=1;
    StockItem item;
    double total=0;
    Scanner sc = new Scanner(System.in);
    public StockList(String filename) {
        this.filename = filename;
    }

    public String addItem() throws IOException {
        String name,desc;
        double pr;
        int num;
        CSVWriter writer = new CSVWriter(new FileWriter("Stock.csv",true),',','\0','\n',"\n");

        System.out.println("Enter the name of item :");
        name=sc.nextLine();
        System.out.println("Enter the description:");
        desc=sc.nextLine();
        System.out.println("Enter the number of items :");
        num=sc.nextInt();

        System.out.println("Enter the price of item:");
        pr=sc.nextDouble();

        final int i1 = 4;
        String line1[] = new String[4];
        line1[0]=name;
        line1[1]=Integer.toString(num);
        line1[2]=Double.toString(pr);
        line1[3]=desc;
        writer.writeNext(line1);
        writer.flush();
        return null;
    }
    public void findItem(String file) throws FileNotFoundException {

        System.out.println("Enter the name of the product");
        String namesearch=sc.nextLine();

        Scanner csvFile = new Scanner(new File(file));
        csvFile.useDelimiter("\n");
        String dataRow;
        int numInStock;
        double price;
        String description;
        boolean isfound=false;
        while (csvFile.hasNextLine()) {
            dataRow = csvFile.nextLine();
            String[] fields = dataRow.split(","); // split method split each line from csv file

            String itemName = fields[0].trim();
            if(namesearch.equals(itemName)){
                numInStock = Integer.parseInt(fields[1].trim());
                price = Double.parseDouble(fields[2].trim());
                description= fields[3].trim();
                // Get rid off white space by trim method
                //check the class to store the value from csv file
                item=new StockItem(itemName,numInStock,price,description);
                System.out.println(item.toString());
                isfound=true;
            }


        }
        if(isfound==false){
            System.out.println(namesearch+" is not found in our system");
        }

    }
    public void removeItem() throws IOException, CsvException {
        load();
        int row;
        System.out.println("Select the row you want to remove");
        row=sc.nextInt();
        if(row>0&&row<i+1){
        CSVReader reader2 = new CSVReader(new FileReader("Stock.csv"));
        List<String[]> allElements = reader2.readAll();
        allElements.remove(row-1);
        FileWriter sw = new FileWriter("Stock.csv");
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(allElements);
        writer.close();
        System.out.println("\n\nYou have succesfully remove row "+row+"\n");
        }
        else {
            System.out.println("THERE IS NO SUCH ROW");
            i=1;
        }
    }
    public void editItem() throws IOException, CsvException {
        load();

        File inputFile = new File("Stock.csv");

// Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
// get CSV row column  and replace with by using row and column
        int row,col;
        System.out.println("Enter the row you want to edit");
        row=sc.nextInt();
        if(row>0&&row<i+1) {
            System.out.println("Enter the colum yow want to edit Hint can be 1-4");
            col = sc.nextInt();
            if (col >0 && col < 5) {
                System.out.println("Enter the value you want");
                if (col == 2) {
                    int a = sc.nextInt();
                    String c = Integer.toString(a);
                    csvBody.get(row - 1)[col - 1] = c;
                }
                if (col == 1 || col == 4) {
                    Scanner sc1 = new Scanner(System.in);
                    String ab = sc1.nextLine();
                    csvBody.get(row - 1)[col - 1] = ab;
                }
                if (col == 3) {
                    Double b = sc.nextDouble();
                    String d = Double.toString(b);
                    csvBody.get(row - 1)[col - 1] = d;
                }

                reader.close();
                CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
                writer.writeAll(csvBody);
                writer.flush();
                writer.close();

                System.out.println("\nEdited Succesfully *************** \n\nthe new stock is");
            }
            else {
                System.out.println("THERE IS NO SUCH COLUMN");
                i=1;
            }
        }

        else {
            System.out.println("THERE IS NO SUCH ROW");
            i=1;
        }

        load();

    }
    public int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
    public double getTotalValue() throws IOException, CsvException {
        File inputFile = new File("Stock.csv");

// Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();

        for(int k=0;k<count("Stock.csv");k++){
            double gross=Integer.parseInt(csvBody.get(k )[1])*Double.parseDouble(csvBody.get(k )[2]);
            //System.out.println(gross);
            total+=gross;
        }
        return total;
    }
    public void load() throws IOException, CsvValidationException {
        FileReader filereader = new FileReader("Stock.csv");

        // create csvReader object passing
        // file reader as a parameter
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;

        // we are going to read data line by line

        while ((nextRecord = csvReader.readNext()) != null&&i>0) {
            System.out.print("row"+i+" ");
            for (String cell : nextRecord) {
                System.out.print("\t"+cell + "\t\t\t");
            }
            i++;
            System.out.println();
        }
        }

    public void save(){

    }
}
