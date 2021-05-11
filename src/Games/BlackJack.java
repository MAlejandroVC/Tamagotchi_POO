package Games;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import Classes.Tamagotchi;

import javax.swing.*;

public class BlackJack {
    public static final int DINERO = 100;
    public static final String[] palo = {"Hearts", "Spades", "Clubs", "Diamonds"};
    public static final String[] cartas = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    Tamagotchi pet;

    public BlackJack(Tamagotchi pet) {
        this.pet = pet;
    }

    public static void main(Tamagotchi pet)	{
        List<Card> Baraja;
        Baraja = novaBaraja(palo, cartas);
        int dolares =	DINERO;
        boolean jugar = true;

        while (dolares > 0 && jugar) {

            int jugador = 0;
            int tamagotchi = 0;

            List<Card> cartasJugador = new ArrayList<>();
            List<Card> cartasTamagotchi = new ArrayList<>();

            Baraja = Barajear(Baraja);
            int roundBet = apostar(dolares);

            JOptionPane.showMessageDialog(null,
                    "Tus cartas",
                    "Jugador",
                    JOptionPane.INFORMATION_MESSAGE);
            jugador += sacarCarta(Baraja, cartasJugador);
            jugador += sacarCarta(Baraja, cartasJugador);

            JOptionPane.showMessageDialog(null,
                    "La carta de " + pet.getName(),
                    "" + pet.getName(),
                    JOptionPane.INFORMATION_MESSAGE);
            tamagotchi += sacarCarta(Baraja, cartasTamagotchi);

            boolean another_card;
            while (jugador < 21){
                another_card = Hit(jugador);
                if (!another_card) {
                    break;
                } else {
                    jugador += sacarCarta(Baraja, cartasJugador);
                }

                for (Card playersCard : cartasJugador) {
                    if (playersCard.casoAS() && jugador > 21) {
                        jugador -= 10;
                    }
                }
            }

            JOptionPane.showMessageDialog(null,
                    "Las cartas de " + pet.getName(),
                    "" + pet.getName(),
                    JOptionPane.INFORMATION_MESSAGE);
            while (tamagotchi < 17 && jugador < 21) {
                Card dealerCard = Baraja.remove(0);

                dealerCard.printCard();
                tamagotchi += dealerCard.giveValue();
                cartasTamagotchi.add(dealerCard);

                for (Card dealersCard : cartasTamagotchi) {
                    if (dealersCard.casoAS() && tamagotchi > 21) {
                        jugador -= 10;
                    }
                }
            }

            System.out.println();
            dolares += Ganador(jugador, tamagotchi, roundBet,pet);
            jugar = jugarNovo(dolares);
        }
    }

    public static int sacarCarta(List<Card> newDeck, List<Card> playersCards){
        int total = 0;
        Card playerCard1 = newDeck.remove(0);
        playerCard1.printCard();
        total = playerCard1.giveValue();
        playersCards.add(playerCard1);
        return total;
    }

    public static List<Card> novaBaraja(String[] suites, String[] names){
        List<Card> deck = new ArrayList<>();
        for (String suite : suites) {
            for (String name : names) {
                Card k = new Card(name, suite);
                deck.add(k);
            }
        }
        return deck;
    }

    public static List<Card> Barajear(List<Card> deck){
        List<Card> shuffledDeck = new ArrayList<>();
        int r;
        while (deck.size() > 0){
            Random card = new Random();
            r = card.nextInt(deck.size());
            Card temp = deck.remove(r);
            shuffledDeck.add(temp);
        }
        return shuffledDeck;
    }

    public static int Ganador(int total, int tamagotchi, int to_play, Tamagotchi pet) {
        int gains_losses;
        if (total == 21) {
            JOptionPane.showMessageDialog(null,
                    "Tienes: 21 \n BlackJack!",
                    "Ganaste!",
                    JOptionPane.PLAIN_MESSAGE);
            gains_losses = 2 * to_play;
        } else if (total > 21) {
            JOptionPane.showMessageDialog(null,
                    "Tienes: " + total + "\nSobrepasaste",
                    "Perdiste",
                    JOptionPane.PLAIN_MESSAGE);
            gains_losses = -1 * to_play;
        } else if (total == tamagotchi) {
            JOptionPane.showMessageDialog(null,
                    "Tienes: " + total + "\n" + pet.getName() +  "tiene: " + tamagotchi,
                    "Empate",
                    JOptionPane.PLAIN_MESSAGE);
            gains_losses = 0;
        } else if (tamagotchi > 21) {
            JOptionPane.showMessageDialog(null,
                    pet.getName() +  "tiene: " + tamagotchi + "\nSobrepasó",
                    "Ganaste!",
                    JOptionPane.PLAIN_MESSAGE);
            gains_losses = 2 * to_play;
        } else if (total < tamagotchi) {
            JOptionPane.showMessageDialog(null,
                    "Tienes: " + total + "\n" + pet.getName() +  "tiene: " + tamagotchi,
                    "Perdiste",
                    JOptionPane.PLAIN_MESSAGE);
            gains_losses = -1 * to_play;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Tienes: " + total + "\n" + pet.getName() +  "tiene: " + tamagotchi,
                    "Ganaste!",
                    JOptionPane.PLAIN_MESSAGE);
            gains_losses = 2 * to_play;
        }
        return gains_losses;
    }

    public static int apostar(int money) {
        int bet = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Dispones de: $" + money + "\n" +
                        "Cuanto quieres apostar?",
                "Apostar",
                JOptionPane.QUESTION_MESSAGE));

        while(bet > money || bet < 10){
            if (bet < 10) {
                bet = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Apuesta mínima: $10 \n" +
                                "Cuanto quieres apostar?",
                        "Apostar",
                        JOptionPane.WARNING_MESSAGE));
            } else {
                bet = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Fondos insuficientes... \n " +
                                "Dispones de: $" + money + "\n" +
                                "Cuanto quieres apostar?",
                        "Apostar",
                        JOptionPane.WARNING_MESSAGE));
            }
        }
        return bet;
    }

    public static boolean Hit(int total){
        boolean ans;
        String answer = JOptionPane.showInputDialog(null,
                "Tienes: " + total + "\nDeseas otra carta? (Y/N)",
                "Hit or Stand",
                JOptionPane.QUESTION_MESSAGE);
        if (answer.indexOf("y") == 0 || answer.indexOf("Y") == 0) {
            ans = true;
        } else if (answer.indexOf("n") == 0 || answer.indexOf("N") == 0) {
            ans = false;
        }	else {
            System.out.println();
            ans = false;
        }
        return ans;
    }

    public static boolean jugarNovo(int money){
        if (money == 0) {
            JOptionPane.showMessageDialog(null,
                    "Te quedaste sin dinero! \nGAME OVER",
                    "Perdiste",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String answer = JOptionPane.showInputDialog(null,
                "Tienes: $" + money + "\nQuieres jugar de nuevo? (Y/N)");
        if (answer.indexOf("y") == 0 || answer.indexOf("Y") == 0) {
            return true;
        } else if (answer.indexOf("n") == 0 || answer.indexOf("N") == 0) {
            if (money > 100) {
                JOptionPane.showMessageDialog(null,
                        "Ganaste: $" + (100 - money),
                        "Felicidades",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Perdiste: $" + (100 - money),
                        "Chale",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return false;
        }
        return false;
    }

    public static class Card{
        private int value;
        private String name;
        private String suite;
        private boolean Ace;

        public Card(String name, String suite){
            this.name = name;
            this.suite = suite;
            this.value = determineCardValue(name);
        }

        public void printCard(){
            JOptionPane.showMessageDialog(null,
                    this.name + " de " + this.suite,
                    "Carta",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        public int giveValue(){
            return this.value;
        }

        public boolean casoAS(){
            return Ace;
        }

        private int determineCardValue(String name) throws NumberFormatException{
            switch(name){
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
                    this.Ace = true;
                    return 11;
                default:
                    return 0;
            }
        }
    }
}
