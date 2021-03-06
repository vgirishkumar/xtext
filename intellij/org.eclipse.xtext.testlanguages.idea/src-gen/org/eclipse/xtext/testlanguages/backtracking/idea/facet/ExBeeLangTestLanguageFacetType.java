package org.eclipse.xtext.testlanguages.backtracking.idea.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetTypeId;
import org.eclipse.xtext.idea.facet.AbstractFacetType;

public class ExBeeLangTestLanguageFacetType  extends AbstractFacetType<ExBeeLangTestLanguageFacetConfiguration> {

	public static final FacetTypeId<Facet<ExBeeLangTestLanguageFacetConfiguration>> TYPEID = new FacetTypeId<Facet<ExBeeLangTestLanguageFacetConfiguration>>("org.eclipse.xtext.testlanguages.backtracking.ExBeeLangTestLanguage");

	public ExBeeLangTestLanguageFacetType() {
		super(TYPEID, "org.eclipse.xtext.testlanguages.backtracking.ExBeeLangTestLanguage", "ExBeeLangTestLanguage");
		org.eclipse.xtext.testlanguages.backtracking.idea.lang.ExBeeLangTestLanguageLanguage.INSTANCE.injectMembers(this);
	}

}
