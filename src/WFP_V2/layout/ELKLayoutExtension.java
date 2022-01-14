package WFP_V2.layout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.elk.GmfLayoutCommand;
import org.eclipse.sirius.diagram.elk.IELKLayoutExtension;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.elk.graph.ElkGraphElement;

/**
 * Extension point used to provide the "Object Type" (Data Element, Constraint etc) of a node to LayoutProvider
 * */
public class ELKLayoutExtension implements IELKLayoutExtension {

	public ELKLayoutExtension() {
		// TODO Auto-generated constructor stub
	}

	@Override
	// Runs before LayoutProvider runs layout
	public void beforeELKLayout(LayoutMapping layoutMapping) {
		// Adds a new property named ObjectName. This property represents the Sirius element name (Data Element, Constraint, etc)
		ArrayList<ElkNode> nodes = new ArrayList<>(layoutMapping.getLayoutGraph().getChildren());
		for (Entry<ElkGraphElement, Object> entry : layoutMapping.getGraphMap().entrySet()) {
		    Object editPart = entry.getValue();
		    
		    if (editPart instanceof DNodeListEditPart) {
		        EObject siriusDiagramElement = ((DNodeListEditPart) editPart).resolveTargetSemanticElement();
		        String elementName = siriusDiagramElement.eClass().getName();
		        IProperty<String> property = new Property("ObjectName");
		        System.out.println(elementName);
		        entry.getKey().setProperty(property, elementName);
		    }
		}
		
	}

	@Override
	public void afterELKLayout(LayoutMapping layoutMapping) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGMFCommandApplied(GmfLayoutCommand gmfLayoutCommand, LayoutMapping layoutMapping) {
		// TODO Auto-generated method stub
		
	}

}
