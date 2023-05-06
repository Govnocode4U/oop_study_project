import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Component
public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{5, 6}, {7, 8}};

        // Создаем пул потоков с фиксированным количеством потоков
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Выполняем умножение матриц в отдельном потоке и получаем объект Future,
        // который позволяет получить результат выполнения задачи
        Future<int[][]> futureResult = executor.submit(() -> multiplyMatrices(matrixA, matrixB));

        try {
            int[][] result = futureResult.get();

            // Вывод исходных матриц
            System.out.println("Матрица A:");
            printMatrix(matrixA);
            System.out.println("Матрица B:");
            printMatrix(matrixB);

            // Вывод результата
            System.out.println("Результат умножения матриц:");
            printMatrix(result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Завершаем работу пула потоков
        executor.shutdown();
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int numRowsA = a.length;
        int numColsA = a[0].length;
        int numRowsB = b.length;
        int numColsB = b[0].length;

        if (numColsA != numRowsB) {
            throw new IllegalArgumentException("Неправильные размеры матриц");
        }

        int[][] result = new int[numRowsA][numColsB];
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < numRowsA; i++) {
            for (int j = 0; j < numColsB; j++) {
                int row = i;
                int col = j;

                // Выполняем умножение элемента матрицы в отдельном потоке и получаем объект Future,
                // который позволяет получить результат выполнения задачи
                Future<Integer> future = executor.submit(() -> {
                    int sum = 0;
                    for (int k = 0; k < numColsA; k++) {
                        sum += a[row][k] * b[k][col];
                    }
                    return sum;
                });

                try {
                    // Получаем результат выполнения задачи и записываем его в результирующую матрицу
                    result[i][j] = future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        // Завершаем работу пула потоков
        executor.shutdown();

        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public void printHello() {
        System.out.println("HELLO");
    }
}


