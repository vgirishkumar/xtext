/*
 * generated by Xtext
 */
grammar PsiInternalTerminalRulesTestLanguage;

options {
	superClass=AbstractPsiAntlrParser;
}

@lexer::header {
package org.eclipse.xtext.parser.terminalrules.idea.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.xtext.parser.terminalrules.idea.parser.antlr.internal;

import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.parser.terminalrules.idea.lang.TerminalRulesTestLanguageElementTypeProvider;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;
import org.eclipse.xtext.parser.terminalrules.services.TerminalRulesTestLanguageGrammarAccess;

import com.intellij.lang.PsiBuilder;
}

@parser::members {

	protected TerminalRulesTestLanguageGrammarAccess grammarAccess;

	protected TerminalRulesTestLanguageElementTypeProvider elementTypeProvider;

	public PsiInternalTerminalRulesTestLanguageParser(PsiBuilder builder, TokenStream input, TerminalRulesTestLanguageElementTypeProvider elementTypeProvider, TerminalRulesTestLanguageGrammarAccess grammarAccess) {
		this(input);
		setPsiBuilder(builder);
    	this.grammarAccess = grammarAccess;
		this.elementTypeProvider = elementTypeProvider;
	}

	@Override
	protected String getFirstRuleName() {
		return "Model";
	}

}

//Entry rule entryRuleModel
entryRuleModel:
	{ markComposite(elementTypeProvider.getModelElementType()); }
	ruleModel
	{ doneComposite(); }
	EOF;
finally {
}

// Rule Model
ruleModel:
	(
		(
			(
				{
					markLeaf();
				}
				lv_idValue_0_0=RULE_ID
				{
					doneLeaf(lv_idValue_0_0, elementTypeProvider.getModel_IdValueIDTerminalRuleCall_0_0ElementType());
				}
			)
		)
		    |
		(
			(
				{
					markLeaf();
				}
				lv_intValue_1_0=RULE_INT
				{
					doneLeaf(lv_intValue_1_0, elementTypeProvider.getModel_IntValueINTTerminalRuleCall_1_0ElementType());
				}
			)
		)
		    |
		(
			(
				{
					markLeaf();
				}
				lv_stringValue_2_0=RULE_STRING
				{
					doneLeaf(lv_stringValue_2_0, elementTypeProvider.getModel_StringValueSTRINGTerminalRuleCall_2_0ElementType());
				}
			)
		)
		    |
		(
			(
				{
					markLeaf();
				}
				lv_richStringValue_3_0=RULE_RICH_STRING
				{
					doneLeaf(lv_richStringValue_3_0, elementTypeProvider.getModel_RichStringValueRICH_STRINGTerminalRuleCall_3_0ElementType());
				}
			)
		)
		    |
		(
			(
				{
					markLeaf();
				}
				lv_mlCommentValue_4_0=RULE_ML_COMMENT
				{
					doneLeaf(lv_mlCommentValue_4_0, elementTypeProvider.getModel_MlCommentValueML_COMMENTTerminalRuleCall_4_0ElementType());
				}
			)
		)
		    |
		(
			(
				{
					markLeaf();
				}
				lv_slCommentValue_5_0=RULE_SL_COMMENT
				{
					doneLeaf(lv_slCommentValue_5_0, elementTypeProvider.getModel_SlCommentValueSL_COMMENTTerminalRuleCall_5_0ElementType());
				}
			)
		)
		    |
		(
			(
				{
					markLeaf();
				}
				lv_wsValue_6_0=RULE_WS
				{
					doneLeaf(lv_wsValue_6_0, elementTypeProvider.getModel_WsValueWSTerminalRuleCall_6_0ElementType());
				}
			)
		)
		    |
		(
			(
				{
					markLeaf();
				}
				lv_anyValue_7_0=RULE_ANY_OTHER
				{
					doneLeaf(lv_anyValue_7_0, elementTypeProvider.getModel_AnyValueANY_OTHERTerminalRuleCall_7_0ElementType());
				}
			)
		)
	)
;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' (RULE_ESCAPED_CHAR|~(('\\'|'"')))* '"'|'\'' (RULE_ESCAPED_CHAR|~(('\\'|'\'')))* '\'');

RULE_RICH_STRING : '\'\'\'' RULE_IN_RICH_STRING* ('\'\'\''|('\'' '\''?)? EOF);

fragment RULE_IN_RICH_STRING : ('\'\'' ~(('\u00AB'|'\''))|'\'' ~(('\u00AB'|'\''))|~(('\u00AB'|'\'')));

fragment RULE_ESCAPED_CHAR : '\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;