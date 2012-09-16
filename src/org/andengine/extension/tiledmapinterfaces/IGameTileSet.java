/**
 * 
 */
package org.andengine.extension.tiledmapinterfaces;

import org.andengine.extension.tmx.util.exception.TMXParseException;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.xml.sax.Attributes;

import android.content.res.AssetManager;

/**
 * AndEngineTMXTiledMapExtension
 * @author Sergio Rodriguez Lumley
 * 17/09/2012
 */
public interface IGameTileSet {
	
	
	/**
	 * Sets the image source for the TileSet
	 * @param pAssetManager AssetManager from where we should get the Texture
	 * @param pTextureManager TextureManager to store the texture once loaded
	 * @param pAttributes Attributes that define the source of the image (See {@link TMXConstants.TAG_IMAGE_ATTRIBUTE_SOURCE})
	 * @throws TMXParseException It may throw a TMXParseException depending on implementation
	 */
	public void setImageSource(final AssetManager pAssetManager, final TextureManager pTextureManager, final Attributes pAttributes) throws TMXParseException;
	
	/**
	 * Gets the path to the ImageSource
	 * @return String with the path to the image source of the GameTileSet
	 */
	public String getImageSource();
	
	/**
	 * Gets a TextureRegion from a Graphics tile ID
	 * @param pGraphicTileID the portion of the TileSet that is represented by the specified ID (e.g. a drawn tree in the sprite sheet)
	 * @return ITextureRegion with the specified texture region
	 */
	public ITextureRegion getTextureRegionFromGlobalTileID(final int pGraphicTileID);
	
	/**
	 * Gets the first graphic tile id found in this Game Tile Set
	 * @return int with the ID of the first graphic tile (the lowest value found in this tile set)
	 */
	public int getFirstGlobalTileID();
	

}
