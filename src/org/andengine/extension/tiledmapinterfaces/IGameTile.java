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
	
	/**
	 * Checks whether this GameTile contains the specified property
	 * @param pProperty String with the name of the property to test
	 * @param pValue String with the value to check
	 * @param pTiledMap IGameMap to where this GameTile belongs
	 * @return true if the tile contains the specified property with the specified value, false otherwise
	 */
	public boolean containsProperty(final String pProperty, final String pValue, final IGameMap pTiledMap);
	
}
