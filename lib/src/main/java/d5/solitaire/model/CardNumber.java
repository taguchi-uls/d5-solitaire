package d5.solitaire.model;

import java.util.Objects;

import javax.annotation.Nonnull;

import lombok.Getter;

public class CardNumber {
	private final Integer MIN_VALUE = 1;
	private final Integer MAX_VALUE = 13;

	@Getter
	private Integer value;

	private CardNumber(@Nonnull Integer value) {
		if (value < MIN_VALUE || MAX_VALUE < value) {
			throw new IllegalArgumentException("無効な数字です。");
		}
		this.value = value;
	}

	@Nonnull
	public static CardNumber of(@Nonnull Integer value) {
		return new CardNumber(value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardNumber other = (CardNumber) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "CardNumber [value=" + value + "]";
	}
}
