package net.sourceforge.plantuml.pcm;

import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;

import net.sourceforge.plantuml.text.AbstractDiagramTextProvider;

import net.sourceforge.plantuml.util.DiagramModus;

public class PcmDiagramTextProvider extends  AbstractDiagramTextProvider {

	private final String testDiagram = "@startuml\r\n" + 
			"actor Nutzer #green \r\n "+
			"Nutzer -> Facade: register()\r\n" + 
			"activate Facade \r\n " +
			"Facade -> UserManagement: register()\r\n" + 
			"activate UserManagement \r\n" + 
			"UserManagement -> UserDBAdapter:addUser()\r\n"+ 
			"activate UserDBAdapter \r\n" + 
			"UserDBAdapter -> DB: query()\r\n"
			+ "deactivate UserDBAdapter \r\n"
			+ "deactivate UserManagement \r\n"
			+ "deactivate Facade \r\n" + 
			"@enduml";
	
	private final DiagramModus modus = DiagramModus.SEQUENZDIAGRAMM;
	
	public DiagramModus getModus() {
		return modus;
	}	

	@Override
	public boolean supportsSelection(ISelection selection) {
		return true;
	}
	
	@Override
	public boolean supportsView(IViewPart part) {
		return true;
	}

	@Override
	public boolean supportsEditor(final IEditorPart editorPart) {
		return true;
	}
	
	@Override
	public String getDiagramText(final IEditorPart editorPart, final ISelection selection) {
		return this.testDiagram;
	}
	
	@Override
	public String getDiagramText(final IEditorPart editorPart, final ISelection selection, final Map<String, Object> markerAttributes) {
		return this.testDiagram;
	}
}
