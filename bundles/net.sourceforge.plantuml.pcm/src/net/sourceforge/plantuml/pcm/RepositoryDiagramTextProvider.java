package net.sourceforge.plantuml.pcm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.palladiosimulator.pcm.repository.presentation.RepositoryEditor;

import de.uka.ipd.sdq.pcm.gmf.seff.part.SeffDiagramEditor;
import net.sourceforge.plantuml.ecore.AbstractEcoreClassDiagramTextProvider;
import de.uka.ipd.sdq.pcm.gmf.repository.part.PalladioComponentModelRepositoryDiagramEditor;
// import org.palladiosimulator.pcm.repository.util.RepositoryResourceImpl;
import org.eclipse.m2m.qvt.oml.blackbox.java.Parameter;
import org.eclipse.m2m.qvt.oml.util.*;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.m2m.qvt.oml.runtime.util.QvtoTransformationHelper;

//import org.palladiosimulator.pcm.repository.util.RepositoryResourceImpl;

public class RepositoryDiagramTextProvider extends AbstractPcmDiagramTextProvider {

	private final String testDiagram = "@startuml\r\n" + "actor Nutzer #green \r\n "
			+ "Nutzer -> Facade: register()\r\n" + "activate Facade \r\n " + "Facade -> UserManagement: register()\r\n"
			+ "activate UserManagement \r\n" + "UserManagement -> UserDBAdapter:addUser()\r\n"
			+ "activate UserDBAdapter \r\n" + "UserDBAdapter -> DB: query()\r\n" + "deactivate UserDBAdapter \r\n"
			+ "deactivate UserManagement \r\n" + "deactivate Facade \r\n" + "@enduml";

	public RepositoryDiagramTextProvider() {
		initEndings();
		this.setEditorType(RepositoryEditor.class);
	}

	@Override
	public void initEndings() {
		this.pathEndings = new LinkedList<String>();
		this.pathEndings.add("repository");
		this.pathEndings.add("repository_diagram");
	}
	
	@Override
	public void initPartTypes() {
		this.partTypes = new ArrayList<Class<?>>();
		this.partTypes.add(RepositoryEditor.class);
		this.partTypes.add(SeffDiagramEditor.class);
		this.partTypes.add(PalladioComponentModelRepositoryDiagramEditor.class);
	}

	@Override
	public String getDiagramText(IPath path) {
		return testDiagram;
	}
	
	public static boolean isEcoreClassDiagramObject(final Object object) {
		return object instanceof EModelElement;
	}

	@Override
	public String getDiagramText(IEditorPart editorPart, ISelection selection, Map<String, Object> markerAttributes) {
		// here happens the transformation
		/*if (selection instanceof IStructuredSelection) {
			final Object sel = ((IStructuredSelection) selection).getFirstElement();
			if (sel instanceof EObject && (! isEcoreClassDiagramObject(sel))) {
				if (markerAttributes != null) {
					markerAttributes.put("eObjectUri", EcoreUtil.getURI((EObject) sel).toString());
				}
				return getDiagramText((EObject) sel);
			}
			return null;
		}
		return getDiagramText(((IEditingDomainProvider) editorPart).getEditingDomain().getResourceSet().getAllContents());
/*
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection strucSel = (IStructuredSelection) selection;
			Object sel = strucSel.getFirstElement();
			if(!(sel instanceof EObject)) {
				return null;
			}
			EObject firstElement = (EObject) sel;
	        if (firstElement instanceof IAdaptable)
	        {
	            IProject project = (IProject)((IAdaptable)firstElement).getAdapter(IProject.class);
	            IPath path = project.getFullPath();	        
	            System.out.println(path);
	        }
		}*/
		return transform();
	}
	private String getDiagramText(TreeIterator<Notifier> allContents) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getDiagramText(EObject sel) {
		// TODO Auto-generated method stub
		return null;
	}

	private String transform() {
		// QvtoTransformationHelper helper = new QvtoTransformationHelper(EcoreUtil.getURI(null).trimFragment());
		return testDiagram;
	}



}
