package net.sourceforge.plantuml.pcm.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sourceforge.plantuml.pcm.RepositoryDiagramTextProvider;

class RepositoryDiagramTextProviderTest {
	
	RepositoryDiagramTextProvider provider;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		provider  = new RepositoryDiagramTextProvider();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void initEndingsTest() {
		assertFalse(provider.pathEndings == null);
	}
	
	@Test
	void initPartTypes() {
		assertFalse(provider.partTypes == null);
	}
	
	@Test
	void supportSelection() {
		provider.supportsSelection(null);
	}
	
	@Test
	void generateTextTest() {
	}

}
