package Games;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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
        Scanner console = new Scanner(System.in);
        List<Card> Baraja;
        Baraja = novaBaraja(palo, cartas);
        int dolares =	DINERO;
        boolean jugar = true;

        while (dolares > 0 && jugar) {

            int jugador = 0;
            int tamagotchi = 0;

            List<Card> cartasJugador = new ArrayList<>();
            List<Card> casrtasTamagotchi = new ArrayList<>();

            Baraja = Barajear(Baraja);
            int roundBet = apostar(dolares);

            JOptionPane.showMessageDialog(null,
                    "Tus cartas",
                    "Jugador",
                    JOptionPane.INFORMATION_MESSAGE);
            jugador += sacarCarta(Baraja, jugador, cartasJugador);
            jugador += sacarCarta(Baraja, jugador, cartasJugador);

            JOptionPane.showMessageDialog(null,
                    "La carta de ",
                    "Jugador",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println(pet.getName() + "muestra:");
            tamagotchi += sacarCarta(Baraja, tamagotchi, casrtasTamagotchi);
            System.out.println(pet.getName() +"obtiene: " + tamagotchi);

            boolean another_card;
            while (jugador < 21){
                another_card = Hit(jugador, console);
                if (!another_card) {
                    break;
                } else {
                    jugador += sacarCarta(Baraja, tamagotchi, casrtasTamagotchi);
                }

                for (Card playersCard : cartasJugador) {
                    if (playersCard.casoAS() && jugador > 21) {
                        jugador -= 10;
                    }
                }
            }

            while (tamagotchi < 17 && jugador < 21) {
                System.out.println(pet.getName() + "muestra:" + tamagotchi);
                Card dealerCard = Baraja.remove(0);

                System.out.println(pet.getName() + "obtiene:");
                dealerCard.printCard();
                tamagotchi += dealerCard.giveValue(tamagotchi);
                casrtasTamagotchi.add(dealerCard);

                for (Card dealersCard : casrtasTamagotchi) {
                    if (dealersCard.casoAS() && tamagotchi > 21) {
                        jugador -= 10;
                    }
                }
            }

            System.out.println();
            dolares += Ganador(jugador, tamagotchi, roundBet,pet);
            jugar = jugarNovo(console, dolares);
        }
    }

    public static int sacarCarta(List<Card> newDeck, int playerTotal, List<Card> playersCards){
        int total = 0;
        Card playerCard1 = newDeck.remove(0);
        playerCard1.printCard();
        total += playerCard1.giveValue(playerTotal);
        playersCards.add(playerCard1);
        return total;
    }

    public static List<Card> novaBaraja(String[] suites, String[] name){
        List<Card> deck = new ArrayList<>();
        for (String suite : suites) {
            for (String s : name) {
                Card k = new Card(s, suite);
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
            System.out.println("Tienes: " + total);
            System.out.println("BlackJack!");
            gains_losses = 2 * to_play;
        } else if (total > 21) {
            System.out.println("Tienes: " + total);
            System.out.println("Sobrepasaste");
            gains_losses = -1 * to_play;
        } else if (total == tamagotchi) {
            System.out.println("Tienes: " + total);
            System.out.println( pet.getName() +  "tiene: " + tamagotchi);
            System.out.println("Empate");
            gains_losses = 0;
        } else if (tamagotchi > 21) {
            System.out.println(pet.getName() +  "tiene: " + tamagotchi);
            System.out.println("sobrepaso  Ganaste!");
            gains_losses = 2 * to_play;
        } else if (total < tamagotchi) {
            System.out.println("Tienes: " + total);
            System.out.println(pet.getName() +  "tiene: " + tamagotchi);
            System.out.println("Gana ");
            gains_losses = -1 * to_play;
        } else {
            System.out.println("Tienes: " + total);
            System.out.println(pet.getName() +  "tiene: " + tamagotchi);
            System.out.println("Ganaste!");
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

    public static boolean Hit(int total, Scanner console){
        boolean ans;
        System.out.println();
        System.out.println("Tienes: " + total);
        System.out.println("Hit?" + "Presione Y o N");
        String answer = console.next();
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

    public static boolean jugarNovo(Scanner console, int money){
        System.out.println("Tienes: $" + money);
        if (money == 0) {
            System.out.println("Quebrado gana");
            return false;
        }
        System.out.println("Quieres jugar de nuevo?" + "Presione Y o N");
        String answer = console.next();
        if (answer.indexOf("y") == 0 || answer.indexOf("Y") == 0) {
            return true;
        } else if (answer.indexOf("n") == 0 || answer.indexOf("N") == 0) {
            if (money > 100) {
                System.out.println("Felicidades, Ganaste: $" + (money - 100));
            } else {
                System.out.println("Perdiste: $" + (100 - money));
            }
            return false;
        } else {
            System.out.println();
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

        public int giveValue(int playerTotal){
            return this.value;
        }

        public boolean casoAS(){
            return Ace;
        }

        private int determineCardValue(String name) throws NumberFormatException{
            int value;
            try{
                value = Integer.parseInt(name.substring(0,1));
                return value;
            } catch (NumberFormatException e){
                if (name.charAt(0) == 'K' || name.charAt(0) == 'J' || name.charAt(0) == 'Q' || name.charAt(0) == '0'){
                    value = 10;
                } else if (name.charAt(0) =='A'){
                    value = 11;
                    this.Ace = true;
                } else {
                    value = Integer.parseInt(name.substring(0, 1));
                }
                return value;
            }
        }
    }
}
