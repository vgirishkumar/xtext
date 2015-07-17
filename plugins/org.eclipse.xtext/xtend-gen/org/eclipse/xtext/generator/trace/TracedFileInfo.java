/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.generator.trace;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class TracedFileInfo {
  private String text;
  
  private ITextRegion frame;
  
  @Pure
  public String getText() {
    return this.text;
  }
  
  public void setText(final String text) {
    this.text = text;
  }
  
  @Pure
  public ITextRegion getFrame() {
    return this.frame;
  }
  
  public void setFrame(final ITextRegion frame) {
    this.frame = frame;
  }
}
