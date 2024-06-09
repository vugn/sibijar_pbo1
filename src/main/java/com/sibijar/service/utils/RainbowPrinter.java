package com.sibijar.service.utils;

import com.github.lalyos.jfiglet.FigletFont;
import org.fusesource.jansi.Ansi.Color;

import java.io.IOException;

import static org.fusesource.jansi.Ansi.ansi;

public class RainbowPrinter {
    private static final Color[] COLORS = { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA };

    public void printRainbow(String text) {
        try {
            String asciiArt = FigletFont.convertOneLine(text);
            String[] lines = asciiArt.split("\n");
            for (int i = 0; i < lines.length; i++) {
                System.out.println(ansi().fg(COLORS[i % COLORS.length]).a(lines[i]).reset());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}