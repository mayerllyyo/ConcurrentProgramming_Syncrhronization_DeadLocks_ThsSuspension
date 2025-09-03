/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.lab_1.threads;

/**
 * CountThread class that extends Thread to create a thread that prints numbers
 * within a specified range from num1 to num2.
 */
public class CountThread extends Thread {
    private int num1, num2;

    /**
     * Constructor that initializes the counting range
     * 
     * @param num1 the starting number of the range (inclusive)
     * @param num2 the ending number of the range (inclusive)
     */
    public CountThread(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    /**
     * Utility method to print the range boundaries
     * This method displays the initial and final values of the counting range
     * 
     * @param num1 the starting number
     * @param num2 the ending number
     */
    public void printNumbers(int num1, int num2) {
        System.out.printf("num1 = %d, num2 = %d%n", num1, num2);
    }

    /**
     * Runs the thread: prints numbers from num1 to num2 with thread name
     */
    @Override
    public void run() {
        for (int i = num1; i <= num2; i++) {
            System.out.printf("Hilo %s: %d%n", Thread.currentThread().getName(), i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
        }
    }
}