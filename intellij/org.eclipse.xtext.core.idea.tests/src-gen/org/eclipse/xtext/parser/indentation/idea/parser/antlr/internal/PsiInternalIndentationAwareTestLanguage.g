/*
 * generated by Xtext
 */
grammar PsiInternalIndentationAwareTestLanguage;

options {
	superClass=AbstractPsiAntlrParser;
}

@lexer::header {
package org.eclipse.xtext.parser.indentation.idea.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.xtext.parser.indentation.idea.parser.antlr.internal;

import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import org.eclipse.xtext.parser.indentation.idea.lang.IndentationAwareTestLanguageElementTypeProvider;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.indentation.services.IndentationAwareTestLanguageGrammarAccess;

import com.intellij.lang.PsiBuilder;
}

@parser::members {

	protected IndentationAwareTestLanguageGrammarAccess grammarAccess;

	protected IndentationAwareTestLanguageElementTypeProvider elementTypeProvider;

	public PsiInternalIndentationAwareTestLanguageParser(PsiBuilder builder, TokenStream input, IndentationAwareTestLanguageElementTypeProvider elementTypeProvider, IndentationAwareTestLanguageGrammarAccess grammarAccess) {
		this(input);
		setPsiBuilder(builder);
    	this.grammarAccess = grammarAccess;
		this.elementTypeProvider = elementTypeProvider;
	}

	@Override
	protected String getFirstRuleName() {
		return "Tree";
	}

}

//Entry rule entryRuleTree
entryRuleTree:
	{ markComposite(elementTypeProvider.getTreeElementType()); }
	ruleTree
	EOF;

// Rule Tree
ruleTree:
	(
		(
			{
				precedeComposite(elementTypeProvider.getTree_TreeAction_0ElementType());
				doneComposite();
			}
		)
		(
			(
				{
					markComposite(elementTypeProvider.getTree_NodesTreeNodeParserRuleCall_1_0ElementType());
				}
				lv_nodes_1_0=ruleTreeNode
				{
					doneComposite();
				}
			)
		)*
	)
;

//Entry rule entryRuleTreeNode
entryRuleTreeNode:
	{ markComposite(elementTypeProvider.getTreeNodeElementType()); }
	ruleTreeNode
	EOF;

// Rule TreeNode
ruleTreeNode:
	(
		(
			(
				{
					markLeaf(elementTypeProvider.getTreeNode_NameIDTerminalRuleCall_0_0ElementType());
				}
				lv_name_0_0=RULE_ID
				{
					doneLeaf(lv_name_0_0);
				}
			)
		)
		(
			{
				markLeaf(elementTypeProvider.getTreeNode_INDENTTerminalRuleCall_1_0ElementType());
			}
			this_INDENT_1=RULE_INDENT
			{
				doneLeaf(this_INDENT_1);
			}
			(
				(
					{
						markComposite(elementTypeProvider.getTreeNode_ChildrenTreeNodeParserRuleCall_1_1_0ElementType());
					}
					lv_children_2_0=ruleTreeNode
					{
						doneComposite();
					}
				)
			)*
			{
				markLeaf(elementTypeProvider.getTreeNode_DEDENTTerminalRuleCall_1_2ElementType());
			}
			this_DEDENT_3=RULE_DEDENT
			{
				doneLeaf(this_DEDENT_3);
			}
		)?
	)
;

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))*;

RULE_INDENT : 'synthetic:INDENT';

RULE_DEDENT : 'synthetic:DEDENT';

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
