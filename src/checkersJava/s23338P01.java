package checkersJava;

public class s23338P01 {
		public static void main(String[] args) {
			long black1 = 0b000111000_000111010_000111100_000111110_000110111_000110101L;
			long black2 = 0b000110011_000110001_000101000_000101010_000101100_000101110L;
			
			long white1 = 0b001000001_001000011_001000101_001000111_001001110_001001100L;
			long white2 = 0b001001000_001001010_001010001_001010011_001010101_001010111L;
			
			
			drawBoard(white1, white2, black1, black2);
			movePiece(true, 5, 2, 4, 3, white1, white2, black1, black2);
			
			movePiece(false, 0, 5, 1, 4, white1, white2, black1, black2);
		}
		
		//Funkcja zwraca zmieną long z przesunietym pionkiem z pozycji start do pozyji end
		public static long giveNewPosition(long figures,int startX,int startY ,int endX, int endY) {
			long figuresResult = figures;
			//szukanie konkretnego pionka
			for (int i = 0; i <= 5; i++) {
				if (((figuresResult & 0b111L) == startX) & (((figuresResult & 0b111000L)>>3) == startY)) {
					 //wspianie pionkowi nowych pozycji
					 figuresResult = figuresResult >> 6 << 3;
					 figuresResult = figuresResult | endY;
					 figuresResult = figuresResult << 3;
					 figuresResult = figuresResult | endX;
					 
					 //przepisanie wartosci pionkow z pozycji mniej znaczacych
					 long mask = 0; 
					 for (int j = 0; j < i; j++) {
						mask = mask << 9;
						mask = mask + 0b111111111;
						figuresResult = figuresResult << 9;
					}
					 figures = figures & mask;
					 figuresResult = figuresResult + figures;
					 //zwrócenie longa z przesunietym pionkiem
					 return(figuresResult);
				}
				figuresResult = figuresResult >> 9;
			}
			return -1;
		}
		
		//Funkcja Do ruszania pionkow (PRZESUNAC BITy)
		public static void movePiece(boolean IsWhitePiece, int startX, int startY,int endX,int endY,long white1, long white2,long black1,long black2) {		
			//sprawdzecznie czy pionek nie wychodzi poza plansze
			if ((startX >= 0) && (endX <=7) && (startY>= 0) && (endY <=7)) {
				System.out.println("Pionek nie wychodzi poza plansze");
				//sprawdzenie czy pole endX i endY jest wolne
				if (!checkFigure(white1, endX, endY) && !checkFigure(white2, endX, endY) && !checkFigure(black1, endX, endY) && !checkFigure(black2, endX, endY)) {
						
					System.out.println("Pole koncowe jest wolne");
					//sprawdzenie czy ruszaja sie biale czy czarne
					if(IsWhitePiece) {
						System.out.println("Rusza sie bialy pionek");
						//sprawdzenie czy pole endX i endY graniczy z startX i startY
						if ((startY+1 == endY) && ((startX+1 == endX) || (startX-1 == endX))) {
							System.out.println("Na pole: (x:" + endX + ", y:" + endY + ")");
							//sprawdzenie czy pionek znajduje sie w startX i startY
							if (checkFigure(white1, startX, startY)) {
								System.out.println("z pola (x:" + startX + ", y:" + startY + ")");
								System.out.println("WYKONYWANIE pionka z white1");
								System.out.println(Long.toBinaryString(white1));
								System.out.println(Long.toBinaryString(giveNewPosition(white1, startX, startY, endX, endY)));
							}
							else if (checkFigure(white2, startX, startY)) {
								System.out.println("z pola (x:" + startX + ", y:" + startY + ")");
								System.out.println("WYKONYWANIE pionka z white2");
								System.out.println(Long.toBinaryString(white2));
								System.out.println(Long.toBinaryString(giveNewPosition(white2, startX, startY, endX, endY)));
							}
						}
					}
					else {
						//Porusza sie czarny
						System.out.println("Rusza sie czarny pionek");
						//sprawdzenie czy pole endX i endY graniczy z startX i startY
						if ((startY-1 == endY) && ((startX+1 == endX) || (startX-1 == endX))) {
							System.out.println("Na pole: (x:" + endX + ", y:" + endY + ")");
							//sprawdzenie czy pionek znajduje sie w startX i startY
							if (checkFigure(black1, startX, startY)) {
								System.out.println("z pola (x:" + startX + ", y:" + startY + ")");
								System.out.println("WYKONYWANIE pionka z black1");
								System.out.println(Long.toBinaryString(black1));
								System.out.println(Long.toBinaryString(giveNewPosition(black1, startX, startY, endX, endY)));
							}
							else if (checkFigure(black2, startX, startY)) {
								System.out.println("z pola (x:" + startX + ", y:" + startY + ")");
								System.out.println("WYKONYWANIE pionka z black2");
								System.out.println(Long.toBinaryString(black2));
								System.out.println(Long.toBinaryString(giveNewPosition(black2, startX, startY, endX, endY)));
							}
						}
					}
				}
			}	
		}	
		//Funkcja rysujaca Plansze
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
			for (int i = 0; i <= 5; i++) {
				if (((Figures & 0b111L) == x) & (((Figures & 0b111000L)>>3) == y)) {	
					return true;
				}
				Figures = Figures >> 9;
			}
			return false;
		}
}
