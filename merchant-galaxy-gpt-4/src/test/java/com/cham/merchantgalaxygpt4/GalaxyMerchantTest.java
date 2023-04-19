package com.cham.merchantgalaxygpt4;

import com.cham.merchantgalaxygpt4.service.GalaxyMerchant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GalaxyMerchantTest {
    private GalaxyMerchant merchant;

    @BeforeEach
    public void setUp() {
        merchant = new GalaxyMerchant();
    }

    @Test
    public void testGalaxyMerchant() {
        merchant.processInput("glob is I");
        merchant.processInput("prok is V");
        merchant.processInput("pish is X");
        merchant.processInput("tegj is L");
        merchant.processInput("glob glob Silver is 34 Credits");
        merchant.processInput("glob prok Gold is 57800 Credits");
        merchant.processInput("pish pish Iron is 3910 Credits");

        assertEquals("pish tegj glob glob is 42", merchant.processInput("how much is pish tegj glob glob ?"));
        assertEquals("glob prok Silver is 68 Credits", merchant.processInput("how many Credits is glob prok Silver ?"));
        assertEquals("glob prok Gold is 57800 Credits", merchant.processInput("how many Credits is glob prok Gold ?"));
        assertEquals("glob prok Iron is 782 Credits", merchant.processInput("how many Credits is glob prok Iron ?"));
        assertEquals("I have no idea what you are talking about", merchant.processInput("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
    }
}

