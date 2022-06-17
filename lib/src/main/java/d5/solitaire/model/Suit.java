package d5.solitaire.model;

import javax.annotation.Nonnull;

public enum Suit {

	SPADE("♠", "B"), CLUB("♣", "B"), HEART("♡", "R"), DIAMOND("♢", "R");

	private String label;
	private String color;

	private Suit(@Nonnull String label, @Nonnull String color) {
		this.label = label;
		this.color = color;
	}

	public String label() {
		return label;
	}

	public String color() {
		return color;
	}
}
