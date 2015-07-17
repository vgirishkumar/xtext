/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.generator.trace;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.generator.trace.TraceRegionToString;
import org.eclipse.xtext.generator.trace.TracedFileInfo;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class TraceRegionToStringTester extends TraceRegionToString {
  private final URI uri1 = URI.createURI("remote1");
  
  private final URI uri2 = URI.createURI("remote2");
  
  private String local;
  
  private String remote1;
  
  private String remote2;
  
  private ITextRegion localFrame;
  
  public TraceRegionToStringTester() {
    URI _createURI = URI.createURI("local1");
    this.setLocalURI(_createURI);
    this.setShowLegend(false);
  }
  
  @Override
  protected TracedFileInfo getFileInfo(final URI uri) {
    TracedFileInfo _switchResult = null;
    boolean _matched = false;
    if (!_matched) {
      URI _localURI = this.getLocalURI();
      if (Objects.equal(uri, _localURI)) {
        _matched=true;
        TracedFileInfo _tracedFileInfo = new TracedFileInfo();
        final Procedure1<TracedFileInfo> _function = new Procedure1<TracedFileInfo>() {
          @Override
          public void apply(final TracedFileInfo it) {
            String _trim = TraceRegionToStringTester.this.local.trim();
            it.setText(_trim);
            it.setFrame(TraceRegionToStringTester.this.localFrame);
          }
        };
        _switchResult = ObjectExtensions.<TracedFileInfo>operator_doubleArrow(_tracedFileInfo, _function);
      }
    }
    if (!_matched) {
      if (Objects.equal(uri, this.uri1)) {
        _matched=true;
        TracedFileInfo _tracedFileInfo_1 = new TracedFileInfo();
        final Procedure1<TracedFileInfo> _function_1 = new Procedure1<TracedFileInfo>() {
          @Override
          public void apply(final TracedFileInfo it) {
            String _trim = TraceRegionToStringTester.this.remote1.trim();
            it.setText(_trim);
          }
        };
        _switchResult = ObjectExtensions.<TracedFileInfo>operator_doubleArrow(_tracedFileInfo_1, _function_1);
      }
    }
    if (!_matched) {
      if (Objects.equal(uri, this.uri2)) {
        _matched=true;
        TracedFileInfo _tracedFileInfo_2 = new TracedFileInfo();
        final Procedure1<TracedFileInfo> _function_2 = new Procedure1<TracedFileInfo>() {
          @Override
          public void apply(final TracedFileInfo it) {
            String _trim = TraceRegionToStringTester.this.remote2.trim();
            it.setText(_trim);
          }
        };
        _switchResult = ObjectExtensions.<TracedFileInfo>operator_doubleArrow(_tracedFileInfo_2, _function_2);
      }
    }
    return _switchResult;
  }
  
  @Pure
  public URI getUri1() {
    return this.uri1;
  }
  
  @Pure
  public URI getUri2() {
    return this.uri2;
  }
  
  @Pure
  public String getLocal() {
    return this.local;
  }
  
  public void setLocal(final String local) {
    this.local = local;
  }
  
  @Pure
  public String getRemote1() {
    return this.remote1;
  }
  
  public void setRemote1(final String remote1) {
    this.remote1 = remote1;
  }
  
  @Pure
  public String getRemote2() {
    return this.remote2;
  }
  
  public void setRemote2(final String remote2) {
    this.remote2 = remote2;
  }
  
  @Pure
  public ITextRegion getLocalFrame() {
    return this.localFrame;
  }
  
  public void setLocalFrame(final ITextRegion localFrame) {
    this.localFrame = localFrame;
  }
}
