package Deadlock;

import java.util.Scanner;

public class Banker {
    private static int P = 5, R =3;

    public static int[][] initNeedMatrix(int[][] max, int[][] allocation) {
        int[][] need = new int[P][R];
        for(int i = 0;i < P;i++)
            for(int j = 0;j < R;j++)
                need[i][j] = max[i][j] - allocation[i][j];

        return need;
    }

    public static void checkSafe(int[][] max, int[][] allocation, int[] avaliable) {
            int[][] need = initNeedMatrix(max, allocation);
            boolean[] finish = new boolean[P];
            int[] work = avaliable;
            int count = 0;
            int[] state = new int[P];
            for(int i = 0;i < P;i++)
                finish[i] = false;

            while(count < P) {
                boolean check = false;
                for (int i = 0;i < P;i++)
                {
                    if (!finish[i])
                    {
                        int j;
                        for (j = 0; j < R; j++)
                            if (work[j] < need[i][j])
                                break;

                        if (j == R)
                        {
                            for (int k = 0 ; k < R ; k++)
                                work[k] += allocation[i][k];

                            state[count] = i;
                            count++;
                            finish[i] = true;
                            check = true;
                        }
                    }
                }

                if (!check)
                {
                    System.out.println("System is not safe");
                    break;
                }
            }

        System.out.println("System is safe, safe sequence is: ");
        for (int i = 0; i < P ; i++)
            System.out.println(state[i] + " ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] allocation = new int[P][R];
        int[][] max = new int[P][R];
        int[] avaliable = new int[R];

        for(int i = 0;i < P;i++)
            for(int j = 0;j < R;j++)
                allocation[i][j] = scanner.nextInt();

        for(int i = 0;i < P;i++)
            for(int j = 0;j < R;j++)
                max[i][j] = scanner.nextInt();

        for(int i = 0;i < R;i++)
            avaliable[i] = scanner.nextInt();

        checkSafe(max, allocation, avaliable);
    }
}
