package discreteOptimization;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class TheBusiestEmployee {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE))
        ) {
            int m = Integer.parseInt(br.readLine());
            long[] works = readArray(br.readLine());

            Queue<Worker> queue = new PriorityQueue<>(Comparator.comparingLong(Worker::getWorkTime));
            IntStream.range(0, m).forEach(i ->
                    queue.add(new Worker(i + 1))
            );

            int[] workerForWork = new int[works.length];

            Integer[] workIndices = IntStream.range(0, works.length)
                    .boxed()
                    .sorted(Comparator.comparingLong(i -> -works[i]))
                    .toArray(Integer[]::new);

            Arrays.stream(workIndices).forEach(i -> {
                        Worker worker = queue.poll();
                        worker.addWork(works[i]);
                        workerForWork[i] = worker.getId();
                        queue.add(worker);
                    }
            );

            Worker maxWorker = Collections.max(queue, Comparator.comparingLong(Worker::getWorkTime));

            bw.write(maxWorker.getWorkTime() + "\n");
            Arrays.stream(workerForWork).forEach(id -> {
                        try {
                            bw.write(id + " ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    private static long[] readArray(String str) {
        StringTokenizer st = new StringTokenizer(str);
        int n = st.countTokens();
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        return arr;
    }

    private static class Worker {
        private final int id;
        private long workTime = 0;

        public Worker(int id) {
            this.id = id;
        }

        public void addWork(long work) {
            this.workTime += work;
        }

        public int getId() {
            return id;
        }

        public long getWorkTime() {
            return workTime;
        }
    }
}