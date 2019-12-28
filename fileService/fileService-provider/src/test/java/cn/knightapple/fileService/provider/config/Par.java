package cn.knightapple.fileService.provider.config;

import java.io.IOException;

import static org.junit.Assert.*;

public class Par {
    float aFun(float a)throws IOException {
        throw  new IOException();
    }
}
class Child extends Par{
     float aFun(float a) throws IOException {
        return super.aFun(a);
    }
}