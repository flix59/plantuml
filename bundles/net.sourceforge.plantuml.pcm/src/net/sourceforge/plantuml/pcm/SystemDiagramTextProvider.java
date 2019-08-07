package net.sourceforge.plantuml.pcm;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.palladiosimulator.pcm.system.presentation.SystemEditor;

public class SystemDiagramTextProvider extends AbstractPcmDiagramTextProvider {

	private final String testDiagram = "@startuml\r\n" + 
			"actor Nutzer #green \r\n "+
			"Nutzer -> System: register()\r\n" + 
			"activate System \r\n " +
			"System -> UserManagement: register()\r\n" + 
			"activate UserManagement \r\n" + 
			"UserManagement -> UserDBAdapter:addUser()\r\n"+ 
			"activate UserDBAdapter \r\n" + 
			"UserDBAdapter -> DB: query()\r\n"
			+ "deactivate UserDBAdapter \r\n"
			+ "deactivate UserManagement \r\n"
			+ "deactivate System \r\n" + 
			"@enduml";
	
	public SystemDiagramTextProvider() {
		initEndings();
	}

	@Override
	public void initEndings() {
		this.pathEndings = new LinkedList<String>();
		this.pathEndings.add("system");
		this.pathEndings.add("composite_diagram");
	}

	@Override
	public void initPartTypes() {
		// TODO Auto-generated method stub
		this.partTypes = new ArrayList<Class<?>>();
		this.partTypes.add(SystemEditor.class);
	}

	@Override 
	public String getDiagramText(IEditorPart editorPart, ISelection selection, Map<String, Object> markerAttributes) {
		// here happens the transformation
		return testDiagram;
	}

	@Override
	public String getDiagramText(IPath path) {
		// TODO Auto-generated method stub
		return testDiagram;
	}


}
