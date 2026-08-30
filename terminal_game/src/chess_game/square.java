package chess_game;

import chess_game.*;
import pieces.*;

public class square {
	
	piece piecememory;
	piece piece;
	int x;
	int y;
	
	
	public square(int x, int y) {
		piece= new nopiece();
		piecememory= piece;
	}
	
	public void setPiece(piece piece) {
		this.piece=piece;
	}
	
	public piece getPiece() {
		return piece;
	}

	public void setPieceMemory(piece piecememory) {
		this.piecememory=piecememory;
	}
	
	public piece getPieceMemory() {
		return piecememory;
	}
}
