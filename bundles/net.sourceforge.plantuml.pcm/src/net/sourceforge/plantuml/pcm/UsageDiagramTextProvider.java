package net.sourceforge.plantuml.pcm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.module.palladio.basiccomponent.BasicComponentGenerator;
import org.eclipse.acceleo.module.palladio.usage.EntryLevelSystemCallGeneration;
import org.eclipse.acceleo.module.palladio.usage.UsageGeneration;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.palladiosimulator.pcm.usagemodel.impl.EntryLevelSystemCallImpl;
import org.palladiosimulator.pcm.usagemodel.impl.ScenarioBehaviourImpl;
import org.palladiosimulator.pcm.usagemodel.presentation.UsagemodelEditor;

public class UsageDiagramTextProvider extends AbstractPcmDiagramTextProvider{
	
	@Override
	public String getDiagramText(IEditorPart editorPart, ISelection selection, Map<String, Object> markerAttributes) {
		if(!(selection instanceof IStructuredSelection)) {
			return notSelectedText;
		} else {
			final Object sel = ((IStructuredSelection) selection).getFirstElement();
			if(sel instanceof ScenarioBehaviourImpl ) {
				return generateText((ScenarioBehaviourImpl) sel);
			}
			if(sel instanceof EntryLevelSystemCallImpl) {
				return generateText((EntryLevelSystemCallImpl) sel);
			}
		}
		return "";
	}
	private String generateText(EntryLevelSystemCallImpl sel) {
		File targetFolder = new File("transformationen");
		try {
			List<String> arguments = new ArrayList<String>();
			arguments.add(this.getModus().name());
			EntryLevelSystemCallGeneration generator = new EntryLevelSystemCallGeneration(sel, targetFolder, arguments);
			String text = transform(generator, targetFolder);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String generateText(ScenarioBehaviourImpl selection) {
		File targetFolder = new File("transformationen");
		try {
			List<String> arguments = new ArrayList<String>();
			arguments.add(this.getModus().name());
			UsageGeneration generator = new UsageGeneration(selection, targetFolder, arguments);
			String text = transform(generator, targetFolder);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
		if(sel instanceof EntryLevelSystemCallImpl) {
			return true;
		}
		return false;
	}
}
