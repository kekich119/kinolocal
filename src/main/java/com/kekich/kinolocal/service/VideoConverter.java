package com.kekich.kinolocal.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class VideoConverter {

    /**
     * Конвертирует .mkv файл в .mp4 с помощью ffmpeg
     * @param mkvFile файл для конвертации
     * @return путь к новому .mp4 файлу или null при ошибке
     */
    public String convertMkvToMp4(File mkvFile) {
        try {
            String outputPath = mkvFile.getAbsolutePath().replace(".mkv", ".mp4");

            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg", "-i", mkvFile.getAbsolutePath(),
                    "-c:v", "libx264", "-preset", "fast",
                    "-crf", "23", "-c:a", "aac", outputPath
            );
            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("❌ Ошибка конвертации: " + mkvFile.getName());
                return null;
            }

            System.out.println("✅ Конвертация завершена: " + outputPath);
            ProcessBuilder pb2 = new ProcessBuilder();
            pb2.command("python3", "deletemkv.py");
            return outputPath;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}