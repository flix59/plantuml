package net.sourceforge.plantuml.pcm;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.ecore.AbstractEcoreClassDiagramTextProvider;

import net.sourceforge.plantuml.util.DiagramModus;

public abstract class AbstractPcmDiagramTextProvider extends  AbstractEcoreClassDiagramTextProvider implements DiagramTextProvider2 {
	
	public List<String> pathEndings;
	private final DiagramModus modus = DiagramModus.SEQUENZDIAGRAMM;
	
	public DiagramModus getModus() {
		return modus;
	}	
	public AbstractPcmDiagramTextProvider() {
		super(IEditingDomainProvider.class);
	}
	
	public AbstractPcmDiagramTextProvider(final Class<?> editorType) {
		super(editorType);
	}
	
	public abstract void initEndings();




	@Override
	public abstract String getDiagramText(IPath path);
}
