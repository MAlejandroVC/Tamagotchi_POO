import javax.swing.*;
import java.util.Calendar;

public class Tests {
    public static void main(String[] args) {
        Calendar cal1 = Calendar.getInstance();
        JOptionPane.showMessageDialog(null,
                "testoi esperando",
                "imprechonante",
                JOptionPane.PLAIN_MESSAGE);
        Calendar cal2 = Calendar.getInstance();

        System.out.println(""+(int)(cal2.getTimeInMillis()-cal1.getTimeInMillis()));
    }
}
