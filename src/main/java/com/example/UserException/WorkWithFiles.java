package com.example.UserException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.io.IOException;
import java.io.BufferedReader;

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
        String randomLine = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("RandomFile.txt"))) {
            Random random = new Random();
            int index = random.nextInt(10) + 1;
            for (int i = 0; i < index; i++) {
                randomLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
        return randomLine;
    }
}