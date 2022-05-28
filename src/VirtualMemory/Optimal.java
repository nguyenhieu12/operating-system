package VirtualMemory;

import java.util.*;

public class Optimal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of frames: ");
        int framesNumber = scanner.nextInt();
        System.out.print("Number of references: ");
        int referencesNumber = scanner.nextInt();
        ArrayList<Integer> referenceList = new ArrayList<>();
        ArrayList<Integer> frames = new ArrayList<>();
        Set<Integer> referenceSet = new HashSet<>();

        System.out.print("Enter reference string: ");
        for(int i = 0;i < referencesNumber;i++)
            referenceList.add(scanner.nextInt());
        Collections.reverse(referenceList);
        int pageFault = 0, pos, curReference;
        for(int i = 0;i < framesNumber && !referenceList.isEmpty();i++) {
            pos = referenceList.size() - 1;
            curReference = referenceList.get(pos);
            if(!frames.contains(curReference)) {
                frames.add(curReference);
                referenceList.remove(pos);
                pageFault++;
            }
            System.out.println(frames);
        }
        while(!referenceList.isEmpty()) {
            boolean check = false;
            pos = referenceList.size() - 1;
            curReference = referenceList.get(pos);
            if(frames.contains(curReference)) {
                referenceList.remove(pos);
                System.out.println(frames);
                continue;
            }
            for(int i = 0;i < framesNumber;i++) {
                if(!referenceList.contains(frames.get(i))) {
                    frames.set(i, curReference);
                    referenceList.remove(pos);
                    pageFault++;
                    System.out.println(frames);
                    check = true;
                    break;
                }
            }
            if(check) {
                continue;
            }
            for(int i = pos;i >= 0;i--) {
                int curValue = referenceList.get(i);
                if(frames.contains(curValue)) {
                    referenceSet.add(curValue);
                    if(referenceSet.size() == framesNumber) {
                        int curPos = frames.indexOf(curValue);
                        frames.set(curPos, curReference);
                        referenceList.remove(pos);
                        referenceSet.clear();
                        pageFault++;
                        System.out.println(frames);
                        break;
                    }
                }
            }
        }

        System.out.println("Number of page fault: " + pageFault);
    }
}
