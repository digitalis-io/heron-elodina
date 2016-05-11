package com.twitter.heron.spouts.kafka;

import com.twitter.heron.api.spout.Scheme;

import java.util.List;

public interface KeyValueScheme extends Scheme {
    List<Object> deserializeKeyAndValue(byte[] key, byte[] value);
}
