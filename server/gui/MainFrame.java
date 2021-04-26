/*     */ package gui;
/*     */ 
/*     */ import guiFrame.MyRender;
/*     */ import guiFrame.SimpleFrame;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import server.ForwardCenter;
/*     */ import server.Server;
/*     */ import server.ServerThread;
/*     */ 
/*     */ 
/*     */ public class MainFrame
/*     */   extends SimpleFrame
/*     */ {
/*     */   static MainFrame in;
/*     */   JTextField field;
/*     */   JTextField field2;
/*     */   JTextField field3;
/*     */   JButton bu;
/*     */   JButton bu2;
/*     */   JLabel lab1;
/*     */   JLabel lab2;
/*     */   JLabel lab3;
/*     */   public JScrollPane socll;
/*     */   public static DefaultListModel<Msg> model;
/*     */   public static JList<Msg> mylist;
/*     */   
/*  38 */   public MainFrame(String name) { super(name); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(JPanel panel) {
/*  44 */     model = new DefaultListModel();
/*  45 */     mylist = new JList();
/*  46 */     mylist.setModel(model);
/*  47 */     mylist.setCellRenderer(new MyRender());
/*  48 */     mylist.setSelectionMode(2);
/*  49 */     mylist.setLayoutOrientation(0);
/*  50 */     mylist.setVisibleRowCount(-1);
/*     */     
/*  52 */     this.socll = new JScrollPane(mylist);
/*     */     
/*  54 */     this.lab1 = new JLabel("当前状态:");
/*  55 */     this.lab1.setFont(new Font("微软雅黑", 1, 18));
/*     */     
/*  57 */     this.lab2 = new JLabel("连接状态:未连接");
/*  58 */     this.lab2.setFont(new Font("微软雅黑", 1, 18));
/*     */ 
/*     */     
/*  61 */     this.lab3 = new JLabel("port:");
/*  62 */     this.lab3.setFont(new Font("微软雅黑", 1, 18));
/*     */     
/*  64 */     this.field2 = new JTextField();
/*     */     
/*  66 */     this.field2.setToolTipText("请输入端口号");
/*  67 */     this.field2.setFont(new Font("微软雅黑", 0, 18));
/*  68 */     this.field2.setSize(new Dimension(450, 40));
/*  69 */     this.bu2 = new JButton("断开服务");
/*  70 */     this.bu2.setFont(new Font("微软雅黑", 1, 18));
/*     */     
/*  72 */     this.bu2.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*     */             try {
/*  78 */               if (Server.server.isClosed()) {
/*     */                 return;
/*     */               }
/*  81 */               Server.server.close();
/*  82 */               Server.clients.removeAll(Server.clients);
/*  83 */               ForwardCenter.init();
/*  84 */               Server.num = 0;
/*  85 */               MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "主动关闭客户端.."));
/*  86 */               MainFrame.this.lab2.setText("连接状态:未连接");
/*  87 */               MainFrame.this.lab3.setText("port:");
/*  88 */             } catch (IOException e1) {
/*     */               
/*  90 */               MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "主动关闭客户端失败！！"));
/*  91 */               MainFrame.this.lab2.setText("连接状态:关闭失败");
/*  92 */               e1.printStackTrace();
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  98 */     this.bu = new JButton("创建服务");
/*  99 */     this.bu.setFont(new Font("微软雅黑", 1, 18));
/*     */     
/* 101 */     this.bu.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 106 */             if (Server.server != null && !Server.server.isClosed()) {
/*     */               
/* 108 */               MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "当前服务未关闭！！"));
/*     */               
/*     */               return;
/*     */             } 
/* 112 */             String port = MainFrame.this.field2.getText();
/* 113 */             if (port == null || port.equals("")) {
/*     */               
/* 115 */               MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "未输入端口号！！"));
/*     */               
/*     */               return;
/*     */             } 
/*     */             try {
/* 120 */               int i = Integer.valueOf(port).intValue();
/*     */             }
/* 122 */             catch (Exception e1) {
/* 123 */               MainFrame.model.addElement(new Msg((byte)-1, System.currentTimeMillis(), "错误的端口形式！！"));
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */ 
/*     */             
/* 130 */             boolean type = Server.createconnect(Integer.valueOf(port).intValue());
/* 131 */             MainFrame.this.lab3.setText("port:" + port);
/* 132 */             if (type) {
/*     */               
/* 134 */               Server.thread = new ServerThread();
/* 135 */               Server.thread.start();
/* 136 */               MainFrame.this.lab2.setText("连接状态:已连接");
/* 137 */               MainFrame.this.field2.setText("");
/*     */             } else {
/*     */               
/* 140 */               MainFrame.this.lab2.setText("连接状态:创建失败");
/* 141 */               System.out.println("连接失败..");
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 146 */     panel.add(this.field2);
/* 147 */     panel.add(this.bu);
/* 148 */     panel.add(this.lab2);
/* 149 */     panel.add(this.socll);
/* 150 */     panel.add(this.bu2);
/* 151 */     panel.add(this.lab1);
/* 152 */     panel.add(this.lab3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setbounds(int width, int height, int x, int y) {
/* 157 */     this.socll.setBounds(0, 0, width, height - 100);
/* 158 */     this.field2.setBounds(0, this.socll.getHeight(), 320, 40);
/* 159 */     this.bu.setBounds(0, height - 50, 150, 40);
/* 160 */     this.bu2.setBounds(this.bu2.getWidth() + 20, height - 50, 150, 40);
/* 161 */     this.lab1.setBounds(390, this.socll.getHeight(), 120, 40);
/* 162 */     this.lab2.setBounds(500, this.socll.getHeight(), 200, 40);
/* 163 */     this.lab3.setBounds(500, this.socll.getHeight() + this.lab2.getHeight(), 160, 40);
/*     */   }
/*     */ }


/* Location:              C:\Users\xianzhikun\Desktop\mytemple\server\server.jar!\gui\MainFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */