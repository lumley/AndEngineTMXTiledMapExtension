package org.andengine.extension.tmx;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.extension.tiledmapinterfaces.IGameLayer;
import org.andengine.extension.tiledmapinterfaces.IGameMap;
import org.andengine.extension.tmx.util.constants.TMXConstants;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import android.util.SparseArray;

/**
 * <p>
 * A TMX Tiled Map is a map in TMX format. It is composed by TMX Layers
 * </p>
 * 
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @author Sergio Rodriguez Lumley
 * @since 19:38:11 - 20.07.2010
 */
public class TMXTiledMap extends Entity implements TMXConstants, IGameMap {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final String mOrientation;
	private final int mTileColumns;
	private final int mTilesRows;
	private final int mTileWidth;
	private final int mTileHeight;

	private final ArrayList<TMXTileSet> mTMXTileSets = new ArrayList<TMXTileSet>();
	private final ArrayList<TMXLayer> mTMXLayers = new ArrayList<TMXLayer>();
	private final ArrayList<TMXObjectGroup> mTMXObjectGroups = new ArrayList<TMXObjectGroup>();

	private final SparseArray<ITextureRegion> mGlobalTileIDToTextureRegionCache = new SparseArray<ITextureRegion>();
	private final SparseArray<TMXProperties<TMXTileProperty>> mGlobalTileIDToTMXTilePropertiesCache = new SparseArray<TMXProperties<TMXTileProperty>>();

	private final TMXProperties<TMXTiledMapProperty> mTMXTiledMapProperties = new TMXProperties<TMXTiledMapProperty>();

	// ===========================================================
	// Constructors
	// ===========================================================

	/* package */ TMXTiledMap(final Attributes pAttributes) {
		this.mOrientation = pAttributes.getValue("", TMXConstants.TAG_MAP_ATTRIBUTE_ORIENTATION);
		if(!this.mOrientation.equals(TMXConstants.TAG_MAP_ATTRIBUTE_ORIENTATION_VALUE_ORTHOGONAL)) {
			throw new IllegalArgumentException(TMXConstants.TAG_MAP_ATTRIBUTE_ORIENTATION + ": '" + this.mOrientation + "' is not supported.");
		}
		this.mTileColumns = SAXUtils.getIntAttributeOrThrow(pAttributes, TMXConstants.TAG_MAP_ATTRIBUTE_WIDTH);
		this.mTilesRows = SAXUtils.getIntAttributeOrThrow(pAttributes, TMXConstants.TAG_MAP_ATTRIBUTE_HEIGHT);
		this.mTileWidth = SAXUtils.getIntAttributeOrThrow(pAttributes, TMXConstants.TAG_MAP_ATTRIBUTE_TILEWIDTH);
		this.mTileHeight = SAXUtils.getIntAttributeOrThrow(pAttributes, TMXConstants.TAG_MAP_ATTRIBUTE_TILEHEIGHT);

		this.setSize(this.mTileColumns * this.mTileWidth, this.mTilesRows * this.mTileHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public String getOrientation() {
		return this.mOrientation;
	}

	public float getWidth() {
		return this.mTileColumns * this.mTileWidth;
	}

	public float getHeight() {
		return this.mTilesRows * this.mTileHeight;
	}

	public final int getTileColumns() {
		return this.mTileColumns;
	}

	public final int getTileRows() {
		return this.mTilesRows;
	}

	public final int getTileWidth() {
		return this.mTileWidth;
	}

	public final int getTileHeight() {
		return this.mTileHeight;
	}

	/* package */ void addTMXTileSet(final TMXTileSet pTMXTileSet) {
		this.mTMXTileSets.add(pTMXTileSet);
	}

	public ArrayList<TMXTileSet> getTMXTileSets() {
		return this.mTMXTileSets;
	}

	/* package */ void addTMXLayer(final TMXLayer pTMXLayer) {
		final int zIndex = this.mTMXLayers.size()*10;
		this.mTMXLayers.add(pTMXLayer);
		this.attachChild(pTMXLayer);
		pTMXLayer.setZIndex(zIndex);
	}

	public ArrayList<TMXLayer> getTMXLayers() {
		return this.mTMXLayers;
	}

	/* package */ void addTMXObjectGroup(final TMXObjectGroup pTMXObjectGroup) {
		this.mTMXObjectGroups.add(pTMXObjectGroup);
	}

	public ArrayList<TMXObjectGroup> getTMXObjectGroups() {
		return this.mTMXObjectGroups;
	}

	public TMXProperties<TMXTileProperty> getTMXTilePropertiesByGlobalTileID(final int pGlobalTileID) {
		return this.mGlobalTileIDToTMXTilePropertiesCache.get(pGlobalTileID);
	}

	public void addTMXTiledMapProperty(final TMXTiledMapProperty pTMXTiledMapProperty) {
		this.mTMXTiledMapProperties.add(pTMXTiledMapProperty);
	}

	public TMXProperties<TMXTiledMapProperty> getTMXTiledMapProperties() {
		return this.mTMXTiledMapProperties;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public TMXProperties<TMXTileProperty> getTMXTileProperties(final int pGlobalTileID) {
		final SparseArray<TMXProperties<TMXTileProperty>> globalTileIDToTMXTilePropertiesCache = this.mGlobalTileIDToTMXTilePropertiesCache;

		final TMXProperties<TMXTileProperty> cachedTMXTileProperties = globalTileIDToTMXTilePropertiesCache.get(pGlobalTileID);
		if(cachedTMXTileProperties != null) {
			return cachedTMXTileProperties;
		} else {
			final ArrayList<TMXTileSet> tmxTileSets = this.mTMXTileSets;

			for(int i = tmxTileSets.size() - 1; i >= 0; i--) {
				final TMXTileSet tmxTileSet = tmxTileSets.get(i);
				if(pGlobalTileID >= tmxTileSet.getFirstGlobalTileID()) {
					return tmxTileSet.getTMXTilePropertiesFromGlobalTileID(pGlobalTileID);
				}
			}
			//throw new IllegalArgumentException("No TMXTileProperties found for pGlobalTileID=" + pGlobalTileID);
			return null;
		}
	}

	public ITextureRegion getTextureRegionFromGlobalTileID(final int pGlobalTileID) {
		final SparseArray<ITextureRegion> globalTileIDToTextureRegionCache = this.mGlobalTileIDToTextureRegionCache;

		final ITextureRegion cachedTextureRegion = globalTileIDToTextureRegionCache.get(pGlobalTileID);
		if(cachedTextureRegion != null) {
			return cachedTextureRegion;
		} else {
			final ArrayList<TMXTileSet> tmxTileSets = this.mTMXTileSets;

			for(int i = tmxTileSets.size() - 1; i >= 0; i--) {
				final TMXTileSet tmxTileSet = tmxTileSets.get(i);
				if(pGlobalTileID >= tmxTileSet.getFirstGlobalTileID()) {
					final ITextureRegion textureRegion = tmxTileSet.getTextureRegionFromGlobalTileID(pGlobalTileID);
					/* Add to cache for the all future pGlobalTileIDs with the same value. */
					globalTileIDToTextureRegionCache.put(pGlobalTileID, textureRegion);
					return textureRegion;
				}
			}
			//throw new IllegalArgumentException("No TextureRegion found for pGlobalTileID=" + pGlobalTileID);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.andengine.extension.tiledmapinterfaces.IGameMap#getLayer(int)
	 */
	@Override
	public IGameLayer getLayer(int pLayer) {
		return this.mTMXLayers.get(pLayer);
	}

	/* (non-Javadoc)
	 * @see org.andengine.extension.tiledmapinterfaces.IGameMap#getLayerCount()
	 */
	@Override
	public int getLayerCount() {
		return this.mTMXLayers.size();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
