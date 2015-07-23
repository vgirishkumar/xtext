/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.idea.completion;

import com.google.inject.Inject;
import com.intellij.codeInsight.completion.AllClassesGetter;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.JavaClassNameCompletionContributor;
import com.intellij.codeInsight.completion.JavaCompletionSorting;
import com.intellij.codeInsight.completion.JavaPsiClassReferenceElement;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiModifier;
import com.intellij.util.Consumer;
import com.intellij.util.ProcessingContext;
import java.util.List;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.idea.lang.AbstractXtextLanguage;
import org.eclipse.xtext.psi.impl.BaseXtextFile;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.ReplaceRegion;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.idea.completion.XtypeCompletionContributor;
import org.eclipse.xtext.xbase.imports.RewritableImportSection;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xtype.XtypePackage;

@SuppressWarnings("all")
public class XbaseCompletionContributor extends XtypeCompletionContributor {
  public static class ImportAddingInsertHandler implements InsertHandler<JavaPsiClassReferenceElement> {
    @Inject
    private RewritableImportSection.Factory factory;
    
    @Override
    public void handleInsert(final InsertionContext context, final JavaPsiClassReferenceElement item) {
      final PsiFile file = context.getFile();
      if ((file instanceof BaseXtextFile)) {
        final XtextResource resource = ((BaseXtextFile)file).getResource();
        IResourceServiceProvider _resourceServiceProvider = resource.getResourceServiceProvider();
        IJvmTypeProvider.Factory _get = _resourceServiceProvider.<IJvmTypeProvider.Factory>get(IJvmTypeProvider.Factory.class);
        ResourceSet _resourceSet = resource.getResourceSet();
        final IJvmTypeProvider typeProvider = _get.findTypeProvider(_resourceSet);
        String _qualifiedName = item.getQualifiedName();
        final JvmType jvmType = typeProvider.findTypeByName(_qualifiedName);
        if ((jvmType instanceof JvmDeclaredType)) {
          final String simpleName = ((JvmDeclaredType)jvmType).getSimpleName();
          Document _document = context.getDocument();
          int _startOffset = context.getStartOffset();
          int _tailOffset = context.getTailOffset();
          _document.replaceString(_startOffset, _tailOffset, simpleName);
          final RewritableImportSection importSection = this.factory.parse(resource);
          boolean _addImport = importSection.addImport(((JvmDeclaredType)jvmType));
          if (_addImport) {
            final List<ReplaceRegion> regions = importSection.rewrite();
            for (final ReplaceRegion reg : regions) {
              Document _document_1 = context.getDocument();
              int _offset = reg.getOffset();
              int _endOffset = reg.getEndOffset();
              String _text = reg.getText();
              _document_1.replaceString(_offset, _endOffset, _text);
            }
          }
        } else {
          AllClassesGetter.INSERT_FQN.handleInsert(context, item);
        }
      } else {
        throw new IllegalStateException(("Not an Xtext psi file " + file));
      }
    }
  }
  
  @Inject
  private XbaseCompletionContributor.ImportAddingInsertHandler importAddingInsertHandler;
  
  public XbaseCompletionContributor(final AbstractXtextLanguage lang) {
    super(lang);
    this.completeJvmParameterizedTypeReference_Type();
    this.completeXImportDeclaration_ImportedType();
    this.completeXConstructorCall_Constructor();
    this.completeXTypeLiteral_Type();
  }
  
  protected void completeJvmParameterizedTypeReference_Type() {
    this.completeJavaTypes(TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE);
  }
  
  protected void completeXImportDeclaration_ImportedType() {
    final Function1<JavaPsiClassReferenceElement, Boolean> _function = new Function1<JavaPsiClassReferenceElement, Boolean>() {
      @Override
      public Boolean apply(final JavaPsiClassReferenceElement it) {
        return Boolean.valueOf(true);
      }
    };
    this.completeJavaTypes(XtypePackage.Literals.XIMPORT_DECLARATION__IMPORTED_TYPE, false, _function);
  }
  
  protected void completeXConstructorCall_Constructor() {
    final Function1<JavaPsiClassReferenceElement, Boolean> _function = new Function1<JavaPsiClassReferenceElement, Boolean>() {
      @Override
      public Boolean apply(final JavaPsiClassReferenceElement it) {
        boolean _xblockexpression = false;
        {
          final PsiClass type = it.getObject();
          boolean _and = false;
          boolean _hasModifierProperty = type.hasModifierProperty(PsiModifier.ABSTRACT);
          boolean _not = (!_hasModifierProperty);
          if (!_not) {
            _and = false;
          } else {
            boolean _isInterface = type.isInterface();
            boolean _not_1 = (!_isInterface);
            _and = _not_1;
          }
          _xblockexpression = _and;
        }
        return Boolean.valueOf(_xblockexpression);
      }
    };
    this.completeJavaTypes(XbasePackage.Literals.XCONSTRUCTOR_CALL__CONSTRUCTOR, true, _function);
  }
  
  protected void completeXTypeLiteral_Type() {
    this.completeJavaTypes(XbasePackage.Literals.XTYPE_LITERAL__TYPE);
  }
  
  protected void completeJavaTypes(final EReference reference) {
    this.completeJavaTypes(reference, true);
  }
  
  protected void completeJavaTypes(final EReference reference, final boolean addImport) {
    final Function1<JavaPsiClassReferenceElement, Boolean> _function = new Function1<JavaPsiClassReferenceElement, Boolean>() {
      @Override
      public Boolean apply(final JavaPsiClassReferenceElement it) {
        return Boolean.valueOf(true);
      }
    };
    this.completeJavaTypes(reference, addImport, _function);
  }
  
  protected void completeJavaTypes(final EReference reference, final boolean addImport, final Function1<? super JavaPsiClassReferenceElement, ? extends Boolean> filter) {
    final CompletionProvider<CompletionParameters> _function = new CompletionProvider<CompletionParameters>() {
      @Override
      protected void addCompletions(final CompletionParameters $0, final ProcessingContext $1, final CompletionResultSet $2) {
        XbaseCompletionContributor.this.completeJavaTypes($0, $2, addImport, filter);
      }
    };
    this.extend(CompletionType.BASIC, reference, _function);
  }
  
  protected void completeJavaTypes(final CompletionParameters completionParameters, final CompletionResultSet completionResultSet, final boolean addImport, final Function1<? super JavaPsiClassReferenceElement, ? extends Boolean> filter) {
    int _invocationCount = completionParameters.getInvocationCount();
    boolean _lessEqualsThan = (_invocationCount <= 2);
    CompletionResultSet _addJavaSorting = JavaCompletionSorting.addJavaSorting(completionParameters, completionResultSet);
    PrefixMatcher _prefixMatcher = _addJavaSorting.getPrefixMatcher();
    final Consumer<LookupElement> _function = new Consumer<LookupElement>() {
      @Override
      public void consume(final LookupElement it) {
        if ((it instanceof JavaPsiClassReferenceElement)) {
          Boolean _apply = filter.apply(((JavaPsiClassReferenceElement)it));
          if ((_apply).booleanValue()) {
            if (addImport) {
              ((JavaPsiClassReferenceElement)it).setInsertHandler(XbaseCompletionContributor.this.importAddingInsertHandler);
            }
            completionResultSet.addElement(it);
          }
        }
      }
    };
    JavaClassNameCompletionContributor.addAllClasses(completionParameters, _lessEqualsThan, _prefixMatcher, _function);
  }
}
