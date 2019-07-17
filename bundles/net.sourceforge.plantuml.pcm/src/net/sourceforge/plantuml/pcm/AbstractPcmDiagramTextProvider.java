package net.sourceforge.plantuml.pcm;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;

import net.sourceforge.plantuml.eclipse.utils.DiagramTextProvider2;
import net.sourceforge.plantuml.ecore.AbstractEcoreClassDiagramTextProvider;

import net.sourceforge.plantuml.util.DiagramModus;

public abstract class AbstractPcmDiagramTextProvider extends AbstractEcoreClassDiagramTextProvider
		implements DiagramTextProvider2 {

	public List<String> pathEndings;
	public List<Class<?>> partTypes;
	private final DiagramModus modus = DiagramModus.SEQUENZDIAGRAMM;

	public DiagramModus getModus() {
		return modus;
	}

	public AbstractPcmDiagramTextProvider() {
		this.initEndings();
		this.initPartTypes();
	}

	public AbstractPcmDiagramTextProvider(final Class<?> editorType) {
		super(editorType);
	}

	public abstract void initEndings();

	public abstract void initPartTypes();

	@Override
	public boolean supportsEditor(final IEditorPart editorPart) {		
		return check(editorPart);
		}
	private boolean check(Object part) {
		boolean response = partTypes.stream().anyMatch( type -> type.isInstance(part));
		return response;
	}
	@Override 
	public boolean supportsView(final IViewPart part) {
		return check(part);
	}
	@Override
	public boolean supportsPath(IPath path) {
		return pathEndings.stream().anyMatch(ending -> ending.equals(path.getFileExtension()));
	}
	@Override
	public boolean supportsSelection(ISelection selection) {
		//TODO FIX
		/*IStructuredSelection strucSel = (IStructuredSelection) selection;
		Object sel = strucSel.getFirstElement();
		if (sel == null) {
			return false;
		}
		String scn = sel.getClass().getSimpleName();
		if (scn.equals("RepositoryDemandingSEFF")) {
			System.out.println("buja");
			return true;
		};
		return false;*/
		return true;
	}

	@Override
	public abstract String getDiagramText(IPath path);
}
