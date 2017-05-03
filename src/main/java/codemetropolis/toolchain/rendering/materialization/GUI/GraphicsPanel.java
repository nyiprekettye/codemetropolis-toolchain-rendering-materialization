package codemetropolis.toolchain.rendering.materialization.GUI;


import java.awt.Dimension;
import java.awt.DisplayMode;

import javax.swing.JPanel;


import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;


public class GraphicsPanel extends JPanel {

	private static final long serialVersionUID = -4771614720014150163L;
	private MainGUI mainGUI;
	private mxGraph graph;
	private mxGraphComponent graphComponent;
	
	public GraphicsPanel(MainGUI mainGUI) {
		this.setMainGUI(mainGUI);
		//add(new Label("Grafikus felület"));
		initGUI();
		
	}
	
	private void initGUI(){
		setSize(900, 700);
		setLocation(1,1);
		//setLocationRelativeTo(null);
		graph = new mxGraph();
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setPreferredSize(new Dimension(400, 400));
		add(graphComponent);
		
		graph.getModel().beginUpdate();
		Object parent = graph.getDefaultParent();
		
		/*
		 * Elrendezese a kovetkezo:
		 * "elem neve", x koordinata elhelyezkedese, y kordinata elhelyezese, elem szelessege, elem magassaga
		 */
		graph.insertVertex(parent, null, "Alap", 10, 340, 340, 40);		
		graph.insertVertex(parent, null, "Epulet", 20, 290, 100, 40);
		//graph.insertEdge(parent, null, "Kapcsolat", 0,0);
		
		graph.getModel().endUpdate();
		setVisible(true);
	}

	public MainGUI getMainGUI() {
		return mainGUI;
	}

	public void setMainGUI(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
	}
	

}
