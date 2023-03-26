import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class BjView extends JFrame{
    JButton btnDrawCard,btnStay,btnBet,btnRetry,btnStart,btnEndGame;
    JLabel lblEndGameMesage,lblBet,lblTotalMoney,lblScore,lblNameYou,lblNameDealer,picLabel,lblGameTitle, lblYouWon, lblYouLost, lblDraw;
    JPanel[] panel = new JPanel[100];
    JPanel[] panelDealer = new JPanel[100];
    JPanel[] panelPlayer = new JPanel[100];
    JPanel panelTopButtons = new JPanel();
    JPanel panelStartGame = new JPanel();
    JTextField tfPlayerName,tfTotalMoney;

    BjView(BjModel model){

        btnDrawCard = new JButton();
        btnDrawCard.setFocusable(false);
        btnDrawCard.setText("Draw");
        btnDrawCard.setVisible(false);

        btnStay = new JButton();
        btnStay.setFocusable(false);
        btnStay.setText("Stay");
        btnStay.setVisible(false);

        btnBet = new JButton();
        btnBet.setFocusable(false);
        btnBet.setText("Bet");
        btnBet.setVisible(false);

        btnRetry = new JButton();
        btnRetry.setBounds(160,400,90,50);
        btnRetry.setFocusable(false);
        btnRetry.setText("Retry");
        btnRetry.setVisible(false);

        btnEndGame = new JButton();
        btnEndGame.setBounds(280,400,90,50);
        btnEndGame.setFocusable(false);
        btnEndGame.setText("End game");
        btnEndGame.setVisible(false);

        lblEndGameMesage= new JLabel();
        lblEndGameMesage.setBounds(200,40,150,30);
        lblEndGameMesage.setFont(new Font("Lucida Calligraphy",Font.BOLD,18));
        lblEndGameMesage.setHorizontalTextPosition(JLabel.CENTER);
        lblEndGameMesage.setVerticalAlignment(JLabel.CENTER);
        lblEndGameMesage.setVisible(false);

        lblScore= new JLabel("Score: 0-0");
        lblScore.setBounds(730,10,150,30);
        lblScore.setFont(new Font("Lucida Calligraphy",Font.BOLD,18));
        lblScore.setHorizontalTextPosition(JLabel.CENTER);
        lblScore.setVerticalAlignment(JLabel.CENTER);
        lblScore.setVisible(false);

        lblBet = new JLabel("Your bet: 100");
        lblBet.setBounds(730,402,90,30);
        lblBet.setFont(new Font("Arial",Font.BOLD,12));
        lblBet.setHorizontalTextPosition(JLabel.CENTER);
        lblBet.setVerticalAlignment(JLabel.CENTER);
        lblBet.setVisible(false);

        lblTotalMoney = new JLabel("Total tokens: ?");
        lblTotalMoney.setBounds(730,422,120,30);
        lblTotalMoney.setFont(new Font("Arial",Font.BOLD,12));
        lblTotalMoney.setHorizontalTextPosition(JLabel.CENTER);
        lblTotalMoney.setVerticalAlignment(JLabel.CENTER);
        lblTotalMoney.setVisible(false);

        lblNameYou = new JLabel();
        lblNameYou.setBounds(10,140,150,30);
        lblNameYou.setFont(new Font("Lucida Calligraphy",Font.BOLD,14));
        lblNameYou.setHorizontalTextPosition(JLabel.CENTER);
        lblNameYou.setVerticalAlignment(JLabel.CENTER);
        lblNameYou.setVisible(false);

        lblNameDealer = new JLabel("Dealer:");
        lblNameDealer.setBounds(10,290,150,30);
        lblNameDealer.setFont(new Font("Lucida Calligraphy",Font.BOLD,14));
        lblNameDealer.setHorizontalTextPosition(JLabel.CENTER);
        lblNameDealer.setVerticalAlignment(JLabel.CENTER);
        lblNameDealer.setVisible(false);

        panelTopButtons.setLayout(new GridLayout(1,3,10,0));
        panelTopButtons.setBackground(new Color(0,153,51));
        panelTopButtons.setBounds(85,20,330,50);
        panelTopButtons.add(btnDrawCard);
        panelTopButtons.add(btnStay);
        panelTopButtons.add(btnBet);
        panelTopButtons.setVisible(false);

        ImageIcon imgbg = new ImageIcon("src/images/HomeScreen.jpg");
        picLabel = new JLabel();
        picLabel.setIcon(imgbg);
        picLabel.setBounds(0,0,870,530);
        picLabel.setHorizontalAlignment(JLabel.CENTER);
        picLabel.setVerticalAlignment(JLabel.CENTER);

        lblGameTitle = new JLabel("Blackjack");
        lblGameTitle.setBounds(35,70,250,60);
        lblGameTitle.setFont(new Font("Lucida Calligraphy",Font.BOLD,40));
        lblGameTitle.setForeground(Color.WHITE);
        lblGameTitle.setHorizontalTextPosition(JLabel.CENTER);
        lblGameTitle.setVerticalAlignment(JLabel.CENTER);
        lblGameTitle.setVisible(true);

        ImageIcon gifYouWon = new ImageIcon("src/images/you-win.gif");
        lblYouWon = new JLabel();
        lblYouWon.setIcon(gifYouWon);
        lblYouWon.setBounds(0,0,870,470);
        lblYouWon.setHorizontalAlignment(JLabel.CENTER);
        lblYouWon.setVerticalAlignment(JLabel.CENTER);
        lblYouWon.setVisible(false);

        ImageIcon gifYouLost = new ImageIcon("src/images/you-lost.gif");
        lblYouLost = new JLabel();
        lblYouLost.setIcon(gifYouLost);
        lblYouLost.setBounds(0,0,870,490);
        lblYouLost.setHorizontalAlignment(JLabel.CENTER);
        lblYouLost.setVerticalAlignment(JLabel.CENTER);
        lblYouLost.setVisible(false);

        ImageIcon gifDraw = new ImageIcon("src/images/draw.gif");
        lblDraw = new JLabel();
        lblDraw.setIcon(gifDraw);
        lblDraw.setBounds(0,0,870,470);
        lblDraw.setHorizontalAlignment(JLabel.CENTER);
        lblDraw.setVerticalAlignment(JLabel.CENTER);
        lblDraw.setVisible(false);


        tfPlayerName = new JTextField("Your name");
        tfTotalMoney = new JTextField("Your bet");

        panelStartGame.setLayout(new GridLayout(2,1,30,5));
        panelStartGame.setBackground(new Color(1f,0f,0f,.0f));
        panelStartGame.setBounds(35,160,220,60);
        panelStartGame.add(tfPlayerName);
        panelStartGame.add(tfTotalMoney);
        panelStartGame.setVisible(true);

        btnStart = new JButton();
        btnStart.setFocusable(false);
        btnStart.setBounds(70,300,130,50);
        btnStart.setText("Start game!");
        btnStart.setVisible(true);

        this.setTitle("Blackjack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(870,530);
        ImageIcon imglogo = new ImageIcon("src/images/Logo.png");
        this.setIconImage(imglogo.getImage());
        this.setLayout(null);
        this.setVisible(true);

        this.add(panelStartGame);
        this.add(btnStart);
        this.add(panelTopButtons);
        this.add(btnRetry);
        this.add(btnEndGame);
        this.add(lblEndGameMesage);
        this.add(lblBet);
        this.add(lblTotalMoney);
        this.add(lblScore);
        this.add(lblNameYou);
        this.add(lblNameDealer);
        this.add(lblGameTitle);
        this.add(picLabel);
        this.add(lblYouWon);
        this.add(lblYouLost);
        this.add(lblDraw);
    }
    void addDrawCardListener(ActionListener mal) {
        btnDrawCard.addActionListener(mal);
    }

    void addStayListener(ActionListener mal) {
        btnStay.addActionListener(mal);
    }
    void addBetListener(ActionListener mal) {
        btnBet.addActionListener(mal);
    }
    void addRetryListener(ActionListener mal) {
        btnRetry.addActionListener(mal);
    }

    void addStartListener(ActionListener mal) {
        btnStart.addActionListener(mal);
    }
    void addEndGameListener(ActionListener mal) {
        btnEndGame.addActionListener(mal);
    }

    void showCardsPlayer(int curentDrawPlayer, String cardPlayer){
        JLabel labelCard = new JLabel();
        labelCard.setIcon( new ImageIcon(cardPlayer));
        JPanel panelAux = new JPanel();
        panelAux.add(labelCard);
        panelAux.setBounds(85*curentDrawPlayer,100,75,115);
        panelPlayer[curentDrawPlayer] = panelAux;
        panelPlayer[curentDrawPlayer].setBackground(new Color(0,153,51));
        panelPlayer[curentDrawPlayer].setVisible(true);
        this.add(panelPlayer[curentDrawPlayer]);
    }

    public void showCardsDealer(int curentDrawDealer, String cardDealer) {
        JLabel labelCard = new JLabel();
        labelCard.setIcon( new ImageIcon(cardDealer));
        JPanel panelAux = new JPanel();
        panelAux.add(labelCard);
        panelAux.setBounds(85*curentDrawDealer,250,75,115);
        panelDealer[curentDrawDealer] = panelAux;
        panelDealer[curentDrawDealer].setBackground(new Color(0,153,51));
        if(curentDrawDealer==1)
            panelDealer[curentDrawDealer].setVisible(true);
        else panelDealer[curentDrawDealer].setVisible(false);

        JLabel labelCardBack = new JLabel();
        labelCardBack.setIcon(new ImageIcon("src/images/back_card.png"));
        JPanel panelAuxBack = new JPanel();
        panelAuxBack.add(labelCardBack);
        panelAuxBack.setBounds(85*curentDrawDealer,250,75,115);
        panelAuxBack.setBackground(new Color(0,153,51));

        panel[curentDrawDealer]=panelAuxBack;
        if(curentDrawDealer==1)
            panel[curentDrawDealer].setVisible(false);
        else panel[curentDrawDealer].setVisible(true);

        this.add(panelAuxBack);
        this.add(panelDealer[curentDrawDealer]);
    }
    public void showDealerCards(int noDrawDealer){
        IntStream.rangeClosed(1, noDrawDealer).forEach(i -> {
            panel[i].setVisible(false);
            panelDealer[i].setVisible(true);
        });
    }

    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    void refresh(){ this.setVisible(true); }

    public void mesageYouWon(int noDrawDealer, int noDrawPlayer) {
        IntStream.rangeClosed(1, noDrawDealer).forEach(i -> {
            panel[i].setVisible(false);
            panelDealer[i].setVisible(false);
        });
        IntStream.rangeClosed(1, noDrawPlayer).forEach(i -> {
            panelPlayer[i].setVisible(false);
        });
        lblNameYou.setVisible(false);
        lblNameDealer.setVisible(false);
        lblYouWon.setVisible(true);
        lblEndGameMesage.setVisible(false);
        lblBet.setVisible(false);
        lblTotalMoney.setVisible(false);
        lblScore.setVisible(false);
        btnRetry.setVisible(false);
        btnEndGame.setVisible(false);
        refresh();
    }

    public void mesageYouLost(int noDrawDealer, int noDrawPlayer) {
        IntStream.rangeClosed(1, noDrawDealer).forEach(i -> {
            panel[i].setVisible(false);
            panelDealer[i].setVisible(false);
        });
        IntStream.rangeClosed(1, noDrawPlayer).forEach(i -> {
            panelPlayer[i].setVisible(false);
        });
        lblNameYou.setVisible(false);
        lblNameDealer.setVisible(false);
        lblYouLost.setVisible(true);
        lblEndGameMesage.setVisible(false);
        lblBet.setVisible(false);
        lblTotalMoney.setVisible(false);
        lblScore.setVisible(false);
        btnRetry.setVisible(false);
        btnEndGame.setVisible(false);
        refresh();
    }

    public void mesageDraw(int noDrawDealer, int noDrawPlayer) {
        IntStream.rangeClosed(1, noDrawDealer).forEach(i -> {
            panel[i].setVisible(false);
            panelDealer[i].setVisible(false);
        });
        IntStream.rangeClosed(1, noDrawPlayer).forEach(i -> {
            panelPlayer[i].setVisible(false);
        });
        lblNameYou.setVisible(false);
        lblNameDealer.setVisible(false);
        lblDraw.setVisible(true);
        lblEndGameMesage.setVisible(false);
        lblBet.setVisible(false);
        lblTotalMoney.setVisible(false);
        lblScore.setVisible(false);
        btnRetry.setVisible(false);
        btnEndGame.setVisible(false);
        refresh();
    }
}