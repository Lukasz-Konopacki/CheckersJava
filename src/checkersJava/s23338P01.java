package checkersJava;

import java.io.Console;

public class s23338P01 {
	public static void main(String[] args) {
		long black1 = 0b000111000_000111010_000111100_000111110_000110111_000110101L;
		long black2 = 0b000110011_000110001_000101000_000101010_000101100_000101110L;
		
		long white1 = 0b001000001_001000011_001000101_001000111_001001110_001001100L;
		long white2 = 0b001001000_001001010_001010001_001010011_001010101_001010111L;
		
		
		drawBoard(white1, white2, black1, black2);
		System.out.println(checkFigure(white2, 5, 2));
		movePiece(true, 5, 2, 4, 3, white1, white2, black1, black2);
		
		System.out.println(checkFigure(black2, 2, 5));
		movePiece(false, 2, 5, 2, 4, white1, white2, black1, black2);
	}
	
	
	//Funkcja Do ruszania pionkow
	public static void movePiece(boolean IsWhitePiece, int startX, int startY,int endX,int endY,long white1, long white2,long black1,long black2) {
		//sprawdzenie czy ruszaja sie biale czy czarne
		if(IsWhitePiece) {
			System.out.println("Rusza sie bialy pionek");
			//sprawdzenie czy pionek znajduje sie w startX i startY
			if (checkFigure(white1, startX, startY) ||checkFigure(white2, startX, startY) ) {
				System.out.println("z pola (x:" + startX + ", y:" + startY + ")");
				//sprawdzenie czy pole endX i endY graniczy z startX i startY
				if ((startY+1 == endY) & ((startX+1 == endX) || (startX-1 == endX))) {
					System.out.println("Na pole: (x:" + endX + ", y:" + endY + ")");
					//sprawdzenie czy pole endX i endY jest wolne
					if (!checkFigure(white1, endX, endY) & !checkFigure(white2, endX, endY) & !checkFigure(black1, endX, endY) & !checkFigure(black2, endX, endY)) {
						System.out.println("Pole koncowe jest wolne, WYKONYWANIE RUCHU");
					}
				}
			}
		}
		else {
			//Porusza si� czarny
			System.out.println("Rusza sie czarny pionek");
			//sprawdzenie czy pionek znajduje sie w startX i startY
			if (checkFigure(black1, startX, startY) ||checkFigure(black2, startX, startY) ) {
				System.out.println("z pola (x:" + startX + ", y:" + startY + ")");
				//sprawdzenie czy pole endX i endY graniczy z startX i startY
				if ((startY-1 == endY) & ((startX+1 == endX) || (startX-1 == endX))) {
					System.out.println("Na pole: (x:" + endX + ", y:" + endY + ")");
					//sprawdzenie czy pole endX i endY jest wolne
					if (!checkFigure(white1, endX, endY) & !checkFigure(white2, endX, endY) & !checkFigure(black1, endX, endY) & !checkFigure(black2, endX, endY)) {
						System.out.println("Pole koncowe jest wolne, WYKONYWANIE RUCHU");
					}
				}
			}
		}
	}	
	//Funkcja rysuj�ca Plansze
	public static void drawBoard(long white1, long white2,long black1,long black2) {
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {					
				if (checkFigure(white1, j, i)) {
					System.out.print("\u2659 ");
				}
				else if (checkFigure(white2, j, i)) {
					System.out.print("\u2659 ");
				} 
				else if(checkFigure(black1, j, i)) {
					System.out.print("\u265F ");
				}
				else if(checkFigure(black2, j, i)) {
					System.out.print("\u265F ");
				}
				else if((i+j)% 2 == 0) {
					System.out.print("\u2B1C ");
				}
				else {
					System.out.print("\u2B1B ");
				}
			}
			System.out.print(i);
			if (i == 4) {
				System.out.print(" Y");
			}
			System.out.println();
			
		}
		System.out.println("0 1 2 3 4 5 6 7 ");
		System.out.println("       X        ");
	}	
	//Funkcja sprawdza czy w pkt x,y jest do narysowania figura z jednej zmienej long
	public static boolean checkFigure(long Figures, int x, int y) {
		long FigureCopy = Figures;
		
		for (int i = 0; i <= 5; i++) {
			if (((FigureCopy & 0b111L) == x) & (((FigureCopy & 0b111000L)>>3) == y)) {	
				return true;
			}
			FigureCopy = FigureCopy >> 9;
		}
		return false;
	}
}