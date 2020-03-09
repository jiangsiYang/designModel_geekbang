package com.design.u054.chess.B;

public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public static enum Color {RED, BLACK}
    // ...省略其他属性和getter方法...
}
