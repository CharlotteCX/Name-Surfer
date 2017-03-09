/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
//import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
		entryGraph=new ArrayList <NameSurferEntry>();
		
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //'
		entryGraph.clear();
		update();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entryGraph.add(entry);
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		// You fill this in //
		removeAll();
		
		for(int i=0;i<NDECADES;i++){
		
		add(new GLine(i*getWidth()/NDECADES,0,i*getWidth()/NDECADES, getHeight()));
		add(new GLabel(""+(START_DECADE+i*10),i*getWidth()/NDECADES,getHeight()-4));
		
		}
		
		add(new GLine(0,GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE));
		add(new GLine(0,getHeight()-GRAPH_MARGIN_SIZE,getWidth(),getHeight()-GRAPH_MARGIN_SIZE));
		
		for(int i=0;i<entryGraph.size();i++){
			drawRanking(entryGraph.get(i), i);
		}
	}
	
	private void drawRanking(NameSurferEntry entry, int inputNumber){
		
		for(int i=0; i<NDECADES-1;i++){
			
			//GLine line= new GLine(getWidth()/NDECADES*i,yValue(entry.getRank(i)),getWidth()/NDECADES*(i+1),yValue(entry.getRank(i+1)));
			int rankLeft=entry.getRank(i);
			int rankRight= entry.getRank(i+1);
			int yValueLeft=0;
			int yValueRight=0;
			
			if (rankLeft==0){
			yValueLeft=getHeight()-GRAPH_MARGIN_SIZE;
			}else{
				yValueLeft=rankLeft*(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK+GRAPH_MARGIN_SIZE;
			}
			
			if (rankRight==0){
				yValueRight=getHeight()-GRAPH_MARGIN_SIZE;
				}else{
					yValueRight=rankRight*(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK+GRAPH_MARGIN_SIZE;
				}
			
			GLine line= new GLine(getWidth()*i/NDECADES,yValueLeft,getWidth()*(i+1)/NDECADES,yValueRight);
			
			if(inputNumber%4==0){
				line.setColor(Color.BLUE);
			}else if(inputNumber%4==1){
				line.setColor(Color.RED);
			}else if(inputNumber%4==2){
				line.setColor(Color.MAGENTA);
			}else if(inputNumber%4==3){
				line.setColor(Color.BLACK);
			}
			
			add(line);
			
		}
		
		for(int i=0;i<NDECADES;i++){
			String name=entry.getName();
			
			int rank=entry.getRank(i);
			if (rank==0){
				rank=getHeight()-GRAPH_MARGIN_SIZE;
				name=name+"*";
				}else{
					rank=rank*(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK+GRAPH_MARGIN_SIZE;
					name=name+" "+entry.getRank(i);
				}
		GLabel label=new GLabel(name,getWidth()*i/NDECADES,rank);
		
		if(inputNumber%4==0){
			label.setColor(Color.BLUE);
		}else if(inputNumber%4==1){
			label.setColor(Color.RED);
		}else if(inputNumber%4==2){
			label.setColor(Color.MAGENTA);
		}else if(inputNumber%4==3){
			label.setColor(Color.BLACK);
		}
		
		add(label);
		}
	}
	
	//private int yValue(int rank){

		//int yCoordinate=(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK;
		//if(rank!=0){
			//rank=yCoordinate*rank+GRAPH_MARGIN_SIZE;
		//}else{
			//rank=getHeight()-GRAPH_MARGIN_SIZE;
		//}
		
		//return rank;
	//}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update();  }
	public void componentShown(ComponentEvent e) { }
	
	//private int[] showRank= new int[NDECADES];
	
	//private String showName;
	private ArrayList<NameSurferEntry> entryGraph;
	//private GLabel showName;

}
