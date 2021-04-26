/*     */ package guiFrame;
/*     */ 
/*     */ import gui.MainFrame;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.LineBorder;
/*     */ import socket.Msg;
/*     */ 
/*     */ public class MyRender
/*     */   implements ListCellRenderer
/*     */ {
/*     */   Box box;
/*     */   Box config;
/*  24 */   public SimpleDateFormat format = new SimpleDateFormat("hh:mm");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/*  30 */     Msg val = (Msg)value;
/*  31 */     this.config = Box.createVerticalBox();
/*  32 */     Box box3 = Box.createHorizontalBox();
/*  33 */     JLabel name = new JLabel();
/*  34 */     name.setFont(new Font("微软雅黑", 1, 15));
/*  35 */     JLabel time = new JLabel("时间");
/*  36 */     time.setFont(new Font("宋体", 0, 15));
/*  37 */     time.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/*  38 */     JLabel msg = new JLabel();
/*  39 */     msg.setFont(new Font("微软雅黑", 0, 20));
/*  40 */     msg.setBackground(new Color(255, 255, 255));
/*  41 */     msg.setOpaque(true);
/*     */     
/*  43 */     Border border3 = BorderFactory.createEmptyBorder(5, 7, 5, 7);
/*  44 */     Border border = new LineBorder(new Color(243, 243, 243), 3);
/*  45 */     Border border2 = BorderFactory.createCompoundBorder(border, border3);
/*  46 */     msg.setBorder(border2);
/*     */     
/*  48 */     Box namebox = Box.createHorizontalBox();
/*  49 */     Box msgbox = Box.createHorizontalBox();
/*     */     
/*  51 */     String clientname = null;
/*  52 */     String msgstr = null;
/*     */ 
/*     */     
/*     */     try {
/*  56 */       clientname = new String(val.getName(), "utf-8");
/*  57 */       msgstr = new String(val.getMsg(), "utf-8");
/*     */     }
/*  59 */     catch (UnsupportedEncodingException e1) {
/*     */       
/*  61 */       e1.printStackTrace();
/*     */     } 
/*  63 */     System.out.println("msgstr=" + msgstr.toString());
/*  64 */     name.setText(clientname);
/*  65 */     msg.setText(msgstr.toString());
/*     */     
/*  67 */     if (MainFrame.client.name.equals(clientname)) {
/*     */       
/*  69 */       namebox.add(Box.createHorizontalGlue());
/*  70 */       namebox.add(name);
/*  71 */       namebox.add(Box.createHorizontalStrut(8));
/*  72 */       msgbox.add(Box.createHorizontalGlue());
/*  73 */       msgbox.add(msg);
/*  74 */       msgbox.add(Box.createHorizontalStrut(20));
/*     */     }
/*     */     else {
/*     */       
/*  78 */       namebox.add(Box.createHorizontalStrut(8));
/*  79 */       namebox.add(name);
/*  80 */       namebox.add(Box.createHorizontalGlue());
/*  81 */       msgbox.add(Box.createHorizontalStrut(20));
/*  82 */       msgbox.add(msg);
/*  83 */       msgbox.add(Box.createHorizontalGlue());
/*     */     } 
/*     */     
/*  86 */     String timestr = this.format.format(new Date(val.getTime()));
/*     */ 
/*     */     
/*  89 */     if (index != 0) {
/*     */       
/*  91 */       Msg val2 = (Msg)list.getModel().getElementAt(index - 1);
/*  92 */       String timestr2 = this.format.format(new Date(val2.getTime()));
/*  93 */       if (!timestr.equals(timestr2))
/*     */       {
/*  95 */         time.setText(timestr);
/*  96 */         Box timebox = Box.createHorizontalBox();
/*  97 */         timebox.add(Box.createHorizontalGlue());
/*  98 */         timebox.add(time);
/*  99 */         timebox.add(Box.createHorizontalGlue());
/* 100 */         this.config.add(timebox);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 105 */       time.setText(timestr);
/* 106 */       Box timebox = Box.createHorizontalBox();
/* 107 */       timebox.add(Box.createHorizontalGlue());
/* 108 */       timebox.add(time);
/* 109 */       timebox.add(Box.createHorizontalGlue());
/* 110 */       this.config.add(timebox);
/*     */     } 
/*     */ 
/*     */     
/* 114 */     this.config.add(namebox);
/* 115 */     this.config.add(msgbox);
/*     */     
/* 117 */     return this.config;
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\client - 副本\client.jar!\guiFrame\MyRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */