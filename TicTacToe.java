import java.awt.*;
import java.awt.event.*;
class TicTacToe extends Frame implements MouseListener
{
    //Frame size
    int height = 520;
    int width= 420;
    // Border Details
    int BorderHeight=height;
    int BorderWidth=width-12;
    int BorderBreath=5;
    //Grid Details
    int Gridbreath =5;

    int coly=170, rowx=137;
    int XX=126,YY =139;
    int axisX=35,axisY=65;
    int Y=0, X=0;
    String[][] game= {{"0","0","0"},{"0","0","0"},{"0","0","0"}};
    String COMP="Your",Form="Start",text="Your's Turn!!";
    boolean Won=false;
    int x=-1,y=-1;
    String ai="First",player="",computer="";
    TicTacToe()
    {
        setBackground(Color.YELLOW);
        setSize(width,height);
        setResizable(false);
        setVisible(true);
        setTitle("Tic Tac Toe");
        addMouseListener(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent evt) {
        X = evt.getX();
        Y = evt.getY();
        if(X>=100 && X<=299 && Y>=307 && Y<=350  && Form.equals("Start"))
            Form="Choice";
        else if((X>=90 && X<=160 && Y>=200 && Y<=280) || (X>=240 && X<=310 && Y>=205 && Y<=275) && Form.equals("Choice"))
        {
            player=(X<=160)?"x":"o";
            computer=(player.equals("x"))?"o":"x";
            Form="Body";
        }
        else if(Won||Draw())
        {
            game= new String[][]{{"0", "0", "0"}, {"0", "0", "0"}, {"0", "0", "0"}};
            text="Your's Turn!";
            Won=false;Draw();
        }
        else{
            Player();
            AI();
        }
        repaint();
    }
    public void win_check()
    {
        check();
        if(Won)
        {
            String txt=(check().equals(player))?"YOU":"COMPUTER";
            text=txt+" WON! CLICK TO PLAY AGAIN.";
        }
        else if(Draw())
            text="DRAW! CLICK TO PLAY AGAIN.";
        else
            text=COMP+"'s Turn!!";
    }
    public void AI()
    {
        Brain();
        if(x>-1 && y>-1 && !Won && COMP.equals("Computer")) {
            game[x][y] = computer;
            x = -1;y = -1;
            COMP = "Your";
        }
        win_check();
    }
    public void Brain()
    {
        if(ai.equals("First"))
        {
            int rand=(int) (Math.random()*(3-1))+1;
            if(rand==2 && game[1][1].equals("0"))
            {x=1;y=1;}
            else
            {
                int h[] = new int[]{0,0,2},i=0;
                while(true)
                {
                    int ax = (int) (Math.random() * (3 - 1)) + 1;
                    int ay = (int) (Math.random() * (3 - 1)) + 1;
                    if (game[h[ax]][h[ay]].equals("0")) {
                        x = h[ax];y = h[ay];
                        break;
                    }
                    i++;
                    if(i==4)
                        break;
                }
                if(x==-1 && y==-1)
                {
                    if(game[2][1].equals("0"))
                    {x=2;y=1;}
                    else if(game[1][2].equals("0"))
                    {x=1;y=2;}
                    else if(game[1][0].equals("0"))
                    {x=1;y=0;}
                    else if(game[0][1].equals("0"))
                    {x=0;y=1;}
                }
            }
            ai="Second";
        }
        else {
            if(!Thinking(computer) && !Thinking(player)) {
                ai="First";
                Brain();
            }
        }
    }
    public boolean Thinking(String l)
    {
        int c=0,c1=0,col=2,ax=-1,ay=-1,ax1=-1,ay1=-1;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(game[i][j].equals(l))
                    c++;
                else if(game[i][j].equals("0"))
                {
                    ax=i;ay=j;
                }
                if(game[j][i].equals(l))
                    c1++;
                else if(game[j][i].equals("0"))
                {
                    ax1=j;ay1=i;
                }
            }
            if(c==2 && ax!=-1 && ay!=-1)
            {x=ax;y=ay;return true;}
            else if(c1==2 && ax1!=-1 && ay1!=-1)
            {x=ax1;y=ay1;return true;}
            else
            {c=0;ax=-1;ay=-1;c1=0;ax1=-1;ay1=-1;}
        }
        for(int i=0;i<3;i++)
        {
            if(game[i][i].equals(l))
                c++;
            else if(game[i][i].equals("0"))
            {
                ax=i;ay=i;
            }
            if(game[i][col-i].equals(l))
                c1++;
            else if(game[i][col-i].equals("0"))
            {
                ax1=i;ay1=col-i;
            }
        }
        if(c==2 && ax!=-1 && ay!=-1)
        {x=ax;y=ay;return true;}
        else if(c1==2 && ax1!=-1 && ay1!=-1)
        {x=ax1;y=ay1;return true;}
        return  false;
    }
    public void Player()
    {
        int cc=X/rowx;
        int rr=Y/coly;
        if(game[rr][cc].equals("0") && COMP.equals("Your"))
        {
            game[rr][cc] = player;
            COMP = "Computer";
        }
        else
            COMP="Your";
        win_check();
    }
    public String check()
    {
        for(int i = 0;i<3;i++)
            if (game[i][0].equals(game[i][1]) && game[i][1].equals(game[i][2])) {
                if(game[i][0].equals("x") || game[i][0].equals("o")) {
                    Won = true;
                    return game[i][0];
                }
            }
        for(int i = 0;i<3;i++)
            if (game[0][i].equals(game[1][i]) && game[1][i].equals(game[2][i])) {
                if(game[0][i].equals("x") || game[0][i].equals("o")) {
                    Won = true;
                    return game[0][i];
                }
            }
        if (game[0][0].equals(game[1][1]) && game[1][1].equals(game[2][2]) && (game[1][1].equals("x") || game[1][1].equals("o")))
        {Won=true;return game[1][1];}
        if (game[0][2].equals(game[1][1]) && game[1][1].equals(game[2][0]) && (game[1][1].equals("x") || game[1][1].equals("o")))
        {Won=true;return game[1][1];}
        return "";
    }
    public boolean Draw()
    {
        int c=0;
        for(int i=0;i<9;i++)
            if(game[i/3][i%3].equals("x") || game[i/3][i%3].equals("o"))
                c++;
        check();
        return c == 9 && !Won;
    }
    public void choice(Graphics ch)
    {
        ch.setColor(Color.BLACK);
        ch.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
        ch.drawString("CHOOSE YOUR SYMBOL",30,180);
        ch.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) ch;
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g2d.drawLine(95,210,155,270);
        g2d.drawLine(155,210,95,270);
        ch.setColor(Color.BLACK);
        ch.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
        ch.drawString("OR",185,270);
        ch.setColor(Color.BLUE);
        g2d = (Graphics2D) ch;
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g2d.drawOval(245,210,60,60);
    }
    public  void border(Graphics b)
    {
        b.setColor(Color.BLACK);
        b.fillRect(0,BorderHeight-13,BorderWidth+5,BorderBreath);
        b.fillRect(0,33,BorderWidth,BorderBreath);
        b.fillRect(8,33,BorderBreath,BorderHeight-70+25);
        b.fillRect(BorderWidth,33,BorderBreath,BorderHeight-70+25);
    }
    public void Grid(Graphics Gd)
    {
        Gd.setColor(Color.DARK_GRAY);
        for(int x=137;x<410;x+=137)
            Gd.fillRect(x,33+13, Gridbreath,BorderHeight-70-23);
        for(int y =170;y<510-30;y+=170-15)
            Gd.fillRect(20,y,BorderWidth-30, Gridbreath);
        Gd.setColor(Color.BLACK);
        Gd.fillRect(0,BorderHeight-40,BorderWidth,BorderBreath);
    }
    public void font(Graphics f)
    {
        f.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        f.setColor(Color.RED);
        f.drawString(text,30,BorderHeight-18);
    }
    public void Circle(Graphics cr)
    {
        cr.setColor(Color.BLUE);
        Graphics2D g2d = (Graphics2D) cr;
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g2d.drawOval(axisX+XX,axisY+YY,95,95);
    }
    public void start(Graphics s)
    {
        s.setColor(Color.BLUE);
        s.setFont(new Font("TimesNewRoman", Font.BOLD, 60));
        s.drawString("Tic Tac Toe",40,150);
        s.setFont(new Font("TimesNewRoman", Font.BOLD, 33));
        s.drawString("Computer VS Human",43,180);
        s.setFont(new Font("TimesNewRoman", Font.BOLD, 60));
        s.setColor(Color.RED);
        s.drawString("START",100,350);
        s.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        s.setColor(Color.BLACK);
        s.drawString("@Developed by RATH95",115,500);
    }
    public void Cross(Graphics c)
    {
        c.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) c;
        g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        g2d.drawLine(axisX+XX, axisY+YY,axisX+XX+120-35,axisY+YY+160-65);
        g2d.drawLine(axisX+XX+120-35,axisY+YY,axisX+XX,axisY+YY+160-65);
    }
    public void Display(Graphics g)
    {
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                XX*=c;
                YY*=r;
                if (game[r][c].equals("x"))
                    Cross(g);
                if (game[r][c].equals("o"))
                    Circle(g);
                XX=126;YY=139;
            }
        }
    }
    public void paint(Graphics g)
    {
        border(g);
        switch (Form) {
            case "Start":
                start(g);
                break;
            case "Choice":
                choice(g);
                game = new String[][]{{"0", "0", "0"}, {"0", "0", "0"}, {"0", "0", "0"}};
                break;
            case "Body":
                Grid(g);
                font(g);
                Display(g);
                break;
        }
    }
    public static  void main(String[] args)
    {
        new TicTacToe();
    }
}