package cz.sparko.hackerrank.Contacts;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Bench {

    @Benchmark
    public static void bench() {
        Contacts contacts = new Contacts();
        for (int i = 0; i < 1_000; i++) {
            contacts.op("add", "hacker" + i);
        }
        for (int i = 0; i < 1_000; i++) {
            contacts.op("find", "hacker" + (i % 10));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Bench.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        new Runner(opt).run();
    }
}
