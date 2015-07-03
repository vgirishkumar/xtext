package org.eclipse.xtext.idea.sdomain.idea.tests.generator

import org.eclipse.xtext.idea.sdomain.idea.tests.containers.LightSdomainTestCase
import com.google.common.io.CharStreams
import java.io.InputStreamReader
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainLanguage
import org.eclipse.xtext.idea.facet.FacetProvider
import com.intellij.openapi.application.ApplicationManager

class GeneratorOptionsTest extends LightSdomainTestCase {

	def void testGenerationWorks() {
		val sourceFile = myFixture.addFileToProject("MyFile.sdomain",'''
			foo.bar {
				entity Huhu {
				}
			}
		''')
		val file = myFixture.findFileInTempDir("src-gen/MyFile.txt")
		assertNotNull(file)
		val content = CharStreams.toString(new InputStreamReader(file.inputStream))
		val facet = SDomainLanguage.INSTANCE.getInstance(FacetProvider).getFacet(myFixture.module)
		
		// disable generator and change code
		facet.configuration.state.activated = false
		ApplicationManager.application.runWriteAction[
			sourceFile.virtualFile.binaryContent = '''
			'''.toString.getBytes()
		]
		val afterFile = myFixture.findFileInTempDir("src-gen/MyFile.txt")
		assertNotNull(afterFile)
		val afterContent = CharStreams.toString(new InputStreamReader(file.inputStream))
		assertEquals(content, afterContent)
		
		// enable generator again
		facet.configuration.state.activated = true
		ApplicationManager.application.runWriteAction[
			sourceFile.virtualFile.binaryContent = '''
				foo.bar {
					entity Huhu2 {
					}
				}
			'''.toString.getBytes()
		]
		val afterReactivateFile = myFixture.findFileInTempDir("src-gen/MyFile.txt")
		assertNotNull(afterReactivateFile)
		val afterReactivateContent = CharStreams.toString(new InputStreamReader(file.inputStream))
		assertTrue(afterReactivateContent, afterReactivateContent.contains("Huhu2"))
	}

}