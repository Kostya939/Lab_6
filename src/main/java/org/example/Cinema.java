package org.example;

public class Cinema {
    private static final int HALLS = 5;
    private static final int ROWS = 10;
    private static final int SEATS_PER_ROW = 20;
    private final int[][][] seats;

    public Cinema() {
        seats = new int[HALLS][ROWS][SEATS_PER_ROW];
    }

    public boolean bookSeats(int hallNumber, int row, int[] seatsToBook) {
        if (!areSeatsValid(hallNumber, row, seatsToBook)) {
            return false;
        }

        for (int seat : seatsToBook) {
            if (seats[hallNumber][row][seat] == 1) {
                System.out.println("Seat " + seat + " in row " + row + " in hall " + hallNumber + " is already booked.");
                return false;
            }
        }

        for (int seat : seatsToBook) {
            seats[hallNumber][row][seat] = 1;
        }
        return true;
    }

    public boolean cancelBooking(int hallNumber, int row, int[] seatsToCancel) {
        if (!areSeatsValid(hallNumber, row, seatsToCancel)) {
            return false;
        }

        for (int seat : seatsToCancel) {
            if (seats[hallNumber][row][seat] == 0) {
                System.out.println("Seat " + seat + " in row " + row + " in hall " + hallNumber + " is not booked.");
                return false;
            }
        }

        for (int seat : seatsToCancel) {
            seats[hallNumber][row][seat] = 0;
        }
        return true;
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int row = 0; row < ROWS; row++) {
            int consecutiveSeats = 0;
            for (int seat = 0; seat < SEATS_PER_ROW; seat++) {
                if (seats[hallNumber][row][seat] == 0) {
                    consecutiveSeats++;
                    if (consecutiveSeats == numSeats) {
                        return true;
                    }
                } else {
                    consecutiveSeats = 0;
                }
            }
        }
        return false;
    }

    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Seating Arrangement for Hall " + hallNumber + ":");
        for (int row = 0; row < ROWS; row++) {
            for (int seat = 0; seat < SEATS_PER_ROW; seat++) {
                System.out.print(seats[hallNumber][row][seat] + " ");
            }
            System.out.println();
        }
    }

    public int[] findBestAvailable(int hallNumber, int numSeats) {
        for (int row = 0; row < ROWS; row++) {
            int startSeat = -1;
            int consecutiveSeats = 0;
            for (int seat = 0; seat < SEATS_PER_ROW; seat++) {
                if (seats[hallNumber][row][seat] == 0) {
                    if (startSeat == -1) {
                        startSeat = seat;
                    }
                    consecutiveSeats++;
                    if (consecutiveSeats == numSeats) {
                        int[] availableSeats = new int[numSeats];
                        for (int i = 0; i < numSeats; i++) {
                            availableSeats[i] = startSeat + i;
                        }
                        return availableSeats;
                    }
                } else {
                    startSeat = -1;
                    consecutiveSeats = 0;
                }
            }
        }
        return new int[0];
    }

    public boolean autoBook(int hallNumber, int numSeats) {
        int[] bestSeats = findBestAvailable(hallNumber, numSeats);
        if (bestSeats.length > 0) {
            return bookSeats(hallNumber, 0, bestSeats);
        }
        return false;
    }

    private boolean areSeatsValid(int hallNumber, int row, int[] seatsToCheck) {
        if (hallNumber < 0 || hallNumber >= HALLS || row < 0 || row >= ROWS) {
            return false;
        }
        for (int seat : seatsToCheck) {
            if (seat < 0 || seat >= SEATS_PER_ROW) {
                return false;
            }
        }
        return true;
    }
}
