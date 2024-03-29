/* 
 * Copyright (C) 2015 eetz1 <eetukah@metropolia.fi>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package stna;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
//import sun.audio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;

/**
 *
 * @author eetz1
 */
public class View extends JPanel implements Runnable {

  private MapController arena;
  private GameEngineController contr;
  private JLabel towerinfo, towerinfo2, playerinfo, playerinfo2, playerinfo3, startLabel, playerinfo4, creditsLabel, musicLabel, towerinfoLong, towerUpPrice, towerSellPrice;
  private JButton tower, tower2, tower3, tower4, tower5, update, start, credits, musicb, sell;
  private JPanel storePanel, towerInfoPane, contentPanel, playerInfoPane, musicPanel;
  private ActionListener actionL;
  private int bsize;//BLOCK SIZE
  private int rows, cols;
  private Graphics buffer;//THIS IS NEEDED FOR DOUBLE BUFFERING
  private Image dbImage;//THIS IMAGE STORES DOUBLEBUFFERED IMAGE, THAT SCREEN WONT FLICKER
  private static double fps = 60;//FRAMES PER SECOND
  private int pSec = 10;//PAUSETIME IN SEC, HOW LONG PAUSE IS BETWEEN WAVES
  private double spawnTime = 1 * (double) (fps);//HOW MANYTIMES PERSEC ENEMIES SPAWN
  private double spawnFrame = spawnTime - fps;//COUNTER TO CHECK UPPER ^
  private int spawnCounter = 0;//COUNTER TO CHECK IF ALL ENEMIES OF WAVE HAVE SPAWNED
  private boolean isFirst = true;//IF ITS FIRST TIME THAT THREAD IS RUN
  private boolean btnPress;//CHECKS IF BTN IS PRESSED
  private boolean towerclick;//CHECKS IF TOWER IS CLICKED
  private boolean sPause = true;//BOOLEAN TO CHECK IF THERE IS PAUSE BETWEEN WAVES
  private String towerid;//JUST TO STORE TOWERID
  private double pauseFrame = 1;//COUNTER FOR PAUSE BETWEEN WAVES
  private double pauseTime = pSec * (double) (fps);//TIME IN FPS BETWEEN WAVES
  private double shootTime = 0.5 * (double) fps;//TIME BETWEEN SHOOTING
  private boolean running = true;//IF GAME IS RUNNING
  private int mouseX, mouseY;
  private boolean shoot_money = false;//boolean to check if show moneys top of moneytower
  private ModelTower utower;//SELECTED TOWER FROM MAP
  private JFrame frame;//FRAME WHERE EVERYTHING HAPPENS
  private String title = "TD: DACK FACK";//TITLE
  private ImageIcon icon, icon2;
  private Thread game = new Thread(this);//THREAD WHERE GAME RUNS
  private boolean musicOn = true;//BOOLEAN TO CHECK IF MUSICA IS ON
  //private AudioStream BGM;
  //private AudioData MD;
  //ContinuousAudioDataStream loop = null;
  //AudioPlayer MGP = AudioPlayer.player;
  Clip clip = null;

  public View() {//constructor

    frame = new JFrame();//creates frame
    frame.setLayout(new BorderLayout());//layout
    frame.setTitle(title);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setResizable(true);
    frame.setLocationRelativeTo(null);

    arena = new MapController();
    contr = new GameEngineController(arena);

    cols = arena.getColumns();
    rows = arena.getRows();
    bsize = arena.getBsize();

    actionL = new ButtonListener();

    try {
      UIManager.setLookAndFeel(
              UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println("Look and feel");
    }

    music(musicOn);//MUUUSIIIC
    initStart();//startscreen

  }

  public void initStart() {//inits startscreen

    //setLayout(new BorderLayout());
    BufferedImage img = null;

    startLabel = new JLabel();//startlabel where whole start screen is

    try {
      img = ImageIO.read(new File("images/bg.png"));//backgroundpic
    } catch (IOException e) {
    }
    Image dimg = img.getScaledInstance(cols * bsize + bsize * 3, rows * bsize + bsize, Image.SCALE_SMOOTH);
    ImageIcon imageIcon = new ImageIcon(dimg);

    startLabel.setLayout(new BoxLayout(startLabel, BoxLayout.PAGE_AXIS));//boxlayout is good layout for this

    startLabel.setIcon(imageIcon);
    //startLabel.setAlignmentX(CENTER_ALIGNMENT);
    //startLabel.setAlignmentY(CENTER_ALIGNMENT);
    startLabel.setSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
    start = new JButton();//startbutton, game begins after this is pressed
    try {
      img = ImageIO.read(new File("images/startb.png"));
    } catch (IOException ex) {
    }
    start.addActionListener(actionL);
    dimg = img.getScaledInstance(img.getWidth() + bsize * 2, img.getHeight() + bsize, Image.SCALE_SMOOTH);//image scaling
    start.setIcon(new ImageIcon(dimg));
    start.setPreferredSize(new Dimension(img.getWidth() * 2, img.getHeight() * 2));
    start.setBorder(null);
    start.setMargin(null);
    start.setContentAreaFilled(false);
    start.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    //start.setAlignmentY(CENTER_ALIGNMENT);

    credits = new JButton();
    try {
      img = ImageIO.read(new File("images/creditsb.png"));
    } catch (IOException ex) {
    }
    credits.addActionListener(actionL);
    dimg = img.getScaledInstance(img.getWidth() + bsize * 2, img.getHeight() + bsize, Image.SCALE_SMOOTH);//image scaling
    credits.setIcon(new ImageIcon(dimg));//image scaling
    credits.setPreferredSize(new Dimension(img.getWidth() * 2, img.getHeight() * 2));
    credits.setBorder(null);
    credits.setMargin(null);
    credits.setContentAreaFilled(false);
    credits.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    //credits.setAlignmentY(CENTER_ALIGNMENT);

    musicb = new JButton();
    try {
      img = ImageIO.read(new File("images/musicbOn.png"));

    } catch (IOException ex) {
      System.out.print(ex);
    }
    musicb.addActionListener(actionL);
    dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);//image scaling
    musicb.setIcon(new ImageIcon(dimg));//image scaling
    musicb.setPreferredSize(new Dimension(bsize, bsize));//image scaling
    musicb.setBorder(null);
    musicb.setMargin(null);
    musicb.setContentAreaFilled(false);
    //musicb.setAlignmentX(JComponent.LEFT_ALIGNMENT);
    //musicb.setAlignmentY(LEFT_ALIGNMENT);

    //musicLabel = new JLabel();
    //musicLabel.setLayout(new BoxLayout(musicLabel, BoxLayout.LINE_AXIS));
    //musicLabel.setSize(new Dimension(img.getWidth()*bsize, img.getHeight()*bsize));
    //musicLabel.add(musicb);
    //musicLabel.setAlignmentX(RIGHT_ALIGNMENT);
    creditsLabel = new JLabel("<html><div style=\'text-align: center;\'>" + "Version: 1.0'Alpha'" + "<br>" + "Powered by: Java" + "</html>");//bottomlabel wrote in html to make it center
    creditsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    creditsLabel.setVerticalAlignment(SwingConstants.CENTER);
    //startLabel.add(Box.createRigidArea(new Dimension(bsize * 200,0)));
    //startLabel.add(Box.createHorizontalGlue());
    //startLabel.add(Box.createHorizontalBox());
    //startLabel.add(musicb);
    //startLabel.add(Box.createHorizontalGlue());

    //startLabel.add(musicb);
    //startLabel.add(Box.createVerticalBox());
    startLabel.add(Box.createVerticalGlue());//glue between jcomponents
    startLabel.add(start);
    //startLabel.add(Box.createVerticalGlue());
    startLabel.add(Box.createRigidArea(new Dimension(0, bsize * 2)));//space between start and creditbuttons
    startLabel.add(credits);
    //startLabel.add(Box.createRigidArea(new Dimension(0, bsize)));

    startLabel.add(Box.createVerticalGlue());

    startLabel.add(creditsLabel);

    frame.add(startLabel);

    frame.setVisible(true);
    frame.pack();

  }

  public void initGame() {//this inits actual game
    frame.setLayout(new BorderLayout());

    frame.remove(startLabel);///SUPER VERY UGLY WAY TO CODE THIS!! MAYBE SHOULD HAVE DONE WITH CARDLAYOUT!! but it works so..

    BufferedImage img = null;
    Image dimg = null;

    this.setLayout(new BorderLayout());//contentpanel layout
    contentPanel = new JPanel(new BorderLayout());

    storePanel = new JPanel();//panel for towerstore, bottom
    storePanel.setBackground(Color.GREEN);
    storePanel.setLayout(new BoxLayout(storePanel, BoxLayout.LINE_AXIS));//box layout is super good for this. and line_axis makes it horizontal

    towerInfoPane = new JPanel();//for all towerinfolabels on the right
    towerInfoPane.setBackground(Color.GREEN);
    towerInfoPane.setLayout(new BoxLayout(towerInfoPane, BoxLayout.PAGE_AXIS));//booox. vertical page_axis

    playerInfoPane = new JPanel();//player infos, moneys and healt. this is inside of storepanel
    playerInfoPane.setBackground(Color.GREEN);
    playerInfoPane.setLayout(new BoxLayout(playerInfoPane, BoxLayout.PAGE_AXIS));//vertical

    //all the buttons and button pics, sizes,outlooks
    tower = new JButton();
    try {
      img = ImageIO.read(new File("images/tower.png"));

    } catch (IOException ex) {
    }
    dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
    tower.setIcon(new ImageIcon(dimg));
    tower.setPreferredSize(new Dimension(bsize, bsize));
    tower.setBorder(null);
    tower.setMargin(null);
    tower.setName("tower");

    tower2 = new JButton();
    try {
      img = ImageIO.read(new File("images/tower5.png"));

    } catch (IOException ex) {
    }
    dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
    tower2.setIcon(new ImageIcon(dimg));
    tower2.setPreferredSize(new Dimension(bsize, bsize));
    tower2.setBorder(null);
    tower2.setMargin(null);

    tower3 = new JButton();
    try {
      img = ImageIO.read(new File("images/tower3.png"));

    } catch (IOException ex) {
    }
    dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
    tower3.setIcon(new ImageIcon(dimg));
    tower3.setPreferredSize(new Dimension(bsize, bsize));
    tower3.setBorder(null);
    tower3.setMargin(null);

    tower4 = new JButton();
    try {
      img = ImageIO.read(new File("images/tower6.png"));

    } catch (IOException ex) {
    }
    dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
    tower4.setIcon(new ImageIcon(dimg));
    tower4.setPreferredSize(new Dimension(bsize, bsize));
    tower4.setBorder(null);
    tower4.setMargin(null);

    tower5 = new JButton();
    try {
      img = ImageIO.read(new File("images/tower8.png"));

    } catch (IOException ex) {
    }
    dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
    tower5.setIcon(new ImageIcon(dimg));
    tower5.setPreferredSize(new Dimension(bsize, bsize));
    tower5.setBorder(null);
    tower5.setMargin(null);

    update = new JButton();
    try {
      img = ImageIO.read(new File("images/upgradeb2.png"));

    } catch (IOException ex) {
    }
    dimg = img.getScaledInstance(bsize * 3, bsize, Image.SCALE_SMOOTH);
    update.setIcon(new ImageIcon(dimg));
    update.setPreferredSize(new Dimension(bsize * 3, bsize));
    update.setBorder(null);
    update.setMargin(null);
    update.setContentAreaFilled(false);
    update.setVisible(false);//not visible if tower is not clicked

    sell = new JButton();
    try {
      img = ImageIO.read(new File("images/sellb.png"));

    } catch (IOException ex) {
    }
    dimg = img.getScaledInstance(bsize * 3, bsize, Image.SCALE_SMOOTH);
    sell.setIcon(new ImageIcon(dimg));
    sell.setPreferredSize(new Dimension(bsize * 3, bsize));
    sell.setBorder(null);
    sell.setMargin(null);
    sell.setContentAreaFilled(false);
    sell.setVisible(false);//not visible if tower is not clicked

    update.addActionListener(actionL);
    sell.addActionListener(actionL);

    tower.addActionListener(actionL);
    tower2.addActionListener(actionL);
    tower3.addActionListener(actionL);
    tower4.addActionListener(actionL);
    tower5.addActionListener(actionL);

    this.addMouseListener(new MouseListener());
    this.addMouseMotionListener(new MouseListener());

    playerinfo = new JLabel("Wave: " + String.valueOf(arena.getPlayer().getLevel()));//wave..
    playerinfo4 = new JLabel("Time: " + Math.round(Math.abs((pauseFrame / fps) - pSec)));//time to next wave

    towerinfo = new JLabel();
    towerinfo2 = new JLabel();
    towerinfoLong = new JLabel();
    towerUpPrice = new JLabel();
    towerSellPrice = new JLabel();

    icon = new ImageIcon("images/heart2.png"); // load the image to a imageIcon
    Image image = icon.getImage(); // transform it 
    Image newimg = image.getScaledInstance(bsize / 2, bsize / 2, Image.SCALE_SMOOTH); // scale it the smooth way  
    icon = new ImageIcon(newimg);  //transform back
    playerinfo2 = new JLabel(String.valueOf(arena.getPlayer().getHealt()), icon, 0);

    icon2 = new ImageIcon("images/money.png"); // load the image to a imageIcon
    image = icon2.getImage(); // transform it 
    newimg = image.getScaledInstance(bsize / 2, bsize / 2, Image.SCALE_SMOOTH); // scale it the smooth way  
    icon2 = new ImageIcon(newimg);  //transform back
    playerinfo3 = new JLabel(String.valueOf(arena.getPlayer().getMoney()), icon2, 0);

    //add all components to panels, check names
    playerInfoPane.add(playerinfo2);//healt
    playerInfoPane.add(playerinfo3);//moneys

    storePanel.add(playerInfoPane);
    storePanel.add(tower);
    storePanel.add(Box.createHorizontalGlue());
    storePanel.add(tower2);
    storePanel.add(Box.createHorizontalGlue());
    storePanel.add(tower3);
    storePanel.add(Box.createHorizontalGlue());
    storePanel.add(tower4);
    storePanel.add(Box.createHorizontalGlue());
    storePanel.add(tower5);

    towerInfoPane.add(playerinfo);
    towerInfoPane.add(playerinfo4);
    towerInfoPane.add(Box.createRigidArea(new Dimension(0, bsize)));
    towerInfoPane.add(towerinfo);
    towerInfoPane.add(towerinfo2);
    towerInfoPane.add(towerUpPrice);
    towerInfoPane.add(towerSellPrice);
    towerInfoPane.add(Box.createRigidArea(new Dimension(0, bsize / 2)));
    towerInfoPane.add(towerinfoLong);
    towerInfoPane.add(Box.createVerticalGlue());
    towerInfoPane.add(update);
    towerInfoPane.add(Box.createRigidArea(new Dimension(0, bsize / 4)));
    towerInfoPane.add(sell);
    towerInfoPane.add(Box.createRigidArea(new Dimension(0, bsize)));

    towerInfoPane.setPreferredSize(new Dimension(bsize * 3, bsize * 3));

    contentPanel.add(this, BorderLayout.CENTER);
    contentPanel.add(storePanel, BorderLayout.SOUTH);

    //finally add content panel and towerinfo to frame
    frame.add(contentPanel, BorderLayout.CENTER);
    frame.add(towerInfoPane, BorderLayout.EAST);

    frame.setVisible(true);
    game.start();
    frame.pack();

  }

  public void music(boolean musicOn) {//audiopalyer, plays wav files
    try {
      if (musicOn) {
        //InputStream test = new FileInputStream("audio/kvakmix2.wav");//one can change this to different. eg. "audio/ANKKA1.wav"

        //BGM = new AudioStream(test);
        AudioInputStream stream = AudioSystem.getAudioInputStream(new File("audio/kvakmix2.wav"));
        //MD = BGM.getData();
        //loop = new ContinuousAudioDataStream(MD);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
        //AudioPlayer.player.start(BGM);//start music
        //MGP.start(loop);
      } else {
        clip.stop();
        //AudioPlayer.player.stop(BGM);//stop
      }
    } catch (FileNotFoundException e) {
      System.out.print(e+ "not");
    } catch (IOException ee) {
      System.out.print(ee+ "io");
    } catch (Exception e) {
      System.out.print("asd "+e);
    }
  }

  public Dimension getPreferredSize() {//SIZE OF THIS VIEW JPANEL
    return new Dimension(bsize * cols, bsize * rows);
  }

  private class ButtonListener implements ActionListener {
//pretty simple just buttonlistener

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == tower) {
        btnPress = true;
        towerid = "tower";
        towerinfo.setText("Price: " + Integer.toString(arena.getImTowers().get(0).getPrice()));
        towerinfoLong.setText(arena.getImTowers().get(0).getInfo());
        towerinfo2.setText("");
        towerUpPrice.setText("");
        towerSellPrice.setText("");
        update.setVisible(false);
        sell.setVisible(false);
        towerInfoPane.updateUI();
      }
      if (e.getSource() == tower2) {
        btnPress = true;
        towerid = "tower2";
        towerinfo.setText("Price: " + Integer.toString(arena.getImTowers().get(1).getPrice()));
        towerinfoLong.setText(arena.getImTowers().get(1).getInfo());
        towerinfo2.setText("");
        towerUpPrice.setText("");
        towerSellPrice.setText("");
        update.setVisible(false);
        sell.setVisible(false);
        towerInfoPane.updateUI();
      }
      if (e.getSource() == tower3) {
        btnPress = true;
        towerid = "tower3";
        towerinfo.setText("Price: " + Integer.toString(arena.getImTowers().get(2).getPrice()));
        towerinfoLong.setText(arena.getImTowers().get(2).getInfo());
        towerinfo2.setText("");
        towerUpPrice.setText("");
        towerSellPrice.setText("");
        update.setVisible(false);
        sell.setVisible(false);
        towerInfoPane.updateUI();
      }
      if (e.getSource() == tower4) {
        btnPress = true;
        towerid = "tower4";
        towerinfo.setText("Price: " + Integer.toString(arena.getImTowers().get(3).getPrice()));
        towerinfoLong.setText(arena.getImTowers().get(3).getInfo());
        towerinfo2.setText("");
        towerUpPrice.setText("");
        towerSellPrice.setText("");
        update.setVisible(false);
        sell.setVisible(false);
        towerInfoPane.updateUI();
      }
      if (e.getSource() == tower5) {
        btnPress = true;
        towerid = "tower5";
        towerinfo.setText("Price: " + Integer.toString(arena.getImTowers().get(4).getPrice()));
        towerinfoLong.setText(arena.getImTowers().get(4).getInfo());
        towerinfo2.setText("");
        towerUpPrice.setText("");
        towerSellPrice.setText("");
        update.setVisible(false);
        sell.setVisible(false);
        towerInfoPane.updateUI();

      }

      if (e.getSource() == update) {
        String[] upgrade = new String[2];//upgrade[0]=DMG, upgrade[1]=RANGE
        upgrade = contr.upgradeTower(utower);
        towerinfo.setText(upgrade[0]);
        towerinfo2.setText(upgrade[1]);
        towerSellPrice.setText("");
        towerUpPrice.setText("");
        //update.setVisible(false);
        towerInfoPane.updateUI();

      }
      //sells the tower you have selected
      if (e.getSource() == sell) {
        contr.sell(utower);
        towerclick = false;
        towerinfo.setText("");
        towerinfo2.setText("");
        towerUpPrice.setText("");
        towerSellPrice.setText("");
        update.setVisible(false);
        sell.setVisible(false);
        towerInfoPane.updateUI();
      }

      if (e.getSource() == start) {
        musicOn = false;
        music(musicOn);
        initGame();
      }
      if (e.getSource() == credits) {//when credits button clicked text changes to this
        creditsLabel.setText("<html><div style=\'text-align: center;\'>" + "Developed by:M2ko & eetz1" + "<br>" + "Music by:OttoPatrik" + "</html>");
      }
      if (e.getSource() == musicb) {
        if (musicOn) {
          musicOn = false;
          music(musicOn);
          musicb.setIcon(icon);
        } else {
          musicOn = true;
          music(musicOn);
        }
      }
    }
  }

  private class MouseListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      //add new towers
      if (btnPress && e.getButton() == 1) {
        arena.newTowerPos(e.getY(), e.getX(), towerid);
        btnPress = false;
        towerclick = false;
        towerinfo.setText("");
        towerinfoLong.setText("");
        towerinfo2.setText(arena.getNewTower()[0]);
        towerUpPrice.setText(arena.getNewTower()[1]);
        update.setVisible(false);
        sell.setVisible(false);
        towerSellPrice.setText("");
        towerInfoPane.updateUI();
      } else {//view info of old towers
        for (ModelTower tower : arena.getTowers()) {
          if (e.getX() / bsize == tower.getX() && e.getY() / bsize == tower.getY()) {
            utower = tower;
            towerinfo.setText("Damage: " + Integer.toString(tower.getDamage()));
            towerinfo2.setText("Range: " + Integer.toString(tower.getRange()));
            if (tower.getUpPrice() != 0) {
              towerUpPrice.setText("Upgrade: " + Integer.toString(tower.getUpPrice()));
            } else {
              towerUpPrice.setText("");
            }
            towerSellPrice.setText("Sell: " + Integer.toString((int) tower.getPrice() / 2));
            update.setVisible(true);
            sell.setVisible(true);
            towerInfoPane.updateUI();

            towerclick = true;
          }
        }
      }
      //Allows you to stop placing tower with right mouseclick
      if (e.getButton() == 3 && btnPress) {
        btnPress = false;

      }
      //Clears the tower info
      if (towerclick && (e.getButton() == 1 || e.getButton() == 3) && "grass".equals(arena.getArena()[e.getY() / bsize][e.getX() / bsize].getid())) {
        towerclick = false;
        towerinfo.setText("");
        towerinfo2.setText("");
        towerUpPrice.setText("");
        towerSellPrice.setText("");
        update.setVisible(false);
        sell.setVisible(false);
        towerInfoPane.updateUI();
      }
    }

    public void mouseMoved(MouseEvent e) {
      if (btnPress) {
        mouseX = e.getX();
        mouseY = e.getY();
      }
    }
  }

  //this method spawns enemies
  public void enemySpawner() {
    //spawns enemy every 1sec(after 60frames) and if spawncounter is not too much. also if spawnpause is true spawning is not happening
    if (spawnFrame >= spawnTime && spawnCounter < arena.getSpawnWave() && !sPause) {
      arena.spawnEnemy();
      spawnFrame = 1;//-= spawnTime;
      spawnCounter++;
      isFirst = false;

    } else {
      spawnFrame++;
    }

  }

  //ends game
  public void terminate() {
    running = false;
  }

  //game happens here!
  public void run() {
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    final double ns = 1000000000.0 / fps;
    double delta = 0;
    int updates = 0, frames = 0;

    while (running) {

      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;

      // Update 60 times a second
      while (delta >= 1) {

        //update();
        if (!arena.getPlayer().isAlive()) {//if player is ded game terminates

          towerinfo.setText("Game over");
          towerinfo2.setText("");
          towerUpPrice.setText("");
          update.setVisible(false);
          towerInfoPane.updateUI();
          towerSellPrice.setText("");
          sell.setVisible(false);
          towerInfoPane.updateUI();
          terminate();

        } else if (!sPause) {//if game is not paused aka cooldown between waves, this is true
          enemySpawner();

        } else if (sPause && isFirst) {//in the beginning of the game wait pSec
          if (pauseFrame >= pauseTime) {
            pauseFrame = 1;
            sPause = false;
          } else {
            pauseFrame++;
            playerinfo4.setText("Next in: " + Math.round(Math.abs((pauseFrame / fps) - pSec)));

            sPause = true;

          }
        }
        //if there is no enemies on the arena and enemyspawner has spawned all the enemies and its not first run, there will be spawnpause and new level
        if (arena.getEnemies().isEmpty() && !isFirst && spawnCounter == arena.getSpawnWave()) {
          if (pauseFrame == 1) {
            contr.shootmoney();//when round is over if player has moneytower it makes moneyyy
            shoot_money = true;

          }
          if (pauseFrame >= pauseTime) {
            arena.setLevel();
            playerinfo.setText("Wave: " + String.valueOf(arena.getPlayer().getLevel()));
            spawnCounter = 0;
            pauseFrame = 1;
            sPause = false;
            shoot_money = false;
          } else {
            playerinfo4.setText("Next in: " + Math.round(Math.abs((pauseFrame / fps) - pSec)));
            pauseFrame++;
            sPause = true;
          }

        }

        updates++;
        repaint();
        contr.moving();
        playerinfo2.setText(String.valueOf(arena.getPlayer().getHealt()));//update healt and moneys
        playerinfo3.setText(String.valueOf(arena.getPlayer().getMoney()));
        delta--;

      }

      frames++;
      if (System.currentTimeMillis() - timer >= 1000) {
        timer += 1000;
        frame.setTitle(title + " | ups: " + updates + " | fps: " + frames);
        updates = 0;
        frames = 0;
      }
    }
  }

  //this is needed for doublebuffering it makes image of the game while paintComponent draws new. it happens so quickly that u cant see it.
  public void paint(Graphics g) {

    dbImage = createImage(cols * bsize, rows * bsize);
    buffer = dbImage.getGraphics();
    paintComponent(buffer);
    g.drawImage(dbImage, 0, 0, this);

  }

  public void paintComponent(Graphics g) {

    drawMap(g);
    g.setColor(Color.PINK);
    g.drawRect(-1, -1, bsize * cols, bsize * rows);//draws pinkrectangle around game Jpanel
    drawShoot(g);
    drawTower(g);
    drawEnemy(g);
    drawMoneyShoot(g);
    drawHover(g);

  }

  public void drawMap(Graphics g) {
    ModelBlock[][] grid = arena.getArena();

    BufferedImage img;
    for (int y = 0; y < grid.length; y++) {//DRAWS MAP
      int h = y;
      h = h * bsize;
      for (int x = 0; x < grid[0].length; x++) {
        try {
          int w = x;
          w = w * bsize;
          img = ImageIO.read(new File(grid[y][x].getImg()));
          g.drawImage(img, w, h, bsize, bsize, this);

        } catch (IOException ex) {
          System.out.println(ex);
        }
      }

    }
  }

  public void drawMoneyShoot(Graphics g) {
    for (ModelTower tower : arena.getTowers()) {//For each tower dis is gonna check if there is enemy to shoot

      if ("tower5".equals(tower.getid()) && shoot_money) {
        Color c = (tower.getClr());
        int fontSize = bsize / 2;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(c);
        g.drawString(Integer.toString(tower.damage), tower.getX() * bsize + bsize / 4, tower.getY() * bsize + bsize / 4);
      }
    }
  }

  public void drawShoot(Graphics g) {
    for (ModelTower tower : arena.getTowers()) {//For each tower dis is gonna check if there is enemy to shoot
      try {
        if (tower.getFireTime() >= shootTime) {//tower has to cool down aka load weapons

          if (tower.getFireTime() <= shootTime * tower.getfRate()) {//this is the time how long tower shoots enemy
            tower.setFireTime(tower.getFireTime() + 1);//each tower has it own firetime

            if ("tower3".equals(tower.getid())) {//for roundtoweer there  is different kind of shooting..
              //for (ModelTower tower : arena.getTowers()) {
              for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {//list
                ModelEnemy enemy = iterator.next();
                if (contr.shootround(tower, enemy)) {//drawing shootlines
                  Color c = (tower.getClr());
                  g.setColor(c);
                  g.drawOval((tower.getX() * bsize - tower.getRange() * bsize + bsize / 2), (tower.getY() * bsize - tower.getRange() * bsize + bsize / 2), tower.getRange() * bsize * 2, tower.getRange() * bsize * 2);
                }
              }
            } else if ("tower2".equals(tower.getid())) {//for freezetower there is different kind of shooting..
              int[] shootList = contr.shootable(tower);//drawing shootlines
              Color c = new Color(shootList[0]);
              g.setColor(c);
              g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 3)), (shootList[4] + (bsize / 3)));
              g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 2)), (shootList[4] + (bsize / 2)));
            } else if ("tower4".equals(tower.getid())) {//boosttower "shoots" other towers = boosts them

              contr.shootImprove((BoostTower) tower, null);

            } else if ("tower".equals(tower.getid())) {
              int[] shootList = contr.shootable(tower);//drawing shootlines for lazertowers
              Color c = new Color(shootList[0]);
              g.setColor(c);
              g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 2)), (shootList[4] + (bsize / 2)));
            }
          } else {
            tower.setFireTime(1);
          }

        } else {
          tower.setFireTime(tower.getFireTime() + 1);
        }
      } catch (Exception e) {
      }
    }

  }

  //Draws the rectangle where you are going to place the tower
  public void drawHover(Graphics g) {
    if (btnPress) {
      int[] coords = arena.hover(mouseX, mouseY, towerid);
      //coords[0]=X, coords[1]=Y, coords[2]=COLOR, coords[3]=RANGE
      g.setColor(Color.BLACK);
      //Sets the color as red if you are hovering on wrong square
      if (coords[2] == 1) {
        g.setColor(Color.RED);
      }
      g.drawRect(coords[0] - bsize, coords[1] - bsize, bsize, bsize);
      //Draws the range of the tower
      g.setColor(Color.white);
      g.drawOval(coords[0] - coords[3] * bsize - bsize / 2, coords[1] - coords[3] * bsize - bsize / 2, coords[3] * bsize * 2, coords[3] * bsize * 2);

    }
    //Draws a rectangle around a selected tower
    if (towerclick) {
      g.setColor(Color.BLACK);
      g.drawRect(utower.getX() * bsize, utower.getY() * bsize, bsize, bsize);
      g.setColor(Color.WHITE);
      g.drawOval(utower.getX() * bsize - utower.getRange() * bsize + bsize / 2, utower.getY() * bsize - utower.getRange() * bsize + bsize / 2, utower.getRange() * bsize * 2, utower.getRange() * bsize * 2);
    }
  }

  public void drawEnemy(Graphics g) {

    BufferedImage img;
    try {//DRAWS ENEMIES

      for (int i = 0; i < arena.getEnemies().size(); i++) {
        ModelEnemy enemy = arena.getEnemies().get(i);
        img = ImageIO.read(new File(enemy.getImg()));
        g.drawImage(img, enemy.getMoveX(), enemy.getMoveY(), bsize + (int) (enemy.getSize() * bsize), bsize + (int) (enemy.getSize() * bsize), this);
      }
    } catch (IOException ex) {
      System.out.print(ex);
    }

  }

  public void drawTower(Graphics g) {
    BufferedImage img;
    try {
      for (ModelTower tower : arena.getTowers()) {
        img = ImageIO.read(new File(tower.getImg()));
        g.drawImage(img, tower.getX() * bsize, tower.getY() * bsize, bsize, bsize, this);
      }
    } catch (IOException ex) {
      System.out.print(ex);
    }
  }

  public static void main(String args[]) {
    new View();

  }

}
