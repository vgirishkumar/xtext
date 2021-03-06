package org.eclipse.xtext.testlanguages.backtracking.idea.lang.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.antlr.runtime.TokenStream;
import org.eclipse.xtext.idea.parser.AbstractXtextPsiParser;
import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.testlanguages.backtracking.idea.lang.SimpleBeeLangTestLanguageElementTypeProvider;
import org.eclipse.xtext.testlanguages.backtracking.idea.parser.antlr.internal.PsiInternalSimpleBeeLangTestLanguageParser;
import org.eclipse.xtext.testlanguages.backtracking.services.SimpleBeeLangTestLanguageGrammarAccess;

import com.google.inject.Inject;
import com.intellij.lang.PsiBuilder;

public class SimpleBeeLangTestLanguagePsiParser extends AbstractXtextPsiParser {

	private static final Set<String> INITIAL_HIDDEN_TOKENS = new HashSet<String>(Arrays.asList("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT"));

	@Inject 
	private SimpleBeeLangTestLanguageGrammarAccess grammarAccess;

	@Inject 
	private SimpleBeeLangTestLanguageElementTypeProvider elementTypeProvider;

	@Override
	protected AbstractPsiAntlrParser createParser(PsiBuilder builder, TokenStream tokenStream) {
		return new PsiInternalSimpleBeeLangTestLanguageParser(builder, tokenStream, elementTypeProvider, grammarAccess);
	}

	@Override
	protected Set<String> getInitialHiddenTokens() {
		return INITIAL_HIDDEN_TOKENS;
	}

}
