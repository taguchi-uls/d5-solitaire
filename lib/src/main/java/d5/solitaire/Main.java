package d5.solitaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import io.vavr.Tuple2;

public class Main {

	public static void main(String[] args) {
		// 初期化
		// １組52枚のトランプを作成(0~51の通し番号)
		var set = new ArrayList<Integer>(); // トランプ一組、手札
		for (int i = 0; i < 13 * 4; i++) {
			set.add(i);
		}
		Collections.shuffle(set); // トランプをシャッフル

		// レーン作成 + 各レーンにトランプを配置(各レーン最後は表向き)。残ったトランプは山札。
		var lines = new ArrayList<List<Tuple2<Integer, Boolean>>>(); // レーン7組
		for (int i = 0; i < 7; i++) {
			var line = new ArrayList<Tuple2<Integer, Boolean>>();
			lines.add(line);
			for (int j = 0; j < i + 1; j++) {
				line.add(new Tuple2<>(set.remove(0), i == j)); // トランプと、裏表のboolean（表ならtrue）
			}
		}

		// 4種ひとまとめのゴールの作成
		var goals = new ArrayList<List<Integer>>(); // ゴール4組
		// 1つ1つのゴールを作成
		for (int i = 0; i < 4; i++) {
			var goal = new ArrayList<Integer>();
			goals.add(goal);
		}

		int index = 0; // 手札のindex(山札の0枚目)
		try (var scanner = new Scanner(System.in)) {
			while (true) {
				// 表示
				// ７つのレーンの表示
				for (int i = 0; i < 7; i++) {
					var line = lines.get(i);
					System.out.print("レーン" + i + ":");
					for (int j = 0; j < line.size(); j++) {
						var card = line.get(j);
						if (card._2) {
							System.out.print(suit(card._1) + number(card._1));
						} else {
							System.out.print("★");
						}
					}
					System.out.println();
				}
				// ゴールの表示
				for (int i = 0; i < 4; i++) {
					var goal = goals.get(i);
					System.out.println("ゴール" + suit2(i) + ":" + (goal.size() == 0 ? "-" : goal.size())); // ゴールは1から順に積み立てなのでsizeを表示。
				}
				// 山札のトップ表示
				var card = set.isEmpty() ? null : set.get(index);
				System.out.println("手札トップ:" + (card == null ? "無し" : (suit(card) + number(card))));

				// 入力、処理
				System.out.print("どうしますか？(操作対象のレーン。手札の場合は7）:");
				var command = scanner.next();
				var commandNum = Integer.parseInt(command);
				// レーンのトランプを操作する場合
				if (commandNum <= 6) {
					System.out.print("レーン" + commandNum + "をどうしますか？(操作対象のレーン。ゴールの場合は7）:");
					var command2 = scanner.next();
					var command2Num = Integer.parseInt(command2);
					// 別のレーンに移動させる場合
					if (command2Num <= 6) {
						System.out.println("★:レーン" + commandNum + "からレーン" + command2Num + "への移動");
						var line = lines.get(commandNum);// 移動元レーン
						var move = line.stream().filter(t -> t._2).findFirst().get();// 移動するトランプ群の先頭トランプ
						var line2 = lines.get(command2Num);// 移動先レーン
						boolean success = false;
						if (line2.isEmpty()) { // 空列に移動できるのは K
							if (move._1 % 13 == 12) { // Kかどうかの判定
								// 移動元レーン情報の更新
								for (int i = 0; i < line.size(); i++) {
									// 移動するトランプ以降のトランプ群を移動先のレーンに追加 + 移動もとレーンから削除
									if (line.get(i) == move) {
										line2.addAll(line.subList(i, line.size()));
										line.removeAll(line.subList(i, line.size()));
										// レーンが空でなかったら、ラインの最後のトランプを表にする
										if (!line.isEmpty()) {
											line.set(line.size() - 1, new Tuple2<>(line.get(line.size() - 1)._1, true));
										}
										// 移動成功
										success = true;
										break;
									}
								}
							}
						} else { // 空列じゃない場合、移動できるのは色違い連番
							// TODO lineの最前(move)からの移動だけでなく、途中からの移動にも対応する
							var line2Last = line2.get(line2.size() - 1);// 移動先レーンの最後のトランプ
							// 移動先レーンの最後のトランプと移動するトランプが連番で色が違う場合
							// 色が違う➡suit()より、トランプの数字/26+1の結果が1なら黒、2なら赤。色違い=足した結果が3(=2進数 11)
							if ((line2Last._1.intValue() % 13) == (move._1.intValue() % 13) + 1
									&& (((move._1.intValue() / 26 + 1) + (line2Last._1.intValue() / 26 + 1)) == 0b11)) {
								// 移動元レーン情報の更新
								for (int i = 0; i < line.size(); i++) {
									// 移動するトランプ以降のトランプ群を移動先のレーンに追加 + 移動もとレーンから削除
									if (line.get(i) == move) {
										line2.addAll(line.subList(i, line.size()));
										line.removeAll(line.subList(i, line.size()));
										// レーンが空でなかったら、ラインの最後のトランプを表にする
										if (!line.isEmpty()) {
											line.set(line.size() - 1, new Tuple2<>(line.get(line.size() - 1)._1, true));
										}
										success = true;
										break;
									}
								}
							}
						}
						if (!success) {
							System.out.println("★★エラー: 移動できません");
						}
					}
					// ゴールに移動させる場合
					else {
						System.out.println("★:レーン" + commandNum + "をゴールへ");
						var line = lines.get(commandNum); // 移動元のレーン
						var lineLast = line.get(line.size() - 1); // 移動もとレーンの最後のトランプ
						var goal = goals.get(lineLast._1 / 13); // 移動する対象(移動するトランプと同じマーク)のゴール
						// ゴールの数字が移動したいトランプの数字より１小さいとき移動可能
						if (goal.size() == lineLast._1 % 13) {// lineLast._1 % 13 = 1~13までのトランプの数字-1
							goal.add(lineLast._1); // ゴールにトランプ追加
							line.remove(line.size() - 1); // 移動元レーンからトランプ削除
							// レーンが空でなかったら、ラインの最後のトランプを表にする
							if (!line.isEmpty()) {
								line.set(line.size() - 1, new Tuple2<>(line.get(line.size() - 1)._1, true));
							}
						} else {
							System.out.println("★★エラー: 移動できません");
						}
					}
				}
				// 山札のトランプを操作する場合
				else {
					// 山札のトップが存在する
					if (card != null) {
						System.out.print("手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:");
						var command2 = scanner.next();
						var command2Num = Integer.parseInt(command2);

						// 山札のトップをレーンに移動させる
						if (command2Num <= 6) {
							var line2 = lines.get(command2Num);// 移動先レーン
							boolean success = false;
							if (line2.isEmpty()) { // 空列に移動できるのは K
								if (card % 13 == 12) { // K
									line2.add(new Tuple2<>(card, true));// 移動先レーンにカードを移動
									set.remove(index);// 山札から移動させたカードを削除
									// 山札のインデックスが山札の枚数を超えたら、インデックスを減らす
									if (index >= set.size()) {
										index--;
									}
									success = true;
								}
							} else { // 空列以外に移動できるのは色違い連番
								var line2Last = line2.get(line2.size() - 1);// 移動先のレーンの最後のトランプ
								// 移動先レーンの最後のトランプと移動するトランプが連番で色が違う場合
								// 色が違う➡suit()より、トランプの数字/26+1の結果が1なら黒、2なら赤。色違い=足した結果が3(=2進数 11)
								if ((line2Last._1.intValue() % 13) == (card.intValue() % 13) + 1
										&& (((card.intValue() / 26 + 1)
												+ (line2Last._1.intValue() / 26 + 1)) == 0b11)) {
									line2.add(new Tuple2<>(card, true));// 移動先のレーンにカード追加
									set.remove(index);// 山札から移動させたカードを削除
									// 山札のインデックスが山札の枚数を超えたら、インデックスを減らす
									if (index >= set.size()) {
										index--;
									}
									success = true;
								}
							}
							if (!success) {
								System.out.println("★★エラー: 移動できません");
							}
						}
						// 山札のトップをゴールに移動させる
						else if (command2Num == 7) {
							System.out.println("★:手札をゴールへ");
							var goal = goals.get(card / 13);// 移動する対象(移動するトランプと同じマーク)のゴール
							// ゴールの数字が移動したいトランプの数字より１小さいとき移動可能
							if (goal.size() == card % 13) {
								goal.add(card);// ゴールにトランプ追加
								set.remove(index);// 山札から移動させたカードを削除
								// 山札のインデックスが山札の枚数を超えたら、インデックスを減らす
								if (index >= set.size()) {
									index--;
								}
							} else {
								System.out.println("★★エラー: 移動できません");
							}
						}
						// 山札を１枚めくる
						else {
							System.out.println("★:手札を繰る");
							index++;
							// 山札のインデックスが山札の枚数を超えたらまた山札の0枚目から開始
							if (index >= set.size()) {
								index = 0;
							}
						}
					} else {
						System.out.println("★★エラー: 手札はありません");
					}
				}
			}
		}
	}

	// スートの文字列に変換
	// 場のトランプのマークを返す
	private static final String suit(int i) {
		if (i < 13)
			return "♠";
		if (i < 13 * 2)
			return "♣";
		if (i < 13 * 3)
			return "♡";
		return "♢";
	}

	// スートの文字列に変換（0～3）
	// ゴールに表示するマークを返す。
	private static final String suit2(int i) {
		if (i == 0)
			return "♠";
		if (i == 1)
			return "♣";
		if (i == 2)
			return "♡";
		return "♢";
	}

	// 表示するトランプの番号の文字を返す。
	private static final String number(int i) {
		int ret = (i % 13) + 1; // トランプを13までの数字にならす。
		if (ret == 11)
			return "J";
		if (ret == 12)
			return "Q";
		if (ret == 13)
			return "K";
		if (ret == 1)
			return "A";
		return "" + ret;
	}
}
