import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

abstract class Shape {
			public abstract void draw(Graphics g);
			public int di1;
			public int di2;
			public int di3;
			public int di4;
			public int modShape;
			public boolean NOTsolid = false;
			public Color modColor;
		
	public Shape(int di1,int di2,int di3, int di4 , Color modColor )
	{
			this.di1 = di1;
			this.di2 = di2;
			this.di3 = di3;
			this.di4 = di4;
			this.modColor = modColor;
		}
	public Shape(int di1,int di2,int di3, int di4 , Color modColor, boolean NOTsolid )
	{
			this.di1 = di1;
			this.di2 = di2;
			this.di3 = di3;
			this.di4 = di4;
			this.modColor = modColor;
			this.NOTsolid = NOTsolid;
	}
   
}

	
class Oval extends Shape 
{

	public Oval(int di1, int di2, int di3, int di4, Color modColor,boolean NOTsolid) 
	{
					super(di1, di2, di3, di4, modColor,NOTsolid);
	} 
	public void draw(Graphics g) 
	{   
	     g.setColor(modColor);
				  if (NOTsolid == false) {g.drawOval(di1, di2, di3-di1, di4-di2);}
				  else {g.fillOval(di1, di2, di3-di1, di4-di2);}
	}
}

class Rectangle extends Shape 
{
     public Rectangle( int di1,  int di2, int di3,  int di4,Color modColor ,boolean NOTsolid)
		{
            super(di1, di2, di3, di4,modColor,NOTsolid);
        }  
		public void draw(Graphics g) 
		{      	 g.setColor(modColor);
						if (NOTsolid == false){g.drawRect (di1, di2, di3-di1, di4-di2);}
						else { g.fillRect (di1, di2, di3-di1, di4-di2);}
		}
}

class Line extends Shape
 { 
      public Line( int di1, int di2, int di3,  int  di4, Color modColor)
		{
            super(di1, di2, di3, di4,modColor);
        }  
		public void draw(Graphics g) 
		{	g.setColor(modColor);
			g.drawLine (di1, di2, di3, di4);
		}
}

class Fd extends Shape
 { 
      public Fd( int di1, int di2, int di3,  int  di4, Color modColor)
			{
				super(di1, di2, di3, di4,modColor);
			}   
     public void draw(Graphics g)
	   {    g.setColor(modColor);
            g.fillRect(di1, di2, 5, 5);
	   }
}

class Eraser extends Shape
 { 
      public Eraser( int di1, int di2, int di3,  int  di4 , Color modColor)
		{
            super(di1, di2, di3, di4,modColor);
        }  
		public void draw(Graphics g) 
		{ g.setColor(Color.WHITE);
			 g.fillRect (di1, di2, 8, 8);
		}
}

class ClearAll extends Shape
 { 
      public ClearAll( int di1, int di2, int di3,  int  di4 , Color modColor)
		{
            super(di1, di2, di3, di4,modColor);
        }  
		public void draw(Graphics g) 
		{ g.setColor(Color.WHITE);
			g.fillRect(0,0,40000,35000);  
		}
}

public class ShapeDraw extends Applet 
{
	ArrayList<Shape> shapes = new ArrayList<>(); 
		public int di1;
		public int di2;
		public int di3;
		public int di4;
		public boolean NOTsolid = false;
		boolean Drawing = false;
	    Color modColor  =  Color.BLACK; 

			 public static final int ovalCNST = 10 ; 
			 public static final int rectCNST = 1 ; 
			 public static final int lineCNST = 2 ;
			 public static final int fdCNST = 3 ; 
			 public static final int eraseCNST = 4 ; 
             public static final int ClearCNST = 5;                ///////////////objects creation
														Oval ovalObject ;
														Rectangle rectangleObject ;
														Line lineObject;
                                                        ClearAll clearObect;
		
				int modShape;
				Button fd;
				Checkbox Solid ; 
				Button blueB;
				Button greenB;
				Button redB;
				Button clearB;	
				Button undoB;
				Button eraseB;		
				Button drawingO;
				Button drawingL;
  Button cyanB;
    Button naviB;
    Button blackCyanB;
    Button toffieB;
				Button drawingR;

	public void init() 
  {
			Solid = new Checkbox("SOLid");			
			Solid.addItemListener(new SolidListener()); 		 
			add(Solid);
			blueB = new Button("BLUE");
					blueB.addActionListener(new BlueListener());
					add(blueB);
			greenB = new Button("GREEN");
					greenB.addActionListener(new GreenListener());
					add(greenB);
			redB = new Button("RED");
					redB.addActionListener(new RedListener());
					add(redB);

 cyanB = new Button("CYAN");
        cyanB.addActionListener(new CyanListener());
        add(cyanB);

        naviB = new Button("NAVI");
        naviB.addActionListener(new NaviListener());
        add(naviB);

        blackCyanB = new Button("grey");
        blackCyanB.addActionListener(new BlackCyanListener());
        add(blackCyanB);

        toffieB = new Button("TOFFIE");
        toffieB.addActionListener(new ToffieListener());
        add(toffieB);

			clearB = new Button("CLEAR ALL");
					clearB.addActionListener(new ClearListener());
					add(clearB);


			undoB = new Button("UNDO");
					undoB.addActionListener(new UndoListener());
					add(undoB);


			drawingO = new Button("OVAL");
			drawingO.addActionListener(new OvalListener());
			add(drawingO);
			
			
			drawingR = new Button("Rectangle");
			drawingR.addActionListener(new RectangleListener());
			add(drawingR);
			
			drawingL = new Button("LINE");
			drawingL.addActionListener(new LineListener());
			add(drawingL);
			
			fd = new Button("pencil");
			fd.addActionListener(new frListener());
			add(fd);
			
			eraseB = new Button("eraser");
			eraseB.addActionListener(new eraseListener());
			add(eraseB);
			
		addMouseListener(new Ml());
	    addMouseMotionListener(new Ml());
    }


   public void paint(Graphics g)  
	{
			g.setColor(modColor);
			for (int i = 0; i < shapes.size(); i++) 
			{
				Shape shape = shapes.get(i);
				shape.draw(g); 
			}
    }


class SolidListener implements ItemListener
{
    public void itemStateChanged(ItemEvent e){
		  NOTsolid =!NOTsolid ;  }
}

 class BlueListener implements ActionListener 
 {
        public void actionPerformed(ActionEvent e) {
            modColor = Color.BLUE;   
        }
 }
	


 class GreenListener implements ActionListener 
 {
        public void actionPerformed(ActionEvent e) {
            modColor = Color.GREEN; 
        }
 }
	


 class RedListener implements ActionListener 
 {
        public void actionPerformed(ActionEvent e) {
             modColor = Color.RED;   
        }
 }

class CyanListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            modColor = new Color(133,179,166); // #99d5a1
        }
    }

    class NaviListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            modColor = new Color(63, 116, 101); // #3f7465
        }
    }

    class BlackCyanListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            modColor = new Color(132,157,157); // #07333b
        }
    }

    class ToffieListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            modColor = new Color(226, 176, 99); // #e2b063
        }
    }
	
 class OvalListener implements ActionListener 
 {
        public void actionPerformed(ActionEvent e) {
            modShape = ShapeDraw.ovalCNST;  
        }
 }
	
	
 class RectangleListener implements ActionListener 
 {     public void actionPerformed(ActionEvent e) {
            modShape = ShapeDraw.rectCNST;  
        }
 }
	
class LineListener implements ActionListener 
{     public void actionPerformed(ActionEvent e) {
            modShape = ShapeDraw.lineCNST;  
        }
}

class frListener implements ActionListener 
{     public void actionPerformed(ActionEvent e) {
            modShape = ShapeDraw.fdCNST;  
        }
}

class eraseListener implements ActionListener 
{     public void actionPerformed(ActionEvent e) {
            modShape = ShapeDraw.eraseCNST;  
        }
}


class ClearListener implements ActionListener 
{     public void actionPerformed(ActionEvent e) 
        {
			   modShape = ShapeDraw.ClearCNST;
			   ClearAll clearObect = new ClearAll( di1, di2, di3, di4,modColor );			
							shapes.add(clearObect);
							 repaint();	   
        }
}

class UndoListener implements ActionListener 
{     public void actionPerformed(ActionEvent e) 
     {
			if(shapes.size()==0){}else{ shapes.remove(shapes.size()-1);repaint();}
     }
}


 class Ml extends MouseAdapter 
{
 
	 public void mousePressed(MouseEvent e) 
				{di1 = e.getX();
					di2 = e.getY();
		switch (modShape) 
				{
			case 10:

					ovalObject = new Oval( di1,  di2,  di3- di1,  di4- di2, modColor , NOTsolid);
		             shapes.add(ovalObject);
					ovalObject.di1 = e.getX();
					ovalObject.di2 = e.getY();
								
					break;
			case 1:
		             rectangleObject= new Rectangle(di1,  di2,  di3,  di4,modColor , NOTsolid);
					 shapes.add(rectangleObject);
					 rectangleObject.di1 = e.getX();
				     rectangleObject.di2 = e.getY();
								
					break;
			case 2:
		lineObject= new Line( di1, di2, di3, di4,modColor);

				        shapes.add(lineObject);
						lineObject.di1 = e.getX();
						lineObject.di2 = e.getY();
					break;
					
				} //bta3 swich
    }

 public void mouseDragged(MouseEvent e) 
    {
		   Drawing = true;
			switch (modShape) 
						{
				case 10:
				 
					if (e.getX()< di1) 
					{
						ovalObject.di1 = e.getX();
						ovalObject.di3 = di1;
					} else 
					{
						ovalObject.di1 = di1;
						ovalObject.di3 = e.getX();
					}
					if (e.getY()< di2) 
					{
						ovalObject.di2 = e.getY();
						ovalObject.di4 = di2;
					} else 
					{
						ovalObject.di2 = di2;
						ovalObject.di4 = e.getY();
					}
						repaint();
				break;
				
			case 1:
			
				if (e.getX()< di1) 
					{
						rectangleObject.di1 = e.getX();
						rectangleObject.di3 = di1;
					} else 
					{
						rectangleObject.di1 = di1;
						rectangleObject.di3 = e.getX();
					}
					if (e.getY()< di2) 
					{
						rectangleObject.di2 = e.getY();
						rectangleObject.di4 = di2;
					} else 
					{
						rectangleObject.di2 = di2;
						rectangleObject.di4 = e.getY();
					}
						repaint();
				 
				break;
						
			case 2:
					lineObject.di3 = e.getX();
					lineObject.di4 = e.getY();
					   repaint();
				break;
			

			case 3:
					di3 = e.getX();
					di4 = e.getY();
						 Fd fdObject = new Fd(di3, di4, 6, 6,modColor);
						 shapes.add(fdObject);
						 repaint();
				break;
						
			case 4 :
					  di3 = e.getX();
					  di4 = e.getY();
					   Eraser EraserObject = new Eraser( di3, di4, 8, 8,Color.RED);
					  shapes.add(EraserObject);
					   repaint();
				break;}//for swich
   }//mouseDragged

     public void mouseReleased(MouseEvent e) 
		{ Drawing = false;
        }
	
 }//for ML
}

