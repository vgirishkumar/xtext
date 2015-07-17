/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.generator.trace;

import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.generator.trace.AbstractTraceRegion;
import org.eclipse.xtext.generator.trace.AbstractTraceRegionToString;
import org.eclipse.xtext.generator.trace.TracedFileInfo;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@Accessors
@SuppressWarnings("all")
public class TraceRegionToString extends AbstractTraceRegionToString {
  private final Map<URI, TracedFileInfo> fileInfo = CollectionLiterals.<URI, TracedFileInfo>newLinkedHashMap();
  
  private AbstractTraceRegion trace;
  
  private URI localURI;
  
  protected TracedFileInfo getFileInfo(final URI uri) {
    return this.fileInfo.get(uri);
  }
  
  @Override
  public String getText(final URI uri) {
    TracedFileInfo _fileInfo = this.getFileInfo(uri);
    return _fileInfo.getText();
  }
  
  @Override
  protected ITextRegion getFrame(final URI uri) {
    TracedFileInfo _fileInfo = this.getFileInfo(uri);
    return _fileInfo.getFrame();
  }
  
  @Pure
  public Map<URI, TracedFileInfo> getFileInfo() {
    return this.fileInfo;
  }
  
  @Pure
  public AbstractTraceRegion getTrace() {
    return this.trace;
  }
  
  public void setTrace(final AbstractTraceRegion trace) {
    this.trace = trace;
  }
  
  @Pure
  public URI getLocalURI() {
    return this.localURI;
  }
  
  public void setLocalURI(final URI localURI) {
    this.localURI = localURI;
  }
}
