package com.leoj;

import javax.print.attribute.IntegerSyntax;
import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    //main
    public static void main(String[] args) {


        //Gameboard character array
        char[][] gameBoardArray = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        //Display Empty GameBoard
        printGameBoard(gameBoardArray);

        //waiting for user
        while (true) {

            //player turn
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement 1-9: ");
            int pos = scan.nextInt();
            while(playerPositions.contains(pos) || cpuPositions.contains(pos)) {
                System.out.println("Position taken! Enter a correct position");
                pos = scan.nextInt();
            }
            placePiece(gameBoardArray, pos, "player");

            String result = checkWinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }

            printGameBoard(gameBoardArray);


            //cpu turn
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoardArray, cpuPos, "cpu");

            result = checkWinner();
            if(result.length()>0) {
                System.out.println(result);
                break;
            }

            printGameBoard(gameBoardArray);



        }
    }

    //printGameBoard Method
    public static void printGameBoard(char[][] gameBoardArray) {
        for (char[] row : gameBoardArray) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    //placePiece Method
    public static void placePiece(char[][] gameBoardArray, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoardArray[0][0] = symbol;
                break;
            case 2:
                gameBoardArray[0][2] = symbol;
                break;
            case 3:
                gameBoardArray[0][4] = symbol;
                break;
            case 4:
                gameBoardArray[2][0] = symbol;
                break;
            case 5:
                gameBoardArray[2][2] = symbol;
                break;
            case 6:
                gameBoardArray[2][4] = symbol;
                break;
            case 7:
                gameBoardArray[4][0] = symbol;
                break;
            case 8:
                gameBoardArray[4][2] = symbol;
                break;
            case 9:
                gameBoardArray[4][4] = symbol;
                break;
        }
    }

    //check winner method
    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List lefCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rhtCol = Arrays.asList(3, 6, 9);
        List lefDia = Arrays.asList(1, 5, 9);
        List rhtDia = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(lefCol);
        winning.add(midCol);
        winning.add(rhtCol);
        winning.add(lefDia);
        winning.add(rhtDia);


        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "Oh no! The CPU won!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a draw!";
            }
        }
     return "";
    }
}






