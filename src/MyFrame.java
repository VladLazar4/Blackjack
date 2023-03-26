import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    JButton btnDrawCard,btnStay,btnBet,btnRetry,btnStart,btnEndGame;
    JLabel lblEndGameMesage,lblBet,lblTotalMoney,lblScore,lblNameYou,lblNameDealer,picLabel,lblGameTitle;
    JPanel[] panel = new JPanel[100];
    JPanel[] panelDealer = new JPanel[100];
    JPanel[] panelPlayer = new JPanel[100];
    JPanel panelTopButtons = new JPanel();
    JPanel panelStartGame = new JPanel();
    JPanel panelBgStartGame = new JPanel();
    JTextField tfPlayerName,tfTotalMoney;
    boolean dealerGameEnd=false;
    String playerName;
    int curentDrawPlayer=0,curentDrawDealer=0,sumPlayer=0,sumDealer=0,bet=100,totalMoney,pointsPlayer=0,pointsDealer=0;
    int [][] remainingCards=new int[5][15];
    int []chanceOfDraw = {100,100,100,100,100,100,100,100,95,95,95,90,90,90,85,85,85,70,50,30,10,0};
    MyFrame(){

        this.totalMoney = totalMoney;

        btnDrawCard = new JButton();
        btnDrawCard.addActionListener(this);
        btnDrawCard.setFocusable(false);
        btnDrawCard.setText("Draw");
        btnDrawCard.setVisible(false);

        btnStay = new JButton();
        btnStay.addActionListener(this);
        btnStay.setFocusable(false);
        btnStay.setText("Stay");
        btnStay.setVisible(false);

        btnBet = new JButton();
        btnBet.addActionListener(this);
        btnBet.setFocusable(false);
        btnBet.setText("Bet");
        btnBet.setVisible(false);

        btnRetry = new JButton();
        btnRetry.setBounds(160,400,90,50);
        btnRetry.addActionListener(this);
        btnRetry.setFocusable(false);
        btnRetry.setText("Retry");
        btnRetry.setVisible(false);

        btnEndGame = new JButton();
        btnEndGame.setBounds(280,400,90,50);
        btnEndGame.addActionListener(this);
        btnEndGame.setFocusable(false);
        btnEndGame.setText("End game");
        btnEndGame.setVisible(false);

        lblEndGameMesage= new JLabel();
        lblEndGameMesage.setBounds(200,40,150,30);
        lblEndGameMesage.setFont(new Font("Lucida Calligraphy",Font.BOLD,18));
        lblEndGameMesage.setHorizontalTextPosition(JLabel.CENTER);
        lblEndGameMesage.setVerticalAlignment(JLabel.CENTER);
        lblEndGameMesage.setVisible(false);

        lblScore= new JLabel("Score: "+String.valueOf(pointsPlayer)+" - "+String.valueOf(pointsDealer));
        lblScore.setBounds(480,10,150,30);
        lblScore.setFont(new Font("Lucida Calligraphy",Font.BOLD,18));
        lblScore.setHorizontalTextPosition(JLabel.CENTER);
        lblScore.setVerticalAlignment(JLabel.CENTER);
        lblScore.setVisible(false);

        lblBet = new JLabel("Your bet: "+String.valueOf(bet));
        lblBet.setBounds(480,402,90,30);
        lblBet.setFont(new Font("Arial",Font.BOLD,12));
        lblBet.setHorizontalTextPosition(JLabel.CENTER);
        lblBet.setVerticalAlignment(JLabel.CENTER);
        lblBet.setVisible(false);

        lblTotalMoney = new JLabel("Total money: "+String.valueOf(totalMoney));
        lblTotalMoney.setBounds(480,422,120,30);
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

        ImageIcon imgbg = new ImageIcon("HomeScreen4.jpg");
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


        tfPlayerName = new JTextField("Your name");
        tfTotalMoney = new JTextField("Your bet");
        //tfPlayerName.setForeground(new Color());
        //tfPlayerName.setBackground(new Color());

        panelStartGame.setLayout(new GridLayout(2,1,30,5));
        panelStartGame.setBackground(new Color(1f,0f,0f,.0f));
        panelStartGame.setBounds(35,160,220,60);
        panelStartGame.add(tfPlayerName);
        panelStartGame.add(tfTotalMoney);
        panelStartGame.setVisible(true);

        btnStart = new JButton();
        btnStart.addActionListener(this);
        btnStart.setFocusable(false);
        btnStart.setBounds(70,300,130,50);
        btnStart.setText("Start game!");
        btnStart.setVisible(true);

        this.setTitle("Blackjack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(870,530);
        ImageIcon imglogo = new ImageIcon("Logo.png");
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

        refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnStart){
            this.playerName = tfPlayerName.getText();
            this.totalMoney = Integer.parseInt(tfTotalMoney.getText());
            initialValues();
            refresh();
        }
        else if(e.getSource()==btnDrawCard){
            curentDrawPlayer++;
            int prob = (int)(Math.random() * 100);
            if(dealerGameEnd==false && sumDealer<=21 && prob<=chanceOfDraw[sumDealer])
                curentDrawDealer++;
            drawBothCard(prob);
            refresh();
        }
        else if(e.getSource()==btnStay){
            while(dealerGameEnd==false){
                int prob = (int)(Math.random() * 100);
                if(dealerGameEnd==false && sumDealer<=21 && prob<=chanceOfDraw[sumDealer])
                    curentDrawDealer++;
                drawDealerCard(prob);
                refresh();
            }

            panelTopButtons.setVisible(false);
            btnRetry.setVisible(true);
            btnEndGame.setVisible(true);
            showDealerCards(curentDrawDealer);
            String textEndGame="";
            if(sumDealer>21 && sumPlayer>21)
                textEndGame="Nobody won :(";
            else{
                if(sumDealer>21 && sumPlayer<=21)
                    textEndGame="You won!";
                else if(sumPlayer>21 && sumDealer<=21)
                    textEndGame="Dealer won!";
                else if(sumDealer==sumPlayer)
                    textEndGame="Draw";
                else if(sumDealer>sumPlayer)
                    textEndGame="Dealer won!";
                else textEndGame="You won!";
            }
            lblEndGameMesage.setText(textEndGame);
            lblEndGameMesage.setVisible(true);
            if(textEndGame.compareTo("You won!")==0){
                totalMoney=totalMoney+bet*2;
                pointsPlayer++;
            }
            else if(textEndGame.compareTo("Dealer won!")==0){
                pointsDealer++;
            }
        }
        else if(e.getSource()==btnBet){
            if(totalMoney>=100){
                totalMoney-=100;
                bet+=100;
                lblBet.setText("Your bet: " + String.valueOf(bet));
                lblTotalMoney.setText("Total money: " + String.valueOf(totalMoney));
            }
        }
        else if(e.getSource()==btnRetry){
            resetValues();
        }
        else if(e.getSource()==btnEndGame){

        }
    }
    public JPanel drawCard(int player) {
        JLabel labelCard = new JLabel();
        JPanel panelAux = new JPanel();
        // panelAux.setBackground(new Color(0,153,51));
        int cardValue,cardType;
        do{
            cardValue = (int)(Math.random() * (14 - 2 + 1) + 2);
            cardType = (int)(Math.random() * (4 - 1 + 1) + 1);
        }while(remainingCards[cardType][cardValue]==0);
        remainingCards[cardType][cardValue]=0;

        String cardValueString="";
        if(cardValue<11){
            cardValueString = String.valueOf(cardValue);
        }
        else{
            if(cardValue==11)
                cardValueString="ace";
            else if(cardValue==12)
                cardValueString="jack";
            else if(cardValue==13)
                cardValueString="queen";
            else cardValueString="king";
        }

        String cardTypeString="";
        if(cardType==1)
            cardTypeString="clubs";
        else if(cardType==2)
            cardTypeString="diamonds";
        else if(cardType==3)
            cardTypeString="hearts";
        else cardTypeString="spades";

        String cardName = cardValueString + "_of_"+cardTypeString+".png";
        ImageIcon imgcard = new ImageIcon(cardName);
        labelCard.setIcon(imgcard);

        int dimy,curentDraw;
        if(player==1){
            dimy=100;
            sumPlayer+=cardValue;
            curentDraw=curentDrawPlayer;
        }
        else {
            dimy = 250;
            sumDealer+=cardValue;
            curentDraw=curentDrawDealer;
        }

        panelAux.add(labelCard);
        panelAux.setBounds(85*curentDraw,dimy,75,115); ///colt stanga sus, dreapta jos

        return panelAux;
    }
    public void showDealerCards(int noDrawDealer){
        for(int i=1;i<=noDrawDealer;i++){
            panel[i].setVisible(false);
            panelDealer[i].setVisible(true);
        }
    }
    public void drawDealerCard(int prob){
        if(dealerGameEnd==false && sumDealer<=21 && prob<=chanceOfDraw[sumDealer]){
            panelDealer[curentDrawDealer]=drawCard(2);
            panelDealer[curentDrawDealer].setBounds(85*curentDrawDealer,250,75,115);
            panelDealer[curentDrawDealer].setBackground(new Color(0,153,51));
            if(curentDrawDealer==1)
                panelDealer[curentDrawDealer].setVisible(true);
            else panelDealer[curentDrawDealer].setVisible(false);

            JLabel labelCard = new JLabel();
            labelCard.setIcon(new ImageIcon("src/images/back_card.png"));

            JPanel panelAux = new JPanel();
            panelAux.add(labelCard);
            panelAux.setBounds(85*curentDrawDealer,250,75,115);
            panelAux.setBackground(new Color(0,153,51));

            panel[curentDrawDealer]=panelAux;
            if(curentDrawDealer==1)
                panel[curentDrawDealer].setVisible(false);
            else panel[curentDrawDealer].setVisible(true);

            this.add(panelAux);
            this.add(panelDealer[curentDrawDealer]);
        }
        else{
            dealerGameEnd=true;
        }
    }
    public void drawPlayerCard(){
        panelPlayer[curentDrawPlayer]=drawCard(1);
        panelPlayer[curentDrawPlayer].setBackground(new Color(0,153,51));
        panelPlayer[curentDrawPlayer].setVisible(true);

        this.add(panelPlayer[curentDrawPlayer]);
    }
    public void drawBothCard(int prob){
        drawPlayerCard();
        drawDealerCard(prob);
    }


    public void initialDraw(){
        curentDrawPlayer++;
        curentDrawDealer++;
        drawBothCard(100);
        refresh();
    }
    public void resetValues(){

        for(int i=1;i<=curentDrawDealer;i++){
            panel[i].setVisible(false);
            panelDealer[i].setVisible(false);
        }
        for(int i=1;i<=curentDrawPlayer;i++){
            panelPlayer[i].setVisible(false);
        }

        sumDealer=sumPlayer=0;
        dealerGameEnd=false;
        curentDrawPlayer=curentDrawDealer=0;
        bet=100;
        totalMoney-=bet;

        panelTopButtons.setVisible(true);
        btnDrawCard.setVisible(true);
        btnStay.setVisible(true);
        btnStay.setVisible(true);
        lblEndGameMesage.setVisible(false);
        btnRetry.setVisible(false);
        btnEndGame.setVisible(false);

        lblScore.setText("Score: "+String.valueOf(pointsPlayer)+" - "+String.valueOf(pointsDealer));
        lblBet.setText("Your bet: " + String.valueOf(bet));
        lblTotalMoney.setText("Total money: " + String.valueOf(totalMoney));

        for(int i=1;i<=4;i++) {
            for (int j = 2; j <= 14; j++) {
                remainingCards[i][j] = 1;
            }
        }
        initialDraw();
    }

    public void initialValues(){
        sumDealer=sumPlayer=0;
        dealerGameEnd=false;
        curentDrawPlayer=curentDrawDealer=0;

        if(totalMoney>=100){
            bet=100;
            totalMoney-=bet;

            panelStartGame.setVisible(false);
            btnStart.setVisible(false);
            lblGameTitle.setVisible(false);
            picLabel.setVisible(false);
            this.getContentPane().setBackground(new Color(0,153,51));
            btnDrawCard.setVisible(true);
            btnStay.setVisible(true);
            btnBet.setVisible(true);
            panelTopButtons.setVisible(true);
            lblScore.setVisible(true);
            lblNameDealer.setVisible(true);
            lblBet.setVisible(true);
            lblTotalMoney.setVisible(true);
            lblNameYou.setText(this.playerName+":");
            lblNameYou.setVisible(true);

            lblScore.setText("Score: "+String.valueOf(pointsPlayer)+" - "+String.valueOf(pointsDealer));
            lblBet.setText("Your bet: " + String.valueOf(bet));
            lblTotalMoney.setText("Total money: " + String.valueOf(totalMoney));

            for(int i=1;i<=4;i++) {
                for (int j = 2; j <= 14; j++) {
                    remainingCards[i][j] = 1;
                }
            }
            initialDraw();
        }
        else {
            JOptionPane.showMessageDialog(this, "Bet must be at least 100");
        }
    }
    public void refresh(){
        this.setVisible(true);
    }
}
