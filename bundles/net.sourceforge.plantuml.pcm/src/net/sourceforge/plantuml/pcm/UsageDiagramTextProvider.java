package net.sourceforge.plantuml.pcm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.acceleo.module.palladio.basiccomponent.BasicComponentGenerator;
import org.eclipse.acceleo.module.palladio.usage.UsageGeneration;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.palladiosimulator.pcm.usagemodel.impl.ScenarioBehaviourImpl;
import org.palladiosimulator.pcm.usagemodel.presentation.UsagemodelEditor;

public class UsageDiagramTextProvider extends AbstractPcmDiagramTextProvider{

	@Override
	public String getDiagramText(IPath path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getDiagramText(IEditorPart editorPart, ISelection selection, Map<String, Object> markerAttributes) {
		if(!(selection instanceof IStructuredSelection)) {
			return notSelectedText;
		} else {
			final Object sel = ((IStructuredSelection) selection).getFirstElement();
			if(sel instanceof ScenarioBehaviourImpl ) {
				return generateText((ScenarioBehaviourImpl) sel);
			}
		}
		return "";
	}
	private String generateText(ScenarioBehaviourImpl selection) {
		File targetFolder = new File("transformationen");
		try {
			UsageGeneration generator = new UsageGeneration(selection, targetFolder, new ArrayList<String>());
			String text = transform(generator, targetFolder);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void initEndings() {
		this.pathEndings = new LinkedList<String>();
		this.pathEndings.add("usagemodel");
		this.pathEndings.add("usagemodel_diagram");
	}

	@Override
	public void initPartTypes() {
		this.partTypes = new ArrayList<Class<?>>();
		this.partTypes.add(UsagemodelEditor.class);
	}
	@Override
	public boolean supportsSelection(ISelection selection) {
		IStructuredSelection strucSel = (IStructuredSelection) selection;
		Object sel = strucSel.getFirstElement();
		if(sel instanceof ScenarioBehaviourImpl) {
			return true;
		}
		return false;
	}
}
