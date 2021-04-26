/*    */ package guiFrame;
/*    */ 
/*    */ import gui.Msg;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Font;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.Box;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.ListCellRenderer;
/*    */ import javax.swing.border.Border;
/*    */ import javax.swing.border.LineBorder;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MyRender
/*    */   implements ListCellRenderer
/*    */ {
/*    */   Box box;
/*    */   Box config;
/* 24 */   public SimpleDateFormat format = new SimpleDateFormat("hh:mm");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 30 */     Msg val = (Msg)value;
/* 31 */     this.config = Box.createVerticalBox();
/* 32 */     Box box3 = Box.createHorizontalBox();
/* 33 */     JLabel cmd = new JLabel();
/* 34 */     cmd.setFont(new Font("微软雅黑", 1, 15));
/* 35 */     JLabel time = new JLabel("时间");
/* 36 */     time.setFont(new Font("宋体", 0, 15));
/* 37 */     time.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 38 */     JLabel msg = new JLabel();
/* 39 */     msg.setFont(new Font("微软雅黑", 0, 20));
/* 40 */     msg.setBackground(new Color(255, 255, 255));
/* 41 */     msg.setOpaque(true);
/*    */     
/* 43 */     Border border3 = BorderFactory.createEmptyBorder(5, 7, 5, 7);
/* 44 */     Border border = new LineBorder(new Color(243, 243, 243), 3);
/* 45 */     Border border2 = BorderFactory.createCompoundBorder(border, border3);
/* 46 */     msg.setBorder(border2);
/*    */     
/* 48 */     Box cmdbox = Box.createHorizontalBox();
/* 49 */     Box msgbox = Box.createHorizontalBox();
/*    */ 
/*    */     
/* 52 */     if (val.cmd != -1)
/*    */     {
/* 54 */       cmd.setText((new StringBuilder(String.valueOf(val.cmd))).toString());
/*    */     }
/* 56 */     msg.setText(val.msg);
/*    */     
/* 58 */     cmdbox.add(Box.createHorizontalStrut(8));
/* 59 */     cmdbox.add(cmd);
/* 60 */     cmdbox.add(Box.createHorizontalGlue());
/* 61 */     msgbox.add(Box.createHorizontalStrut(20));
/* 62 */     msgbox.add(msg);
/* 63 */     msgbox.add(Box.createHorizontalGlue());
/*    */ 
/*    */     
/* 66 */     String timestr = this.format.format(new Date(val.getTime()));
/*    */ 
/*    */     
/* 69 */     if (index != 0) {
/*    */       
/* 71 */       Msg val2 = (Msg)list.getModel().getElementAt(index - 1);
/* 72 */       String timestr2 = this.format.format(new Date(val2.getTime()));
/* 73 */       if (!timestr.equals(timestr2))
/*    */       {
/* 75 */         time.setText(timestr);
/* 76 */         Box timebox = Box.createHorizontalBox();
/* 77 */         timebox.add(Box.createHorizontalGlue());
/* 78 */         timebox.add(time);
/* 79 */         timebox.add(Box.createHorizontalGlue());
/* 80 */         this.config.add(timebox);
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 85 */       time.setText(timestr);
/* 86 */       Box timebox = Box.createHorizontalBox();
/* 87 */       timebox.add(Box.createHorizontalGlue());
/* 88 */       timebox.add(time);
/* 89 */       timebox.add(Box.createHorizontalGlue());
/* 90 */       this.config.add(timebox);
/*    */     } 
/*    */ 
/*    */     
/* 94 */     this.config.add(cmdbox);
/* 95 */     this.config.add(msgbox);
/*    */     
/* 97 */     return this.config;
/*    */   }
/*    */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\guiFrame\MyRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */