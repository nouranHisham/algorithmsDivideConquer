package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int rand_partition(int[] arr, int left, int right)
    {
        Random r = new Random();
        int temp;
        int i = r.nextInt(right - left) + left;
        temp = arr[right];
        arr[right] = arr[i];
        arr[i] = temp;
        return partition(arr, left, right);
    }

    static int partition(int[] arr, int left, int right)
    {
        int x = arr[right];
        int i = left-1;
        for(int j = left; j<right; j++)
        {
            if(arr[j]<=x){
                i=i+1;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, right);
        return i+1;
    }

    static void swap(int[] arr, int x, int y)
    {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    static int rand_select(int[] arr, int left, int right, int i)
    {
        if (left == right)
            return arr[left];
        int q = rand_partition(arr, left, right);
        int k = q - left + 1;
        if (i == k)
            return arr[q];
        else if(i < k)
            return rand_select(arr, left, q-1, i);
        else
            return rand_select(arr, q+1, right, i-k);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int array_upperbound = 201;
        int[] intArray;
        int resultDivideConquer = 0;
        int resultNaive = 0;
        long startDivideConquer, endDivideConquer;
        long timeDivideConquer = 0;
        long averageDivideConquer;
        long startNaive, endNaive;
        long timeNaive = 0;
        long averageNaive;

        System.out.println("Finding the kth smallest element");
        System.out.println("Choose how you want the size of your array\r\n1) Randomly generated\r\n2) Specific size of your choice");
        Scanner ch = new Scanner(System.in);
        System.out.println("Please enter your choice");
        int choice = ch.nextInt();

        if (choice == 1) {
            int size_upperbound = 1000001;
            int random_array_size = rand.nextInt(size_upperbound);
            intArray = new int[random_array_size];
            System.out.println("Size of array is "+random_array_size);

            for(int i = 0; i<random_array_size; i++)
            {
                intArray[i] = rand.nextInt(array_upperbound);
            }
        }
        else {
            Scanner ss = new Scanner(System.in);
            System.out.println("Please enter the size of your array");
            int user_array_size = ss.nextInt();
            intArray = new int[user_array_size];

            for(int i = 0; i<user_array_size; i++)
            {
                intArray[i] = rand.nextInt(array_upperbound);
            }
        }

        int[] arrayDuplicate = Arrays.copyOf(intArray, intArray.length);

        //System.out.println(Arrays.toString(intArray));

        Scanner index = new Scanner(System.in);
        System.out.println("Please enter the index you want the smallest element at");
        int indexValue = index.nextInt();
        
        for(int m=0; m<10; m++) {
            startDivideConquer = System.currentTimeMillis();
            resultDivideConquer = rand_select(intArray, 0, intArray.length - 1, indexValue);
            endDivideConquer = System.currentTimeMillis();
            timeDivideConquer += endDivideConquer - startDivideConquer;
            intArray = Arrays.copyOf(arrayDuplicate, arrayDuplicate.length);
        }
        
        System.out.println("The result using randomized divide and conquer algorithm");
        System.out.println("The smallest " + indexValue + " element in the array is " + resultDivideConquer);
        averageDivideConquer = timeDivideConquer/10;
        System.out.println("Average time taken by randomized divide and conquer algorithm is "+averageDivideConquer+" milliseconds");

        System.out.println("---------------------------------------------------------------------------------");

        for(int n=0; n<10; n++) {
            startNaive = System.currentTimeMillis();
            Arrays.sort(intArray);
            resultNaive = intArray[indexValue - 1];
            endNaive = System.currentTimeMillis();
            timeNaive += endNaive - startNaive;
            intArray = Arrays.copyOf(arrayDuplicate, arrayDuplicate.length);
        }

        System.out.println("The result using the naive method");
        System.out.println("The smallest " + indexValue + " element in the array is " + resultNaive);
        averageNaive = timeNaive/10;
        System.out.println("Average time taken by naive method is "+averageNaive+" milliseconds");
    }
}
