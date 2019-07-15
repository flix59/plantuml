package net.sourceforge.plantuml.pcm;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.ecore.AbstractEcoreClassDiagramTextProvider;

import net.sourceforge.plantuml.util.DiagramModus;

public abstract class AbstractPcmDiagramTextProvider extends  AbstractEcoreClassDiagramTextProvider implements DiagramTextProvider2 {
	
	public List<String> pathEndings;
	private final DiagramModus modus = DiagramModus.SEQUENZDIAGRAMM;
	
	public DiagramModus getModus() {
		return modus;
	}	
	
	public abstract void initEndings();

	@Override
	public boolean supportsPath(IPath path) {
		System.out.println(this.getClass().toString() + ": " + path.toString());
		if(pathEndings == null) {
			this.initEndings();
		}
		return pathEndings.stream().anyMatch(ending-> ending.equals(path.getFileExtension()));
	}


	@Override
	public abstract String getDiagramText(IPath path);
}
