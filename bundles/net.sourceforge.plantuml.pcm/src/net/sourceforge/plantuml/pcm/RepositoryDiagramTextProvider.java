package net.sourceforge.plantuml.pcm;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.m2m.qvt.oml.blackbox.java.Parameter;
//import org.palladiosimulator.pcm.repository.util.RepositoryResourceImpl;

public class RepositoryDiagramTextProvider extends AbstractPcmDiagramTextProvider {

	private final String testDiagram = "@startuml\r\n" + "actor Nutzer #green \r\n "
			+ "Nutzer -> Facade: register()\r\n" + "activate Facade \r\n " + "Facade -> UserManagement: register()\r\n"
			+ "activate UserManagement \r\n" + "UserManagement -> UserDBAdapter:addUser()\r\n"
			+ "activate UserDBAdapter \r\n" + "UserDBAdapter -> DB: query()\r\n" + "deactivate UserDBAdapter \r\n"
			+ "deactivate UserManagement \r\n" + "deactivate Facade \r\n" + "@enduml";

	public RepositoryDiagramTextProvider() {
		initEndings();
	}

	@Override
	public void initEndings() {
		this.pathEndings = new LinkedList<String>();
		this.pathEndings.add("repository");
		this.pathEndings.add("repository_diagram");
	}

	@Override
	public boolean supportsView(final IViewPart viewPart) {
		return true;
	}

	@Override
	public boolean supportsPath(IPath path) {
		System.out.println(this.getClass().toString() + ": " + path.toString());
		if (pathEndings == null) {
			this.initEndings();
		}
		return pathEndings.stream().anyMatch(ending -> ending.equals(path.getFileExtension()));
	}

	@Override
	public boolean supportsSelection(ISelection selection) {
		IStructuredSelection strucSel = (IStructuredSelection) selection;
		Object sel = strucSel.getFirstElement();
		if (sel == null) {
			return false;
		}
		String scn = sel.getClass().getSimpleName();
		if (scn.equals("RepositoryDemandingSEFF")) {
			System.out.println("buja");
			return true;
		};
		return false;
	}

	@Override
	public boolean supportsEditor(final IEditorPart editorPart) {
		String title = editorPart.getTitle();
		if (editorPart.getTitle().equals("repository"))
			;
		/*
		 * if (partType != null && (! (partType.isInstance(editorPart)))) { return
		 * false; }
		 */
		return true;
	}

	@Override
	public String getDiagramText(IPath path) {
		return testDiagram;
	}

	@Override
	public String getDiagramText(IEditorPart editorPart, ISelection selection, Map<String, Object> markerAttributes) {
		// here happens the transformation
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection strucSel = (IStructuredSelection) selection;
			System.out.print(strucSel.getFirstElement().toString());
		}
		return testDiagram;
	}

}
