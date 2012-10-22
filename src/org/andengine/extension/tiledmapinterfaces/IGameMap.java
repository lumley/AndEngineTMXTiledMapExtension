/**
 * 
 */
package org.andengine.extension.tiledmapinterfaces;

import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Escondite
 * @author Sergio Rodriguez Lumley
 * 16/09/2012
 */
public interface IGameMap extends IEntity {

	/**
	 * Gets the specified layer
	 * @param pLayer int that identifies the layer by its depth (being 0 the first lowest depth)
	 * @return IGameLayer specified by pLayer
	 */
	public IGameLayer getLayer(final int pLayer);
	
	/**
	 * Gets the number of layers of this map
	 * @return int with the number of layers
	 */
	public int getLayerCount();
	
	/**
	 * Gets the width of the map in pixels
	 * @return float with the width of the map
	 */
	public float getWidth();
	
	/**
	 * Gets the height of the map in pixels
	 * @return float with the height of the map
	 */
	public float getHeight();
	
	/**
	 * Gets the width of a tile in pixels
	 * @return width of a tile of this map
	 */
	public int getTileWidth();

	/**
	 * Gets the height of a tile in pixels
	 * @return height of a tile of this map
	 */
	public int getTileHeight();
	
	/**
	 * Gets the number of columns that the map has
	 * @return int with the number of tiles as columns
	 */
	public int getTileColumns();
	
	/**
	 * Gets the number of rows that the map has
	 * @return int with the number of tiles as rows
	 */
	public int getTileRows();
	
	/**
	 * Gets the texture region specified by parameters
	 * @param pGraphicsTileID int that identifies the concrete texture region
	 * @return ITextureRegion requested, null if not found
	 */
	public ITextureRegion getTextureRegionFromGlobalTileID(final int pGraphicsTileID);
	
}
