package org.eclipse.xtext.formatting2.internal.idea.lang.parser;

import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.formatting2.internal.idea.lang.FormatterTestLanguageElementTypeProvider;
import org.eclipse.xtext.formatting2.internal.idea.parser.antlr.internal.PsiInternalFormatterTestLanguageParser;
import org.eclipse.xtext.formatting2.internal.services.FormatterTestLanguageGrammarAccess;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;

public class FormatterTestLanguagePsiParser extends AbstractXtextPsiParser {

	@Inject 
	private FormatterTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private FormatterTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalFormatterTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

}