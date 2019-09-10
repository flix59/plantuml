package net.sourceforge.plantuml.pcm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;

import net.sourceforge.plantuml.eclipse.utils.ModusDiagramTextProvider;
import net.sourceforge.plantuml.ecore.AbstractEcoreClassDiagramTextProvider;

import net.sourceforge.plantuml.util.DiagramModus;

public abstract class AbstractPcmDiagramTextProvider extends AbstractEcoreClassDiagramTextProvider implements ModusDiagramTextProvider{
	protected static final String notSelectedText = "@startuml \n title not Implemented yet\n @enduml";


	public List<Class<?>> partTypes;
	private DiagramModus modus = DiagramModus.UML;

	public DiagramModus getModus() {
		return modus;
	}
	
	public void setDiagramModus(DiagramModus modus) {
		this.modus = modus;
	}

	public AbstractPcmDiagramTextProvider() {
		this.initPartTypes();
	}

	public AbstractPcmDiagramTextProvider(final Class<?> editorType) {
		super(editorType);
	}

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
	
	protected String readStringFromFile(File transformationFile) {
		try {
			FileInputStream fis = new FileInputStream(transformationFile);
			StringBuilder sb = new StringBuilder();
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	protected String transform(AbstractAcceleoGenerator generator, File directory) {
		try {
			generator.doGenerate(new BasicMonitor());
			File transformationFile = new File(directory.getAbsolutePath() + "/generatedTransformation");
			String text = readStringFromFile(transformationFile);
			return text;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
