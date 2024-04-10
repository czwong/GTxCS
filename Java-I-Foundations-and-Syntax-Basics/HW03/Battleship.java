import java.util.Scanner;

public class Battleship {
	private static Scanner scanner = new Scanner(System.in);
	private static String ErrorMessage = "Invalid coordinates. Choose different coordinates.";

	public static void main(String[] args) {
		System.out.println("Welcome to Battleship!\n");

		char[][] player1Board = createPlayerBoard(5, 5);
		char[][] player2Board = createPlayerBoard(5, 5);
		char[][] player1TargetHistory = createPlayerBoard(5, 5);
		char[][] player2TargetHistory = createPlayerBoard(5, 5);


		promptEnterCoordinates(player1Board, "PLAYER 1");
		printBattleShip(player1Board);
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}

		promptEnterCoordinates(player2Board, "PLAYER 2");
		printBattleShip(player2Board);
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}

		startGame(player1Board, player2Board, player1TargetHistory, player2TargetHistory);
	}

	// Method to commence game
	private static void startGame(char[][] player1Board, char[][] player2Board, char[][] player1TargetHistory,
																char[][] player2TargetHistory) {
		byte player1Sunk = 0;
		byte player2Sunk = 0;
		byte currentPlayer = 1;
		char[][] opposingBoard;
		char[][] opposingHistoryBoard;

		do {
			try {
				opposingBoard = currentPlayer == 1 ? player2Board : player1Board;
				opposingHistoryBoard = currentPlayer == 1 ? player2TargetHistory : player1TargetHistory;
				System.out.println(String.format("Player %s, enter hit row/column:", currentPlayer));

				int row = scanner.nextInt();
				int col = scanner.nextInt();

				if (opposingHistoryBoard[row][col] == 'O' || opposingHistoryBoard[row][col] == 'X') {
					System.out.println("You already fired on this spot. Choose different coordinates.");
					continue;
				}

				if (opposingBoard[row][col] == '@') {
					opposingBoard[row][col] = 'X';
					opposingHistoryBoard[row][col] = 'X';
					System.out.println(String.format("PLAYER %s HIT PLAYER %s's SHIP!", currentPlayer, currentPlayer == 1 ? 2 : 1));

					if (currentPlayer == 1) player2Sunk++;
					else player1Sunk++;
				} else {
					opposingBoard[row][col] = 'O';
					opposingHistoryBoard[row][col] = 'O';
					System.out.println(String.format("PLAYER %s MISSED!", currentPlayer));
				}

				printBattleShip(opposingHistoryBoard);
				if (player1Sunk == 5 || player2Sunk == 5) break;
				System.out.print("\n");

				currentPlayer = (byte) (currentPlayer == 1 ? 2 : 1);
			} catch(Exception e) {
				System.out.println(ErrorMessage);
			} finally {
				scanner.nextLine();
			}
		} while (player1Sunk < 5 || player2Sunk < 5);

		System.out.println(String.format("PLAYER %s WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n", currentPlayer));
		System.out.println("Final boards:\n");
		printBattleShip(player1Board);
		System.out.print("\n");
		printBattleShip(player2Board);
	}

	// Method to prompt each user to enter coordinates
	private static void promptEnterCoordinates(char[][] player, String currentPlayer) {
		System.out.println(String.format("%s, ENTER YOUR SHIPS' COORDINATES.", currentPlayer));

		byte playerShipCount = 1;

		do {
			try {
				System.out.println(String.format("Enter ship %s location:", playerShipCount));

				int row = scanner.nextInt();
				int col = scanner.nextInt();

				if (player[row][col] == '@') {
					System.out.println("You already have a ship there. Choose different coordinates.");
					continue;
				}

				player[row][col] = '@';
				playerShipCount++;
			} catch (Exception e) {
				System.out.println(ErrorMessage);
			} finally {
				scanner.nextLine();
			}
		} while (playerShipCount <= 5);
	}

	// Method to create board
	private static char[][] createPlayerBoard(int rows, int columns) {
		char[][] array = new char[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
					array[i][j] = '-';
			}
		}

		return array;
	}

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}