package com.example.demo.common.argument;

import java.io.InputStream;

public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}
