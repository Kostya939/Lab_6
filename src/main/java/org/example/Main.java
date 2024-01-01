package org.example;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.bookSeats(0, 0, new int[]{1, 2, 3});
        cinema.printSeatingArrangement(0);
    }
}
