package com.example.UserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.*;
import java.io.IOException;

@Component
public class WorkWithFiles {
    public void writeFile(String text) {
        try (FileWriter fileWriter = new FileWriter("UserData.txt", true)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(text);
            fileWriter.write(json);
            fileWriter.write("\n");
        } catch (IOException e) {
            System.out.println("Ошибка " + e);
        }
    }
    public void readFile(String text){
    }
}
