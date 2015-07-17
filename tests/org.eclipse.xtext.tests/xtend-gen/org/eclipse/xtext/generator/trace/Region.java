/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.generator.trace;

import java.util.List;
import org.eclipse.xtext.generator.trace.AbstractTraceRegion;
import org.eclipse.xtext.generator.trace.ILocationData;
import org.eclipse.xtext.generator.trace.TraceRegion;

@SuppressWarnings("all")
public class Region extends TraceRegion {
  public Region(final int myOffset, final int myLength, final List<ILocationData> allLocationData, final AbstractTraceRegion parent) {
    super(myOffset, myLength, 0, 0, allLocationData, parent);
  }
  
  public Region(final int myOffset, final int myLength, final List<ILocationData> allLocationData) {
    super(myOffset, myLength, 0, 0, allLocationData, null);
  }
  
  public Region addChild(final int myOffset, final int myLength, final List<ILocationData> allLocationData) {
    final Region child = new Region(myOffset, myLength, allLocationData, this);
    return child;
  }
}
