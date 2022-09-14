package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.KillEvent;
import com.templars_server.mb2_log_reader.schema.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KillParserTest {

    private KillParser killParser;

    @BeforeEach
    void beforeEach() {
        killParser = new KillParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        int testKiller = 1;
        int testVictim = 666;
        Weapon testWeapon = new Weapon();
        testWeapon.setId(7777);
        testWeapon.setName("MOD_REAL_THERMAL");
        String testLine = String.format("Kill: %d %d %d: Padawan killed Padawan[1] by %s", testKiller, testVictim, testWeapon.getId(), testWeapon.getName());

        KillEvent actualEvent = killParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getKiller()).isEqualTo(testKiller);
        assertThat(actualEvent.getVictim()).isEqualTo(testVictim);
        assertThat(actualEvent.getWeapon()).usingRecursiveComparison().isEqualTo(testWeapon);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        KillEvent actualEvent = killParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Kill: 1 1 1: Padawan killed Padawan[1] by a\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        KillEvent actualEvent = killParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
