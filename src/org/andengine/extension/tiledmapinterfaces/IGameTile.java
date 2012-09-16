/**
 * 
 */
package org.andengine.extension.tiledmapinterfaces;


/**
 * Escondite
 * @author Sergio Rodriguez Lumley
 * 16/09/2012
 */
public interface IGameTile {

	/**
	 * Gets the column of the tile within the layer where it is stored
	 * @return int with the column of the tile
	 */
	public int getColumn();
	
	/**
	 * Gets the row of the tile within the layer where it is stored
	 * @return int with the row of the tile
	 */
	public int getRow();
	
}
