package capps.scrabble; 

public class Rack {

	private String tiles; 

	public Rack(String initialTiles) throws ScrabbleException {
		tiles = initialTiles.toUpperCase(); 
		if (tiles.length() != 7) 
			throw new ScrabbleException("Initial tiles not size 7"); 
	}

	@Override
	public String toString() {
		return tiles; 
	}

	public boolean hasTiles(String t) {
		boolean[] marked = new boolean[tiles.length()]; 
		boolean markedSomething = false; 

		for (int i = 0; i < t.length(); i++) {
			markedSomething = false; 
			for (int j = 0; j < tiles.length(); j++) {
				if (!marked[j] && tiles.charAt(j) == t.charAt(i)) {
					marked[j] = true; 
					markedSomething = true; 
					break; 
				}
			}
			if (!markedSomething)
				return false; 
		}

		return true; 
	}

	public void addTiles(String toAdd) throws ScrabbleException {
		if (tiles.length() + toAdd.length() > 7)
			throw new ScrabbleException("Too many tiles given to add"); 

		tiles = (tiles + toAdd).toUpperCase(); 
	}

	public void removeTiles (String toRemove) throws ScrabbleException {
		if (toRemove.length() > tiles.length()) {
			throw new ScrabbleException("Too many tiles given to remove"); 
		}

		boolean[] markedToRemove = new boolean[tiles.length()]; 

		boolean markedSomething = false; 

		for (int i = 0; i < toRemove.length(); i++) {
			markedSomething = false; 
			for (int j = 0; j < tiles.length(); j++) {
				if (tiles.charAt(j) == toRemove.charAt(i) && !markedToRemove[j]) {
					markedSomething = true; 
					markedToRemove[j] = true; 
					break; 
				}
			}
			if (!markedSomething) {
				throw new ScrabbleException("Tiles to remove had extra tiles."); 
			}
		}

		StringBuffer newTiles = new StringBuffer(); 

		for (int i = 0; i < tiles.length(); i++) {
			if (!markedToRemove[i])
				newTiles.append(tiles.charAt(i)); 
		}
		tiles = newTiles.toString(); 	
	}

}