/*     */ package gui;
/*     */ 
/*     */ import guiFrame.MyRender;
/*     */ import guiFrame.SimpleFrame;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import socket.Client;
/*     */ import socket.Msg;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainFrame
/*     */   extends SimpleFrame
/*     */ {
/*     */   public static MainFrame frame;
/*     */   public static Client client;
/*     */   JLabel lab1;
/*     */   JTextArea text;
/*     */   JButton bu;
/*     */   public JScrollPane socll;
/*     */   public static DefaultListModel<Msg> model;
/*     */   public static JList<Msg> mylist;
/*     */   JButton bu2;
/*     */   
/*  52 */   public MainFrame(String name) { super(name); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(JPanel panel) {
/*  61 */     this.text = new JTextArea();
/*  62 */     this.text.setEditable(true);
/*  63 */     this.text.setFont(new Font("微软雅黑", 0, 18));
/*  64 */     this.text.setLineWrap(true);
/*  65 */     this.text.setRows(3);
/*     */     
/*  67 */     this.bu = new JButton("发送");
/*  68 */     this.bu.setFont(new Font("微软雅黑", 1, 18));
/*  69 */     this.bu.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/*  72 */             String value = MainFrame.this.text.getText();
/*  73 */             if (value.equals(""))
/*     */               return; 
/*  75 */             MainFrame.client.sendstr(value, (byte)101);
/*  76 */             MainFrame.this.text.setText("");
/*     */           }
/*     */         });
/*  79 */     model = new DefaultListModel();
/*  80 */     mylist = new JList();
/*  81 */     mylist.setModel(model);
/*  82 */     mylist.setCellRenderer(new MyRender());
/*  83 */     mylist.setSelectionMode(2);
/*  84 */     mylist.setLayoutOrientation(0);
/*  85 */     mylist.setVisibleRowCount(-1);
/*     */     
/*  87 */     this.socll = new JScrollPane(mylist);
/*     */     
/*  89 */     this.bu2 = new JButton("断开连接");
/*  90 */     this.bu2.setFont(new Font("微软雅黑", 1, 18));
/*  91 */     this.bu2.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  95 */             MainFrame.client.sendstr("close", (byte)1);
/*  96 */             MainFrame.client.close();
/*  97 */             MainFrame.this.setVisible(false);
/*  98 */             ConnectFrame.in.setVisible(true);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 103 */     panel.add(this.text);
/* 104 */     panel.add(this.bu2);
/* 105 */     panel.add(this.socll);
/* 106 */     panel.add(this.bu);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setbounds(int width, int height, int x, int y) {
/* 113 */     this.bu.setBounds(width - 100 - this.bu2.getWidth(), height - 50, 80, 30);
/* 114 */     this.socll.setBounds(0, 0, width, height - 140);
/* 115 */     this.text.setSize(width, 75);
/* 116 */     this.text.setBounds(0, this.socll.getHeight(), width, 75);
/* 117 */     this.bu2.setBounds(width - 120, height - 50, 120, 30);
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\client - 副本\client.jar!\gui\MainFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */