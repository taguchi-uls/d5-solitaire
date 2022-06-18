package d5.solitaire.model;

import javax.annotation.Nonnull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
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
}
