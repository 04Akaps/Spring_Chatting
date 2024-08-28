package com.example.demo.common.constrants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final long ONE_MINUTE_TO_MILLIS = 60 * 1000L;
    public static final long ONE_HOUR_TO_MILLIS = 60 * ONE_MINUTE_TO_MILLIS;
    public static final String AUTH_CACHE_KEY = "AUTH";
}



