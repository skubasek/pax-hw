package com.sk.hw_3.xo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Challenge #3: X0 combinations.
 *
 * Given a string composed of 1s, 0s and Xs, prints out all combinations,
 * replacing X with 0 and 1.
 *
 * Solution:
 * Given a string, find all the locations of X. Then increment the binary
 * bits, ie, if we have 2 Xs, like X0X0 start with
 * 00
 * 01
 * 10
 * 11
 *
 * This is a recursive solution.
 */
public class XoPrinter {
    String input;
    char[] inputArr;
    List<Integer> locations = new ArrayList<>();
    int[] currVals;
    char[] currStr;

    public XoPrinter(String input) {
        this.input = input;
        // find locations
        inputArr = input.toCharArray();
        currStr = inputArr;
        locations = new Stack<>();
        for (int i = 0; i < inputArr.length; i++) {
            if (inputArr[i] == 'X') {
                locations.add(i);
            }
        }
    }


    public void printAllCombos() {
        currVals = new int[locations.size()];
        Arrays.fill(currVals, 0);
        print(currVals);

        // set curr idx to end
        int currIdx = currVals.length - 1; // start from end

        incrementAt(currIdx, currVals);
    }

    private void incrementAt(int idx, int[] currVals) {
        // if value at current idx is 0
        if (currVals[idx] == 0) {
            // increment to 1
            currVals[idx] = 1;

            // reset all values & start over
            int end = currVals.length - 1;
            if (idx != end) {
                resetAllFrom(currVals, idx + 1);
                print(currVals);
                idx = end; // start over at end
            } else {
                print(currVals);
            }
            incrementAt(idx, currVals);
        } else {
            // already at 1, increment higher bit
            if (idx > 0) {
                incrementAt(idx - 1, currVals);
            }
        }
    }

    private void resetAllFrom(int[] arr, int startIdx) {
        for (int i = startIdx; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    private void print(int[] arr) {
        for (int i = 0; i < locations.size(); i++) {
            int loc = locations.get(i);
            char val = Character.forDigit(arr[i], 10);
            currStr[loc] = val;
        }
        System.out.println(currStr);
    }

    public static void main(String[] args) {
        new XoPrinter(args[0]).printAllCombos();
    }
}
