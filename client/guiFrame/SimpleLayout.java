/*    */ package guiFrame;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.LayoutManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleLayout
/*    */   implements LayoutManager
/*    */ {
/*    */   public void addLayoutComponent(String name, Component comp) {}
/*    */   
/*    */   public void removeLayoutComponent(Component comp) {}
/*    */   
/* 25 */   public Dimension preferredLayoutSize(Container parent) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public Dimension minimumLayoutSize(Container parent) { return null; }
/*    */   
/*    */   public void layoutContainer(Container parent) {}
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\client - 副本\client.jar!\guiFrame\SimpleLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */