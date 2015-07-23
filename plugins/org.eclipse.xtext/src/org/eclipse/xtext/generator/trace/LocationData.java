/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.generator.trace;

import org.eclipse.xtext.util.ITextRegionWithLineInformation;
import org.eclipse.xtext.util.TextRegionWithLineInformation;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class LocationData extends TextRegionWithLineInformation implements ILocationData {

	private final SourceRelativeURI path;
	
	public LocationData(int offset, int length, int lineNumber, int endLineNumber,  /* @Nullable */ SourceRelativeURI path) {
		super(offset, length, lineNumber, endLineNumber);
		this.path = path;
	}
	
	public LocationData(/* @NonNull */ ITextRegionWithLineInformation region,  /* @Nullable */ SourceRelativeURI path) {
		this(region.getOffset(), region.getLength(), region.getLineNumber(), region.getEndLineNumber(), path);
	}

	/* @Nullable */
	@Override
	public SourceRelativeURI getSrcRelativePath() {
		return path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(/* @Nullable */ Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationData other = (LocationData) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	/* @NonNull */
	public String toString() {
		return "LocationData [" + super.toString() + "][path=" + path + "]";
	}

}
