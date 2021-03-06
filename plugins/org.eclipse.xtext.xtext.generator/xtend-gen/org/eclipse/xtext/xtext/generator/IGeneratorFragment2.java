/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xtext.generator;

import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.xtext.xtext.generator.IGuiceAwareGeneratorComponent;
import org.eclipse.xtext.xtext.generator.LanguageConfig2;
import org.eclipse.xtext.xtext.generator.XtextGenerator;

/**
 * A fragment that contributes to the {@link XtextGenerator}.
 */
@SuppressWarnings("all")
public interface IGeneratorFragment2 extends IGuiceAwareGeneratorComponent {
  public abstract void checkConfiguration(final XtextGenerator generator, final Issues issues);
  
  public abstract void generate(final LanguageConfig2 language);
}
