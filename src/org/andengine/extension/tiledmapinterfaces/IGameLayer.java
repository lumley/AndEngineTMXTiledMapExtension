/**
 * 
 */
package org.andengine.extension.tiledmapinterfaces;

import org.andengine.entity.IEntity;

/**
 * Escondite
 * @author Sergio Rodriguez Lumley
 * 16/09/2012
 */
public interface IGameLayer extends IEntity {

	/**
	 * Gets the specified tile of the layer
	 * @param pTileColumn int with the column of the tile to retrieve
	 * @param pTileRow int with the row of the tile to retrieve 
	 * @return IGameTile to be retrieved
	 */
	public IGameTile getTile(final int pTileColumn, final int pTileRow);
}
