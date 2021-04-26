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
/* 26 */     setVisible(true);
/*    */   }
/*    */   
/*    */   public abstract void setbounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */   
/*    */   public class mylayout
/*    */     extends SimpleLayout {
/*    */     public void layoutContainer(Container parent) {
/* 34 */       Rectangle ect = parent.getBounds();
/* 35 */       Insets in2 = parent.getInsets();
/*    */       
/* 37 */       ect.x += in2.left;
/* 38 */       ect.y += in2.top;
/*    */       
/* 40 */       ect.width -= in2.left + in2.right;
/* 41 */       ect.height -= in2.top + in2.bottom;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 49 */       SimpleFrame.this.setbounds(ect.width, ect.height, ect.x, ect.y);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\client - 副本\client.jar!\guiFrame\SimpleFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */