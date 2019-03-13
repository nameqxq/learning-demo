package qxq.registration.center.utils;

import qxq.registration.center.common.ServiceInfo;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/13 16:23
 **/
public class UnmodifiableListCollector implements Collector<ServiceInfo, List<ServiceInfo>, List<ServiceInfo>> {
    private Supplier<List<ServiceInfo>> supplier;

    public UnmodifiableListCollector(Supplier<List<ServiceInfo>> supplier) {
        this.supplier = supplier;
    }

    @Override
    public Supplier<List<ServiceInfo>> supplier() {
        return supplier;
    }

    @Override
    public BiConsumer<List<ServiceInfo>, ServiceInfo> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<ServiceInfo>> combiner() {
        return (left, right) -> {left.addAll(right); return left;};
    }

    @Override
    public Function<List<ServiceInfo>, List<ServiceInfo>> finisher() {
        return Collections::unmodifiableList;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT, Characteristics.UNORDERED));
    }
}
