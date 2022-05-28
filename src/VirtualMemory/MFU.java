package VirtualMemory;

import java.util.ArrayList;
import java.util.Scanner;

public class MFU {
    private static int n, i, j, frequency, currTime;

    private static int pageFound(int pno, Frame[] frames) {
        int i;
        for(i = 0;i < n;i++) {
            if(frames[i].pno == pno)
                return i;
        }
        return -1;
    }

    private static int freeFrame(Frame[] frames) {
        for(int i = 0;i < n;i++) {
            if(frames[i].pno == -1)
                return i;
        }
        return -1;
    }

    private static int mfu(Frame[] frames) {
        int max = 0;
        for(int i = 0;i < n;i++) {
            if(frames[i].freq > frames[max].freq)
                max = i;
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of frames: ");
        int size = scanner.nextInt();
        Frame[] frames = new Frame[size];
        for(int i = 0;i < size;i++) {
            frames[i].setPno(-1);
            frames[i].setFreq(0);
        }
        ArrayList<Integer> references = new ArrayList<>();
        for(int i = 0;i < 10;i++) {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 10 + 1;
            int randomInt = (int) randomDouble;
            references.add(randomInt);
        }
        int np = references.size(), p, pno, pageFault = 0, flag;
        currTime = 1;
        for(p = 0;p < np;p++) {
            frequency = 0;
            flag = 0;
            pno = references.get(p);
            j = pageFound(pno, frames);
            if(j == -1) {
                pageFault++;
                j = freeFrame(frames);
                if(j == -1) {
                    j = mfu(frames);
                }
                frames[j].pno = pno;
                frames[j].freq = 1;
                flag = 1;
            }
            else frames[j].freq++;

            for(int i = 0;i < size;i++) {
                System.out.print("Frame: " + frames[i].pno + ". Frequency: " + frames[i].freq);
            }
            if(flag == 1)
                System.out.println("\tPage fault");
            else System.out.println("\tPage hit");
        }

        System.out.println("Number of page faults: " + pageFault);
    }
}

class Frame {
    int pno;
    int freq;

    public void setPno(int pno) {
        this.pno = pno;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}