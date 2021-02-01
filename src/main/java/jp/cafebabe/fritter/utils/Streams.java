package jp.cafebabe.fritter.utils;

import jp.cafebabe.fritter.entities.Pair;

import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @see <a href="http://d.hatena.ne.jp/nowokay/20140321">http://d.hatena.ne.jp/nowokay/20140321</a>
 */
public class Streams {
    private Streams(){
    }

    public static <T, U> Stream<Pair<T,U>> zip(Stream<T> f, Stream<U> s){
        Iterator<Pair<T, U>> iterator = iterator(f, s);
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, NONNULL | ORDERED), false);
    }

    private static <T, U> Iterator<Pair<T, U>> iterator(Stream<T> f, Stream<U> s){
        Iterator<T> fitesIterator = f.iterator();
        Iterator<U> sitesIterator = s.iterator();
        return new PairIterator<>(fitesIterator, sitesIterator);
    }
}