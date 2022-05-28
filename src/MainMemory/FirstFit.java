package MainMemory;

import java.util.Arrays;
import java.util.Scanner;

public class FirstFit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of processes: ");
        int numberProcess = scanner.nextInt();
        System.out.print("Number of partitions: ");
        int numberPartition = scanner.nextInt();

        int countProcess = numberProcess;
        int[] processList = new int[numberProcess];
        int[] partitionList = new int[numberPartition];

        System.out.print("Size of processes: ");
        for(int i = 0;i < numberProcess;i++) {
            processList[i] = scanner.nextInt();
        }
        System.out.print("Size of partitions: ");
        for(int i = 0;i < numberPartition;i++) {
            partitionList[i] = scanner.nextInt();
        }

        for (int k : processList) {
            for (int j = 0; j < partitionList.length; j++) {
                if (k <= partitionList[j]) {
                    partitionList[j] -= k;
                    countProcess--;
                    break;
                }
            }
        }

        System.out.println("Number of remaining processes: " + countProcess);
        System.out.println("State of partitions: " + Arrays.toString(partitionList));
    }
}
