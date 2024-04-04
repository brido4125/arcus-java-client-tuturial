package mod;

import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

public class BenchmarkOperations {
  public static void main(String[] args) {
    final int SIZE = 1000000;  // 데이터 크기
    final int DIVISOR = 1024;  // 나누는 수 (2의 거듭제곱)
    final int N = 20;  // 반복 횟수

    try (FileWriter csvWriter = new FileWriter("benchmark_results.csv")) {
      // CSV 파일 헤더 작성
      csvWriter.append("Iteration,Operation,Average,Min,Max,Median,Standard Deviation\n");

      // N번 실행
      for (int n = 0; n < N; n++) {
        long[] timesModulo = new long[SIZE];
        long[] timesBitwiseAnd = new long[SIZE];

        // 모듈로 연산 벤치마크
        for (int i = 0; i < SIZE; i++) {
          long startTime = System.nanoTime();
          int result = i % DIVISOR;
          long endTime = System.nanoTime();
          timesModulo[i] = endTime - startTime;
        }

        // 비트 AND 연산 벤치마크
        for (int i = 0; i < SIZE; i++) {
          long startTime = System.nanoTime();
          int result = i & (DIVISOR - 1);
          long endTime = System.nanoTime();
          timesBitwiseAnd[i] = endTime - startTime;
        }

        // 결과를 CSV 파일에 기록
        writeStatsToCSV(csvWriter, n + 1, "Modulo", timesModulo);
        writeStatsToCSV(csvWriter, n + 1, "Bitwise AND", timesBitwiseAnd);
        csvWriter.append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeStatsToCSV(FileWriter csvWriter, int iteration, String operation, long[] times) throws IOException {
    Arrays.sort(times);  // 통계를 위해 정렬합니다.
    long total = 0;
    for (long time : times) {
      total += time;
    }
    double average = (double) total / times.length;
    double variance = 0;
    for (long time : times) {
      variance += Math.pow(time - average, 2);
    }
    double standardDeviation = Math.sqrt(variance / times.length);

    // 중앙값 계산
    double median = (times[times.length / 2 - 1] + times[times.length / 2]) / 2.0;

    // CSV 파일에 기록
    csvWriter.append(String.format("%d,%s,%.2f,%d,%d,%.2f,%.2f\n", iteration, operation, average, times[0], times[times.length - 1], median, standardDeviation));
  }
}
