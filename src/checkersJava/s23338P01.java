package checkersJava;

import java.util.Scanner;

public class s23338P01 {
		public static void main(String[] args) {
			long white1 = 0b001000001_001000011_001000101_001000111_001001110_001001100L;
			long white2 = 0b001001000_001001010_001010001_001010011_001010101_001010111L;
			long black1 = 0b000111000_000111010_000111100_000111110_000110111_000110101L;
			long black2 = 0b000110011_000110001_000101000_000101010_000101100_000101110L;
			
			boolean GameOver = false;
			boolean IsWhiteMove = true;
			Scanner scan = new Scanner(System.in);
			
			
			black1 = giveNewPosition(black1, 0, 7, 2,3);
			black2 = giveNewPosition(black2, 0, 5, 1,4);
			checkCapture(white2, white1, black1, black2);
			
			
			while(!GameOver) {
				drawBoard(white1,white2,black1,black2);
				if(IsWhiteMove) {
					System.out.println("Ruch graj¹cego bia³ymi");

				}
				else {
					System.out.println("Ruch graj¹cego czarnymi");
				}
				System.out.println("Podaj pozycje X figury do przesuniecia: ");
				int Xstart = scan.nextInt();	
				System.out.println("Podaj pozycje Y figury do przesuniecia: ");
				int Ystart = scan.nextInt();		
				System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
				int Xend = scan.nextInt();
				System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
				int Yend = scan.nextInt();
				//Sprawdzenie czy pole Xend, Y end jest wolne
				if (!checkFigurePos(white1, Xend, Yend) && !checkFigurePos(white2, Xend, Yend) && !checkFigurePos(black1, Xend, Yend) && !checkFigurePos(black2, Xend, Yend)){
					//sprawdzecznie czy pionek nie wychodzi poza plansze
					if ((Xend >= 0) && (Xend <=7) && (Yend>= 0) && (Yend <=7)) {
						//Sprawdzenie czy znajduje siê pionek na pozycji Xstart Y start
						if(checkFigurePos(white1, Xstart, Ystart) || checkFigurePos(white2, Xstart, Ystart)  || checkFigurePos(black1, Xstart, Ystart)  || checkFigurePos(black2, Xstart, Ystart)) {
								//Sprawdzenie czy figura jest dama czy pionkiem
								if(checkTypeOfFigure(white1, Xstart, Ystart) || checkTypeOfFigure(white1, Xstart, Ystart) || checkTypeOfFigure(black1, Xstart, Ystart) || checkTypeOfFigure(black2, Xstart, Ystart)) {
									System.out.println("Figura jest Dama");
								}
								else {
									System.out.println("Figura jest Pionkiem");
									//Sprawdzamy czy gracz porusza pionkiem dobrego koloru
									if((checkFigurePos(white1, Xstart, Ystart) || checkFigurePos(white2, Xstart, Ystart)) && IsWhiteMove) {
										System.out.println("Bialy rusza bialy");
									}
									else if((checkFigurePos(black1, Xstart, Ystart) || checkFigurePos(black2, Xstart, Ystart)) && !IsWhiteMove) {
										System.out.println("czarny rusza czarny");
									}
									else {
										System.out.println("Probujesz poruszyc nie swoj pionek");
									}
								}
							
						}
						else {
							System.out.println("Brak Pionka na pozycji (X:"+ Xstart + ", Y:" + Ystart + ")");
						}
					}
					else {
						System.out.println("Nie mozna wyjsc pionkiem poza plansze");
					}
				}
				else {
					System.out.println("Na polu (X:"+ Xend + ", Y:" + Yend + ") znajduje sie inna figura");
				}
			}	
			scan.close();	
		}
		
		//Funkcja Spawdzajaca czy jest dostepne bicie
		public static void checkCapture(long FiguresToCheck, long SameColorFigures, long secondColor1, long  secondColor2) {
				for (int i = 0; i <= 5; i++) {
					//sprawdzanie czy na pozycji x+1 y+1 jest pion pczeciwnego kolru
					if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)+1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)+1)) {
						//sprawdzanie czy pole x+2 y+2 jest wolne
						if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
							if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
								if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
									if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
										//sprawdzenie czy bicie nie konczy sie poza plansza
										if((FiguresToCheck & 0b111L)+2 >= 0 && (FiguresToCheck & 0b111L)+2 <= 7 && (FiguresToCheck & 0b111000L)>>3+2 >= 0 && (FiguresToCheck & 0b111000L)>>32 <= 7) {
											System.out.println("Mo¿liwe bicie opcja2");
										}
									}
								}
							}
						}
					}
					else if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)-1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)-1)) {
						if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
							if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
								if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
									if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
										if((FiguresToCheck & 0b111L)+2 >= 0 && (FiguresToCheck & 0b111L)+2 <= 7 && (FiguresToCheck & 0b111000L)>>3-2 >= 0 && (FiguresToCheck & 0b111000L)>>3-2 <= 7) {
											System.out.println("Mo¿liwe bicie opcja2");
										}
									}
								}
							}
						}
					}
					else if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)-1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)-1)) {
						if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
							if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
								if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
									if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
										if((FiguresToCheck & 0b111L)-2 >= 0 && (FiguresToCheck & 0b111L)-2 <= 7 && (FiguresToCheck & 0b111000L)>>3-2 >= 0 && (FiguresToCheck & 0b111000L)>>3-2 <= 7) {
											System.out.println("Mo¿liwe bicie opcja3");
										}
									}
								}
							}
						}
					}
					else if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)+1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)+1)) {
						if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
							if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
								if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
									if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
										if((FiguresToCheck & 0b111L)-2 >= 0 && (FiguresToCheck & 0b111L)-2 <= 7 && (FiguresToCheck & 0b111000L)>>3+2 >= 0 && (FiguresToCheck & 0b111000L)>>3+2 <= 7) {
											System.out.println("Mo¿liwe bicie opcja4");
										}
									}
								}
							}
						}
					}
					FiguresToCheck = FiguresToCheck >> 9;
				}
			System.out.println("Koniec funkcji");	
		}
		
		//Funkcja zwraca zmienÄ… long z przesunietym pionkiem z pozycji start do pozyji end
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
					 //zwrÃ³cenie longa z przesunietym pionkiem
					 return(figuresResult);
				}
				figuresResult = figuresResult >> 9;
			}
			return -1;
		}
		
		//Funkcja rysujaca Plansze
		public static void drawBoard(long white1, long white2,long black1,long black2) {
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {					
					if (checkFigurePos(white1, j, i)) {
						System.out.print("\u2659 ");
					}
					else if (checkFigurePos(white2, j, i)) {
						System.out.print("\u2659 ");
					} 
					else if(checkFigurePos(black1, j, i)) {
						System.out.print("\u265F ");
					}
					else if(checkFigurePos(black2, j, i)) {
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
		
		//Funkcja sprawdza czy figura jest Pionek Czy Dama
		public static boolean checkTypeOfFigure(long Figures, int x, int y){
			for (int i = 0; i <= 5; i++) {
				if (((Figures & 0b10000000) > 0) & ((Figures & 0b111L) == x) & (((Figures & 0b111000L)>>3) == y)) {
						return true;
				}
				Figures = Figures >> 9;
			}
			return false;
		}

		//Funkcja sprawdza czy w pkt x,y jest do narysowania figura z jednej zmienej long
		public static boolean checkFigurePos(long Figures, int x, int y) {	
			for (int i = 0; i <= 5; i++) {
				if (((Figures & 0b111L) == x) & (((Figures & 0b111000L)>>3) == y)) {	
					return true;
				}
				Figures = Figures >> 9;
			}
			return false;
		}
}
