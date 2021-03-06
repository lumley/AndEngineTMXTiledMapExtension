package org.andengine.extension.tmx;

import org.andengine.extension.tiledmapinterfaces.IGameMap;
import org.andengine.extension.tiledmapinterfaces.IGameTile;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * A TMXTile corresponds to a tile of a TMX Layer.
 * 
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @author Sergio Rodriguez Lumley
 * @since 10:39:48 - 05.08.2010
 */
public class TMXTile implements IGameTile{
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	/* package */ int mGlobalTileID;
	private final int mTileRow;
	private final int mTileColumn;
	private final int mTileWidth;
	private final int mTileHeight;
	ITextureRegion mTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	public TMXTile(final int pGlobalTileID, final int pTileColumn, final int pTileRow, final int pTileWidth, final int pTileHeight, final ITextureRegion pTextureRegion) {
		this.mGlobalTileID = pGlobalTileID;
		this.mTileRow = pTileRow;
		this.mTileColumn = pTileColumn;
		this.mTileWidth = pTileWidth;
		this.mTileHeight = pTileHeight;
		this.mTextureRegion = pTextureRegion;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * A global tile ID is used to define what properties and what texture uses this tile
	 * in a TMX Layer
	 * @return
	 */
	public int getGlobalTileID() {
		return this.mGlobalTileID;
	}

	public int getTileRow() {
		return this.mTileRow;
	}

	public int getTileColumn() {
		return this.mTileColumn;
	}

	public int getTileWidth() {
		return this.mTileWidth;
	}

	public int getTileHeight() {
		return this.mTileHeight;
	}

	public ITextureRegion getTextureRegion() {
		return this.mTextureRegion;
	}

	/**
	 * Note this will also set the {@link ITextureRegion} with the associated pGlobalTileID of the {@link TMXTiledMap}.
	 * @param pTMXTiledMap
	 * @param pGlobalTileID
	 */
	public void setGlobalTileID(final TMXTiledMap pTMXTiledMap, final int pGlobalTileID) {
		this.mGlobalTileID = pGlobalTileID;
		this.mTextureRegion = pTMXTiledMap.getTextureRegionFromGlobalTileID(pGlobalTileID);
	}

	/**
	 * You'd probably want to call {@link TMXTile#setGlobalTileID(TMXTiledMap, int)} instead.
	 * @param pTextureRegion
	 */
	public void setTextureRegion(final ITextureRegion pTextureRegion) {
		this.mTextureRegion = pTextureRegion;
	}

	public TMXProperties<TMXTileProperty> getTMXTileProperties(final TMXTiledMap pTMXTiledMap) {
		return pTMXTiledMap.getTMXTileProperties(this.mGlobalTileID);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/* (non-Javadoc)
	 * @see org.andengine.extension.tiledmapinterfaces.IGameTile#getColumn()
	 */
	@Override
	public int getColumn() {
		return this.mTileColumn;
	}

	/* (non-Javadoc)
	 * @see org.andengine.extension.tiledmapinterfaces.IGameTile#getRow()
	 */
	@Override
	public int getRow() {
		return this.mTileRow;
	}
	
	/* (non-Javadoc)
	 * @see org.andengine.extension.tiledmapinterfaces.IGameTile#containsProperty(java.lang.String, java.lang.String, org.andengine.extension.tiledmapinterfaces.IGameMap)
	 */
	@Override
	public boolean containsProperty(String pProperty, String pValue,
			IGameMap pTiledMap) {
		boolean hasAttribute = false;
		if(pTiledMap instanceof TMXTiledMap){
			TMXTiledMap tiledMap = (TMXTiledMap) pTiledMap;
			TMXProperties<TMXTileProperty> properties = tiledMap.getTMXTileProperties(this.mGlobalTileID);
			
			if(properties != null){
				hasAttribute = properties.containsTMXProperty(pProperty, pValue);				
			}
			
		}else{
			return false;
		}
		
		return hasAttribute;
	}
	
	/* (non-Javadoc)
	 * @see org.andengine.extension.tiledmapinterfaces.IGameTile#getPropertyValue(java.lang.String, org.andengine.extension.tiledmapinterfaces.IGameMap)
	 */
	@Override
	public String getPropertyValue(String pProperty, IGameMap pTiledMap) {
		String result = null;
		if(pTiledMap instanceof TMXTiledMap){
			TMXTiledMap tiledMap = (TMXTiledMap) pTiledMap;
			TMXProperties<TMXTileProperty> tileProperties = tiledMap.getTMXTileProperties(this.mGlobalTileID);
			
			if(tileProperties != null){
				TMXTileProperty tileProperty = null;
				for(TMXTileProperty tProperty : tileProperties){
					if(tProperty.getName().compareTo(pProperty) == 0){
						tileProperty = tProperty;
						break;
					}
				}
				if(tileProperty != null){
					result = tileProperty.getValue();
				}
			}
		}
		
		return result;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
