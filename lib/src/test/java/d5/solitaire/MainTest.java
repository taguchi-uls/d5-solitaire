package d5.solitaire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void test初期表示() throws UnsupportedEncodingException {
        Random random = new Random(0L);
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream, true, "UTF-8");
        try {
            Main.game(random, in, out);
            org.junit.jupiter.api.Assertions.fail("scanner.next() で例外が発生する");
        } catch (NoSuchElementException e) {
            String actual = byteArrayOutputStream.toString("UTF-8");
            assertEquals(""
                + "レーン0:♢9\r\n"
                + "レーン1:★♣K\r\n"
                + "レーン2:★★♢5\r\n"
                + "レーン3:★★★♠6\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★★★♡Q\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:", actual);
        }
    }
    
    @Test
    public void testレーンからレーンの移動() throws UnsupportedEncodingException {
        Random random = new Random(0L);
        ByteArrayInputStream in = new ByteArrayInputStream("5\n1\n".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream, true, "UTF-8");
        try {
            Main.game(random, in, out);
            org.junit.jupiter.api.Assertions.fail("scanner.next() で例外が発生する");
        } catch (NoSuchElementException e) {
            String actual = byteArrayOutputStream.toString("UTF-8");
            assertEquals(""
                + "レーン0:♢9\r\n"
                + "レーン1:★♣K\r\n"
                + "レーン2:★★♢5\r\n"
                + "レーン3:★★★♠6\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★★★♡Q\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:レーン5をどうしますか？(操作対象のレーン。ゴールの場合は7）:★:レーン5からレーン1への移動\r\n"
                + "レーン0:♢9\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:★★♢5\r\n"
                + "レーン3:★★★♠6\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★★♣8\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:", actual);
        }
    }
    
    @Test
    public void test手札からゴールへの移動() throws UnsupportedEncodingException {
        Random random = new Random(0L);
        ByteArrayInputStream in = new ByteArrayInputStream("5\n1\n5\n0\n2\n3\n2\n5\n6\n5\n7\n8\n7\n8\n7\n8\n7\n8\n7\n8\n7\n3\n7\n7\n".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream, true, "UTF-8");
        try {
            Main.game(random, in, out);
            org.junit.jupiter.api.Assertions.fail("scanner.next() で例外が発生する");
        } catch (NoSuchElementException e) {
            String actual = byteArrayOutputStream.toString("UTF-8");
            assertEquals(""
                + "レーン0:♢9\r\n"
                + "レーン1:★♣K\r\n"
                + "レーン2:★★♢5\r\n"
                + "レーン3:★★★♠6\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★★★♡Q\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:レーン5をどうしますか？(操作対象のレーン。ゴールの場合は7）:★:レーン5からレーン1への移動\r\n"
                + "レーン0:♢9\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:★★♢5\r\n"
                + "レーン3:★★★♠6\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★★♣8\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:レーン5をどうしますか？(操作対象のレーン。ゴールの場合は7）:★:レーン5からレーン0への移動\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:★★♢5\r\n"
                + "レーン3:★★★♠6\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:レーン2をどうしますか？(操作対象のレーン。ゴールの場合は7）:★:レーン2からレーン3への移動\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:★♣3\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:レーン2をどうしますか？(操作対象のレーン。ゴールの場合は7）:★:レーン2からレーン5への移動\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3\r\n"
                + "レーン6:★★★★★★♢2\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:レーン6をどうしますか？(操作対象のレーン。ゴールの場合は7）:★:レーン6からレーン5への移動\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:★:手札を繰る\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♣2\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:★:手札を繰る\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♠7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:★:手札を繰る\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♠9\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:★:手札を繰る\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♡7\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:★:手札を繰る\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♠4\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5♠4\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:-\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♠A\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:手札をどうしますか？(操作対象のレーン。ゴールの場合は7, 繰る場合は8）:★:手札をゴールへ\r\n"
                + "レーン0:♢9♣8\r\n"
                + "レーン1:★♣K♡Q\r\n"
                + "レーン2:♡J\r\n"
                + "レーン3:★★★♠6♢5♠4\r\n"
                + "レーン4:★★★★♡6\r\n"
                + "レーン5:★★★♢4♣3♢2\r\n"
                + "レーン6:★★★★★♡K\r\n"
                + "ゴール♠:1\r\n"
                + "ゴール♣:-\r\n"
                + "ゴール♡:-\r\n"
                + "ゴール♢:-\r\n"
                + "手札トップ:♠5\r\n"
                + "どうしますか？(操作対象のレーン。手札の場合は7）:", actual);
        }
    }
}
