/*    */ package guiFrame;
/*    */ import java.awt.Container;
/*    */ import java.awt.Insets;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public abstract class SimpleFrame extends JFrame {
/*    */   public int panewidth;
/*    */   public int paneheight;
/*    */   public JPanel pane;
/*    */   
/*    */   public abstract void run(JPanel paramJPanel);
/*    */   
/*    */   public SimpleFrame(String name) {
/* 16 */     super(name);
/* 17 */     this.pane = new JPanel();
/* 18 */     this.pane.setLayout(new mylayout());
/* 19 */     setContentPane(this.pane);
/* 20 */     setSize(1000, 600);
/* 21 */     setLocation(getWidth() / 2, 0);
/* 22 */     run(this.pane);
/*    */ 
/*    */     
/* 25 */     setDefaultCloseOperation(3);
/*    */     
/* 27 */     setVisible(true);
/*    */   }
/*    */   
/*    */   public abstract void setbounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */   
/*    */   public class mylayout
/*    */     extends SimpleLayout {
/*    */     public void layoutContainer(Container parent) {
/* 35 */       Rectangle ect = parent.getBounds();
/* 36 */       Insets in2 = parent.getInsets();
/*    */       
/* 38 */       ect.x += in2.left;
/* 39 */       ect.y += in2.top;
/*    */       
/* 41 */       ect.width -= in2.left + in2.right;
/* 42 */       ect.height -= in2.top + in2.bottom;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 50 */       SimpleFrame.this.setbounds(ect.width, ect.height, ect.x, ect.y);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\guiFrame\SimpleFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */