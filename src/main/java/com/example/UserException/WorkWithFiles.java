package com.example.UserException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

@Component
public class WorkWithFiles {
    public void writeFile(String text) {
        try (FileWriter fileWriter = new FileWriter("UserData.txt", true)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(text);
            fileWriter.write(json);
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
    public static String readFile() {
        ArrayList<String> countLine = new ArrayList<String>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("RandomFile.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                countLine.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
        Random random = new Random();
        int index = random.nextInt(countLine.size());
        return countLine.get(index);
    }
}