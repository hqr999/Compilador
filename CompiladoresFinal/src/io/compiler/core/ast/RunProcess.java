package io.compiler.core.ast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class RunProcess {

    public static void main(String[] args) {
        try {
            runProcess(new String[]{"javac", "meuPrograma.java"});
            Process process = runProcess(new String[]{"java", "meuPrograma"});
            handleProcessIO(process);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Process runProcess(String[] command) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        return process;
    }

    private static void handleProcessIO(Process process) throws Exception {
    	// output
        Thread outputThread = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        outputThread.start();

        // input
        Thread inputThread = new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in);
                 OutputStream outputStream = process.getOutputStream()) {
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    outputStream.write((input + "\n").getBytes());
                    outputStream.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        inputThread.start();

        process.waitFor();
        outputThread.join();
        inputThread.join();
    }
}