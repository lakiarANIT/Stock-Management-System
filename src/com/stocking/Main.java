package com.stocking;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CsvException {
	// write your code here


        StockList list=new StockList("Stock.csv");
        //list.addItem();
        System.out.println("\tcolumn 1\tcolumn 2\tcolumn 3\tcolumn 4");
        System.out.println("\tproduct name\tStock Count\tprice\tDescription");
        //list.findItem("Stock.csv");
        //list.editItem();
        //list.removeItem();
        System.out.println(list.getTotalValue());

    }
}
