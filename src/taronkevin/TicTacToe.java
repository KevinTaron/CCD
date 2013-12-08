package taronkevin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TicTacToe {
	private String[][] spielfeld;
	private int ZEILEN = 3;
	private int SPALTEN = 3;
	private String LEERESFELD = " ";
	private String Spieler1 = "O";
	private String Spieler2 = "X";
	private String TRENNZEICHEN = " |";
	private String NEUEZEILE = "\n";
	private String UEBERSCHRIFT = "  " + TRENNZEICHEN +" A" + TRENNZEICHEN + " B" 
									+ TRENNZEICHEN + " C" + TRENNZEICHEN + NEUEZEILE;
	private String EINGABEAUFORDERUNG = "Bitte Feld eingeben (z.B. C0, A2, B1):";
	private String EINGABEMOEGLICHKEITEN = "[ABCabc][012]";
	private String GEWINNMELDUNG = "Gewonnen. Herzlichen Glückwunsch Spieler ";
	private String DRAWMELDUNG = "Unendschieden.";
	private String FEHLER = "Die Eingabe war nicht korrekt. Bitte versuchen Sie es erneut. z.b. A1";
	private String FELDBELEGT = "Das Feld ist schon belegt. Bitte nehmen Sie ein anderes Feld.";
	private int aktuellerSpieler = 0;
	private String letzterSpieler = Spieler1;
	
	public TicTacToe() {
		spielfeld = new String[ZEILEN][SPALTEN];
		for(int i = 0; i < ZEILEN; i++) {
			for(int k = 0; k < SPALTEN; k++) {
				spielfeld[i][k] = LEERESFELD;
			}
		}
	}
	
	private boolean setFeld(int zeile, int spalte, int spieler) {
		if (getSpielfeld()[zeile][spalte] == LEERESFELD) {
			if (spieler == 1) {
				getSpielfeld()[zeile][spalte] = Spieler1;
				aktuellerSpieler = 0;
				letzterSpieler = Spieler1;
				return true;
			} else {
				getSpielfeld()[zeile][spalte] = Spieler2;
				aktuellerSpieler = 1;
				letzterSpieler = Spieler2;
				return true;
			}
		}
		return false;
	}
	
	private String[][] getSpielfeld() {
		return spielfeld;
	}
	
	private String printSpielfeld() {
		String line = UEBERSCHRIFT;
		for(int i = 0; i < ZEILEN; i++) {
			line += LEERESFELD + i + TRENNZEICHEN;
			for(int k = 0; k < SPALTEN; k++) {
				line += " " + getSpielfeld()[i][k] + TRENNZEICHEN;
			}
			line += NEUEZEILE;
		}
		return line;
	}
	
	private boolean checkGameOver() {
		if(checkRows()) return true;
		if(checkColumns()) return true;
		if(checkDiagonale()) return true;
		return false;
	}
	
	private boolean checkRows() {
		for(int i = 0; i < ZEILEN; i++) {
			if(getSpielfeld()[i][0] == getSpielfeld()[i][1] && 
			getSpielfeld()[i][0] == getSpielfeld()[i][2] &&
			getSpielfeld()[i][0] != LEERESFELD) return true;
		}
		return false;
	}
	
	private boolean checkColumns() {
		for(int i = 0; i < SPALTEN; i++) {
			if(getSpielfeld()[0][i] == getSpielfeld()[1][i] && 
			getSpielfeld()[0][i] == getSpielfeld()[2][i] &&
			getSpielfeld()[0][i] != LEERESFELD) return true;
		}
		return false;
	}
	
	private boolean checkDiagonale() {
		if(getSpielfeld()[1][1] == getSpielfeld()[0][0] && 
			getSpielfeld()[1][1] == getSpielfeld()[2][2] &&
			getSpielfeld()[1][1] != LEERESFELD) return true;
		if(getSpielfeld()[1][1] == getSpielfeld()[0][2] && 
				getSpielfeld()[1][1] == getSpielfeld()[2][0] &&
				getSpielfeld()[1][1] != LEERESFELD) return true;
		return false;
	}
	
	private boolean hasFreeFields() {
		for(int i = 0; i < ZEILEN; i++) {
			for(int k = 0; k < SPALTEN; k++) {
				if(getSpielfeld()[i][k] == LEERESFELD) return true;
			}
		}
		return false;
	}
	
	private void getInput() {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 	System.out.println(EINGABEAUFORDERUNG);
	        String line = null;
	        try {
	            while( (line = br.readLine())!=null){
	               if (line.matches(EINGABEMOEGLICHKEITEN)) {	            	   
	            	   int spalte = line.charAt(0);
	            	   switch (spalte) {
	            	   case 'A': 
	            		   spalte = 0;
	            		   break;
	            	   case 'a': 
	            		   spalte = 0;
	            		   break;
	            	   case 'B': 
	            		   spalte = 1;
	            		   break;
	            	   case 'b': 
	            		   spalte = 1;
	            		   break;
	            	   case 'C': 
	            		   spalte = 2;
	            		   break;
	            	   case 'c': 
	            		   spalte = 2;
	            		   break;	            		  
	            	   } 
	            	   
	            	   int zeile = Character.getNumericValue(line.charAt(1));
	            	   if(!setFeld(zeile, spalte, aktuellerSpieler)) System.out.println(FELDBELEGT);
	            	   System.out.println(printSpielfeld());
	            	   if (checkGameOver()) { System.out.println(GEWINNMELDUNG + letzterSpieler); break; };
	            	   if(hasFreeFields()) { 
	            		   System.out.println(EINGABEAUFORDERUNG);
	            	   } else {  System.out.println(DRAWMELDUNG); break; };	            	  
	               } else {
            		   System.out.println(FEHLER);
	               }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicTacToe game = new TicTacToe();
		System.out.println(game.printSpielfeld());
		game.getInput();

	}
	
	

}
