package com.musicshop.databases;

import java.util.*;

public class FindedMetaData {
    LinkedList<String> tables = new LinkedList<>();
    Map<String, LinkedList<String>> columns = new HashMap<>();

    public FindedMetaData() {
    }

    public void setColumns(String table, LinkedList<String> columnsList){
        tables.add(table);
        columns.put(table, columnsList);
    }

    public LinkedList<String> getColumns(int index){
        return columns.get(tables.get(index));
    }

    public LinkedList<String> getColumns(String table){
        return columns.get(table);
    }

    public int getSize(){
        return columns.size();
    }

    public LinkedList<String> getTables() {
        return tables;
    }
}
