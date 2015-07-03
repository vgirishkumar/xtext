package org.eclipse.xtext.idea.sdomain.idea.tests.generator;

import com.google.common.io.CharStreams;
import com.intellij.facet.Facet;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import java.io.InputStream;
import java.io.InputStreamReader;
import junit.framework.TestCase;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.idea.facet.AbstractFacetConfiguration;
import org.eclipse.xtext.idea.facet.FacetProvider;
import org.eclipse.xtext.idea.facet.GeneratorConfigurationState;
import org.eclipse.xtext.idea.sdomain.idea.lang.SDomainLanguage;
import org.eclipse.xtext.idea.sdomain.idea.tests.containers.LightSdomainTestCase;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class GeneratorOptionsTest extends LightSdomainTestCase {
  public void testGenerationWorks() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("foo.bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("entity Huhu {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final PsiFile sourceFile = this.myFixture.addFileToProject("MyFile.sdomain", _builder.toString());
      final VirtualFile file = this.myFixture.findFileInTempDir("src-gen/MyFile.txt");
      TestCase.assertNotNull(file);
      InputStream _inputStream = file.getInputStream();
      InputStreamReader _inputStreamReader = new InputStreamReader(_inputStream);
      final String content = CharStreams.toString(_inputStreamReader);
      FacetProvider _instance = SDomainLanguage.INSTANCE.<FacetProvider>getInstance(FacetProvider.class);
      Module _module = this.myFixture.getModule();
      final Facet<? extends AbstractFacetConfiguration> facet = _instance.getFacet(_module);
      AbstractFacetConfiguration _configuration = facet.getConfiguration();
      GeneratorConfigurationState _state = _configuration.getState();
      _state.setActivated(false);
      Application _application = ApplicationManager.getApplication();
      final Runnable _function = new Runnable() {
        @Override
        public void run() {
          try {
            VirtualFile _virtualFile = sourceFile.getVirtualFile();
            StringConcatenation _builder = new StringConcatenation();
            String _string = _builder.toString();
            byte[] _bytes = _string.getBytes();
            _virtualFile.setBinaryContent(_bytes);
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      _application.runWriteAction(_function);
      final VirtualFile afterFile = this.myFixture.findFileInTempDir("src-gen/MyFile.txt");
      TestCase.assertNotNull(afterFile);
      InputStream _inputStream_1 = file.getInputStream();
      InputStreamReader _inputStreamReader_1 = new InputStreamReader(_inputStream_1);
      final String afterContent = CharStreams.toString(_inputStreamReader_1);
      TestCase.assertEquals(content, afterContent);
      AbstractFacetConfiguration _configuration_1 = facet.getConfiguration();
      GeneratorConfigurationState _state_1 = _configuration_1.getState();
      _state_1.setActivated(true);
      Application _application_1 = ApplicationManager.getApplication();
      final Runnable _function_1 = new Runnable() {
        @Override
        public void run() {
          try {
            VirtualFile _virtualFile = sourceFile.getVirtualFile();
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("foo.bar {");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("entity Huhu2 {");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("}");
            _builder.newLine();
            String _string = _builder.toString();
            byte[] _bytes = _string.getBytes();
            _virtualFile.setBinaryContent(_bytes);
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      _application_1.runWriteAction(_function_1);
      final VirtualFile afterReactivateFile = this.myFixture.findFileInTempDir("src-gen/MyFile.txt");
      TestCase.assertNotNull(afterReactivateFile);
      InputStream _inputStream_2 = file.getInputStream();
      InputStreamReader _inputStreamReader_2 = new InputStreamReader(_inputStream_2);
      final String afterReactivateContent = CharStreams.toString(_inputStreamReader_2);
      boolean _contains = afterReactivateContent.contains("Huhu2");
      TestCase.assertTrue(afterReactivateContent, _contains);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
