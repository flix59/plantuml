package net.sourceforge.plantuml.pcm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Map;
import org.eclipse.acceleo.module.palladio.system.SystemGeneration;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.palladiosimulator.pcm.system.impl.SystemImpl;
import org.palladiosimulator.pcm.system.presentation.SystemEditor;

public class SystemDiagramTextProvider extends AbstractPcmDiagramTextProvider {

	private static final String notSelectedText = "@startuml \n title not Implemented yet\n @enduml";

	
	public SystemDiagramTextProvider() {
	}


	@Override
	public void initPartTypes() {
		// TODO Auto-generated method stub
		this.partTypes = new ArrayList<Class<?>>();
		this.partTypes.add(SystemEditor.class);
	}

	@Override 
	public String getDiagramText(IEditorPart editorPart, ISelection selection, Map<String, Object> markerAttributes) {
		if (!(selection instanceof IStructuredSelection)) {
			return notSelectedText;
		} else {
			final Object sel = ((IStructuredSelection) selection).getFirstElement();
			if (!(sel instanceof EObject) || isEcoreClassDiagramObject(sel)) {
				return notSelectedText;
			}
			if(sel instanceof SystemImpl) {
				getDiagramText((SystemImpl)sel);
			}

		}
		return notSelectedText;
	}
	
	private String getDiagramText(SystemImpl selection) {
		File targetFolder = new File("transformationen");
		try {
			SystemGeneration generator = new SystemGeneration(selection, targetFolder, new ArrayList<String>());
			return transform(generator, targetFolder);	
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Override
	public boolean supportsSelection(ISelection selection) {
		return true;
	}


}
