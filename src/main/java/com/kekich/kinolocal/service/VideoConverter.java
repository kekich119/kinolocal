package com.kekich.kinolocal.service;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class VideoConverter {

    /**
     * Конвертирует .mkv файл в .mp4 с аппаратным ускорением VideoToolbox
     */
    public String convertMkvToMp4(File mkvFile) {
        try {
            String outputPath = mkvFile.getAbsolutePath().replace(".mkv", ".mp4");

            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg",
                    "-i", mkvFile.getAbsolutePath(),
                    "-vcodec", "h264_videotoolbox", // аппаратное ускорение
                    "-b:v", "4M",                   // битрейт видео
                    "-acodec", "aac",               // аудио
                    "-b:a", "192k",                 // битрейт аудио
                    "-preset", "fast",              // быстрый режим
                    "-threads", "4",                 // используем 4 потока CPU
                    "-movflags", "+faststart",      // ускорение старта воспроизведения mp4
                    outputPath
            );

            pb.inheritIO();
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("❌ Ошибка конвертации: " + mkvFile.getName());
                return null;
            }

            System.out.println("✅ Конвертация завершена: " + outputPath);

            // Запуск скрипта удаления исходника


            return outputPath;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }
}