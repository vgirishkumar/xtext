/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.generator.trace;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.generator.trace.LocationData;

@SuppressWarnings("all")
public class Location extends LocationData {
  public Location(final int offset, final int length, final URI path) {
    super(offset, length, 0, 0, path);
  }
  
  public Location(final int offset, final int length) {
    this(offset, length, null);
  }
}
