package tk.yannickfelix.dronespace.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanni on 23.06.2016.
 */
public class GameConsole extends JEditorPane {

    private String text = "", displayedText = "", fontFamily = "Banana Square";
    private int viewX = 55;
    private final int writeSpeed = 1, fontSize = 24;
    private boolean isWriting = false, markup = false;
    private final float fontFactor = 14.5f;

    GameConsole() {
        super("text/html", "<html><body style='background-color: black;'></body><html>");
        super.setEditable(false);
        super.setFocusable(false);
        super.setBorder(new LineBorder(Color.black, 0));
    }

    public void printMessage(String text, String side, String name, boolean autowrap, boolean writing, boolean newline) {
        ArrayList<String> lines;

        if(!text.equals("") && !text.equals(" ")){
            //removeLastLine();
            if(autowrap) lines = stringBreak(text, viewX);
            else lines = new ArrayList<>(Arrays.asList(text.split("\n")));
            int nameLength = (name+"> ").length();

            switch (side) {
                case "left":
                    this.text += name + "> " + lines.get(0) + "\n";
                    lines.remove(0);

                    if (autowrap) lines = stringBreak(String.join(" ", lines), viewX);

                    for (String line : lines) {
                        this.text += line + "\n";
                    }
                    break;
                case "right":
                    int spaces = viewX - nameLength - lines.get(0).length();
                    this.text += mulString(" ", spaces) + lines.get(0) + " <" + name + "\n";
                    lines.remove(0);

                    if (autowrap) lines = stringBreak(String.join(" ", lines), viewX);

                    for (String line : lines) {
                        int lineLength = line.length();
                        this.text += mulString(" ", viewX - lineLength) + line + "\n";
                    }
                    break;
                case "center":
                    for (String line : lines) {
                        int lineLength = line.length();
                        this.text += mulString(" ", (viewX - lineLength) / 2) + line + "\n";
                    }
                    break;
            }

            if(newline) {
                this.text += "\n";
            }

            if(writing) {
                isWriting = true;
                System.out.println(this.text);
            } else {
                displayedText += this.text;
                isWriting = false;
                this.text = "";
            }
        }
    }

    public void updateInputting(String currInput, int sizeX) {
        this.viewX = (int) (sizeX / fontFactor + .5);
        if(!isWriting) {
            removeLastLine();
            String caret = " ";
            if(System.currentTimeMillis() % 500 <= 250) caret = "_";

            int lineLength = currInput.length()+3;
            displayedText += "\n"+mulString(" ", viewX-lineLength) + currInput + caret + " &lt;";
        }
    }

    void writeTick(int sizeX) {
        this.viewX = (int) (sizeX / fontFactor + .5);
        for(int i = 0; i < writeSpeed; i++) {
            if(!text.equals("") && isWriting) {
                String charToPrint = text.substring(0,1);
                displayedText += charToPrint;
                text = text.substring(1);
            } else if(isWriting && text.equals("")) {
                isWriting = false;
            }
        }
        displayedText = displayedText.replace("<","&lt;").replace(">", "&gt;");
        super.setText("<html><body style='background-color: black;'><pre style='font-size: "+fontSize+";font-family: \"Courier New\"; color: #18F500;'>"
                + displayedText + "</pre></body></html>");
    }

    private void removeLastLine() {
        if(!displayedText.endsWith("\n")) {
            String[] splitted = displayedText.split("\n");
            displayedText = String.join("\n", (CharSequence[]) Arrays.copyOf(splitted, splitted.length - 1));
        }
    }

    private String mulString(String string, int length) {
        return new String(new char[length]).replace("\0", string);
    }

    private static ArrayList<String> stringBreak(String string, int maxChar) {
        List<String> subLines = new ArrayList<String>();

        int length = string.length();
        int start = 0;
        int end = maxChar;
        if (length > maxChar) {
            int noOfLines = (length / maxChar) + 1;
            int endOfStr[] = new int[noOfLines];
            for (int f = 0; f < noOfLines - 1; f++) {
                int end1 = maxChar;
                endOfStr[f] = end;
                if (string.charAt(end - 1) != ' ') {
                    if (string.charAt(end - 2) == ' ') {
                        subLines.add(string.substring(start, end - 1));
                        start = end - 1;
                        end = end - 1 + end1;
                    } else if (string.charAt(end - 2) != ' ' && string.charAt(end) == ' ') {

                        subLines.add(string.substring(start, end));
                        start = end;
                        end = end + end1;
                    } else if (string.charAt(end - 2) != ' ') {

                        subLines.add(string.substring(start, end) + "-");
                        start = end;
                        end = end + end1;
                    } else if (string.charAt(end + 2) == ' ') {
                        System.out.println("m here ............");
                        int lastSpaceIndex = string.substring(start, end)
                                .lastIndexOf("");
                        subLines.add(string.substring(start, lastSpaceIndex));

                        start = lastSpaceIndex;
                        end = lastSpaceIndex + end1;
                    }
                } else {
                    subLines.add(string.substring(start, end));
                    start = end;
                    end = end + end1;
                }
            }
            subLines.add(string.substring(endOfStr[noOfLines - 2], length));
        }
        return (ArrayList<String>) subLines;
    }
}
