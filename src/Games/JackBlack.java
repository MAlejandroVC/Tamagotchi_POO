package Games;

import Classes.Tamagotchi;
import javax.swing.*;
import java.util.*;

public class JackBlack {
    public static void main(Tamagotchi pet){
        int money = 100;
        boolean playing = true;

        ArrayList<Card> deck = new ArrayList<>();
        newDeck(deck);

        Hand playerH = new Hand();
        Hand houseH = new Hand();

        while(money>0 && playing){
            playerH.clear();
            houseH.clear();
            playerH.hit(deck);
            playerH.hit(deck);
            houseH.hit(deck);
            int apuesta = apostar(money);
            String[] options = {"Hit", "Stand"};
            int option;
            while(playerH.getValue() < 21){
                option = JOptionPane.showOptionDialog(null,
                        "Tu mano: \n" + playerH + "\n" +
                        "La mano de " + pet.getName() + ": \n" + houseH + "\n",
                        "Tu turno",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);
                if(option == 1)
                    break;
                playerH.hit(deck);
            }
            if(playerH.blackjack() && playerH.getValue() == 21) {
                JOptionPane.showMessageDialog(null,
                        "Tu mano: \n" + playerH + "\n" +
                                "Blackjack! Ganaste!",
                        "Blackjack!",
                        JOptionPane.PLAIN_MESSAGE);
                money += 2*apuesta;
            } else if(playerH.getValue() > 21) {
                JOptionPane.showMessageDialog(null,
                        "Tu mano: \n" + playerH + "\n" +
                                "Te excediste!",
                        "Perdiste.",
                        JOptionPane.PLAIN_MESSAGE);
                money -= apuesta;
            } else{
                while(houseH.getValue() < 17) {
                    JOptionPane.showMessageDialog(null,
                            "Tu mano: \n" + playerH + "\n" +
                                    "La mano de " + pet.getName() + ": \n" + houseH + "\n",
                            "Turno de " + pet.getName(),
                            JOptionPane.PLAIN_MESSAGE);
                    houseH.hit(deck);
                }
            }
            if(playerH.getValue() > 21 || (playerH.blackjack() && playerH.getValue() == 21))
                System.out.println("Saltar ifs");
            else if(houseH.blackjack() && houseH.getValue() == 21) {
                JOptionPane.showMessageDialog(null,
                        "Tu mano: \n" + playerH + "\n" +
                                "La mano de " + pet.getName() + ": \n" + houseH + "\n" +
                                pet.getName() + " tiene Blackjack.",
                        "Blackjack",
                        JOptionPane.PLAIN_MESSAGE);
                money -= apuesta;
            } else if(houseH.getValue() > 21) {
                JOptionPane.showMessageDialog(null,
                        "Tu mano: \n" + playerH + "\n" +
                                "La mano de " + pet.getName() + ": \n" + houseH + "\n" +
                                pet.getName() + " se excedió.",
                        "Ganaste!",
                        JOptionPane.PLAIN_MESSAGE);
                money += 2*apuesta;
            } else if(playerH.getValue() > houseH.getValue()) {
                JOptionPane.showMessageDialog(null,
                        "Tu mano: \n" + playerH + "\n" +
                                "La mano de " + pet.getName() + ": \n" + houseH + "\n" +
                                "Tu mano es mejor. Ganaste!",
                        "Ganaste!",
                        JOptionPane.PLAIN_MESSAGE);
                money += 2*apuesta;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Tu mano: \n" + playerH + "\n" +
                                "La mano de " + pet.getName() + ": \n" + houseH + "\n" +
                                "La mano de " + pet.getName() + " es mejor." + pet.getName()+ " ganó.",
                        "Perdiste",
                        JOptionPane.PLAIN_MESSAGE);
                money -= apuesta;
            }
            if(JOptionPane.showOptionDialog(null,
                    "Quieres seguir jugando?",
                    "Blackjack",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null) == 1)
                playing = false;
        }
    }

    private static class Card{
        String figura;
        String palo;

        public Card(String figura, String palo){
            this.figura = figura;
            this.palo = palo;
        }

        public Card(String figura){
            this.figura = figura;
            this.palo = "Espadas";
        }

        public int getValue() {
            switch(figura){
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
                case "8":
                    return 8;
                case "9":
                    return 9;
                case "10":
                case "J":
                case "Q":
                case "K":
                    return 10;
                case "A":
                    return 11;
                default:
                    return 0;
            }
        }

        public String toString(){
            return "" + figura + " de " + palo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Card card = (Card) o;

            return figura.equals(card.figura);
        }
    }

    private static class Hand{
        ArrayList<Card> cards = new ArrayList<>();

        public void hit(ArrayList<Card> deck){
            cards.add(deck.remove(0));
        }

        public String toString(){
            String str = "";
            for(Card card : cards)
                str += card + "\n";
            return str;
        }

        public int getValue(){
            int total = 0;
            for(Card card: cards){
                total += card.getValue();
                if(card.figura.equals("A") && total > 21)
                    total -= 10;
            }
            return total;
        }

        public boolean blackjack(){
            if(cards.contains(new Card("J")) && cards.contains(new Card("A"))) return true;
            if(cards.contains(new Card("Q")) && cards.contains(new Card("A"))) return true;
            if(cards.contains(new Card("K")) && cards.contains(new Card("A"))) return true;
            return false;
        }

        public void clear(){
            cards.clear();
        }
    }

    private static int apostar(int money) {
        int bet = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Dispones de: $" + money + "\n" +
                        "Cuanto quieres apostar?",
                "Apostar",
                JOptionPane.QUESTION_MESSAGE));
        while(bet < 10){
            bet = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Apuesta mínima: $10 \n" +
                            "Cuanto quieres apostar?",
                    "Apostar",
                    JOptionPane.WARNING_MESSAGE));
        }
        while(bet > money) {
            bet = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Fondos insuficientes... \n " +
                            "Dispones de: $" + money + "\n" +
                            "Cuanto quieres apostar?",
                    "Apostar",
                    JOptionPane.WARNING_MESSAGE));
        }
        return bet;
    }

    private static void newDeck(ArrayList<Card> deck){
        String[] figuras = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] palos = {"Espadas", "Treboles", "Diamantes", "Corazones"};

        for(String figura: figuras)
            for(String palo: palos)
                deck.add(new Card(figura, palo));

        Collections.shuffle(deck);
    }
}
