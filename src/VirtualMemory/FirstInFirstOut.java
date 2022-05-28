package VirtualMemory;

import java.util.ArrayList;
import java.util.Scanner;

public class FirstInFirstOut {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of frames: ");
        int framesNumber = scanner.nextInt();
        System.out.print("Number of references: ");
        int referencesNumber = scanner.nextInt();
        ArrayList<Integer> frames = new ArrayList<>();

        int pageFault = 0, fullFrame = 0, currentReference;
        System.out.print("Enter reference string: ");
        for(int i = 0;i < framesNumber;i++) {
            currentReference = scanner.nextInt();
            if(!frames.contains(currentReference)) {
                frames.add(currentReference);
                pageFault++;
                System.out.println(frames);
            }
        }
        for(int i = 0;i < referencesNumber - framesNumber;i++) {
            currentReference = scanner.nextInt();
            if(fullFrame == framesNumber - 1 && !frames.contains(currentReference)) {
                frames.set(fullFrame, currentReference);
                pageFault++;
                fullFrame = 0;
            }
            else if(!frames.contains(currentReference)) {
                frames.set(fullFrame, currentReference);
                pageFault++;
                fullFrame++;
            }
            System.out.println(frames);
        }

        System.out.println("Number of page fault: " + pageFault);
    }
}
