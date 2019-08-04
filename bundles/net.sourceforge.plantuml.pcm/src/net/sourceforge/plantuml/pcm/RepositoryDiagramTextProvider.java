package net.sourceforge.plantuml.pcm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.acceleo.module.palladio.basiccomponent.BasicComponentGenerator;
import org.eclipse.acceleo.module.palladio.common.GeneratePalladio;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.impl.BasicComponentImpl;
import org.palladiosimulator.pcm.repository.presentation.RepositoryEditor;
import org.palladiosimulator.pcm.seff.impl.ResourceDemandingSEFFImpl;

import de.uka.ipd.sdq.pcm.gmf.seff.part.SeffDiagramEditor;
import de.uka.ipd.sdq.pcm.gmf.repository.part.PalladioComponentModelRepositoryDiagramEditor;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;

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
		if (!(selection instanceof IStructuredSelection)) {
			return "@startuml \n @enduml";	
		} else {
		
		if(selection instanceof PalladioComponentModelRepositoryDiagramEditor) {
			
		}

		final Object sel = ((IStructuredSelection) selection).getFirstElement();
		if (!(sel instanceof EObject) || isEcoreClassDiagramObject(sel)) {
			return null;
		}
		if (sel instanceof ResourceDemandingSEFFImpl) {
			return getDiagramText((ResourceDemandingSEFFImpl) sel);
		}	
		if (sel instanceof BasicComponentImpl) {
			return getDiagramText((BasicComponentImpl) sel);
		}
		}
		
		return "@startuml \n facade -> facade : test \n @enduml";
	}

	private String getDiagramText(TreeIterator<Notifier> allContents) {
		// TODO Auto-generated method stub
		return transform();
	}
	

	private String getDiagramText(BasicComponentImpl sel) {
		File targetFolder = new File("transformationen");
		try {
		BasicComponentGenerator generator = new BasicComponentGenerator(sel,targetFolder,new ArrayList());
		String text = transform(generator, targetFolder);
		return text;
		} catch (IOException e) {
		e.printStackTrace();
	}
		return null;
	}

	private String getDiagramText(ResourceDemandingSEFFImpl sel) {
		EObject repository = sel.eContainer();
		File targetFolder = new File("transformationen");
		List<String> arguments = new ArrayList<String>();
		BasicComponent component = (BasicComponent) sel.eContainer();
		arguments.add(component.getId());
		arguments.add(sel.getId());
		try {
			GeneratePalladio generator = new GeneratePalladio(repository, targetFolder, arguments);
			String text = transform(generator, targetFolder);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String transform(AbstractAcceleoGenerator generator, File directory) {
		try {
			generator.doGenerate(new BasicMonitor());
			File transformationFile = new File(directory.getAbsolutePath() + "/generatedTransformation");
			String text = readString(transformationFile);
			return text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String readString(File transformationFile) {
		try {
			FileInputStream fis = new FileInputStream(transformationFile);
			 StringBuilder sb = new StringBuilder();
		      String line;
		      BufferedReader br = new BufferedReader( new InputStreamReader(fis, "UTF-8" ));
		      while(( line = br.readLine()) != null ) {
		         sb.append( line );
		         sb.append( '\n' );
		      }
		      return sb.toString();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean supportsSelection(ISelection selection) {
		IStructuredSelection strucSel = (IStructuredSelection) selection;
		Object sel = strucSel.getFirstElement();
		if (sel == null) {
			return false;
		}
		if (sel instanceof ResourceDemandingSEFFImpl) {
			return true;
		}

		if (sel instanceof BasicComponentImpl) {
			return true;
		}
		if(sel instanceof NodeImpl) {
			return true;
		}
		return false;
	}

	private String transform() {
		return testDiagram;
	}

}
