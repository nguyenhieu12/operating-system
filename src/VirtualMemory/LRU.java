package VirtualMemory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class LRU {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of frames: ");
        int framesNumber = scanner.nextInt();
        System.out.print("Number of references: ");
        int referencesNumber = scanner.nextInt();
        ArrayList<Integer> mainFrames = new ArrayList<>();
        ArrayList<Integer> subFrames = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int pageFault = 0, currentReference;
        System.out.print("Enter reference string: ");
        for(int i = 0;i < framesNumber;i++) {
            currentReference = scanner.nextInt();
            if(!mainFrames.contains(currentReference)) {
                mainFrames.add(currentReference);
                subFrames.add(currentReference);
                pageFault++;
                System.out.println(mainFrames);
            }
        }

        for(int i = framesNumber;i < referencesNumber;i++) {
            currentReference = scanner.nextInt();
            subFrames.add(currentReference);
            if(mainFrames.contains(currentReference)) {
                continue;
            }
            else {
                for (int j = i - 1; j >= -1; j--) {
                    if(stack.size() == framesNumber) {
                        int curPos = mainFrames.indexOf(subFrames.get(j+1));
                        mainFrames.set(curPos, currentReference);
                        pageFault++;
                        stack.clear();
                        break;
                    }
                    else if(mainFrames.contains(subFrames.get(j)) && (stack.isEmpty()
                            || !stack.contains(subFrames.get(j)))) {
                        stack.add(subFrames.get(j));
                    }
                }
            }
            System.out.println(mainFrames);
        }

        System.out.println("Number of page fault: " + pageFault);
    }
}