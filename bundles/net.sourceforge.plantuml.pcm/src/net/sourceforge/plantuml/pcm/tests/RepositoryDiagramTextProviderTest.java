package net.sourceforge.plantuml.pcm.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.repository.impl.BasicComponentImpl;
import org.palladiosimulator.pcm.seff.impl.ResourceDemandingSEFFImpl;

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
//		BasicComponentImpl seff = new BasicComponentImpl();
//		TreeSelection selection = new TreeSelection();
//		selection
//		provider.supportsSelection(null);
	}
}
