package de.rwilms.maven_artifact_test;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.DefaultArtifactFactory;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.handler.manager.ArtifactHandlerManager;
import org.apache.maven.artifact.handler.manager.DefaultArtifactHandlerManager;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;

public class TestArtifactInclusion {
	
	final DefaultArtifactFactory factory;
    final String[] scopes = new String[] {Artifact.SCOPE_COMPILE, Artifact.SCOPE_COMPILE_PLUS_RUNTIME, Artifact.SCOPE_IMPORT, Artifact.SCOPE_PROVIDED, Artifact.SCOPE_RUNTIME, Artifact.SCOPE_RUNTIME_PLUS_SYSTEM, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_TEST};
	
	public TestArtifactInclusion() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		factory = createArtifactFactory();
	}
	
	private DefaultArtifactFactory createArtifactFactory() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		DefaultArtifactFactory factory = new DefaultArtifactFactory();
		ArtifactHandlerManager artifactHandlerManager = new DefaultArtifactHandlerManager();
		Field f = factory.getClass().getDeclaredField("artifactHandlerManager");
		f.setAccessible(true);
		f.set(factory, artifactHandlerManager);
		f.setAccessible(false);

		f = artifactHandlerManager.getClass().getDeclaredField("artifactHandlers");
		f.setAccessible(true);
		f.set(artifactHandlerManager, new HashMap<String, ArtifactHandler>());
		f.setAccessible(false);

		return factory;
	}

	
	private void testArtifactInclusion(String scope, String artifactScope) {
	    ScopeArtifactFilter filter = new ScopeArtifactFilter(scope);
        Artifact artifact = factory.createProjectArtifact("test", "test", "0.0.1", artifactScope);
        System.out.println(filter.getScope()+"\t" + artifactScope + "\t" + (filter.include(artifact) ? "YES" : "NO"));
	}
	
	public boolean runTests() {
		System.out.println("Scope\tArtifact Scope\tIncluded");
		String scope = Artifact.SCOPE_RUNTIME;
		for(String artifactScope : scopes) {
			testArtifactInclusion(scope, artifactScope);
		}
		return true;
	}
	
	public static void main(String[] args) {
		try {
			new TestArtifactInclusion().runTests();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
