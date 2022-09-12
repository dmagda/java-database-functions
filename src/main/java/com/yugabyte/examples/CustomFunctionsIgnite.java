package com.yugabyte.examples;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteRunnable;

public class CustomFunctionsIgnite {
    public static void main(String[] args) {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);

        Ignite ignite = Ignition.start(cfg);

        IgniteCompute compute = ignite.compute(ignite.cluster().forServers());

        compute.run(new IgniteRunnable() {
            @Override
            public void run() {
                igniteSayHi();
            }
        });
    }

    public static void igniteSayHi() {
        System.out.println("Hey, this Java task is executed by Ignite");

        System.out.println("This is my environment:");

        System.out.println("    OS -> " + System.getProperty("os.name"));
        System.out.println("    OS version -> " + System.getProperty("os.version"));
        System.out.println("    OS architecture -> " + System.getProperty("os.arch"));

        System.out.println("    Java version -> " + System.getProperty("java.version"));

        System.out.println("    Total memory -> " + Runtime.getRuntime().totalMemory());
        System.out.println("    Free memory -> " + Runtime.getRuntime().freeMemory());
        System.out.println("    Max memory -> " + Runtime.getRuntime().maxMemory());
    }
}
