package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        FileReader reader = new FileReader(new File(fileName));
        char[] buff = new char[2400];
        String jsonString = "";
        while (reader.ready()) {
            reader.read(buff);
            jsonString = buff.toString();
        }
        StringReader stringReader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(reader, clazz);
    }

    public static void main(String[] args) {

    }
}
