package d5.solitaire;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class TestSample {
    
    private StandardInputStream in = new StandardInputStream();
    private StandardOutputStream out = new StandardOutputStream();

    @Test
    public void test() {
        in.inputln("2");
        in.inputln("3");
        in.inputln("aaa");
        var game = new Game();
        assertThrows(NumberFormatException.class, () -> game.play(in, out, new Random(0)));
        assertEquals("start game", out.readLine());
        assertEquals("レーン0:♢9", out.readLine());
        assertEquals("レーン1:☆♣K", out.readLine());
        assertEquals("レーン2:☆☆♢5", out.readLine());
        assertEquals("レーン3:☆☆☆♠6", out.readLine());
        assertEquals("レーン4:☆☆☆☆♡6", out.readLine());
        assertEquals("レーン5:☆☆☆☆☆♡Q", out.readLine());
        assertEquals("レーン6:☆☆☆☆☆☆♢2", out.readLine());
        assertEquals("ゴール♠:-", out.readLine());
        assertEquals("ゴール♣:-", out.readLine());
        assertEquals("ゴール♡:-", out.readLine());
        assertEquals("ゴール♢:-", out.readLine());
        assertEquals("手札トップ:♣7", out.readLine());
        assertEquals("どうしますか？(操作対象のレーン。手札の場合は7）:レーン2をどうしますか？(操作対象のレーン。ゴールの場合は7）:☆:レーン2からレーン3への移動", out.readLine());
        assertEquals("レーン0:♢9", out.readLine());
        assertEquals("レーン1:☆♣K", out.readLine());
        assertEquals("レーン2:☆♣3", out.readLine());
        assertEquals("レーン3:☆☆☆♠6♢5", out.readLine());
        assertEquals("レーン4:☆☆☆☆♡6", out.readLine());
        assertEquals("レーン5:☆☆☆☆☆♡Q", out.readLine());
        assertEquals("レーン6:☆☆☆☆☆☆♢2", out.readLine());
        assertEquals("ゴール♠:-", out.readLine());
        assertEquals("ゴール♣:-", out.readLine());
        assertEquals("ゴール♡:-", out.readLine());
        assertEquals("ゴール♢:-", out.readLine());
        assertEquals("手札トップ:♣7", out.readLine());
        assertEquals("どうしますか？(操作対象のレーン。手札の場合は7）:", out.readLine());
        System.out.println(out.readLine());
    }
}
