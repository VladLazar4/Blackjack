import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BjController {

    String playerName;
    private BjModel m_model;
    private BjView m_view;
    BjController(BjModel model, BjView view) {
        m_model = model;
        m_view = view;

        view.addStartListener(new StartListener());
        view.addDrawCardListener(new DrawCardListener());
        view.addStayListener(new StayListener());
        view.addRetryListener(new RetryListener());
        view.addBetListener(new BetListener());
        view.addEndGameListener(new EndGameListener());
    }

    class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                playerName = m_view.tfPlayerName.getText();
                m_model.setTotalMoney(Integer.parseInt(m_view.tfTotalMoney.getText()));
                m_model.setSumDealer(0);
                m_model.setSumPlayer(0);
                m_model.setDealerGameEnd(false);
                m_model.setCurentDrawPlayer(0);
                m_model.setCurentDrawDealer(0);

                if(m_model.getTotalMoney()>=100){
                    m_model.setBet(100);
                    m_model.setTotalMoney(m_model.getTotalMoney()-m_model.getBet());

                    m_view.panelStartGame.setVisible(false);
                    m_view.btnStart.setVisible(false);
                    m_view.lblGameTitle.setVisible(false);
                    m_view.picLabel.setVisible(false);
                    m_view.getContentPane().setBackground(new Color(0,153,51));
                    m_view.btnDrawCard.setVisible(true);
                    m_view.btnStay.setVisible(true);
                    m_view.btnBet.setVisible(true);
                    m_view.panelTopButtons.setVisible(true);
                    m_view.lblScore.setVisible(true);
                    m_view.lblNameDealer.setVisible(true);
                    m_view.lblBet.setVisible(true);
                    m_view.lblTotalMoney.setVisible(true);
                    m_view.lblNameYou.setText(playerName+":");
                    m_view.lblNameYou.setVisible(true);

                    m_view.lblScore.setText("Score: "+String.valueOf(m_model.getPointsPlayer())+" - "+String.valueOf(m_model.getPointsDealer()));
                    m_view.lblBet.setText("Your bet: " + String.valueOf(m_model.getBet()));
                    m_view.lblTotalMoney.setText("Total tokens: " + String.valueOf(m_model.getTotalMoney()));

                    for(int i=1;i<=4;i++) {
                        for (int j = 2; j <= 14; j++) {
                            m_model.setRemainingCards(i,j,1);
                        }
                    }
                }
                else{
                    m_view.showError("Your bet needs to be at least 100");
                }
            } catch (NumberFormatException nfex) {
                m_view.showError("Your bet needs to be at least 100");
            }
        }
    }

    class DrawCardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                m_model.setCurentDrawPlayer(m_model.getCurentDrawPlayer()+1);
                m_view.showCardsPlayer(m_model.getCurentDrawPlayer(),m_model.drawPlayerCard());
                int prob = (int)(Math.random() * 100);
                if(m_model.isDealerGameEnd()==false && m_model.getSumDealer()<=21 && prob<=m_model.getChanceOfDraw(m_model.getSumDealer())) {
                    m_model.setCurentDrawDealer(m_model.getCurentDrawDealer() + 1);
                    m_view.showCardsDealer(m_model.getCurentDrawDealer(),m_model.drawDealerCard(prob));
                }
                m_view.refresh();
            } catch (NumberFormatException nfex) {
                m_view.showError("Error at card randomizer");
            }
        }
    }

    class StayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                while(m_model.isDealerGameEnd()==false){
                    int prob = (int)(Math.random() * 100);
                    if(m_model.isDealerGameEnd()==false && m_model.getSumDealer()<=21 && prob<=m_model.getChanceOfDraw(m_model.getSumDealer())) {
                        m_model.setCurentDrawDealer(m_model.getCurentDrawDealer()+1);
                        m_view.showCardsDealer(m_model.getCurentDrawDealer(), m_model.drawDealerCard(prob));
                    }
                    else
                        m_model.setDealerGameEnd(true);
                    m_view.refresh();
                }

                System.out.println(m_model.getSumDealer() +" "+ m_model.getSumPlayer());
                m_view.panelTopButtons.setVisible(false);
                m_view.btnRetry.setVisible(true);
                m_view.btnEndGame.setVisible(true);
                m_view.showDealerCards(m_model.getCurentDrawDealer());
                String textEndGame="";
                if(m_model.getSumDealer() >21 && m_model.getSumPlayer() >21)
                    textEndGame="Nobody won :(";
                else{
                    if(m_model.getSumDealer() >21 && m_model.getSumPlayer() <=21)
                        textEndGame="You won!";
                    else if(m_model.getSumPlayer() >21 && m_model.getSumDealer() <=21)
                        textEndGame="Dealer won!";
                    else if(m_model.getSumDealer() == m_model.getSumPlayer())
                        textEndGame="Draw";
                    else if(m_model.getSumDealer() > m_model.getSumPlayer())
                        textEndGame="Dealer won!";
                    else textEndGame="You won!";
                }

                m_view.lblEndGameMesage.setText(textEndGame);
                m_view.lblEndGameMesage.setVisible(true);
                if(textEndGame.compareTo("You won!")==0){
                    m_model.setTotalMoney(m_model.getTotalMoney() + m_model.getBet() * 2);
                    m_model.setPointsPlayer(m_model.getPointsPlayer()+1);
                }
                else if(textEndGame.compareTo("Dealer won!")==0){
                    m_model.setPointsDealer(m_model.getPointsDealer()+1);
                }

            } catch (NumberFormatException nfex) {
                m_view.showError("Error stay button");
            }
        }
    }

    class RetryListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if(m_model.getTotalMoney()- m_model.getBet()<0){
                    m_view.showError("You dont have enough money to play!");
                    System.exit(0);
                }
                for(int i = 1; i<= m_model.getCurentDrawDealer(); i++){
                    m_view.panel[i].setVisible(false);
                    m_view.panelDealer[i].setVisible(false);
                }
                for(int i = 1; i<= m_model.getCurentDrawPlayer(); i++){
                    m_view.panelPlayer[i].setVisible(false);
                }

                m_model.setSumDealer(0);
                m_model.setSumPlayer(0);
                m_model.setDealerGameEnd(false);
                m_model.setCurentDrawPlayer(0);
                m_model.setCurentDrawDealer(0);
                m_model.setBet(100);
                m_model.setTotalMoney(m_model.getTotalMoney() - m_model.getBet());

                m_view.panelTopButtons.setVisible(true);
                m_view.btnDrawCard.setVisible(true);
                m_view.btnStay.setVisible(true);
                m_view.btnStay.setVisible(true);
                m_view.lblEndGameMesage.setVisible(false);
                m_view.btnRetry.setVisible(false);
                m_view.btnEndGame.setVisible(false);

                m_view.lblScore.setText("Score: "+String.valueOf(m_model.getPointsPlayer())+" - "+String.valueOf(m_model.getPointsDealer()));
                m_view.lblBet.setText("Your bet: " + String.valueOf(m_model.getBet()));
                m_view.lblTotalMoney.setText("Total tokens: " + String.valueOf(m_model.getTotalMoney()));

                for(int i=1;i<=4;i++) {
                    for (int j = 2; j <= 14; j++) {
                        m_model.setRemainingCards(i,j,1);
                    }
                }
            } catch (NumberFormatException nfex) {
                m_view.showError("Your bet needs to be at least 100");
            }
        }
    }

    class BetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if(m_model.getTotalMoney() >=100){
                    m_model.setTotalMoney(m_model.getTotalMoney()-100);
                    m_model.setBet(m_model.getBet()+100);
                    m_view.lblBet.setText("Your bet: " + String.valueOf(m_model.getBet()));
                    m_view.lblTotalMoney.setText("Total tokens: " + String.valueOf(m_model.getTotalMoney()));
                }
            } catch (NumberFormatException nfex) {
                m_view.showError("Error in betting");
            }
        }
    }

    class EndGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println(m_model.getPointsDealer()+" "+m_model.getPointsPlayer());
                if(m_model.getPointsDealer()>m_model.getPointsPlayer()){
                    m_view.mesageYouLost(m_model.getCurentDrawDealer(), m_model.getCurentDrawPlayer());//dealer won
                } else if (m_model.getPointsDealer()<m_model.getPointsPlayer()) {
                    m_view.mesageYouWon(m_model.getCurentDrawDealer(), m_model.getCurentDrawPlayer());
                } else  {
                    m_view.mesageDraw(m_model.getCurentDrawDealer(), m_model.getCurentDrawPlayer());//draw
                }
                //System.exit(0);
            } catch (NumberFormatException nfex) {
                m_view.showError("Error in exit");
            }
        }
    }
}
