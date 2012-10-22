package org.andengine.extension.tmx;

import java.util.ArrayList;

import org.andengine.extension.tmx.util.constants.TMXConstants;

/**
 * TMXProperties keeps the properties of a TMX class type.
 * 
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @author Sergio Rodriguez Lumley
 * @since 10:14:06 - 27.07.2010
 */
public class TMXProperties<T extends TMXProperty> extends ArrayList<T> implements TMXConstants {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = -836172859725272883L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	/**
	 * Returns wether a TMXProperties object contains the specified TMXProperty, defined by name
	 * @param pName Name of the property
	 * @param pValue Value to test if matches the value of the property named by name
	 * @return true if TMXProperties has the specified object and it has the specified value
	 */
	public boolean containsTMXProperty(final String pName, final String pValue) {
		for(int i = this.size() - 1; i >= 0; i--) {
			final T tmxProperty = this.get(i);
			if(tmxProperty.getName().equals(pName) && tmxProperty.getValue().equals(pValue)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the property value that corresponds to the specified tile
	 * @param pName String with the name of the property to test
	 * @return String with the value of the property
	 */
	public String getTMXPropertyValue(final String pName){
		String value = null;
		for(int i = this.size() - 1; i >= 0 && value == null; --i) {
			final T tmxProperty = this.get(i);
			if(tmxProperty.getName().equals(pName)) {
				value = tmxProperty.getValue();
			}
		}
		return value;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
