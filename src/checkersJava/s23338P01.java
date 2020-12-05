package checkersJava;

import java.util.Scanner;

public class s23338P01 {
		public static void main(String[] args) {
			long white1 = 0b101000001_101000011_101000101_101000111_101001110_101001100L;
			long white2 = 0b101001000_101001010_111010001_101010011_101010101_101010111L;
			long black1 = 0b100111000_100111010_100111100_100111110_100110111_100110101L;
			long black2 = 0b100110011_110110001_100101000_100101010_100101100_100101110L;
			
			boolean GameOver = false;
			boolean IsWhiteMove = true;
			boolean IsPossibleToCapture = false;
			Scanner scan = new Scanner(System.in);
			
			
			white2 = giveNewPosition(white2, 1, 2, 0,3);
			black2 = giveNewPosition(black2, 0, 5, 1,4);
			black2 = giveNewPosition(black2, 2, 5, 3,4);
			
			if(IsWhiteMove) {
				if(checkCapture(white1, white2, black1, black2) || checkCapture(white2, white1, black1,black2)) {
					IsPossibleToCapture = true;
				}
				else {
					IsPossibleToCapture = false;
				}
			}
			else {
				if(checkCapture(black1, black2, white1, white2) || checkCapture(black2, black1, white1,white2)) {
					IsPossibleToCapture = true;
				}
				else {
					System.out.println("Czarne nie maja bicia");
					IsPossibleToCapture = false;
				}
			}	
			
			
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
				
				//sprawdzenie czy jest mo¿liwe bicie
				if(IsWhiteMove) {
					if(checkCapture(white1, white2, black1, black2) || checkCapture(white2, white1, black1,black2)) {
						IsPossibleToCapture = true;
					}
					else {
						IsPossibleToCapture = false;
					}
				}
				else {
					if(checkCapture(black1, black2, white1, white2) || checkCapture(black2, black1, white1,white2)) {
						IsPossibleToCapture = true;
					}
					else {
						System.out.println("Czarne nie maja bicia");
						IsPossibleToCapture = false;
					}
				}	
				
				//Sprawdzenie czy pole Xend, Y end jest wolne
				if (!checkFigurePos(white1, Xend, Yend) && !checkFigurePos(white2, Xend, Yend) && !checkFigurePos(black1, Xend, Yend) && !checkFigurePos(black2, Xend, Yend)){
					//sprawdzecznie czy pionek nie wychodzi poza plansze
					if ((Xend >= 0) && (Xend <=7) && (Yend>= 0) && (Yend <=7)) {
						//Sprawdzenie czy znajduje siê pionek na pozycji Xstart Y start
						if(checkFigurePos(white1, Xstart, Ystart) || checkFigurePos(white2, Xstart, Ystart)  || checkFigurePos(black1, Xstart, Ystart)  || checkFigurePos(black2, Xstart, Ystart)) {
								//Sprawdzenie czy figura jest pionkiem czy dama
								if(checkTypeOfFigure(white1, Xstart,Ystart) || checkTypeOfFigure(white2, Xstart,Ystart) || checkTypeOfFigure(black1, Xstart,Ystart) || checkTypeOfFigure(black2, Xstart,Ystart)) {
									System.out.println("Figura jest Pionkiem");
									//Sprawdzamy czy gracz porusza pionkiem dobrego koloru
									if((checkFigurePos(white1, Xstart, Ystart) || checkFigurePos(white2, Xstart, Ystart)) && IsWhiteMove) {
										//sprawdzenie czy gracz wykonal bicie
										if(IsPossibleToCapture && Math.abs(Xstart - Xend) == 2 && Math.abs(Ystart - Yend) == 2 && (checkFigurePos(black1, (Xstart + Xend)/2, (Ystart + Yend)/2)|| checkFigurePos(black2, (Xstart + Xend)/2, (Ystart + Yend)/2))) {
											//wykonywanie bicie bialymi
											if(checkFigurePos(white1, Xstart, Ystart)) {
												white1 =  giveNewPosition(white1,Xstart,Ystart,Xend,Yend);
												if(checkFigurePos(black1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													black1 = exludeFigure(black1,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												else if(checkFigurePos(black2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													black2 = exludeFigure(black2,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												System.out.println("Sprawdzanie dalszego bicia");
												if(checkCaptureOnPosition(white1, white2, black1, black2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(white1, white2, black1, black2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(Math.abs(Xstart - Xend) == 2 && Math.abs(Ystart - Yend) == 2 && (checkFigurePos(black1, (Xstart + Xend)/2, (Ystart + Yend)/2)|| checkFigurePos(black2, (Xstart + Xend)/2, (Ystart + Yend)/2))) {
															white1 =  giveNewPosition(white1,Xstart,Ystart,Xend,Yend);
															if(checkFigurePos(black1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																black1 = exludeFigure(black1,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
															else if(checkFigurePos(black2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																black2 = exludeFigure(black2,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = false;
												}
												else {
													IsWhiteMove = false;
												}
												
											}
											else if(checkFigurePos(white2, Xstart, Ystart)) {
												white2 =  giveNewPosition(white2,Xstart,Ystart,Xend,Yend);
												if(checkFigurePos(black1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													black1 = exludeFigure(black1,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												else if(checkFigurePos(black2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													black2 = exludeFigure(black2,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												if(checkCaptureOnPosition(white2, white1, black1, black2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(white2, white1, black1, black2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(Math.abs(Xstart - Xend) == 2 && Math.abs(Ystart - Yend) == 2 && (checkFigurePos(black1, (Xstart + Xend)/2, (Ystart + Yend)/2)|| checkFigurePos(black2, (Xstart + Xend)/2, (Ystart + Yend)/2))) {
															white2 =  giveNewPosition(white2,Xstart,Ystart,Xend,Yend);
															if(checkFigurePos(black1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																black1 = exludeFigure(black1,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
															else if(checkFigurePos(black2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																black2 = exludeFigure(black2,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = false;
												}
												else {
													IsWhiteMove = false;
												}
											}
										}
										else if(IsPossibleToCapture) {
											System.out.println("Zly ruch jest mo¿liwe bicie");
										}
										//Brak bicia
										else {
											//Sprawdzenie czy ruch jest poprawny
											if( Math.abs(Xstart - Xend) == 1 && (Yend - Ystart) == 1) {
												if(checkFigurePos(white1, Xstart, Ystart)) {
													white1 =  giveNewPosition(white1,Xstart,Ystart,Xend,Yend);
												}
												else if(checkFigurePos(white2, Xstart, Ystart)) {
													white2 =  giveNewPosition(white2,Xstart,Ystart,Xend,Yend);
												}
												IsWhiteMove = false;
											}
											else {
												System.out.println("Nie mozna tu przesunac pionka");
											}
										}
									}
									else if((checkFigurePos(black1, Xstart, Ystart) || checkFigurePos(black2, Xstart, Ystart)) && !IsWhiteMove) {
										//sprawdzenie czy gracz wykonal bicie
										if(IsPossibleToCapture && Math.abs(Xstart - Xend) == 2 && Math.abs(Ystart - Yend) == 2 && (checkFigurePos(white1, (Xstart + Xend)/2, (Ystart + Yend)/2)|| checkFigurePos(white2, (Xstart + Xend)/2, (Ystart + Yend)/2))) {
											//wykonywanie bicie czarnymi
											if(checkFigurePos(black1, Xstart, Ystart)) {
												black1 =  giveNewPosition(black1,Xstart,Ystart,Xend,Yend);
												if(checkFigurePos(white1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													white1 = exludeFigure(white1,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												else if(checkFigurePos(white2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													white2 = exludeFigure(white2,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												if(checkCaptureOnPosition(black1, black2, white1, white2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(black1, black2, white1, white2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(Math.abs(Xstart - Xend) == 2 && Math.abs(Ystart - Yend) == 2 && (checkFigurePos(white1, (Xstart + Xend)/2, (Ystart + Yend)/2)|| checkFigurePos(white2, (Xstart + Xend)/2, (Ystart + Yend)/2))) {
															black1 =  giveNewPosition(black1,Xstart,Ystart,Xend,Yend);
															if(checkFigurePos(white1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																white1 = exludeFigure(white1,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
															else if(checkFigurePos(white2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																white2 = exludeFigure(white2,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = true;
												}
												else {
													IsWhiteMove = true;
												}
											}
											else if(checkFigurePos(black2, Xstart, Ystart)) {
												black2 =  giveNewPosition(black2,Xstart,Ystart,Xend,Yend);
												if(checkFigurePos(white1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													white1 = exludeFigure(white1,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												else if(checkFigurePos(white2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
													white2 = exludeFigure(white2,(Xstart + Xend)/2,(Ystart + Yend)/2);
												}
												if(checkCaptureOnPosition(black2, black1, white1, white2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(black2, black1, white1, white2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(Math.abs(Xstart - Xend) == 2 && Math.abs(Ystart - Yend) == 2 && (checkFigurePos(white1, (Xstart + Xend)/2, (Ystart + Yend)/2)|| checkFigurePos(white2, (Xstart + Xend)/2, (Ystart + Yend)/2))) {
															black2 =  giveNewPosition(black2,Xstart,Ystart,Xend,Yend);
															if(checkFigurePos(white1, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																white1 = exludeFigure(white1,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
															else if(checkFigurePos(white2, (Xstart + Xend)/2, (Ystart + Yend)/2)) {
																white2 = exludeFigure(white2,(Xstart + Xend)/2,(Ystart + Yend)/2);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = true;
												}
												else {
													IsWhiteMove = true;
												}
											}
										}
										else if(IsPossibleToCapture) {
											System.out.println("Zly ruch jest mo¿liwe bicie");
										}
										//Brak bicia
										else {
											//Sprawdzenie czy ruch jest poprawny
											if( Math.abs(Xstart - Xend) == 1 && (Yend - Ystart) == -1) {
												if(checkFigurePos(black1, Xstart, Ystart)) {
													black1 =  giveNewPosition(black1,Xstart,Ystart,Xend,Yend);
												}
												else if(checkFigurePos(black2, Xstart, Ystart)) {
													black2 =  giveNewPosition(black2,Xstart,Ystart,Xend,Yend);
												}
												IsWhiteMove = true;
											}
											else {
												System.out.println("Nie mozna tu przesunac pionka");
											}
										}
									}
									else {
										System.out.println("Probujesz poruszyc nie swoj pionek");
									}
								}
								else {
									System.out.println("figura jest dama");
									//Sprawdzamy czy gracz dama pionkiem dobrego koloru
									if((checkFigurePos(white1, Xstart, Ystart) || checkFigurePos(white2, Xstart, Ystart)) && IsWhiteMove) {
										//sprawdzenie czy gracz wykonal bicie
										if(IsPossibleToCapture && (checkQueenCapture(white1, Xstart, Ystart, Xend, Yend, white2, black1, black2) || checkQueenCapture(white2, Xstart, Ystart, Xend, Yend, white1, black1, black2))) {
											//wykonywanie bicia
											if(checkFigurePos(white1, Xstart, Ystart)) {
												white1 =  giveNewPosition(white1,Xstart,Ystart,Xend,Yend);
												int XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
												int YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
												
												if(checkFigurePos(black1, XtoExlude, YtoExlude)) {
													black1 = exludeFigure(black1, XtoExlude,YtoExlude);
												}
												else if(checkFigurePos(black2, XtoExlude, YtoExlude)) {
													black2 = exludeFigure(black2,XtoExlude,YtoExlude);
												}
												if(checkCaptureOnPosition(white1, white2, black1, black2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(white1, white2, black1, black2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(checkQueenCapture(white1, Xstart, Ystart, Xend, Yend, white2, black1, black2)) {
															white1 =  giveNewPosition(white1,Xstart,Ystart,Xend,Yend);
															XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
															YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
															
															if(checkFigurePos(black1, XtoExlude, YtoExlude)) {
																black1 = exludeFigure(black1, XtoExlude,YtoExlude);
															}
															else if(checkFigurePos(black2, XtoExlude, YtoExlude)) {
																black2 = exludeFigure(black2,XtoExlude,YtoExlude);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = false;												
												}
												else {
													IsWhiteMove = false;
												}
											}
											else if(checkFigurePos(white2, Xstart, Ystart)) {
												white2 =  giveNewPosition(white2,Xstart,Ystart,Xend,Yend);
												int XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
												int YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
												
												if(checkFigurePos(black1, XtoExlude, YtoExlude)) {
													black1 = exludeFigure(black1, XtoExlude,YtoExlude);
												}
												else if(checkFigurePos(black2, XtoExlude, YtoExlude)) {
													black2 = exludeFigure(black2,XtoExlude,YtoExlude);
												}
												if(checkCaptureOnPosition(white2, white1, black1, black2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(white2, white1, black1, black2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(checkQueenCapture(white2, Xstart, Ystart, Xend, Yend, white1, black1, black2)) {
															white2 =  giveNewPosition(white2,Xstart,Ystart,Xend,Yend);
															XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
															YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
															
															if(checkFigurePos(black1, XtoExlude, YtoExlude)) {
																black1 = exludeFigure(black1, XtoExlude,YtoExlude);
															}
															else if(checkFigurePos(black2, XtoExlude, YtoExlude)) {
																black2 = exludeFigure(black2,XtoExlude,YtoExlude);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = false;
												}
												else {
													IsWhiteMove = false;
												}
											}
										}
										else if(IsPossibleToCapture) {
											System.out.println("Zly ruch jest mo¿liwe bicie");
										}
										//Brak bicia
										else {
											//Sprawdzenie czy ruch jest poprawny
											if(chechQueenMove(white1, Xstart,Ystart, Xend,Yend, white2, black1,black2) || chechQueenMove(white2, Xstart,Ystart, Xend,Yend, white1, black1,black2)) {
												if(checkFigurePos(white1, Xstart, Ystart)) {
													white1 =  giveNewPosition(white1,Xstart,Ystart,Xend,Yend);
												}
												else if(checkFigurePos(white2, Xstart, Ystart)) {
													white2 =  giveNewPosition(white2,Xstart,Ystart,Xend,Yend);
												}
												IsWhiteMove = false;
											}
											else {
												System.out.println("Nie mozna tu przesunac damy");
											}
										}
									}
									else if((checkFigurePos(black1, Xstart, Ystart) || checkFigurePos(black2, Xstart, Ystart)) && !IsWhiteMove) {
										//sprawdzenie czy gracz wykonal bicie
										if(IsPossibleToCapture && (checkQueenCapture(black1, Xstart, Ystart, Xend, Yend, black2, white1, white2) || checkQueenCapture(black2, Xstart, Ystart, Xend, Yend, black1, white1, white2))) {
											//wykonywanie bicia
											if(checkFigurePos(black1, Xstart, Ystart)) {
												black1 =  giveNewPosition(black1,Xstart,Ystart,Xend,Yend);
												int XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
												int YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
												
												if(checkFigurePos(white1, XtoExlude, YtoExlude)) {
													white1 = exludeFigure(white1, XtoExlude,YtoExlude);
												}
												else if(checkFigurePos(white2, XtoExlude, YtoExlude)) {
													white2 = exludeFigure(white2,XtoExlude,YtoExlude);
												}
												if(checkCaptureOnPosition(black1, black2, white1, white2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(black1, black2, white1, white2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(checkQueenCapture(black1, Xstart, Ystart, Xend, Yend, black2, white1, white2)) {
															black1 =  giveNewPosition(black1,Xstart,Ystart,Xend,Yend);
															XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
															YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
															
															if(checkFigurePos(white1, XtoExlude, YtoExlude)) {
																white1 = exludeFigure(white1, XtoExlude,YtoExlude);
															}
															else if(checkFigurePos(white2, XtoExlude, YtoExlude)) {
																white2 = exludeFigure(white2,XtoExlude,YtoExlude);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = false;												
												}
												else {
													IsWhiteMove = false;
												}
											}
											else if(checkFigurePos(black2, Xstart, Ystart)) {
												black2 =  giveNewPosition(black2,Xstart,Ystart,Xend,Yend);
												int XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
												int YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
												
												if(checkFigurePos(white1, XtoExlude, YtoExlude)) {
													white1 = exludeFigure(white1, XtoExlude,YtoExlude);
												}
												else if(checkFigurePos(black2, XtoExlude, YtoExlude)) {
													white2 = exludeFigure(white2,XtoExlude,YtoExlude);
												}
												if(checkCaptureOnPosition(black2, black1, white1, white2, Xend, Yend)) {
													System.out.println("Dostepne dalsze bicie");
													while(checkCaptureOnPosition(black2, black1, white1, white2, Xend, Yend)){
														drawBoard(white1, white2,black1,black2);
														Xstart = Xend;
														Ystart = Yend;
														System.out.println("Podaj pozycje X na która przesun¹æ figure: ");
														Xend = scan.nextInt();
														System.out.println("Podaj pozycje Y na która przesun¹æ figure: ");
														Yend = scan.nextInt();
														if(checkQueenCapture(black2, Xstart, Ystart, Xend, Yend, black1, white1, white2)) {
															black2 =  giveNewPosition(black2,Xstart,Ystart,Xend,Yend);
															XtoExlude = Xend - (Math.abs(Xend-Xstart)/(Xend-Xstart));
															YtoExlude = Yend - (Math.abs(Yend-Ystart)/(Yend-Ystart));
															
															if(checkFigurePos(white1, XtoExlude, YtoExlude)) {
																white1 = exludeFigure(white1, XtoExlude,YtoExlude);
															}
															else if(checkFigurePos(white2, XtoExlude, YtoExlude)) {
																white2 = exludeFigure(white2,XtoExlude,YtoExlude);
															}
														}
														else{
															System.out.println("z³e dane mo¿liwe jest dalsze bicie");
															Xend = Xstart;
															Yend = Ystart;
														}
													}
													IsWhiteMove = false;
												}
												else {
													IsWhiteMove = false;
												}
											}
										}
										else if(IsPossibleToCapture) {
											System.out.println("Zly ruch jest mo¿liwe bicie");
										}
										//Brak bicia
										else {
											//Sprawdzenie czy ruch jest poprawny
											if(chechQueenMove(black1, Xstart,Ystart, Xend,Yend, black2, white1,white2) || chechQueenMove(black2, Xstart,Ystart, Xend,Yend, black1, white1,white2)) {
												if(checkFigurePos(black1, Xstart, Ystart)) {
													black1 =  giveNewPosition(black1,Xstart,Ystart,Xend,Yend);
												}
												else if(checkFigurePos(black2, Xstart, Ystart)) {
													black2 =  giveNewPosition(black2,Xstart,Ystart,Xend,Yend);
												}
												IsWhiteMove = true;
											}
											else {
												System.out.println("Nie mozna tu przesunac damy");
											}
										}
									}
									else {
										System.out.println("Probujesz poruszyc nie swoj dame");
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
		
		//Funkcja zmienia pionka w dame jestli dojdzie do linki krolewskiej
		public static void ChangeInToQueen() {
			
		}
		
		//Funkcja sprawdza czy mozna wykonoc ruch dama
		public static boolean chechQueenMove(long figures, int Xstart, int Ystart, int Xend,int Yend, long SameColorFigures, long secondColor1,long secondColor2) {
			for (int i = 0; i <= 5; i++) {
				//szukanie odpowiedniego pionka
				if(checkFigurePos(figures, Xstart, Ystart)) {
					if(Math.abs(Xend-Xstart) == Math.abs(Yend- Ystart)) {
						int Xdirection = Math.abs(Xend-Xstart) / (Xend-Xstart);
						int Ydirection = Math.abs(Yend-Ystart) / (Yend-Ystart);
						
						if(Xdirection == 1 && Ydirection == 1) {
							int n = 1;
							while(Xstart+n < Xend) {
								if(checkFigurePos(figures,Xstart+n,Ystart+n) || checkFigurePos(SameColorFigures,Xstart+n,Ystart+n) || checkFigurePos(secondColor1,Xstart+n,Ystart+n) || checkFigurePos(secondColor2,Xstart+n,Ystart+n)) {
									System.out.println("Pomiedzy polami znajduje sie figura");
									return false;
								}
								n++;
							}
							return true;
						}
						else if(Xdirection == -1 && Ydirection == 1) {
							int n = 1;
							while(Ystart+n < Yend) {
								if(checkFigurePos(figures,Xstart-n,Ystart+n) || checkFigurePos(SameColorFigures,Xstart-n,Ystart+n) || checkFigurePos(secondColor1,Xstart-n,Ystart+n) || checkFigurePos(secondColor2,Xstart-n,Ystart+n)) {
									System.out.println("Pomiedzy polami znajduje sie figura");
									return false;
								}
								n++;
							}
							return true;
						}
						else if(Xdirection == -1 && Ydirection == -1) {
							int n = 1;
							while(Ystart-n > Yend) {
								if(checkFigurePos(figures,Xstart-n,Ystart-n) || checkFigurePos(SameColorFigures,Xstart-n,Ystart-n) || checkFigurePos(secondColor1,Xstart-n,Ystart-n) || checkFigurePos(secondColor2,Xstart-n,Ystart-n)) {
									System.out.println("Pomiedzy polami znajduje sie figura");
									return false;
								}
								n++;
							}
							return true;
						}
						else if(Xdirection == 1 && Ydirection == -1) {
							int n = 1;
							while(Xstart+n < Xend) {
								if(checkFigurePos(figures,Xstart+n,Ystart-n) || checkFigurePos(SameColorFigures,Xstart+n,Ystart-n) || checkFigurePos(secondColor1,Xstart+n,Ystart-n) || checkFigurePos(secondColor2,Xstart+n,Ystart-n)) {
									System.out.println("Pomiedzy polami znajduje sie figura");
									return false;
								}
								n++;
							}
							return true;
						}
					}
				}
				figures = figures >> 9;
			}
			return false;
		}
		
		//funkcja sprawdza czy dama moze dokonac bicia
		public static boolean checkQueenCapture(long figures, int Xstart, int Ystart, int Xend,int Yend, long SameColorFigures, long secondColor1,long secondColor2){	
			if(Math.abs(Xstart-Xend) == Math.abs(Ystart - Yend)) {	
				if(Xend-Xstart > 0 && Yend-Ystart > 0) {
					System.out.println("Prawa dó³");
					for(int i=1; i < Math.abs(Xend-Xstart); i++) {
						if(!(checkFigurePos(secondColor1, Xstart+i, Ystart+i) || checkFigurePos(secondColor2, Xstart+i, Ystart+i) || checkFigurePos(figures, Xstart+i, Ystart+i) || checkFigurePos(SameColorFigures, Xstart+i, Ystart+i))) {
							System.out.println("Pole puste sprawdzanie dalej");
						}
						else {
							System.out.println("Pole zajête");
							if(checkFigurePos(secondColor1, Xend-1, Yend-1) || checkFigurePos(secondColor2, Xend-1,  Yend-1)) {
								System.out.println("przedostatnie pole");
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
				else if(Xend-Xstart > 0 && Yend-Ystart < 0) {
					System.out.println("Prawa góra");
					for(int i=1; i < Math.abs(Xend-Xstart); i++) {
						if(!(checkFigurePos(secondColor1, Xstart+i, Ystart-i) || checkFigurePos(secondColor2, Xstart+i, Ystart-i)|| checkFigurePos(figures, Xstart+i, Ystart-i) || checkFigurePos(SameColorFigures, Xstart+i, Ystart-i))) {
							System.out.println("Pole puste sprawdzanie dalej");
						}
						else {
							System.out.println("Pole zajête");
							if(checkFigurePos(secondColor1, Xend-1, Yend+1) || checkFigurePos(secondColor2, Xend-1,  Yend+1)) {
								System.out.println("przedostatnie pole");
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
				else if(Xend-Xstart < 0 && Yend-Ystart < 0) {
					System.out.println("lewa góra");
					for(int i=1; i < Math.abs(Xend-Xstart); i++) {
						if(!(checkFigurePos(secondColor1, Xstart-i, Ystart-i) || checkFigurePos(secondColor2, Xstart-i, Ystart-i) || checkFigurePos(figures, Xstart-i, Ystart-i) || checkFigurePos(SameColorFigures, Xstart-i, Ystart-i))) {
							System.out.println("Pole puste sprawdzanie dalej");
						}
						else {
							System.out.println("Pole zajête");
							if(checkFigurePos(secondColor1, Xend+1, Yend+1) || checkFigurePos(secondColor2, Xend+1,  Yend+1)) {
								System.out.println("przedostatnie pole");
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
				else if(Xend-Xstart < 0 && Yend-Ystart > 0) {
					System.out.println("lewa dó³");
					for(int i=1; i < Math.abs(Xend-Xstart); i++) {
						if(!(checkFigurePos(secondColor1, Xstart-i, Ystart+i) || checkFigurePos(secondColor2, Xstart-i, Ystart+i))) {
							System.out.println("Pole puste sprawdzanie dalej");
						}
						else {
							System.out.println("Pole zajête");
							if(checkFigurePos(secondColor1, Xend+1, Yend-1) || checkFigurePos(secondColor2, Xend+1,  Yend-1)) {
								System.out.println("przedostatnie pole");
								return true;
							}
							else {
								return false;
							}
						}
					}
				}
				else {
					return false;
				}
			}
				return false;
		}
		
		//Funkcja oznacza pionka jako zbitego
		public static long exludeFigure(long figures,int Xpos,int Ypos) {
			long figuresResult = figures;
			//szukanie konkretnego pionka
			for (int i = 0; i <= 5; i++) {
				if (((figuresResult & 0b111L) == Xpos) & (((figuresResult & 0b111000L)>>3) == Ypos)) {
					 //oznaczenie pionka jako zbitego
					 figuresResult = figuresResult >> 9 << 9;
					 
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
		
		//Funkcja sprawdza czy z  figura na pozycji X Y ma dostepne bicie
		public static boolean checkCaptureOnPosition(long FiguresToCheck, long SameColorFigures, long secondColor1, long  secondColor2, int posX, int posY) {
			for (int i = 0; i <= 5; i++) {
				if((FiguresToCheck & 0b111L) == posX && (FiguresToCheck & 0b111000L)>>3 == posY) {
					//Sprawdzenie czy mamy pionka czy dame
					if((FiguresToCheck &0b10000000)>> 7 == 0 ) {
						//sprawdzanie czy na pozycji x+1 y+1 jest pion pczeciwnego kolru
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)+1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)+1)) {
							//sprawdzanie czy pole x+2 y+2 jest wolne
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
											//sprawdzenie czy bicie nie konczy sie poza plansza
											if((FiguresToCheck & 0b111L)+2 >= 0 && (FiguresToCheck & 0b111L)+2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)+2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)+2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja1");
												return true;
											}
										}
									}
								}
							}
						}
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)-1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)-1)) {
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
											if((FiguresToCheck & 0b111L)+2 >= 0 && (FiguresToCheck & 0b111L)+2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)-2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)-2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja2");
												return true;
											}
										}
									}
								}
							}
						}
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)-1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)-1)) {
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
											if((FiguresToCheck & 0b111L)-2 >= 0 && (FiguresToCheck & 0b111L)-2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)-2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)-2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja3");
												return true;
											}
										}
									}
								}
							}
						}
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)+1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)+1)) {
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
											if((FiguresToCheck & 0b111L)-2 >= 0 && (FiguresToCheck & 0b111L)-2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)+2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)+2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja4");
												return true;
											}
										}
									}
								}
							}
						}
					}
					//dama
					else {
						int n=1;
						//Sprawdzenie czy na pierwszej przekatnej nie ma bicia
						while((FiguresToCheck & 0b111L)+n < 7 && ((FiguresToCheck & 0b111000L)>>3)+n < 7) {
							//sprawdzenie czy jakies z pul jest zajete
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n)) {
								//sprawdzenie czy pole za zajetym polem jest puste
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1)) {
									System.out.println("Mo¿liwe bicie dama opcja1");
									return true;
								}
							}
							n++;
						}
						n=1;
						while((FiguresToCheck & 0b111L)-n > 0 && ((FiguresToCheck & 0b111000L)>>3)+n < 7) {
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n)) {
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1)) {
									System.out.println("Mo¿liwe bicie dama opcja2");
									return true;
								}
							}
							n++;
						}
						n=1;
						while((FiguresToCheck & 0b111L)-n > 0 && ((FiguresToCheck & 0b111000L)>>3)-n > 0) {
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n)) {
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1)) {
									System.out.println("Mo¿liwe bicie dama opcja3");
									return true;
								}
							}
							n++;
						}
						n=1;
						while((FiguresToCheck & 0b111L)+n < 7 && ((FiguresToCheck & 0b111000L)>>3)-n > 0) {
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n)) {
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1)) {
									System.out.println("Mo¿liwe bicie dama opcja4");
									return true;
								}
							}
							n++;
						}
						System.out.println("Brak opcji dla damy");
					}
				}
				
				FiguresToCheck = FiguresToCheck >> 9;
			}
			return false;
	}
		
		//Funkcja Spawdzajaca czy jest dostepne bicie
		public static boolean checkCapture(long FiguresToCheck, long SameColorFigures, long secondColor1, long  secondColor2) {
				for (int i = 0; i <= 5; i++) {
					
					//Sprawdzenie czy mamy pionka czy dame
					if((FiguresToCheck &0b10000000)>> 7 == 0 ) {
						//sprawdzanie czy na pozycji x+1 y+1 jest pion pczeciwnego kolru
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)+1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)+1)) {
							//sprawdzanie czy pole x+2 y+2 jest wolne
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
											//sprawdzenie czy bicie nie konczy sie poza plansza
											if((FiguresToCheck & 0b111L)+2 >= 0 && (FiguresToCheck & 0b111L)+2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)+2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)+2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja1");
												return true;
											}
										}
									}
								}
							}
						}
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)-1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+1,((int)(FiguresToCheck & 0b111000L)>>3)-1)) {
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))+2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
											if((FiguresToCheck & 0b111L)+2 >= 0 && (FiguresToCheck & 0b111L)+2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)-2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)-2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja2");
												return true;
											}
										}
									}
								}
							}
						}
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)-1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)-1)) {
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)-2)) {
											if((FiguresToCheck & 0b111L)-2 >= 0 && (FiguresToCheck & 0b111L)-2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)-2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)-2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja3");
												return true;
											}
										}
									}
								}
							}
						}
						if(checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)+1) || checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-1,((int)(FiguresToCheck & 0b111000L)>>3)+1)) {
							if(!checkFigurePos(secondColor1, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
								if(!checkFigurePos(secondColor2, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
									if(!checkFigurePos(FiguresToCheck, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
										if(!checkFigurePos(SameColorFigures, ((int)(FiguresToCheck & 0b111L))-2,((int)(FiguresToCheck & 0b111000L)>>3)+2)) {
											if((FiguresToCheck & 0b111L)-2 >= 0 && (FiguresToCheck & 0b111L)-2 <= 7 && ((FiguresToCheck & 0b111000L)>>3)+2 >= 0 && ((FiguresToCheck & 0b111000L)>>3)+2 <= 7) {
												System.out.println("Mo¿liwe bicie opcja4");
												return true;
											}
										}
									}
								}
							}
						}
					}
					//dama
					else {
						int n=1;
						//Sprawdzenie czy na pierwszej przekatnej nie ma bicia
						while((FiguresToCheck & 0b111L)+n < 7 && ((FiguresToCheck & 0b111000L)>>3)+n < 7) {
							//sprawdzenie czy jakies z pul jest zajete
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)+n)) {
								//sprawdzenie czy pole za zajetym polem jest puste
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1)) {
									System.out.println("Mo¿liwe bicie dama opcja1");
									return true;
								}
							}
							n++;
						}
						n=1;
						while((FiguresToCheck & 0b111L)-n > 0 && ((FiguresToCheck & 0b111000L)>>3)+n < 7) {
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)+n)) {
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)+n+1)) {
									System.out.println("Mo¿liwe bicie dama opcja2");
									return true;
								}
							}
							n++;
						}
						n=1;
						while((FiguresToCheck & 0b111L)-n > 0 && ((FiguresToCheck & 0b111000L)>>3)-n > 0) {
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n,(int)((FiguresToCheck & 0b111000L)>>3)-n)) {
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)-n-1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1)) {
									System.out.println("Mo¿liwe bicie dama opcja3");
									return true;
								}
							}
							n++;
						}
						n=1;
						while((FiguresToCheck & 0b111L)+n < 7 && ((FiguresToCheck & 0b111000L)>>3)-n > 0) {
							if(checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n) || checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n,(int)((FiguresToCheck & 0b111000L)>>3)-n)) {
								if(!checkFigurePos(FiguresToCheck,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(SameColorFigures,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor1,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1) && !checkFigurePos(secondColor2,(int)(FiguresToCheck & 0b111L)+n+1,(int)((FiguresToCheck & 0b111000L)>>3)-n-1)) {
									System.out.println("Mo¿liwe bicie dama opcja4");
									return true;
								}
							}
							n++;
						}
						System.out.println("Brak opcji dla damy");
					}
					FiguresToCheck = FiguresToCheck >> 9;
				}
				return false;
		}
		
		//Funkcja zwraca zmiena long z przesunietym pionkiem z pozycji start do pozyji end
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
						if(checkTypeOfFigure(white1, j,i)){
							System.out.print("\u2659 ");
						}
						else {
							System.out.print("\u2655 ");
						}
						
					}
					else if (checkFigurePos(white2, j, i)) {
						if(checkTypeOfFigure(white2, j,i)){
							System.out.print("\u2659 ");
						}
						else {
							System.out.print("\u2655 ");
						}
					} 
					else if(checkFigurePos(black1, j, i)) {
						if(checkTypeOfFigure(black1, j,i)){
							System.out.print("\u265F ");
						}
						else {
							System.out.print("\u265B ");
						}
					}
					else if(checkFigurePos(black2, j, i)) {
						if(checkTypeOfFigure(black2, j,i)){
							System.out.print("\u265F ");
						}
						else {
							System.out.print("\u265B ");
						}
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
				if (((Figures & 0b010000000) == 0) && ((Figures & 0b111L) == x) && (((Figures & 0b111000L)>>3) == y)) {
						return true;
				}
				Figures = Figures >> 9;
			}
			return false;
		}

		//Funkcja sprawdza czy w pkt x,y jest do narysowania figura z jednej zmienej long
		public static boolean checkFigurePos(long Figures, int x, int y) {	
			for (int i = 0; i <= 5; i++) {
				if (((Figures & 0b111L) == x) & (((Figures & 0b111000L)>>3) == y) & ((Figures & 0b100000000) >> 8 == 1)) {	
					return true;
				}
				Figures = Figures >> 9;
			}
			return false;
		}
}
