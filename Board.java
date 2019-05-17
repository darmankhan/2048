
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;



public class Board extends JFrame implements KeyListener{
	 int max = 0;
	 int moves = 0;
	 Random random = new Random();
	 int num1;
	 int num2;
	 int[][] array = new int[4][4];
	 boolean quit_check = false;
	 boolean restart_check = false;
	 boolean move_valid= false;
	 boolean gameend = false;
	
	public Board() {

		addKeyListener(this);
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 68){ //d
			System.out.println("Key pressed: D");
			System.out.println();
			move(1);
		}
		else if(e.getKeyCode() == 65) { //a
			System.out.println("Key pressed: A");
			System.out.println();
			move(2);
		}
		
		else if(e.getKeyCode() == 83) { //s
			System.out.println("Key pressed: S");
			System.out.println();
			move(3);
		}
		
		else if(e.getKeyCode() == 87){ //w
			System.out.println("Key pressed: W");
			System.out.println();
			move(4);
		}	 
		else if(e.getKeyCode() == 81) { //q
			System.out.println("Key pressed: Q");
			System.out.println();
			quit_check = true;
			System.out.println("Are you sure you want to quit?");
			System.out.println("Press L to quit. Press SPACE to continue.");
			System.out.println();
			
		}
		else if(e.getKeyCode()== 76) { //L
			if(quit_check==true) { //works only if q was pressed previously
				System.out.println("Key pressed: L");
				System.out.println();
			System.out.println("Game terminated.");
			System.out.println("The max is: " + max);
			System.out.println("Moves: "+ moves);
			System.out.println();
			System.exit(0);
			}
		} 
		else if(e.getKeyCode()==32){ //space
			if(quit_check==true || restart_check==true) { //works only if r or q was pressed previously
				System.out.println("Key pressed: SPACE");
				System.out.println();
			System.out.println("Continue game.");
			System.out.println();
			printarray(array);
			quit_check = false;
			restart_check = false;
			}
		}
		else if(e.getKeyCode() == 82) { //r
			restart_check = true;
			System.out.println("Key pressed: R");
			System.out.println();
			System.out.println("Are you sure you want to restart(Y/N)?");
			System.out.println("Press Y to restart. Press SPACE to continue.");
			System.out.println();
		}
		else if (e.getKeyCode()==89) { //Y
			if (restart_check == true) { //works only if r was pressed previously 
				System.out.println("Key pressed: Y");
				System.out.println();
				restart();
			restart_check = false;
			}
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	//generates random number with the described probabilities 
	public int numGenerator() {
		int num;
		if ((random.nextInt(5)) + 1 == 3) { //0.2 probability 
			num = 4;
		} else {
			num = 2;
		}
		return num;
	}

	//generates random position
	public int randomPos() {
		int pos = random.nextInt(4);
		return pos;
	}

	//checks if array has an empty tile
	public boolean checkEmptyPos() {
		boolean pos = false;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 0) {
					pos = true;
				}
			}
		}
		return pos;
	}
	
	//ends game
	public void gameOver() {
		System.out.println("Game Over!");
		System.out.println();
		System.exit(0);
	}

	//adds random number to array
	public void addNumber() {
		if (checkEmptyPos() && move_valid==true) {
			int newNum = numGenerator();
			int pos1 = randomPos();
			int pos2 = randomPos();
			while (array[pos1][pos2] != 0) {
				pos1 = randomPos();
				pos2 = randomPos();
			}
			array[pos1][pos2] = newNum;
		} else {
			gameOver();
		}
	}

	//generates a new board
	public void newBoard() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = 0;
			}
		}
		int pos1 = randomPos();
		int pos2 = randomPos();	
		int pos3 = randomPos();
		int pos4 = randomPos();
		while(pos1 == pos3 && pos2 == pos4) {
		 pos3 = randomPos();
		 pos4 = randomPos();
		}
		num1 = numGenerator();
		num2 = numGenerator();
		array[pos1][pos2] = num1;
		array[pos3][pos4] = num1;

	}
	
	//starts game 
	public void start() {
		System.out.println("WELCOME TO 2048!");
		System.out.println("Press W to move up, S to move down, D to move right, A to move left.");
		System.out.println("Press R to Restart. Press Q to Quit.");
		System.out.println();
		newBoard();
		max = 0;
		moves = 0;
		System.out.println("The max is: " + max);
		System.out.println("Moves: "+ moves);
		printarray(array);
		
	}
	
	//intializes moves and max to 0 and restarts 
	public void restart() {
		newBoard();
		max = 0;
		moves = 0;
		System.out.println("Game restarted.");
		System.out.println();
		System.out.println("The max is: " + max);
		System.out.println("Moves: "+ moves);
		printarray(array);
	}

	//prints the 2D array
	public void printarray(int[][] array) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < (array[i]).length; j++) {
				if (((array[i])[j] == 0)) {
					System.out.print("*" + "    ");
			    } else if ((array[i])[j] > 0 && array[i][j] <= 9) {
					System.out.print((array[i])[j] + "    ");
				} else if((array[i])[j] <= 99){
					System.out.print((array[i])[j] + "   ");
				} else if((array[i])[j] <= 999) {
					System.out.print((array[i])[j] + "  ");
				} else if(((array[i])[j] <= 9999)) {
					System.out.print((array[i])[j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	//if max becomes 2048 win function restarts game
	public void win() {
		System.out.println();
		System.out.println("YOU WIN!");
		restart();
	}

	//moves tiles depending on direction input (1,2,3,4)
	public void move(int direction) {
		move_valid = false;
		gameend = true;
		if (direction == 1) { // right
			//4 loops are used to move numbers to rightmost available place
			//moves numbers to next right column
			for (int i = 0; i < 4; i++) {
				for (int j = 3; j > 0; j--) {
					if (array[i][j] == 0 && array[i][j - 1] != 0) {
						array[i][j] = array[i][j - 1];
						array[i][j - 1] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}
			
			//adds if numbers are same
			for (int i = 0; i < 4; i++) {
				for (int j = 3; j > 0; j--) {
					if (array[i][j] == 0 && array[i][j - 1] != 0) {
						array[i][j] = array[i][j - 1];
						array[i][j - 1] = 0;
						gameend=false;
					}
				}
			}
			
			//moves numbers to next right column
			for (int i = 0; i < 4; i++) {
				for (int j = 3; j > 0; j--) {
					if (array[i][j] == array[i][j - 1] && array[i][j - 1] != 0) {
						array[i][j] *= 2;
						array[i][j - 1] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}
			
			//moves numbers to next right column
			for (int i = 0; i < 4; i++) {
				for (int j = 3; j > 0; j--) {
					if (array[i][j] == 0 && array[i][j - 1] != 0) {
						array[i][j] = array[i][j - 1];
						array[i][j - 1] = 0;
						gameend=false;
					}
				}
			}
			
			
		}

		else if (direction == 2) { // left
			//4 loops are used to move numbers to leftmost available place
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if (array[i][j] == 0 && array[i][j + 1] != 0) {
						array[i][j] = array[i][j + 1];
						array[i][j + 1] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if (array[i][j] == 0 && array[i][j + 1] != 0) {
						array[i][j] = array[i][j + 1];
						array[i][j + 1] = 0;
						gameend=false;
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if (array[i][j] == array[i][j + 1] && array[i][j + 1] != 0) {
						array[i][j] *= 2;
						array[i][j + 1] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					if (array[i][j] == 0 && array[i][j + 1] != 0) {
						array[i][j] = array[i][j + 1];
						array[i][j + 1] = 0;
						gameend=false;
					}
				}
			}

		} else if (direction == 3) { // down
			//4 loops are used to move numbers to bottommost available place
			for (int i = 3; i > 0; i--) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == 0 && array[i - 1][j] != 0) {
						array[i][j] = array[i - 1][j];
						array[i - 1][j] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}

			for (int i = 3; i > 0; i--) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == 0 && array[i - 1][j] != 0) {
						array[i][j] = array[i - 1][j];
						array[i - 1][j] = 0;
						gameend=false;
					}
				}
			}

			for (int i = 3; i > 0; i--) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == array[i - 1][j] && array[i - 1][j] != 0) {
						array[i][j] *= 2;
						array[i - 1][j] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}

			for (int i = 3; i > 0; i--) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == 0 && array[i - 1][j] != 0) {
						array[i][j] = array[i - 1][j];
						array[i - 1][j] = 0;
						gameend=false;
					}
				}
			}
		}

		else if (direction == 4) { // up
			//4 loops are used to move numbers to topmost available place
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == 0 && array[i + 1][j] != 0) {
						array[i][j] = array[i + 1][j];
						array[i + 1][j] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == 0 && array[i + 1][j] != 0) {
						array[i][j] = array[i + 1][j];
						array[i + 1][j] = 0;
						gameend=false;
					}
				}
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == array[i + 1][j] && array[i + 1][j] != 0) {
						array[i][j] *= 2;
						array[i + 1][j] = 0;
						move_valid = true;
						gameend=false;
					}
				}
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 4; j++) {
					if (array[i][j] == 0 && array[i + 1][j] != 0) {
						array[i][j] = array[i + 1][j];
						array[i + 1][j] = 0;
						gameend=false;
					}
				}
			}
			
		}
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] > max) {
					max = array[i][j];
					gameend=false;
				}
			}
		}
		

		
		//no empty tiles or valid moves left
		if (gameend==true  && checkEmptyPos()==false) {
			gameOver();
		}
		
		if(max==2048) {
			win();
		}
		if(move_valid == false) {
			System.out.println("Invalid move");
			
		} else {
			System.out.println("Valid move.");
			moves++;
			addNumber();
		}
		System.out.println("The max is: " + max);
		System.out.println("Moves: "+ moves);
		printarray(array);

	}
}
