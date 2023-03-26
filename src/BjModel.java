import javax.swing.*;
import java.awt.*;

public class BjModel{
    private int curentDrawPlayer=0,curentDrawDealer=0,sumPlayer=0,sumDealer=0,bet=100,totalMoney,pointsPlayer=0,pointsDealer=0;
    private int [][] remainingCards=new int[5][15];
    private int []chanceOfDraw = {100,100,100,100,100,100,100,100,95,95,95,90,90,90,85,85,85,70,50,30,10,0};
    private boolean dealerGameEnd=false;
    BjModel() {

    }
    public String drawCard(int player) {
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

        String cardName = "src/images/"+cardValueString + "_of_"+cardTypeString+".png";

        if(player==1){
            sumPlayer+=cardValue;
        }
        else {
            sumDealer+=cardValue;
        }

        return cardName;
    }

    public String drawDealerCard(int prob){
        if(dealerGameEnd==false && sumDealer<=21 && prob<=chanceOfDraw[sumDealer]){
            return drawCard(2);
        }
        else{
            dealerGameEnd=true;
            return "nada";
        }

    }
    public String drawPlayerCard(){
        return drawCard(1);
    }

    public int getCurentDrawPlayer() {
        return curentDrawPlayer;
    }

    public int getCurentDrawDealer() {
        return curentDrawDealer;
    }

    public int getSumPlayer() {
        return sumPlayer;
    }

    public int getSumDealer() {
        return sumDealer;
    }

    public int getBet() {
        return bet;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public int getPointsPlayer() {
        return pointsPlayer;
    }

    public int getPointsDealer() {
        return pointsDealer;
    }

    public int[][] getRemainingCards() {
        return remainingCards;
    }

    public int getChanceOfDraw(int i) {
        return chanceOfDraw[i];
    }

    public boolean isDealerGameEnd() {
        return dealerGameEnd;
    }

    public void setCurentDrawPlayer(int curentDrawPlayer) {
        this.curentDrawPlayer = curentDrawPlayer;
    }

    public void setCurentDrawDealer(int curentDrawDealer) {
        this.curentDrawDealer = curentDrawDealer;
    }

    public void setSumPlayer(int sumPlayer) {
        this.sumPlayer = sumPlayer;
    }

    public void setSumDealer(int sumDealer) {
        this.sumDealer = sumDealer;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setPointsPlayer(int pointsPlayer) {
        this.pointsPlayer = pointsPlayer;
    }

    public void setPointsDealer(int pointsDealer) {
        this.pointsDealer = pointsDealer;
    }

    public void setRemainingCards(int i, int j, int value) {
        this.remainingCards[i][j] = value;
    }

    public void setChanceOfDraw(int[] chanceOfDraw) {
        this.chanceOfDraw = chanceOfDraw;
    }

    public void setDealerGameEnd(boolean dealerGameEnd) {
        this.dealerGameEnd = dealerGameEnd;
    }
}
