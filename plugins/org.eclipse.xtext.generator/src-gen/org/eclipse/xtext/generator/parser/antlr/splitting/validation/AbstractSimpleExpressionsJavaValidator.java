/*
* generated by Xtext
*/
package org.eclipse.xtext.generator.parser.antlr.splitting.validation;
 
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;

public class AbstractSimpleExpressionsJavaValidator extends org.eclipse.xtext.validation.AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(org.eclipse.xtext.generator.parser.antlr.splitting.simpleExpressions.SimpleExpressionsPackage.eINSTANCE);
		return result;
	}

}
