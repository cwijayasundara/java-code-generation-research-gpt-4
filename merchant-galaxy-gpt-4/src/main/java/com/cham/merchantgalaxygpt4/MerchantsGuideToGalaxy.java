package com.cham.merchantgalaxygpt4;

import com.cham.merchantgalaxygpt4.service.GalaxyMerchant;

public class MerchantsGuideToGalaxy {
    public static void main(String[] args) {
        GalaxyMerchant merchant = new GalaxyMerchant();
        merchant.processInput("glob is I");
        merchant.processInput("prok is V");
        merchant.processInput("pish is X");
        merchant.processInput("tegj is L");
        merchant.processInput("glob glob Silver is 34 Credits");
        merchant.processInput("glob prok Gold is 57800 Credits");
        merchant.processInput("pish pish Iron is 3910 Credits");
        System.out.println(merchant.processInput("how much is pish tegj glob glob ?"));
        System.out.println(merchant.processInput("how many Credits is glob prok Silver ?"));
        System.out.println(merchant.processInput("how many Credits is glob prok Gold ?"));
        System.out.println(merchant.processInput("how many Credits is glob prok Iron ?"));
        System.out.println(merchant.processInput("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
    }
}

