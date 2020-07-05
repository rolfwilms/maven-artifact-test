package de.rwilms.maven_artifact_test.test;

import static org.junit.Assert.*;

import de.rwilms.maven_artifact_test.TestArtifactInclusion;

public class Test {

	@org.junit.Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		TestArtifactInclusion test = new TestArtifactInclusion();
		assertTrue(test.runTests());
	}

}
