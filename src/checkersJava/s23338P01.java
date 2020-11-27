package checkersJava;

public class s23338P01 {
	public static void main(String[] args) {
		long white1 = 0b001000111_001010111_001100111_001110111_001111110_001101110L;
		long white2 = 0b001011110_001001110_001000101_001010101_001100101_001110101L;
		
		long black1 = 0b000001000_000011000_000101000_000111000_000110001_000100001L;
		long black2 = 0b000000001_000010001_000001010_000011010_000101010_000111010L;
		
		drawBoard(white1, white2, black1, black2);
	}
	
	
	//Funkcja rysuj¹ca Plansze
	public static void drawBoard(long white1, long white2,long black1,long black2) {
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {					
				if (drawFigure(white1, i, j)) {
					System.out.print("\u2659 ");
				}
				else if (drawFigure(white2, i, j)) {
					System.out.print("\u2659 ");
				} 
				else if(drawFigure(black1, i, j)) {
					System.out.print("\u265F ");
				}
				else if(drawFigure(black2, i, j)) {
					System.out.print("\u265F ");
				}
				else if((i+j)% 2 == 0) {
					System.out.print("\u2B1C ");
				}
				else {
					System.out.print("\u2B1B ");
				}
			}
			System.out.println();
		}
	}
	
	//Funkcja sprawdza czy w pkt x,y jest do narysowania figura z jednej zmienej long
	public static boolean drawFigure(long Figures, int x, int y) {
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



//1	000
//2	001	
//3	010
//4	011
//5	100
//6	101
//7	110
//8	111