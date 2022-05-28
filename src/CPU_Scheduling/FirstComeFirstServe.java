package CPU_Scheduling;

import java.util.Scanner;

public class FirstComeFirstServe {
    private static class FCFS {
        private float arrivalTime;
        private float burstTime;
        private float waitingTime;
        private float turnAroundTime;

        public FCFS(float arrivalTime, float burstTime) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }

        public void setTurnAroundTime(float turnAroundTime) {
            this.turnAroundTime = turnAroundTime;
        }

        public void setWaitingTime(float waitingTime) {
            this.waitingTime = waitingTime;
        }

        public float getTurnAroundTime() {
            return turnAroundTime;
        }

        public float getWaitingTime() {
            return waitingTime;
        }

        public float getArrivalTime() {
            return arrivalTime;
        }

        public float getBurstTime() {
            return burstTime;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        FCFS[] process = new FCFS[n];
        float at, bt;
        for (int i = 0;i < n;i++) {
            System.out.print("Enter arrival time of process " + i + ": ");
            at = scanner.nextFloat();
            System.out.print("Enter burst time of process " + i + ": ");
            bt = scanner.nextFloat();
            process[i] = new FCFS(at, bt);
        }

        float sumTurnAroundTime = 0, sumWaitingTime = 0, startTime = 0;
        for (int i = 0;i < n;i++) {
            /**
             * TAT = BT + WT = CT - AT
             */
            process[i].setWaitingTime(startTime - process[i].getArrivalTime());
            sumWaitingTime += process[i].getWaitingTime();
            sumTurnAroundTime += process[i].getBurstTime() + process[i].getWaitingTime();
            startTime += process[i].getBurstTime();
        }

        System.out.println("Average turn around time: " + (sumTurnAroundTime/n));
        System.out.println("Average waiting time: " + (sumWaitingTime/n));
    }
}
