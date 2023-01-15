package be.ucll.ip.web3.util;

import java.util.Properties;

public class Secret extends Credentials {

    public static void setPass(Properties dbProperties) {
        //Schrijf hier je gegevens in effe
        dbProperties.setProperty("user", "local_r0843312");
        dbProperties.setProperty("password", "YkaOkf1P3fkt");
    }
}
