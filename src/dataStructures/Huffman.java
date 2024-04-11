package dataStructures;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Huffman {
    private static long[] freq;
    private static final List<Long> cFreq = new ArrayList<>();

    public static void main(String[] args) {
        readFromFile();
        writeToFile(getLen());
    }

    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("huffman.in"))) {
            int n = Integer.parseInt(reader.readLine());
            freq = new long[n];
            String[] buff = reader.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                freq[i] = Long.parseLong(buff[i]);
            }

            Arrays.sort(freq);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long getLen() {
        long totalLength = 0;
        int frequencyIndex = 0;
        int combinedIndex = 0;

        while (cFreq.size() != freq.length - 1) {
            long combinedFrequency = 0;
            if (frequencyIndex >= freq.length) {
                combinedFrequency = cFreq.get(combinedIndex) + cFreq.get(combinedIndex + 1);
                combinedIndex += 2;

            } else {
                for (int i = 0; i < 2; i++) {
                    if (frequencyIndex == freq.length) {
                        combinedFrequency += cFreq.get(combinedIndex);
                        combinedIndex++;
                    } else {
                        if (combinedIndex == cFreq.size()) {
                            combinedFrequency += freq[frequencyIndex];
                            frequencyIndex++;
                        } else {
                            if (freq[frequencyIndex] < cFreq.get(combinedIndex)) {
                                combinedFrequency += freq[frequencyIndex];
                                frequencyIndex++;
                            } else {
                                combinedFrequency += cFreq.get(combinedIndex);
                                combinedIndex++;
                            }
                        }
                    }
                }

            }
            totalLength += combinedFrequency;
            cFreq.add(combinedFrequency);
        }

        return totalLength;
    }

    private static void writeToFile(long num) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("huffman.out"))) {
            bufferedWriter.write(String.valueOf(num));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}