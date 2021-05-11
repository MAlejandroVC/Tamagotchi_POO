import Classes.SPECIAL;
import Classes.Species.Species;
import Classes.Species.Subspecies;
import Classes.Tamagotchi;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class Tests {
    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                Font.MONOSPACED, Font.PLAIN, 12)));

        Object[] options = {"Yes, please",
                "No, thanks",
                "No eggs, no ham!"};
        int n = JOptionPane.showOptionDialog(null,//parent container of JOptionPane
                "Would you like some green eggs to go "
                        + "with that ham?",
                "A Silly Question",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                options[2]);//default button title
    }
}
